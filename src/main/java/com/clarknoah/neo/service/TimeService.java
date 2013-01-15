package com.clarknoah.neo.service;

import java.util.ArrayList;
import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clarknoah.neo.domain.*;
import com.clarknoah.neo.repository.TimeRepository;



public class TimeService {
	
	@Autowired
	private TimeRepository timeRepository;

	 public Time getTimeNode(){
		 long id = 81;
		 return timeRepository.findOne(id);		
	 }
	
}
