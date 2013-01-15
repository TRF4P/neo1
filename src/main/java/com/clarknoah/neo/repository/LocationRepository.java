	package com.clarknoah.neo.repository;

import com.clarknoah.neo.domain.Location;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.RelationshipOperationsRepository;
import org.springframework.transaction.annotation.Transactional;
import com.clarknoah.neo.relationship.*;

public interface LocationRepository extends GraphRepository<Location>,
RelationshipOperationsRepository<Location> 
 {

}