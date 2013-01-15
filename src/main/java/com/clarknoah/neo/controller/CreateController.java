package com.clarknoah.neo.controller;


import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.clarknoah.neo.relationship.RelTime;
import com.clarknoah.neo.repository.EventRepository;
import com.clarknoah.neo.repository.PeopleRepository;
import com.clarknoah.neo.repository.TimeRepository;
import com.clarknoah.neo.service.TimelineJson;
import com.clarknoah.neo.domain.Event;
import com.clarknoah.neo.domain.Project;
import com.clarknoah.neo.domain.Time;
import com.clarknoah.neo.repository.ProjectRepository;
/**
 * Handles requests for the application home page.
 */
@Controller
public class CreateController {
	
	private static final Logger logger = LoggerFactory.getLogger(CreateController.class);
	
	
	@Autowired
	private PeopleRepository peepRepo;
	@Autowired
	private TimeRepository teepRepo;
	@Autowired
	private ProjectRepository projRepo;
	@Autowired
	private EventRepository eventRepo;

	
//@RequestMapping(value = "create/time", method = RequestMethod.GET)
//	public String timeForm(Model model) {
//		Time time = timeService.createTime();
//		logger.info("Creating the Time node "+time.getDisplayName());
	//	model.addAttribute("displayName", time.getDisplayName());
		//model.addAttribute("nodeType", time.getNodeType());
		//model.addAttribute("nodeId", time.getNodeId());
		//return "result";
	//	}
			
      	
	@RequestMapping(value = "json", method = RequestMethod.GET)
	public String JSON(Model model) {
		logger.info("Loading Peoplsss");
		long timeKey = 2;
		Time time = teepRepo.findOne(timeKey);
		Iterable<RelTime> allTime = time.getRelLastModified();		
		TimelineJson jOb = new TimelineJson(allTime, peepRepo);
		
		jOb.lastModifiedJson();
		System.out.println(jOb.mainTimeline);
		 model.addAttribute("json", jOb.mainTimeline.toString());			
		
		return "test";
		}
	@RequestMapping(value = "jsonEvent", method = RequestMethod.GET)
	public String JSONevent(Model model) {
		logger.info("Loading Peoplsss");
		long timeKey = 11;
		Project project = projRepo.findOne(timeKey);
		Iterable<Event> events = project.getProjEvent();

		//Iterable<RelTime> allTime = time.getRelLastModified();	
		
		TimelineJson jOb = new TimelineJson(events, eventRepo);
		
		jOb.projectEvents(events);
		System.out.println(jOb.mainTimeline);
		 model.addAttribute("json", jOb.mainTimeline.toString());			
		
		return "test";
		}
	

	
	
}
