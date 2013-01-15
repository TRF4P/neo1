package com.clarknoah.neo.domain;


import static org.neo4j.graphdb.Direction.INCOMING;


import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.*;
import org.springframework.data.neo4j.support.index.IndexType;
import org.springframework.data.neo4j.template.Neo4jOperations;

import com.clarknoah.neo.relationship.RelTime;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@NodeEntity public class Time extends Master{
	private @GraphId Long nodeId;
	private static String displayName = "Master Time Node";
	private static String nodeType = "Time"; 
	Date lastModified;
	
	@Fetch
	@RelatedToVia(direction=Direction.INCOMING)
	Set<RelTime> lastModifiedRel = new HashSet<RelTime>();

	@Fetch
	@RelatedToVia(direction=Direction.INCOMING)
	Set<RelTime> firstCreatedRel = new HashSet<RelTime>();
	
	
	public RelTime lastMod(Master person, String title) {
		RelTime relTime =new RelTime(person, this);
		lastModifiedRel.add(relTime);
		return relTime;
		    }
	
	public RelTime setFirstCreated(Master person, String title) {
		RelTime relTime =new RelTime(person, this);
		firstCreatedRel.add(relTime);
		return relTime;
		    }

	public String getNodeType() {
		return nodeType;
	}
	 public Long getNodeId() {
			return nodeId;
	}
	 public String getDisplayName() {                                              
			return displayName;
		}
		public void setDisplayName(String displayName) {
			this.displayName = displayName;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((nodeId == null) ? 0 : nodeId.hashCode());
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
			Time other = (Time) obj;
			if (nodeId == null) {
				if (other.nodeId != null)
					return false;
			} else if (!nodeId.equals(other.nodeId))
				return false;
			return true;
		}
		public Iterable<RelTime> getRelLastModified() {
			
	        return lastModifiedRel;
	    }
		
}

