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

@NodeEntity public class PhoneNumber extends Master{

	@GraphId Long nodeId;
	
	@Indexed(indexType=IndexType.UNIQUE, indexName = "phoneNum")
	private String phoneNum;
	
	private String phoneType;
	
	@RelatedTo(type = "phone_number_of", direction = Direction.OUTGOING)
	People phoneNumberOf;

	private String nodeType = "Phone_Number";
	
	public People getPhoneNumberOf() {
		return phoneNumberOf;
	}

	public void setPhoneNumberOf(People phoneNumberOf) {
		this.phoneNumberOf = phoneNumberOf;
	}

	public String getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}

	
	
	
	@RelatedTo(direction=Direction.OUTGOING)
	Time lastModifiedRel;
	
	

	public PhoneNumber(){	
	}
	
	public PhoneNumber(String str) {
		this.phoneNum = str;
		this.displayName = str;
	}
	public PhoneNumber(String str, String type) {
		this.phoneNum = str;
		this.displayName = str;
	}


	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getNodeType() {
		return nodeType;
	}
	
	public Long getNodeId() {
		return nodeId;
}	
	
}