package it.italiangrid.portal.registration.controller;

import it.italiangrid.portal.registration.dirac.DiracTaskJSON;
import it.italiangrid.portal.registration.server.DiracRegistration;

import javax.portlet.Event;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.EventMapping;

@Controller(value = "diracTaskController")
@RequestMapping(value = "VIEW")
public class DiracTaskController {
	
	private static final Logger log = Logger.getLogger(DiracTaskController.class);
	
	@EventMapping(value="{http://liferay.com/event}add.task")
	public void addTask(EventRequest request, EventResponse response){
		
		Event event = request.getEvent();
		String encodedString = (String) event.getValue();
		log.info("Encoded String: " + encodedString);
		
		DiracTaskJSON diracTaskJSON = new DiracTaskJSON(encodedString);
		log.info("DiracTask: " + diracTaskJSON.getDiracTask());
		
		DiracRegistration.addDiracTask(diracTaskJSON.getDiracTask());
	}

}
