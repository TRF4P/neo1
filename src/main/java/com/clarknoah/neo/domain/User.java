package com.clarknoah.neo.domain;

import com.clarknoah.neo.domain.User.Roles;
import org.neo4j.graphdb.Direction;
import org.neo4j.helpers.collection.IteratorUtil;
import org.springframework.data.neo4j.annotation.*;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Set;

@NodeEntity
public class User {
    @GraphId Long nodeId;

    private static final String SALT = "cewuiqwzie";
    public static final String FRIEND = "FRIEND";
    public static final String RATED = "RATED";
    @Indexed
    String login;
    String name;
    String password;
    String info;
    private Roles[] roles;

    public User() {
    }

    public User(String login, String name, String password, Roles... roles) {
        this.login = login;
        this.name = name;
        this.password = encode(password);
        this.roles = roles;
    }

    private String encode(String password) {
        return new Md5PasswordEncoder().encodePassword(password, SALT);
    }

    
    @Override
    public String toString() {
        return String.format("%s (%s)", name, login);
    }

    public String getName() {
        return name;
    }



    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
    public void updatePassword(String old, String newPass1, String newPass2) {
        if (!password.equals(encode(old))) throw new IllegalArgumentException("Existing Password invalid");
        if (!newPass1.equals(newPass2)) throw new IllegalArgumentException("New Passwords don't match");
        this.password = encode(newPass1);
    }

    public void setName(String name) {
        this.name = name;
    }

    public Roles[] getRole() {
        return roles;
    }

    public enum Roles implements GrantedAuthority {
        ROLE_USER, ROLE_ADMIN;

        @Override
        public String getAuthority() {
            return name();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        if (nodeId == null) return super.equals(o);
        return nodeId.equals(user.nodeId);

    }

    public Long getId() {
        return nodeId;
    }

    @Override
    public int hashCode() {

        return nodeId != null ? nodeId.hashCode() : super.hashCode();
    }
}
