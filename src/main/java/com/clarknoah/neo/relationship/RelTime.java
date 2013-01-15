package com.clarknoah.neo.relationship;



import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.RelationshipType;
import org.springframework.data.neo4j.annotation.StartNode;
import org.springframework.transaction.annotation.Transactional;
import com.clarknoah.neo.service.TimeService;
import com.clarknoah.neo.domain.*;
import com.clarknoah.neo.repository.*;

import com.clarknoah.neo.service.TimeService;


@RelationshipEntity(type="lastModified")
public class RelTime {
	@GraphId public Long id;
	Date timeStamp;
	TimeService ts;

  	@StartNode private Master node;
   // @EndNode Time time = ts.getTimeNode();
    @EndNode Time time;

    
    
    public RelTime(){}
    
    public RelTime(Master node,Time time){
  	this.time = time;
  	this.node = node;
  	this.timeStamp = node.getLastModified();
   }

    public RelTime(Master node,Time time, String lastCreated){
      	this.time = time;
      	this.node = node;
      	this.timeStamp = node.getFirstCreated();
       }
    
    public Master getPerson() {
        return node;
    }
    
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
}

