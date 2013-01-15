package com.clarknoah.neo.service;

import java.util.Date;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.CRUDRepository;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clarknoah.neo.domain.*;
import com.clarknoah.neo.repository.EventRepository;
import com.clarknoah.neo.repository.OrganizationRepository;
import com.clarknoah.neo.repository.PeopleRepository;
import com.clarknoah.neo.repository.ProjectRepository;
import com.clarknoah.neo.repository.TimeRepository;


public class PeopleService {
	

	@Autowired
	private OrganizationRepository orgRepo;
	@Autowired
	private EventRepository eventRepo;
	@Autowired
	private  ProjectRepository projRepo;
	@Autowired
	private PeopleRepository peopleRepository;	
	@Autowired
	private TimeRepository timeRepository;

	public PeopleService(){
	}
	
	
	public long getNumberOfPeoples() {
		return peopleRepository.count();
	}
	

	
	public People createEntity(People object) {
		object.setLastModified(new Date());
		People person = peopleRepository.save(object);
		time(person);
		return person;
	}
	
	@Transactional
	public Event createEntity(Event object) {
		Event person = eventRepo.save(object);
		time(person);
		return person;
	}
	
	@Transactional
	public Organization createEntity(Organization object) {
		Organization person = orgRepo.save(object);
		time(person);
		return person;
	}
	
	@Transactional
	public Project createEntity(Project object) {
		Project person = projRepo.save(object);
		time(person);
		return person;
	}
	
	
	@Transactional
	
	public Iterable<People> getAllPeople() {
		return peopleRepository.findAll();
	}
	
	public People findPeopleById(Long id) {
		return peopleRepository.findOne(id);
	}
	
	public People findPeopleByName(String firstName) {
		return peopleRepository.findByPropertyValue("firstName", firstName);
	}
	
	public void time(Master person){

		Time time = timeRepository.findOne(Static.timeNode);
		time.lastMod(person, "lastMod");
		timeRepository.save(time);
	}
	public void timeCreated(Master person){
		Time time = timeRepository.findOne(Static.timeNode);
		time.setFirstCreated(person, "firstedCreated");
		timeRepository.save(time);
	}
	
	
	
}
