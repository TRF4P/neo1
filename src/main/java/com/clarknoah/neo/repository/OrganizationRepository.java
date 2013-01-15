	package com.clarknoah.neo.repository;

import com.clarknoah.neo.domain.Organization;
import com.clarknoah.neo.domain.People;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.NamedIndexRepository;
import org.springframework.data.neo4j.repository.RelationshipOperationsRepository;
import org.springframework.transaction.annotation.Transactional;

import com.clarknoah.neo.relationship.*;

public interface OrganizationRepository extends GraphRepository<Organization>,
RelationshipOperationsRepository<Organization>,
NamedIndexRepository<Organization>
{
	Page<Organization> findByDisplayName(String displayName, Pageable page);

}