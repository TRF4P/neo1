package com.clarknoah.neo.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.clarknoah.neo.domain.*;
import com.clarknoah.neo.relationship.RelTime;
import com.clarknoah.neo.repository.*;
import com.clarknoah.neo.service.GoogleCalendarService;
import com.clarknoah.neo.service.GoogleContactsService;
import com.clarknoah.neo.service.PeopleService;
import com.clarknoah.neo.service.TimeService;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
import com.google.gson.*;

@Controller
public class TestController {

	
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
	@Autowired
	private GoogleCalendarService calService;
	
	private long timeKey = 81;
    
//	public final PeopleService ts = new PeopleService(peepRepo,teepRepo);

	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
/**
 * 	Methods for creating nodes and committing them to the database
 * 
 * 
 */
	
	@RequestMapping(value = "create/*", method = RequestMethod.GET)
	public String create(Model model) {
		return "create/create";
		}
	@RequestMapping(value = "create/org", method = RequestMethod.GET)
	public String startOrg(Model model) {
		return "create/org";
		}
	
	@RequestMapping(value = "create/addOrg", method = RequestMethod.POST)
	public String addOrg(
            @RequestParam(value = "aName") String orgAcronym,
            @RequestParam(value = "fName") String orgName, 
            Model model) {
			Organization noah = pplServ.createEntity(new Organization(orgName, orgAcronym));
			model = noah.getModel(model);
		return "result";
		}
	
	@RequestMapping(value = "create/event", method = RequestMethod.GET)
	public String startEvent(Model model) {
		return "create/event";
		}
	
	@RequestMapping(value = "create/addEvent", method = RequestMethod.POST)
	public String addEvent(
			@RequestParam(value = "eventName") String eventName,
			@RequestParam(value = "startDate") String startDate,
			@RequestParam(value = "startTime") String startTime,
            @RequestParam(value = "endDate") String endDate, 
            @RequestParam(value = "endTime") String endTime,					
		Model model) throws ParseException {
		System.out.println(startTime+" "+endTime);
		Date strDate = new SimpleDateFormat("yyy-MM-dd", Locale.ENGLISH).parse(startDate);		
	//	strDate.setTime(time));
		Date edDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(endDate);		
			Event noah = pplServ.createEntity(new Event(eventName, strDate, edDate));
			model = noah.getModel(model);	
		return "result";
		}
	
	@RequestMapping(value = "create/project", method = RequestMethod.GET)
	public String startProject(Model model) {
		return "create/project";
		}
	
	@RequestMapping(value = "create/addProject", method = RequestMethod.GET)
	public String addProject(
			@RequestParam(value = "projectName") String projectName,
			@RequestParam(value = "startDate") String startDate,
		    @RequestParam(value = "endDate") String endDate,
			@RequestParam(value = "iocDate") String iocDate,					
			Model model) throws ParseException {		
		Date strDate = new SimpleDateFormat("yyy-MM-dd", Locale.ENGLISH).parse(startDate);
		Date edDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(endDate);
		Date ioDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(iocDate);
		System.out.println(startDate+" "+endDate+" "+iocDate);
			Project noah = pplServ.createEntity(new Project(projectName, strDate, edDate, ioDate));
			model = noah.getModel(model);
		return "result";
		}
	
	@RequestMapping(value = "create/ppl", method = RequestMethod.GET)
	public String startPerson() {
		logger.info("Loacting Create ");
		return "create/ppl";
		}
	
    @RequestMapping(value = "create/addPpl", method = RequestMethod.POST)
    public String addPerson(
            @RequestParam(value = "age") int age,
            @RequestParam(value = "firstName") String firstName,
            @RequestParam(value = "lastName") String lastName,
            Model model) {				    
		 People noah = pplServ.createEntity(new People(age, firstName, lastName));
		model = noah.getModel(model); 	         	   
            return "result";       
    }

    /**
     * 	Handles listing out the existing Nodes
     * 
     * 
     */   
	@RequestMapping(value = "/list/", method = RequestMethod.GET)
	public String list() {
		return "list/list";
		}
	@RequestMapping(value = "/list/{nodeType}/{pageNum}", method = RequestMethod.GET, headers = "Accept=text/html")
    public String listEntities(Model model,
    		@PathVariable Integer pageNum,
    		@PathVariable String nodeType
    		) {
    		Page entityPage;
    		if (nodeType.contains("ppl")){
    			 entityPage = peepRepo.findAll(new PageRequest(pageNum,20));
    		   }
    		   else if(nodeType.contains("org")){
    		    entityPage = orgRepo.findAll(new PageRequest(pageNum,20));  
    		   }
    		   else if(nodeType.contains("event")){
    			entityPage = eventRepo.findAll(new PageRequest(pageNum,20));
    		   }
    		   else if(nodeType.contains("project")){
    			entityPage = projRepo.findAll(new PageRequest(pageNum,20)); 
    		   }
    		   else {
    			   return "result";
    		   }
            int current = entityPage.getNumber();
            int begin = Math.max(0, current - 5);
            int end = Math.min(begin + 10, entityPage.getTotalPages());            
            model.addAttribute("people", entityPage.getContent());
            model.addAttribute("beginIndex", begin);
            model.addAttribute("nodeType", nodeType);
            model.addAttribute("endIndex", end);
            model.addAttribute("currentIndex", current);
            model.addAttribute("totalPages", entityPage.getTotalPages());
        return "/list/page";
    }

    /**
     * 	Handles querying nodes
     * 
     * 
     */ 
	
	@RequestMapping(value = "/query/*", method = RequestMethod.GET)
	public String query() {
		return "query/query";
		}
 
   
    @RequestMapping(value = "/query/{nodeType}/{pageNum}{noah}", method = RequestMethod.GET, headers = "Accept=text/html")
    public String queryEntities(Model model,
    		@PathVariable Integer pageNum,
    		@RequestParam(value = "q") String query,
    		@PathVariable String nodeType,
    		@PathVariable String noah
    		) {
    		Page entityPage;
    	
    		if (nodeType.contains("ppl")){
    			entityPage = peepRepo.findByDisplayName(query, new PageRequest(pageNum,20)) ;
    		   }
    		   else if(nodeType.contains("org")){
    		    entityPage = orgRepo.findByDisplayName(query, new PageRequest(pageNum,20)) ; 
    		   }
    		   else if(nodeType.contains("event")){
    			entityPage = eventRepo.findByDisplayName(query, new PageRequest(pageNum,20)) ;
    		   }
    		   else if(nodeType.contains("project")){
    			entityPage = projRepo.findByDisplayName(query, new PageRequest(pageNum,20)) ;
    		   }
    		   else {
    			   return "result";
    		   }
    		String persist = "?q="+query+"&Search=Submit";
            int current = entityPage.getNumber();
            int begin = Math.max(1, current - 5);
            int end = Math.min(begin + 10, entityPage.getTotalPages());            
            model.addAttribute("people", entityPage.getContent());
            model.addAttribute("beginIndex", begin);
            model.addAttribute("nodeType", nodeType);
            model.addAttribute("search", persist);
            model.addAttribute("endIndex", end);
            model.addAttribute("currentIndex", current);
            model.addAttribute("q", query);
            model.addAttribute("totalPages", entityPage.getTotalPages());
        return "/query/page";
    }
    @RequestMapping(value = "/node/{nodeType}/{nodeId}", method = RequestMethod.GET)


    /**
     * 	Handles single node view requests
     * 
     * 
     */ 
    public String viewNode(
			@PathVariable long nodeId,
			@PathVariable String nodeType, Model model) {
    	
    	if (nodeType.contains("ppl")){
			 People modelNode = peepRepo.findOne(nodeId);
			 model = modelNode.getModel(model);
		   }
		   else if(nodeType.contains("org")){
		   Organization modelNode = orgRepo.findOne(nodeId);		 	  	   
		   model = modelNode.getModel(model);
		   }
		   else if(nodeType.contains("event")){
			   Event modelNode = eventRepo.findOne(nodeId);
			  // model = modelNode.getModel(model);
		   }
		   else if(nodeType.contains("project")){
			Project modelNode = projRepo.findOne(nodeId);
			model = modelNode.getModel(model);
		   }
		   else {
			   return "/indexAccess";
		   }
    
		return "result";
		}
   
	@RequestMapping(value = "/delete/{nodeId}", method = RequestMethod.GET)
	public  String deleteNode(@PathVariable long nodeId, Model model) {
		People person = peepRepo.findOne(nodeId);
		peepRepo.delete(person);
		model.addAttribute("people", "Node Deleted!");
		return "result";
		}
	
	@RequestMapping(value = "/jsonRequest", method = RequestMethod.GET)
	public @ResponseBody String jsonRequest(@RequestParam("query") String query,
			Model model) {
		//Get query character
		
		//send query character to Repository
		
		Iterable<People> ajaxSearch = peepRepo.findAllByQuery("displayName", query+"*");
		Iterable<Organization> ajaxSearchOrg = orgRepo.findAllByQuery("displayName", query+"*");
				
		//		("displayName", query);
		//receive query results in interable object
		//parse results into json object

		//take ajaxSearch iterable and convert to json object
		//create query object
		JsonObject suggestions =new JsonObject();
		suggestions.addProperty("query", query);
		
		//create suggestions array
		JsonArray suggArray = new JsonArray();

		//populate suggestions suggestion object array with string character
		Iterator<People> tmCount = ajaxSearch.iterator();	
		while(tmCount.hasNext()) {		
			JsonObject uniqueSugg =new JsonObject();
			People qInst = tmCount.next();
			uniqueSugg.addProperty("value", qInst.getDisplayName());
			System.out.println(qInst.getDisplayName());
			System.out.println(qInst.getNodeId().toString());
			uniqueSugg.addProperty("data", qInst.getNodeId().toString());
			//save suggestion into array (until finished)
			suggArray.add(uniqueSugg);
	     }
	
		suggestions.add("suggestions", suggArray);
		//save array into suggestions object
		
		//return object
		System.out.println(suggestions.toString());
		return suggestions.toString();
	}
	@RequestMapping(value = "/jsonRequestOrg/{nodeId}", method = RequestMethod.GET)
	public @ResponseBody String jsonRequestOrg(@PathVariable long nodeId, Model model) {
			
		long id = nodeId;
		Organization org = orgRepo.findOne(id);
		Organization rootOrg = org;
		JsonObject masterObject = sunburstObject(org);
		//JsonArray masterArray = masterObject.getAsJsonArray("children");
		JsonArray masterArray = new JsonArray();
		masterArray.add(masterObject);
		System.out.println(masterArray.toString());
		return masterArray.toString();
	//	return sunburstJson(org);
	}

	@RequestMapping(value = "/initialize", method = RequestMethod.GET)
	public String initialize(Model model) throws ServiceException, IOException, ParseException {
		//String noah = randomColor();
		GoogleContactsService test = new GoogleContactsService();
			
		//Email newEmail = new Email("noahbc08@gmail.com");
		//long noah = 1;
		//newEmail.setAddressOf(peepRepo.findOne(noah));
		//pplServ.createEntity(newEmail);
		
		//People noah = new People(23, "Noah","Clark");		
		//noah.setEmailList("noahbc08@gmail.com");
		//pplServ.createEntity(noah);
		//System.out.println(noah.getNodeId().toString());
		calService.getCalendar();
		calService.getEvents();
		//test.printAllContacts(peepRepo);
		//long noahd =1;
		//People noah = peepRepo.findOne(noahd);
		//pplServ.createEntity(noah);
		return "/decorators/main";
	}

	public String sunburstJson(Organization org)
	{	
		Organization rootOrg = org;
		JsonArray rootArray = new JsonArray();
		JsonObject masterObject = sunburstObject(org);
		System.out.println(masterObject.toString());
		JsonElement masterArray = masterObject.get("children");
		System.out.println(masterArray.toString());
		System.out.println(rootArray.toString());
		return masterArray.toString();
	}
	public JsonObject sunburstObject(Organization org){
		System.out.println(org.getDisplayName());
		System.out.println(org.getNodeId().toString());
		//creates JSON object
		JsonObject sunburstOrg = new JsonObject();	
		sunburstOrg.addProperty("name", org.getOrgAcronym());
		sunburstOrg.addProperty("colour", randomColor());
		//creates the set sub orgs 
		Set<Organization> subOrgs = org.getSubOrg();
		
		//This checks the organization to see if it has any sub categories
		if(subOrgs.isEmpty()==false){
			
			//If sub category exists, it creates an array object to hold it
			JsonArray subArray = new JsonArray();
			
			//the sub object then turns to an itegrator to itegrate the object
			Iterator<Organization> orgIt = subOrgs.iterator();
			//while there is another object in the itegrator
			while(orgIt.hasNext()){			
				//take the existing subArray object and put the org object inside of it
				Organization subOrg1 = orgRepo.findOne(orgIt.next().getNodeId());
				System.out.println("moving into: "+subOrg1.getDisplayName());
				System.out.println("moving into: "+subOrg1.getDisplayName());
				JsonObject subObject = sunburstObject(subOrg1);
				subArray.add(subObject);
				//System.out.println(subArray.toString());
			}	
			sunburstOrg.add("children", subArray);
			System.out.println("The rr "+subArray.toString());
		}
		//System.out.println("The rr "+subArray.toString());
		return sunburstOrg;	
	}
	
	public static String randomColor(){
		   String code = ""+(int)(Math.random()*256);
		   code = code+code+code;
		   int  i = Integer.parseInt(code);
		 
		   String randomColor = ("#"+Integer.toHexString( 0x1000000 | i).substring(1).toUpperCase());		
		  System.out.println(randomColor);
		 return randomColor;
		 }


}
