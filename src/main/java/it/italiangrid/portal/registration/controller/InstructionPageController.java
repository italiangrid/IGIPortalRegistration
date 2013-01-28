package it.italiangrid.portal.registration.controller;

import java.io.IOException;
import java.net.URL;

import javax.portlet.ActionResponse;

import it.italiangrid.portal.registration.exception.RegistrationException;
import it.italiangrid.portal.registration.model.RegistrationModel;
import it.italiangrid.portal.registration.util.RegistrationConfig;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

@Controller(value = "instructionPageController")
@RequestMapping(value = "VIEW")
public class InstructionPageController {
	
	private static final Logger log = Logger.getLogger(InstructionPageController.class);
	
	@RenderMapping(params = "myaction=showInstructionPage")
	public String showInstructionPage(){
		log.debug("myaction=showInstructionPage");
		return "instructionPage";
	}
	
	@ModelAttribute("registrationModel")
	public RegistrationModel getRegistrationModel() {
		log.debug("Initialize registration process.");	
		return new RegistrationModel();
	}
	
	@ModelAttribute("idpEnabled")
	public String getIdpEnabled(){
		try {
			return RegistrationConfig.getProperties("Registration.properties", "idp.enabled");
		} catch (RegistrationException e) {
			e.printStackTrace();
		}
		return "false";
	}
	
	
	
//	
	
	@ActionMapping(params = "myaction=startRegistration")
	public void redirectToWayf(ActionResponse response){
		
		try {
			URL url = new URL(RegistrationConfig.getProperties("Registration.properties", "retrieve.user.information"));
			
			log.error(url);
			response.sendRedirect(url.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RegistrationException e) {
			e.printStackTrace();
		}
	}

}
