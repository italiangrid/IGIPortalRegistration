package it.italiangrid.portal.registration.controller;

import it.italiangrid.portal.dbapi.domain.UserInfo;
import it.italiangrid.portal.registration.exception.RegistrationException;
import it.italiangrid.portal.registration.model.RegistrationModel;
import it.italiangrid.portal.registration.util.RegistrationConfig;

import java.util.List;

import javax.portlet.RenderRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import portal.registration.utils.TokenCreator;

@Controller(value = "caController")
@RequestMapping(value = "VIEW")
public class CAController {
	private static final Logger log = Logger.getLogger(CAController.class);
	
	@RenderMapping(params = "myaction=showCAForm")
	public String showCAForm() {
		log.debug("Show caForm.jsp");
		return "caForm";
	}
	
	@ModelAttribute("caUrl")
	public String getCaUrl(RenderRequest request) {
		
		try {
			log.error(RegistrationConfig.getProperties("Registration.properties", "login.url"));
			RegistrationModel registrationModel = (RegistrationModel) request.getAttribute("registrationModel");	
			List<String> tokens = TokenCreator.getToken(registrationModel.getEmail());
			return RegistrationConfig.getProperties("Registration.properties", "CAOnline.url")+"?t1="+tokens.get(0)+"&t2="+tokens.get(1);
		} catch (RegistrationException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@ModelAttribute("loginUrl")
	public String getLoginUrl() {	
		try {
			log.error(RegistrationConfig.getProperties("Registration.properties", "login.url"));
			return RegistrationConfig.getProperties("Registration.properties", "login.url");
		} catch (RegistrationException e) {
			e.printStackTrace();
		}
		return null;
	}
}
