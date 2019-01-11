package com.dev.neosearch;

import java.io.StringReader;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.stream.JsonParsingException;

import org.apache.log4j.Logger;

/**
* <h1>NeoRecordParsingHelper</h1>
* The NeoRecordParsingHelper class provides helper functions 
* 
*/
public class NeoRecordParsingHelper {
	  final static Logger logger = Logger.getLogger(NeoRecordParsingHelper.class);
	  static void handleParsingError(String s) {
		 	  logger.info("Parsing error, Stopped");
			  logger.debug(s);
	  }
	  public static String partialParseNextLink(String json) {
		  try {
		      int fromIndex = json.indexOf("\"links\" :");
		      if(fromIndex==-1) {
		    	  logger.debug("can not find links.");
		    	  throw new NullPointerException();
		      }
			  int beginIndex = json.indexOf('{', fromIndex);
			  int endIndex = json.indexOf('}', beginIndex);
			  String s = json.substring(beginIndex, endIndex+1);
			  
			  JsonReader jsonReader = Json.createReader(new StringReader(s));
			  JsonObject jobj = jsonReader.readObject(); 
			  if(jobj.containsKey("next")) {
				  return jobj.getString("next");
			  }
			  else {
				  return null;
			  }
		  }
		  catch(JsonParsingException e) {
			  handleParsingError(e.toString());
		      return null;
		  }
		  catch(NullPointerException e) {
			  handleParsingError(e.toString());
			  return null;
		  }
	  }
	  public static Optional<NeoResult> findCloestNeo(JsonArray ja ) {
			Optional<NeoResult> neoDistanceResult = ja.stream().map(p->{
				try {
				String ref_id = ((JsonObject)p).getString("neo_reference_id");
				String name = ((JsonObject)p).getString("name");
				JsonArray approachData = ((JsonObject)p).getJsonArray("close_approach_data");
				Optional<BigDecimal> distance = approachData.parallelStream().map(data->{
					JsonObject jo =((JsonObject)data).getJsonObject("miss_distance");
					if(jo!=null) {
						CharSequence c = jo.getJsonString("miles").getChars();
						return new BigDecimal(c.toString());
					}
					else
						return new BigDecimal(Integer.MAX_VALUE);
					}).collect(Collectors.minBy(Comparator.naturalOrder()));
				BigDecimal d = distance.isPresent()?distance.get():new BigDecimal(Integer.MAX_VALUE);
				return new NeoResult(NeoResult_Type.Distance, d, ref_id,name);
				}
				catch(NullPointerException e) {
					logger.debug(e.toString());
					return new NeoResult(NeoResult_Type.Distance, new BigDecimal(Integer.MAX_VALUE),"","");
				}
				catch(NumberFormatException e ) {
					logger.debug(e.toString());
					return new NeoResult(NeoResult_Type.Distance, new BigDecimal(Integer.MAX_VALUE),"","");
				}
			
			}).collect(Collectors.minBy(Comparator.comparing(NeoResult::getValue)));
			return neoDistanceResult;
	  }
	  public static Optional<NeoResult> findLargestNeo(JsonArray ja) {
		  	
			Optional<NeoResult> neoSizeResult = ja.stream().map(p->{
				try {
					String name = ((JsonObject)p).getString("name");
					BigDecimal min = ((JsonObject)p).getJsonObject("estimated_diameter").getJsonObject("miles").getJsonNumber("estimated_diameter_min").bigDecimalValue();
					BigDecimal max = ((JsonObject)p).getJsonObject("estimated_diameter").getJsonObject("miles").getJsonNumber("estimated_diameter_max").bigDecimalValue();
					String ref_id = ((JsonObject)p).getString("neo_reference_id");
					return new NeoResult(NeoResult_Type.Size, min.add(max), ref_id,name);
				}
				catch(NullPointerException e) {
					logger.debug(e.toString());
					return new NeoResult(NeoResult_Type.Size, BigDecimal.ZERO,"","");
				}
				
			}).collect(Collectors.maxBy(Comparator.comparing(NeoResult::getValue)));
			return neoSizeResult;
	  }
}

