package com.clarknoah.neo.repository;

import com.clarknoah.neo.domain.User;
import com.clarknoah.neo.service.NeoUserDetailsService;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.RelationshipOperationsRepository;

/**
 * @author mh
 * @since 02.04.11
 */
public interface UserRepository extends GraphRepository<User>,
        RelationshipOperationsRepository<User>,
        NeoUserDetailsService {

    User findByLogin(String login);
}
