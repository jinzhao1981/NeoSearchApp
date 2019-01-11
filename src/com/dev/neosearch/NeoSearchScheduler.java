package com.dev.neosearch;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.log4j.Logger;

class Command{
	private String payload; 
	public Command(String p) {
		payload =p;
	}
	public String getPayload() {
		return payload;
	} 
}

class ScheduleResult{
	public void getResult(Command c) {
		if(c.getPayload()=="Done"){
			state = 0;
		}
		else if(c.getPayload()=="Retry"){
			state = 1;
		}
		else {
			state = 2;
		}
	}
	private int state = 0;
	public ScheduleResult(Command c) {getResult(c);}
	public boolean isDone() {return state == 0;}
	public boolean isRetry() {return state == 1;}
	public boolean isProceed() {return state == 2;}
}

/**
* <h1>NeoSearchScheduler</h1>
* The NeoSearchScheduler class is used to schedule the 
* workload. It retrieves URLs of Neo records from urlQueue, 
* and assigns them to a thread pool of workers to fetch and
* process Neo records. 
*
*/
public class NeoSearchScheduler implements Runnable{
	  private final NeoRecordFetcher processor;
	  private final String key;
	  final static Logger logger = Logger.getLogger(NeoSearchScheduler.class);
	  
	  private int retryTime = initialRetryTime;
	  private static final int maxRetryTime = 1800000;
	  private static final int initialRetryTime = 5000;
	  
	  boolean probe() {
		  try {
			String probeUrl = "https://api.nasa.gov/neo/rest/v1/neo/browse?api_key="+key;
			URL url = new URL(probeUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			  
			if (conn.getResponseCode() != 200) {
			    return false;
			}

		  } catch (IOException e) {
			  logger.debug(e.toString());
			  return false;
		  }
		  return true;
	  }
	  void retry() {
			while(true) {
				logger.info("Quota Exceeded. Retrying in "+retryTime/1000 +" s");
				retryTime*=2;
				if(retryTime> maxRetryTime) {
					retryTime = maxRetryTime;
				}
			
				try {
					Thread.sleep(retryTime);
				} catch (InterruptedException e) {
					logger.debug(e.toString());
				}
				if(probe()) {
					logger.debug("probe succeed");
					retryTime = initialRetryTime;
					break;
				}
			}
	  }
	  
	  public NeoSearchScheduler(NeoRecordFetcher p, String k) { 
		  processor = p;
		  key = k;
	  }
	 
	  @Override
	  public void run() {
		  
		    logger.debug("Start Scheduler " + Thread.currentThread().getName());
			BlockingQueue<Command> urlQueue = new ArrayBlockingQueue<Command> (100);
			String str = "https://api.nasa.gov/neo/rest/v1/neo/browse?api_key="+key;
			try {
			  urlQueue.put(new Command(str));
			} catch (InterruptedException e1) {
			   logger.debug(e1.toString());
			}
			ExecutorService executor = Executors.newFixedThreadPool(3);
			while(true) {
	           try {       		
    				Command cmd  = urlQueue.take();
    				
    				ScheduleResult res = new ScheduleResult(cmd);
    				if(res.isRetry()) {
    					retry();
    				}
    				if(res.isDone()) {
    					logger.debug("NeoSearch finished in Scheduler " + Thread.currentThread().getName());
        				break;
    				}
    				if(res.isProceed()) {
    				   	Runnable worker = processor.makeWorker(urlQueue, cmd.getPayload());
    					executor.execute(worker);
    				}
	        	}
	        	catch(InterruptedException e) {
	        		logger.debug(e.toString());
	        	}
	        }
	        executor.shutdown();
	        while (!executor.isTerminated()) {
	        }
	        logger.debug("Scheduler " + Thread.currentThread().getName()+" Exited");
	        NeoSearchResultSingleton.getInstance().printResult();
	 }
}
