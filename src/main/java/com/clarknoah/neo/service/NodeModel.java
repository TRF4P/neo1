package com.clarknoah.neo.service;

import org.springframework.ui.Model;

import com.clarknoah.neo.domain.*;
public abstract class NodeModel{

	public Model getPeopleModel(People noah, Model model){		
	    	model.addAttribute("displayName", noah.getDisplayName());
	        model.addAttribute("firstName", noah.getFirstName());
	   	    model.addAttribute("age", noah.getAge().toString());
	   	    model.addAttribute("lastName", noah.getLastName());
	   	    model.addAttribute("nodeId", noah.getNodeId().toString());
	   	    model.addAttribute("nodeType", noah.getNodeType());
	   	    model.addAttribute("lastModified", noah.getLastModified().toString());
	   	    model.addAttribute("firstCreated", noah.getFirstCreated().toString()); 
	   	    return model;
	    }
	}
