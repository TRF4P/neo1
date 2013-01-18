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

@NodeEntity public class Email extends Master{
	private String displayName;
	
	@Indexed
	private String email;
	
	@Indexed
	private String emailType;
	
	@RelatedTo(type = "address_of", direction = Direction.OUTGOING)
	People addressOf;
	
	public People getAddressOf() {
		return addressOf;
	}

	public void setAddressOf(People addressOf) {
		this.addressOf = addressOf;
	}

	public String getEmailType() {
		return emailType;
	}

	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}

	private String nodeType;
	
	@GraphId Long nodeId;
	
	@RelatedTo(direction=Direction.OUTGOING)
	Time lastModifiedRel;
	
	

	public Email(){	
	}
	
	public Email(String str) {
		this.email = str;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNodeType() {
		return nodeType;
	}
	
	public Long getNodeId() {
		return nodeId;
}	
	
}