package com.clarknoah.neo.service;

import com.clarknoah.neo.domain.Event;
import com.clarknoah.neo.domain.Email;
import com.clarknoah.neo.domain.People;
import com.clarknoah.neo.repository.EmailRepository;
import com.clarknoah.neo.repository.EventRepository;
import com.clarknoah.neo.repository.OrganizationRepository;
import com.clarknoah.neo.repository.PeopleRepository;
import com.clarknoah.neo.repository.ProjectRepository;
import com.clarknoah.neo.repository.TimeRepository;
import com.google.gdata.client.*;
import com.google.gdata.client.calendar.*;
import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.data.*;
import com.google.gdata.data.acl.*;
import com.google.gdata.data.calendar.*;
import com.google.gdata.data.extensions.*;
import com.google.gdata.util.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;



public class GoogleCalendarService {
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
	@Autowired
	private EmailRepository emailRepo;
	@Autowired
	private PeopleService pplServ;
	public static CalendarService myService;
	List<String> attendeeSet = new ArrayList<String>();

	public static void setService() throws AuthenticationException{
		myService = new CalendarService("exampleCo-exampleApp-1");
		myService.setUserCredentials("noahbc08@gmail.com", "**********");
	}

    public void getCalendar() throws IOException, ServiceException{
    	// Send the request and print the response
    	URL feedUrl = new URL("https://www.google.com/calendar/feeds/default/allcalendars/full");
    	CalendarFeed resultFeed = myService.getFeed(feedUrl, CalendarFeed.class);
    	System.out.println("Calendars you own:");
    	System.out.println();
    	for (int i = 0; i<resultFeed.getEntries().size(); i++) {
    	  CalendarEntry entry = resultFeed.getEntries().get(i);
    	  System.out.println("\t" + entry.getTitle().getPlainText());
    	}
    }
    
    public void getEvents() throws IOException, ServiceException{    	

    	// Set up the URL and the object that will handle the connection:
    	URL feedUrl = new URL("https://www.google.com/calendar/feeds/default/private/full");
    	CalendarQuery myQuery = new CalendarQuery(feedUrl);
    	myQuery.setMinimumStartTime(DateTime.parseDateTime("2013-01-26T00:00:00"));
    	myQuery.setMaximumStartTime(DateTime.parseDateTime("2013-01-30T23:59:59"));
    	// Send the request and receive the response:
    	CalendarEventFeed myFeed = myService.query(myQuery, CalendarEventFeed.class);
    	List<CalendarEventEntry> calList = myFeed.getEntries();
    	Iterator<CalendarEventEntry> eventIt = calList.iterator();
    	while(eventIt.hasNext()){
    		System.out.println("");
    		System.out.println("-----------------Event------------------");
    		//eventIt.next().getPlainTextContent();
    		CalendarEventEntry current = eventIt.next();
    		Iterator<When> times = current.getTimes().iterator();
    		System.out.println("ICalUID "+current.getIcalUID());
    		System.out.println("Id "+current.getId());
    		System.out.println("eTag "+current.getEtag());
    		Iterator<EventWho> attendees = current.getParticipants().iterator();   	
    		while(attendees.hasNext()){
    			EventWho currentAttendee = attendees.next();
    			System.out.println("Participant Email: "+currentAttendee.getEmail());   			
    			
    			System.out.println("Participant Status:  "+currentAttendee.getAttendeeStatus());
    			System.out.println("Participant Attendee type: "+currentAttendee.getAttendeeType());
    			System.out.println("Participant String Value: "+currentAttendee.getValueString());
    			System.out.println("Participant Rel: "+currentAttendee.getRel());
    			System.out.println("");
    			Email currentEmail = emailRepo.findByPropertyValue("email", currentAttendee.getEmail());
    			if (currentEmail != null && currentEmail.getAddressOf().getNodeId() != null){
    			attendeeSet.add(currentEmail.getAddressOf().getNodeId().toString());
    			}
    			
    			//People currentPerson = peopleRepository.findOne(attendeeId); 		
    		}
    		
    		//If the specific calendar entry has a title, do this:
    		if(current.getTitle().getPlainText().isEmpty()==false){
    			
    		System.out.println("title: "+current.getTitle().getPlainText());
    		}
    		if(current.getPlainTextContent().isEmpty()==false){
    		System.out.println(current.getPlainTextContent());  		
    		} 
    		while(times.hasNext()){
    			When timesr = times.next();
    			System.out.println(timesr.getStartTime().getValue());
    			System.out.println(timesr.getStartTime().toUiString());
    			Date startDate = null;
    			Date endDate = null;
    			try {
					startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(timesr.getStartTime().toUiString());
				} catch (ParseException e) {
					try {
						startDate = new SimpleDateFormat("yyyy-MM-dd").parse(timesr.getStartTime().toUiString());
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
    			try {
					endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(timesr.getEndTime().toUiString());
				} catch (ParseException e) {
					try {
						endDate = new SimpleDateFormat("yyyy-MM-dd").parse(timesr.getEndTime().toUiString());
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
    			Event eventEnt = new Event(current.getTitle().getPlainText(), startDate, endDate);
    			//eventRepo.save(eventEnt);
    			//eventEnt = 
    			Event savedEvent = pplServ.createEntity(eventEnt);
    			System.out.println(savedEvent.getNodeId().toString());
    			if(attendeeSet != null){
    			Iterator<String> pplIt = attendeeSet.iterator();
    			while(pplIt.hasNext())
    			{
    				People thisPpl = peopleRepository.findOne(new Long(pplIt.next()));
    				 thisPpl.setAttended(eventEnt);
    				 pplServ.createEntity(thisPpl);
    				}
    			}
    			else
    				{
    				System.out.println("No found attendees");
    				}
    		}
    	}
    }
}
