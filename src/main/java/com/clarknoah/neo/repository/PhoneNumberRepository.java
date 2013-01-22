package com.clarknoah.neo.repository;


import com.clarknoah.neo.domain.PhoneNumber;
import com.clarknoah.neo.domain.People;
import com.clarknoah.neo.relationship.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.NamedIndexRepository;
import org.springframework.data.neo4j.repository.RelationshipOperationsRepository;
import org.springframework.transaction.annotation.Transactional;

public interface PhoneNumberRepository extends GraphRepository<PhoneNumber>,
RelationshipOperationsRepository<PhoneNumber>,
NamedIndexRepository<PhoneNumber>
{
	Page<PhoneNumber> findByDisplayName(String displayName, Pageable page);


}
