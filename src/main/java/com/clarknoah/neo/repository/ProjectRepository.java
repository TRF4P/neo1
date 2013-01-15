	package com.clarknoah.neo.repository;


import com.clarknoah.neo.domain.Project;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.NamedIndexRepository;
import org.springframework.data.neo4j.repository.RelationshipOperationsRepository;
import org.springframework.transaction.annotation.Transactional;
import com.clarknoah.neo.relationship.*;

public interface ProjectRepository extends GraphRepository<Project>,
RelationshipOperationsRepository<Project>,
NamedIndexRepository<Project>
{
	Page<Project> findByDisplayName(String displayName, Pageable page);

}