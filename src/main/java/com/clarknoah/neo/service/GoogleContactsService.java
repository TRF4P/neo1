package com.clarknoah.neo.service;

import com.clarknoah.neo.domain.People;
import com.clarknoah.neo.domain.Email;
import com.clarknoah.neo.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.CRUDRepository;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gdata.client.*;
import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.client.contacts.*;
import com.google.gdata.data.*;
import com.google.gdata.data.contacts.*;
import com.google.gdata.data.contacts.ContactEntry;
import com.google.gdata.data.contacts.ContactFeed;
import com.google.gdata.data.extensions.*;
import com.google.gdata.util.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;



public class GoogleContactsService {

	@Autowired
	EmailRepository emailRepo;
	@Autowired
	PhoneNumberRepository numRepo;
	@Autowired
	private OrganizationRepository orgRepo;
	@Autowired
	private EventRepository eventRepo;
	@Autowired
	private  ProjectRepository projRepo;
	@Autowired
	private PeopleRepository peopleRepo;	
	@Autowired
	private TimeRepository timeRepository;
	@Autowired	
	private PeopleService pplServ;
	public String user;
	public String pass;
	
	public static ContactsService myService;
	public int counter =0;
	public GoogleContactsService(){
		
	}
	
	
	public void setService() throws AuthenticationException{
		myService = new ContactsService("exampleCo-exampleApp-1");
		myService.setUserCredentials(user, pass);
	}
	
	public void importAllContacts() throws IOException, ServiceException{
		//import contacts
		setService();
	
		  URL feedUrl = new URL("https://www.google.com/m8/feeds/contacts/noahbc08@gmail.com/full");
		  Query myQuery = new Query(feedUrl);
		  myQuery.setMaxResults(10);
		  
		  ContactFeed resultFeed = myService.query(myQuery, ContactFeed.class);
		  // Print the results
		  System.out.println(resultFeed.getTitle().getPlainText());
		  for (int i = 0; i < resultFeed.getEntries().size(); i++) {
		    ContactEntry entry = resultFeed.getEntries().get(i);
		  //Determine if this contact entry already exists in database (check Google ID):
		    System.out.println(entry.getEtag());
		    System.out.println(entry.getId());
		    // if individual exists with Google ID, check to see if that invididual has all the emails listed:
		//   People tryPerosn = peopleRepo.findByPropertyValue("googleId", "googleId", entry.getId());
		   if(peopleRepo.findByPropertyValue("googleId", "googleId", entry.getEtag())!= null)
			   {
			   
			   System.out.println(peopleRepo.findByPropertyValue("googleId", "googleId", entry.getId()).getNodeId().toString()+" He exists!");
			   }

		    else 
		    {
		    importContact(entry);
		    }
		    entry.getEmailAddresses();
		  }			
	    
	    //If entry exists, check to see if entry 
	}
	public People importContact(ContactEntry entry){
		  People newContact = new People();
		  counter++;
		  System.out.println(counter);
		//  System.out.println(entry.getName().getFullName().getValue().toString().isEmpty());
		 if(entry.hasName()==true){
		 if(entry.getBirthday()!= null){		
			
			try {
				Date bDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(entry.getBirthday().getWhen());
				newContact.setBirthdate(bDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		 }
		//  newContact.setAge()
		 if(entry.getName().getFullName().getValue().toString()!=null){
			 System.out.println(entry.getName().getFullName().getValue().toString());
			  newContact.setDisplayName(entry.getName().getFullName().getValue().toString());
		 }

		  if(entry.getName().getGivenName()!=null){
			  newContact.setFirstName(entry.getName().getGivenName().getValue().toString());
			  System.out.println(entry.getName().getGivenName().getValue().toString());
			 }
		  
		  if(entry.getName().getFamilyName()!=null){
			  newContact.setLastName(entry.getName().getFamilyName().getValue());
			  System.out.println(entry.getName().getFamilyName().getValue());
			 }
		  newContact.setGoogleId(entry.getId());		  
		  
		People commitContact = pplServ.createEntity(newContact);
		
		System.out.println(commitContact.getNodeId().toString());
		 
		Iterator<com.google.gdata.data.extensions.Email> emailList = entry.getEmailAddresses().iterator();
		 while(emailList.hasNext()){
			 com.google.gdata.data.extensions.Email thisEmail = emailList.next();
			 System.out.println(thisEmail.getAddress());
			 if(emailRepo.findByPropertyValue("email", "email", thisEmail.getAddress())== null){
				 System.out.println(thisEmail.getAddress());
				 Email newEmail = new Email(thisEmail.getAddress());
				 Email commitEmail = pplServ.createEntity(newEmail);
				 commitContact.setAddressOf(commitEmail);
				 pplServ.createEntity(commitContact);
			 }
			 	
		 }
		 
		Iterator<com.google.gdata.data.extensions.PhoneNumber> numbers = entry.getPhoneNumbers().iterator();
		while(numbers.hasNext()){
			com.google.gdata.data.extensions.PhoneNumber thisNum = numbers.next();
			 System.out.println(thisNum.getPhoneNumber());
			 System.out.println(thisNum.getLabel());
			 if(numRepo.findByPropertyValue("phoneNum", "phoneNum", thisNum.getPhoneNumber())== null){com.clarknoah.neo.domain.PhoneNumber newPhone = new com.clarknoah.neo.domain.PhoneNumber(thisNum.getPhoneNumber(), thisNum.getLabel());
				 com.clarknoah.neo.domain.PhoneNumber commitNum = pplServ.createEntity(newPhone);
			//	 commitNum.setPhoneNumberOf(commitContact);
				 commitContact.setPhoneNum(commitNum);
				 pplServ.createEntity(commitNum);
			 }
			 	
		 }
		 }
		 // newContact.setAddressOf(entry.)
		  
		return newContact;
	  }
	
	public void printAllContacts()
		    throws ServiceException, IOException {
		  // Request the feed
	
		
		setService();
		  URL feedUrl = new URL("https://www.google.com/m8/feeds/contacts/noahbc08@gmail.com/full");
		  Query myQuery = new Query(feedUrl);
		  myQuery.setMaxResults(8);
		  myQuery.setStringCustomParameter("group", "Business Contacts");
		  ContactFeed resultFeed = myService.query(myQuery, ContactFeed.class);
		  // Print the results
		  System.out.println(resultFeed.getTitle().getPlainText());
		  for (int i = 0; i < resultFeed.getEntries().size(); i++) {
		    ContactEntry entry = resultFeed.getEntries().get(i);
		    
		  Name test = entry.getName();
		 FullName namee= test.getFullName();
		   People person= peopleRepo.save(new People(23,entry.getTitle().getPlainText(), entry.getTitle().getPlainText()));
		 System.out.println(person.getNodeId().toString());
		   System.out.println("\t" + entry.getTitle().getPlainText());

		    System.out.println("Email addresses:");
		    for (com.google.gdata.data.extensions.Email email : entry.getEmailAddresses()) {
		      System.out.print(" " + email.getAddress());
		      if (email.getRel() != null) {
		        System.out.print(" rel:" + email.getRel());
		      }
		      if (email.getLabel() != null) {
		        System.out.print(" label:" + email.getLabel());
		      }
		      if (email.getPrimary()) {
		        System.out.print(" (primary) ");
		      }
		      System.out.print("\n");
		    }

		    System.out.println("IM addresses:");
		    for (Im im : entry.getImAddresses()) {
		      System.out.print(" " + im.getAddress());
		      if (im.getLabel() != null) {
		        System.out.print(" label:" + im.getLabel());
		      }
		      if (im.getRel() != null) {
		        System.out.print(" rel:" + im.getRel());
		      }
		      if (im.getProtocol() != null) {
		        System.out.print(" protocol:" + im.getProtocol());
		      }
		      if (im.getPrimary()) {
		        System.out.print(" (primary) ");
		      }
		      System.out.print("\n");
		    }

		    System.out.println("Groups:");
		    for (GroupMembershipInfo group : entry.getGroupMembershipInfos()) {
		      String groupHref = group.getHref();
		      System.out.println("  Id: " + groupHref);
		    }

		    System.out.println("Extended Properties:");
		    for (ExtendedProperty property : entry.getExtendedProperties()) {
		      if (property.getValue() != null) {
		        System.out.println("  " + property.getName() + "(value) = " +
		            property.getValue());
		      } else if (property.getXmlBlob() != null) {
		        System.out.println("  " + property.getName() + "(xmlBlob)= " +
		            property.getXmlBlob().getBlob());
		      }
		    }

		    String photoLink = entry.getContactPhotoLink().getHref();
		    System.out.println("Photo Link: " + photoLink);

		   

		    System.out.println("Contact's ETag: " + entry.getEtag());
		  }
		}
}
