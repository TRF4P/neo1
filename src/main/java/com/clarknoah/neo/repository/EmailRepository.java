package com.clarknoah.neo.repository;


import com.clarknoah.neo.domain.Email;
import com.clarknoah.neo.domain.People;
import com.clarknoah.neo.relationship.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.NamedIndexRepository;
import org.springframework.data.neo4j.repository.RelationshipOperationsRepository;
import org.springframework.transaction.annotation.Transactional;

public interface EmailRepository extends GraphRepository<Email>,
RelationshipOperationsRepository<Email>,
NamedIndexRepository<Email>
{
	Page<Email> findByDisplayName(String displayName, Pageable page);
	Email findByAddressOf(String addressOf);

}
