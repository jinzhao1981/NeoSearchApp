package com.dev.neosearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.BlockingQueue;
import org.apache.log4j.Logger;

interface NeoRecordFetcher{
	public String fetchNeoRecord(String strUrl);
	public Runnable makeWorker(BlockingQueue<Command> q, String payload);
}

class NeoRecordTestFetcher implements NeoRecordFetcher{
	public String fetchNeoRecord(String strUrl) {
		 return testData.getInstance().getData();
	}
    public Runnable makeWorker(BlockingQueue<Command> q, String payload) {
		return new Runnable() {
			@Override public void run() {
				new NeoSearchWorker(new NeoRecordTestFetcher()).process(q,payload);
			}
		};
	}
}
/**
* <h1>NeoRecordNasaFetcher</h1>
* The NeoRecordNasaFetcher class is used to fetch the Neo 
* records from Nasa site.  
*
*/
class NeoRecordNasaFetcher implements NeoRecordFetcher{
	final static Logger logger = Logger.getLogger(NeoRecordNasaFetcher.class);
	public Runnable makeWorker(BlockingQueue<Command> q, String payload) {
		return new Runnable() {
			@Override public void run() {
				new NeoSearchWorker(new NeoRecordNasaFetcher()).process(q,payload);
			}
		};
	}
	public String fetchNeoRecord(String strUrl) {
		  try {
				URL url = new URL(strUrl);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        conn.setRequestMethod("GET");
		        conn.setRequestProperty("Accept", "application/json");
	          
		        if (conn.getResponseCode() != 200) {
		        	logger.debug("received response code "+conn.getResponseCode());
		        	return "Retry";
		        }
	
		        BufferedReader br = new BufferedReader(new InputStreamReader(
	                  (conn.getInputStream())));
	
		        String output;
		        StringBuilder sb=new StringBuilder("");
	          
		        while ((output = br.readLine()) != null) {
		        	sb.append(output);
		        }
		        conn.disconnect();
	                      
		        return sb.toString();
		  } catch (MalformedURLException e) {
			  	logger.debug(e.toString());
		  } catch (IOException e) {
			    logger.debug(e.toString());
		  }
		  return "";
	}
}