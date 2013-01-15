package com.clarknoah.neo.domain;


import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.*;
import org.springframework.data.neo4j.support.index.IndexType;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.ui.Model;

import com.clarknoah.neo.relationship.RelTime;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NodeEntity public class Event extends Master{
	private String eventName;
	private Date eventStart;
	private Date eventEnd;
	private String nodeType = "Event";
	@GraphId Long nodeId;
	
	@RelatedTo(direction=Direction.OUTGOING)
	Time lastModifiedRel;
	
	@RelatedTo(type="is_hosting",direction=Direction.INCOMING)
	Set<Organization> hosting = new HashSet<Organization>();
	
//	@RelatedTo(type="relates_to",direction=Direction.OUTGOING)
//	Set projEvent = new HashSet();
//	
//	public Set<Event> getProjEvent() {
//		return projEvent;
//	}
//	public void setProjEvent(Event event) {
//		projEvent.add(event);
//	}

public Set<Organization> getHosting() {
		return hosting;
	}

	public void setHosting(Organization org) {
		hosting.add(org);
	}

public Event(){
		
	}
	
	public Event(String eventName, Date eventStart, Date eventEnd) {
		super();
		this.eventName = eventName;
		this.eventStart = eventStart;
		this.eventEnd = eventEnd;
		this.displayName = eventName;
	}




	public String getNodeType() {
		return nodeType;
	}
	public String getEventName() {
		return eventName;
	}





	public void setEventName(String eventName) {
		this.eventName = eventName;
	}





	public Date getEventStart() {
		return eventStart;
	}





	public void setEventStart(Date eventStart) {
		this.eventStart = eventStart;
	}





	public Date getEventEnd() {
		return eventEnd;
	}





	public void setEventEnd(Date eventEnd) {
		this.eventEnd = eventEnd;
	}





	public Long getNodeId() {
		return nodeId;
}
 
	public Model getModel(Model model){		
    	model.addAttribute("displayName", getDisplayName());
   	    model.addAttribute("nodeId", getNodeId().toString());
   	    model.addAttribute("nodeType", getNodeType());
   	    model.addAttribute("lastModified", getLastModified().toString());
   	    model.addAttribute("firstCreated", getFirstCreated().toString()); 
   	    return model;
    }

	
	
}