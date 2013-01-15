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

@NodeEntity public class Organization extends Master{
	private String orgName;
	private String orgAcronym;
	private String nodeType = "Organization";
	
	@RelatedTo(direction=Direction.OUTGOING)
	Time lastModifiedRel;
	
	
	@RelatedTo(type="works_at",direction=Direction.INCOMING)
	Set<People> worksAt = new HashSet<People>();
	
	@Fetch
	@RelatedTo(type="sub_organization_of",direction=Direction.INCOMING)
	Set<Organization> subOrg = new HashSet<Organization>();
	
	@RelatedTo(type="is_hosting",direction=Direction.OUTGOING)
	Set<Organization> hosting = new HashSet<Organization>();
	
	public Set<Organization> getHosting() {
		return hosting;
	}
	public void setHosting(Organization orghosting) {
		hosting.add(orghosting);
	}
	@RelatedTo(type="prime_contractor_for",direction=Direction.OUTGOING)
	Set<Project> primeCtrFor = new HashSet<Project>();
	
	
	@RelatedTo(type="owns",direction=Direction.OUTGOING)
	Set<Project> owns = new HashSet<Project>();
	
	@GraphId Long nodeId;
	public Long getNodeId() {
		return nodeId;
}
	public Organization(){
		
	}
	public String getNodeType() {
		return nodeType;
	}
	
	
	public Organization(String orgName, String orgAcronym){
		this.orgName = orgName;
		this.orgAcronym = orgAcronym;
		this.displayName = orgName;
	}
	public String getOrgName() {
		return orgName;
	}
	
	public Set<Organization> getSubOrg() {
		return subOrg;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgAcronym() {
		return orgAcronym;
	}
	public void setOrgAcronym(String orgAcronym) {
		this.orgAcronym = orgAcronym;
	}
	public Model getModel(Model model){		
    	model.addAttribute("displayName", getDisplayName());
   	    model.addAttribute("orgAcronym", getOrgAcronym());
   	    model.addAttribute("orgName", getOrgName());
   	    model.addAttribute("nodeId", getNodeId().toString());
   	    model.addAttribute("nodeType", getNodeType());
   	    model.addAttribute("lastModified", getLastModified().toString());
   	    model.addAttribute("firstCreated", getFirstCreated().toString()); 
   	    return model;
    }
	public Set<People> getWorksAt() {
		return worksAt;
	}
	public void setWorksAt(People person) {
		worksAt.add(person);
	}
	public void setSubOrg(Organization org) {
		subOrg.add(org);
	}
	public Set<Project> getPrimeCtrFor() {
		return primeCtrFor;
	}
	public void setPrimeCtrFor(Project project) {
		primeCtrFor.add(project);
	}
	public Set<Project> getOwns() {
		return owns;
	}
	public void setOwns(Project project) {
		owns.add(project);
	}
	
	
}