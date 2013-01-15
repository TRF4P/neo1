package com.clarknoah.neo.domain;



import org.neo4j.graphdb.Direction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.annotation.*;
import org.springframework.data.neo4j.support.index.IndexType;
import org.springframework.data.neo4j.template.Neo4jOperations;

import com.clarknoah.neo.relationship.RelTime;
import com.clarknoah.neo.repository.TimeRepository;
import com.clarknoah.neo.service.TimeService;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

 
public abstract class Master {
	

	private Long nodeId;	
	private Date lastModified = new Date();
	private Date firstCreated = new Date();	
	
	@Indexed(indexType=IndexType.FULLTEXT, indexName = "search")
	protected String displayName;
	
	
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public void firstCreated(Date lastModified)
	{
		this.lastModified = lastModified;
	}
	
	public Date getLastModified() {
		return lastModified;
	}
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	public Date getFirstCreated() {
		return firstCreated;
	}
	public void setFirstCreated(Date firstCreated) {
		this.firstCreated = firstCreated;
	}

	public Long getNodeId() {
		return nodeId;
	}


	
	
}