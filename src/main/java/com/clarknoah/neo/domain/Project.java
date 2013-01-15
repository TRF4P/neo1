package com.clarknoah.neo.domain;


import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.*;
import org.springframework.data.neo4j.support.index.IndexType;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.ui.Model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NodeEntity public class Project extends Master{
	private String projectName;
	private Date projectStart; //Rel
	private Date projectEnd; //Rel
	private Date ioc;  //Rel	
	private String nodeType = "Project";
	@GraphId Long nodeId;
	
	@RelatedTo(direction=Direction.OUTGOING)
	Time lastModifiedRel;
	
	@RelatedTo(type="is_working_on",direction=Direction.INCOMING)
	Set<People> workingOn = new HashSet<People>();
	
	@Fetch
	@RelatedTo(type="relates_to",direction=Direction.INCOMING)
	Set<Event> projEvent = new HashSet<Event>();
	
	
	@RelatedTo(type="prime_contractor_for",direction=Direction.INCOMING)
	Organization primeCtrFor;
	
	@RelatedTo(type="owns",direction=Direction.INCOMING)
	Organization owns;
	
	//@RelatedTo(type="discussed_at",direction=Direction.OUTGOING)
	// discussed;
	
	
	public Long getNodeId() {
		return nodeId;
}
	public Project(){
		
	}
	public Project(String projectName, Date projectStart, Date projectEnd,
			Date ioc) {
		super();
		this.projectName = projectName;
		this.projectStart = projectStart;
		this.projectEnd = projectEnd;
		this.ioc = ioc;
		this.displayName = projectName;
	}
	public String getNodeType() {
		return nodeType;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Date getProjectStart() {
		return projectStart;
	}
	public void setProjectStart(Date projectStart) {
		this.projectStart = projectStart;
	}
	public Date getProjectEnd() {
		return projectEnd;
	}
	public void setProjectEnd(Date projectEnd) {
		this.projectEnd = projectEnd;
	}
	public Date getIoc() {
		return ioc;
	}
	public void setIoc(Date ioc) {
		this.ioc = ioc;
	}
	public Model getModel(Model model){		
    	model.addAttribute("displayName", getDisplayName());
   	    model.addAttribute("startDate", getProjectStart().toString());
   	    model.addAttribute("endDate", getProjectEnd().toString());
   	    model.addAttribute("iocDate", getIoc().toString());
   	    model.addAttribute("nodeId", getNodeId().toString());
   	    model.addAttribute("nodeType", getNodeType());
   	    model.addAttribute("lastModified", getLastModified().toString());
   	    model.addAttribute("firstCreated", getFirstCreated().toString()); 
   	    return model;
    }
	public Set<People> getWorkingOn() {
		return workingOn;
	}

	public Set<Event> getProjEvent() {
		return projEvent;
	}
	public void setProjEvent(Event event) {
		projEvent.add(event);
	}
	public void setWorkingOn(People person) {
		workingOn.add(person);
	}
	public Organization getPrimeCtrFor() {
		return primeCtrFor;
	}
	public void setPrimeCtrFor(Organization org) {
		primeCtrFor = org;
	}
	public Organization getOwns() {
		return owns;
	}
	public void setOwns(Organization owns) {
		this.owns = owns;
	}

}