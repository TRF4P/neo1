package com.clarknoah.neo.domain;


import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.*;
import org.springframework.data.neo4j.support.index.IndexType;
import org.springframework.data.neo4j.template.Neo4jOperations;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NodeEntity public class Location extends Master{
	private String city;
	private String state;
	private String zip;
	private String address;
	private String country;
	private Location location;
	private String nodeType = "Location";
	@GraphId Long nodeId;
	
	@RelatedTo(direction=Direction.OUTGOING)
	Time lastModifiedRel;
	public Long getNodeId() {
		return nodeId;
}
	public String getNodeType() {
		return nodeType;
	}
}