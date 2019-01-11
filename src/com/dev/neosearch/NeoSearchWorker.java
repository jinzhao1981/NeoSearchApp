package com.dev.neosearch;

import java.io.StringReader;
import java.math.BigDecimal;
import java.util.Optional;
import javax.json.*;
import javax.json.stream.JsonParsingException;

import org.apache.log4j.Logger;
import java.util.concurrent.BlockingQueue;

/**
* <h1>NeoSearchWorker</h1>
* The NeoSearchWorker class is used to fetch and process 
* Neo records. It will parse the next URL and put it into 
* urlQueue. Also it will process the records and display the 
* results to the users.
*
*/
public class NeoSearchWorker {
	 private NeoRecordFetcher fetcher;
	 final static Logger logger = Logger.getLogger(NeoSearchWorker.class);

	 public NeoSearchWorker(NeoRecordFetcher f){
		 fetcher = f;
	 }
	 /**
	   * This method is used to fetch the Neo record, partially parse the
	   * next link and put into urlQueue. Also it will process the result 
	   * and display it to the users
	   * @param urlQueue Where the worker will put partially parsed next url 
	   * @return void
	   */
	  public void process(BlockingQueue<Command> urlQueue, String url ) {
	    try {
	       
	       String record = fetcher.fetchNeoRecord(url);
	       if(record.compareTo("Retry")==0) {
	       	   urlQueue.put(new Command("Retry"));
		       Thread.sleep(500);
               urlQueue.put(new Command(url));
	       }
	       else {
		       String nextUrl = NeoRecordParsingHelper.partialParseNextLink(record);
		       if(nextUrl!=null) {
		    	   urlQueue.put(new Command(nextUrl));
		       }
		       processRecord(record);
		       if(nextUrl ==null) {
		    	   Thread.sleep(500);
			       urlQueue.put(new Command("Done"));
			   }
	       }
	    }
	    catch (InterruptedException e) {
	    	logger.debug(e.toString());
	    }
	  }
	  void processRecord(String record) {
		  try {
			JsonReader jsonReader = Json.createReader(new StringReader(record));
			JsonObject jobj = jsonReader.readObject();        
			
			JsonArray ja = jobj.getJsonArray("near_earth_objects");
			Optional<NeoResult> neoDistanceResult= NeoRecordParsingHelper.findCloestNeo(ja);
			Optional<NeoResult> neoSizeResult = NeoRecordParsingHelper.findLargestNeo(ja);
			
			String totalCount = jobj.getJsonObject("page").getJsonNumber("total_elements").toString();
			
			NeoResult nr = new NeoResult(NeoResult_Type.Count, new BigDecimal(ja.size()), "",totalCount);
			NeoSearchResultSingleton.getInstance().processNeo(nr);
			
			if(neoDistanceResult.isPresent()) {
				nr = neoDistanceResult.get();
				NeoSearchResultSingleton.getInstance().processNeo(nr);
						
			}
			
			if(neoSizeResult.isPresent()) {
				nr = neoSizeResult.get();
		  		NeoSearchResultSingleton.getInstance().processNeo(nr);
			}
			
			if(!jobj.getJsonObject("links").containsKey("next")) {
				Thread.sleep(500);
				logger.info("Finished Neo Seraching");  
				
		  	}
		  }
		  catch(InterruptedException e) {
			  logger.debug(e.toString());
		  }
		  catch(NullPointerException e) {
			  logger.debug(e.toString());
		  }
		  catch(JsonParsingException e) {
			  logger.debug(e.toString());
		  }
	  }
}
