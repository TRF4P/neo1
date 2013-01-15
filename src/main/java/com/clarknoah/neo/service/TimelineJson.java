package com.clarknoah.neo.service;


import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.clarknoah.neo.repository.EventRepository;
import com.clarknoah.neo.repository.TimeRepository;
import com.clarknoah.neo.domain.Event;
import com.clarknoah.neo.domain.Organization;
import com.clarknoah.neo.domain.People;
import com.clarknoah.neo.domain.Project;
import com.clarknoah.neo.domain.Time;
import com.clarknoah.neo.relationship.RelTime;
import com.google.gson.*;
import com.clarknoah.neo.repository.PeopleRepository;
public class TimelineJson{
	private PeopleRepository peepRepo;
	private EventRepository eventRepo;
	//long timeKey = 81;
	//Time mTime;
	//Time time = TimeRepository.findOne(timeKey);
	
	
	public JsonObject mainTimeline =new JsonObject();
	JsonObject headline =new JsonObject();	
	JsonObject asset = new JsonObject();
	JsonObject date = new JsonObject();		
	JsonArray dateArray = new JsonArray();
	Iterable<RelTime> allTime;
	Iterable<Event> events;
	
	//For Headline Object
	String mhl = "Timeline";
	String mty = "default";
	String mtx = "Put text in";
	
	//For Asset Object
	String media = "Web link";
	String thumbnail = "thumbnail link";
	String credit ="source?";
	String caption ="Your caption";


	public TimelineJson(Iterable<RelTime> allTime, PeopleRepository pplRepo){	
	//	public TimelineJson(Iterable<RelTime> allTime){	
		this.allTime = allTime;
		this.peepRepo = pplRepo;
		headline.addProperty("headline",mhl);	
		headline.addProperty("type",mty);	
		headline.addProperty("text",mtx);		
		asset.addProperty("media",media);
		asset.addProperty("thumbnail",thumbnail);
		asset.addProperty("credit",credit);
		asset.addProperty("caption",caption);	
	}	
	
	public TimelineJson(Iterable<Event> allTime, EventRepository eventRepo){	
			this.events = allTime;
			this.eventRepo = eventRepo;
			headline.addProperty("headline",mhl);	
			headline.addProperty("type",mty);	
			headline.addProperty("text",mtx);		
			asset.addProperty("media",media);
			asset.addProperty("thumbnail",thumbnail);
			asset.addProperty("credit",credit);
			asset.addProperty("caption",caption);	
		}
		

	public void lastModifiedJson(){
		String title = "Last Modified";
		Iterator<RelTime> tmCount = allTime.iterator();			
		while(tmCount.hasNext()) {			
			RelTime element = tmCount.next();
			addEvent(element, title);			
	     }
		headline.add("date",dateArray);
		headline.add("asset", asset);		
		mainTimeline.add("timeline", headline);	
		
	}
	
	public void projectEvents(Iterable<Event> events){
		String title = "Project Events";
		Iterator<Event> tmCount = events.iterator();
		//Iterator<RelTime> tmCount = allTime.iterator();			
		while(tmCount.hasNext()) {			
			Event element = tmCount.next();
			addEvent(element, title);			
	     }
		headline.add("date",dateArray);
		headline.add("asset", asset);		
		mainTimeline.add("timeline", headline);	
		
	}
	public void firstCreatedJson(){
		String title = "First Created";
		Iterator<RelTime> tmCount = allTime.iterator();			
		while(tmCount.hasNext()) {			
			RelTime element = tmCount.next();
			addCreate(element, title);			
	     }
		headline.add("date",dateArray);
		headline.add("asset", asset);		
		mainTimeline.add("timeline", headline);	
		
	}
	public void addEvent(Event element, String title){	
		JsonObject specificEvent = new JsonObject();
        // long pplKey = element.getPerson().getNodeId();
        // People intppl = peepRepo.findOne(pplKey);
       //  People ppl = peepRepo.findOne(pplKey);
         specificEvent.addProperty(new String("headline"),"Event: "+element.getDisplayName()); 
         specificEvent.addProperty(new String("startDate"), element.getEventStart().toString());	 
         specificEvent.addProperty(new String("endDate"), element.getEventEnd().toString());	 
         specificEvent.addProperty(new String("text"), new String("<p>"+title+"</p><br><p>NodeId:  "+
        		 element.getNodeId().toString()+"</p>"));
 		dateArray.add(specificEvent);
		}
	public void addEvent(RelTime element, String title){	
		JsonObject specificEvent = new JsonObject();
         long pplKey = element.getPerson().getNodeId();
        // People intppl = peepRepo.findOne(pplKey);
         People ppl = peepRepo.findOne(pplKey);
         specificEvent.addProperty(new String("headline")," Name is: "+ppl.getDisplayName()); 
         specificEvent.addProperty(new String("startDate"), element.getTimeStamp().toString());	 
         specificEvent.addProperty(new String("endDate"), element.getTimeStamp().toString());	 
         specificEvent.addProperty(new String("text"), new String("<p>"+title+"</p><br><p>NodeId:  "+
        		 element.getPerson().getNodeId().toString()+"</p>"));
 		dateArray.add(specificEvent);
		}
	
	public void addCreate(RelTime element, String title){	
		JsonObject specificEvent = new JsonObject();
         long pplKey = element.getPerson().getNodeId();
         People ppl = peepRepo.findOne(pplKey);
         specificEvent.addProperty(new String("headline")," Name is: "+ppl.getDisplayName()); 
         specificEvent.addProperty(new String("startDate"), ppl.getFirstCreated().toString()); 
         specificEvent.addProperty(new String("endDate"), ppl.getFirstCreated().toString()); 
         specificEvent.addProperty(new String("text"), new String("<p>"+title+"</p><br><p>NodeId:  "+
        		 element.getPerson().getNodeId().toString()+"</p>"));
 		dateArray.add(specificEvent);
		}
	
	public String sunburstJson(Organization org)
	{
		
		//take organization object
		Organization rootOrg = org;

				
				//create base JSON sunburst object
	JsonArray rootArray = new JsonArray();
	rootArray.add(sunburstObject(org));
				//create array object of suborganizations
				
				//for each organization in array, create array object of their suborgs
				
				//query orgs
		return rootArray.toString();
	}
	public JsonObject sunburstObject(Organization org){
		JsonObject sunburstObject = new JsonObject();
		sunburstObject.addProperty("name", org.getDisplayName());
		sunburstObject.addProperty("colour", "#f0e2a3");
		
		if(org.getSubOrg()!=null){
		Set<Organization> subOrgs = org.getSubOrg();
		JsonArray subArray = new JsonArray();
		while(subOrgs.iterator().hasNext()){			
			subArray.add(sunburstObject(subOrgs.iterator().next()));
			
		}
		sunburstObject.add("Children", subArray);
		}
		return sunburstObject;
	}
}


