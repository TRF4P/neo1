package com.clarknoah.neo.controller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.clarknoah.neo.domain.*;
import com.clarknoah.neo.repository.*;
import com.clarknoah.neo.service.PeopleService;
import com.clarknoah.neo.service.TimeService;


@Controller
public class RelController {

	
	@Autowired 
	private TimeRepository teepRepo;
	@Autowired
	private PeopleRepository peepRepo;
	@Autowired
	private OrganizationRepository orgRepo;
	@Autowired
	private EventRepository eventRepo;
	@Autowired
	private  ProjectRepository projRepo;
	@Autowired
	private PeopleService pplServ;
	
    
//	public final PeopleService ts = new PeopleService(peepRepo,teepRepo);

	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
/**
 * 	Methods for creating nodes and committing them to the database
 * 
 * 
 */
	
	@RequestMapping(value = "/makeRel/{target}/{rel}/{source}", method = RequestMethod.GET)
	public String makeRel(@PathVariable long target,
						  @PathVariable long source,
						  @PathVariable String relType, Model model) {
		People targetNode = peepRepo.findOne(source);
		People sourceNode = peepRepo.findOne(source);
		sourceNode.setFriendsWith(targetNode);
		pplServ.createEntity(sourceNode);
		return "result";
		}
	
	@RequestMapping(value = "/makeRelPost", method = RequestMethod.POST)
	public String makeRelPost(
						  @RequestParam("targetId") long targetId,
						  @RequestParam("sourceId") long sourceId,
						  @RequestParam("relType") String rel,
						   Model model) {
		if(rel.equals("friends with"))
		{
			People sourceNode = peepRepo.findOne(sourceId);
			People targetNode = peepRepo.findOne(targetId);
			sourceNode.setFriendsWith(targetNode);
			pplServ.createEntity(sourceNode);
		}
		else if(rel.equals("works for"))
		{
			People sourceNode = peepRepo.findOne(sourceId);
			Organization targetNode = orgRepo.findOne(targetId);
			sourceNode.setWorksAt(targetNode);
			pplServ.createEntity(sourceNode);
		}
		else if(rel.equals("works under"))
		{
			People sourceNode = peepRepo.findOne(sourceId);
			People targetNode = peepRepo.findOne(targetId);
			sourceNode.setWorksUnder(targetNode);
			pplServ.createEntity(sourceNode);
		}
		else if(rel.equals("is working on"))
		{
			People sourceNode = peepRepo.findOne(sourceId);
			Project targetNode = projRepo.findOne(targetId);
			sourceNode.setWorkingOn(targetNode);
			pplServ.createEntity(sourceNode);
		}
		else if(rel.equals("attended"))
		{
			People sourceNode = peepRepo.findOne(sourceId);
			Event targetNode = eventRepo.findOne(targetId);
			sourceNode.setAttended(targetNode);
			pplServ.createEntity(sourceNode);
		}
		else if(rel.equals("is hosting"))
		{
			Organization sourceNode = orgRepo.findOne(sourceId);
			Event targetNode = eventRepo.findOne(targetId);
			sourceNode.setHosting(sourceNode);
			pplServ.createEntity(sourceNode);
		}
		else if(rel.equals("relates to project"))
		{
			Event sourceNode = eventRepo.findOne(sourceId);
			Project targetNode = projRepo.findOne(targetId);
			targetNode.setProjEvent(sourceNode);
			pplServ.createEntity(targetNode);
		}
		else if(rel.equals("owns"))
		{
			Organization sourceNode = orgRepo.findOne(sourceId);
			Project targetNode = projRepo.findOne(targetId);
			sourceNode.setOwns(targetNode);
			pplServ.createEntity(sourceNode);
		}
		else if(rel.equals("is working on"))
		{
			People sourceNode = peepRepo.findOne(sourceId);
			Project targetNode = projRepo.findOne(targetId);
			targetNode.setWorkingOn(sourceNode);
			pplServ.createEntity(targetNode);
		}
		else if(rel.equals("sub-organization of"))
		{
			Organization sourceNode = orgRepo.findOne(sourceId);
			Organization targetNode = orgRepo.findOne(targetId);
			if(sourceNode.getNodeId()!=targetNode.getNodeId())
			{
			targetNode.setSubOrg(sourceNode);
			pplServ.createEntity(targetNode);
		}
			else
			{
				System.out.println("Cannot recursively link node");
			}
		}

		else
		{}
		return "/create/create";
		}


}
