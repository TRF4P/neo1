	package com.clarknoah.neo.repository;

import com.clarknoah.neo.domain.Time;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.RelationshipOperationsRepository;
import org.springframework.transaction.annotation.Transactional;
import com.clarknoah.neo.relationship.*;

public interface TimeRepository extends GraphRepository<Time>,
RelationshipOperationsRepository<Time>
 {
	
}