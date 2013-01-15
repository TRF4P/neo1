package com.clarknoah.neo.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.HashMap;

import com.google.gson.stream.JsonReader;
import com.google.gson.*;
import com.google.gson.JsonElement;
public class test {


	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
	//	TimelineJson myJson = new TimelineJson();
		//System.out.println(myJson);
	//	myJson.
	//	String flat = gson.toJson(myJson);
		//JsonReader.toJava("[]")
	//	System.out.println(flat);
	//	JsonReader reader = new JsonReader(
	//	        new InputStreamReader(new FileInputStream("src/main/webapp/resources/json/object.json")));
	//	JsonParser jsonParser = new JsonParser();
		
	//	JsonObject bob= jsonParser.parse(reader).getAsJsonObject();	    
	
		//calls the main objects
		JsonObject mainTimeline = new JsonObject();
		JsonObject headline = new JsonObject();	
		JsonObject asset = new JsonObject();
		JsonObject date = new JsonObject();		
		JsonArray dateArray = new JsonArray();
		JsonObject specificEvent = new JsonObject();
		JsonObject specificAsset = new JsonObject();
		
		headline.addProperty("headline", new String("Fuck ya!"));	
		headline.addProperty("type", new String("Whatev"));	
		headline.addProperty(new String("text"), new String("<p>fucking workin it</p>"));	
		
		asset.addProperty(new String("media"), new String("Fuck ya!"));
		asset.addProperty(new String("thumbnail"), new String("Fuck ya!"));
		asset.addProperty(new String("credit"), new String("Fuck ya!"));
		asset.addProperty(new String("caption"), new String("Fuck ya!"));
		
		specificAsset.addProperty(new String("media"), new String("Fuck ya!"));
		specificAsset.addProperty(new String("thumbnail"), new String("Fuck ya!"));
		specificAsset.addProperty(new String("credit"), new String("Fuck ya!"));
		specificAsset.addProperty(new String("caption"), new String("Fuck ya!"));
				
		specificEvent.addProperty(new String("startDate"), new String("2012,2,2"));
		specificEvent.addProperty(new String("endDate"), new String("2012,2,2"));
		specificEvent.addProperty(new String("headline"), new String("2012,2,2"));
		specificEvent.addProperty(new String("text"), new String("2012,2,2"));
		
		specificEvent.add("asset",specificAsset);
		dateArray.add(specificEvent);
		date.add("date",dateArray);
		headline.add("asset", asset);
		mainTimeline.add("timeline", headline);
		
		
		
		
		
		Gson hl = new Gson();
		String rat = gson.toJson(hl);
		System.out.println(mainTimeline.toString());
		//System.out.println(rat.toString());
		
		
		//TimelineJson noah = gson.fromJson(na TimelineJson.class);

		  // now you have a real java object
		//  System.out.println(myJson.getFoo());
	}

}
