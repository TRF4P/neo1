	package com.clarknoah.neo.repository;

	import com.clarknoah.neo.relationship.*;
import com.clarknoah.neo.domain.People;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.NamedIndexRepository;
import org.springframework.data.neo4j.repository.RelationshipOperationsRepository;
import org.springframework.transaction.annotation.Transactional;

public interface PeopleRepository extends GraphRepository<People>,
RelationshipOperationsRepository<People>,
NamedIndexRepository<People>
 {
	Page<People> findByDisplayName(String displayName, Pageable page);

}