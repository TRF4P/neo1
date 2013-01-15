package com.clarknoah.neo.domain;

import static org.neo4j.graphdb.Direction.INCOMING;

import org.neo4j.graphdb.Direction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.neo4j.*;
import org.springframework.data.neo4j.annotation.*;
import org.springframework.data.neo4j.support.index.IndexType;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.clarknoah.neo.relationship.RelTime;
import com.clarknoah.neo.service.TimeService;

//import com.clarknoah.neo.controller.HomeController;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import com.clarknoah.neo.service.PeopleService;

@NodeEntity
public class People extends Master {
	private static final Logger logger = LoggerFactory.getLogger(People.class);

	@GraphId
	Long nodeId;
	@Indexed
	private String firstName;
	@Indexed
	private String lastName;
	@Indexed
	private int age;
	private String nodeType = "People";

	@RelatedTo(direction = Direction.OUTGOING)
	Time lastModifiedRel;

	// @RelatedTo(type="First_Created",direction=Direction.OUTGOING)
	// Time firstCreatedRel;

	@Fetch
	@RelatedTo(type = "is_friends_with", direction = Direction.BOTH)
	private Set<People> friendsWith = new HashSet<People>();

	@RelatedTo(type = "works_at", direction = Direction.OUTGOING)
	Organization worksAt;

	@RelatedTo(type = "works_under", direction = Direction.OUTGOING)
	People worksUnder;

	@RelatedTo(type = "is_working_on", direction = Direction.OUTGOING)
	Set<Project> workingOn = new HashSet<Project>();

	@RelatedTo(type = "attended", direction = Direction.OUTGOING)
	Set<Event> attended = new HashSet<Event>();

	// @RelatedToVia(direction=Direction.OUTGOING)
	// RelTime lastModifiedRel;

	// public RelTime setLastModifiedRel(Time time){
	// RelTime relTime = new RelTime(this, time, "lastModified");
	// lastModifiedRel.add(relTime);
	// return relTime;
	// }

	public People(int age, String firstName, String lastName) {
		logger.info("setting name shit brah");
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.displayName = firstName + " " + lastName;
	}

	public People() {
	}

	public Set<People> getFriendsWith() {
		return friendsWith;
	}

	public void setFriendsWith(People person) {
		friendsWith.add(person);
	}

	public Organization getWorksAt() {
		return worksAt;
	}

	public void setWorksAt(Organization worksAt) {
		this.worksAt = worksAt;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAge(Integer age) {
		this.age = age;

	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public Set<Project> getWorkingOn() {
		return workingOn;
	}

	public void setWorkingOn(Project project) {
		workingOn.add(project);
	}

	public void setWorksUnder(People worksUnder) {
		this.worksUnder = worksUnder;
	}

	public void setAttended(Event event) {
		attended.add(event);
	}

	public Integer getAge() {
		return age;
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public String getNodeType() {
		return nodeType;
	}

	public Model getModel(Model model) {
		model.addAttribute("displayName", getDisplayName());
		model.addAttribute("firstName", getFirstName());
		model.addAttribute("age", getAge().toString());
		model.addAttribute("lastName", getLastName());
		model.addAttribute("nodeId", getNodeId().toString());
		model.addAttribute("nodeType", getNodeType());
		model.addAttribute("lastModified", getLastModified().toString());
		model.addAttribute("firstCreated", getFirstCreated().toString());
		return model;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nodeId == null) ? 0 : nodeId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		People other = (People) obj;
		if (nodeId == null) {
			if (other.nodeId != null)
				return false;
		} else if (!nodeId.equals(other.nodeId))
			return false;
		return true;
	}

	public People getWorksUnder() {
		return worksUnder;
	}

	public Set<Event> getAttended() {
		return attended;
	}
}
