package it.italiangrid.portal.registration.controller;

import it.italiangrid.portal.registration.exception.RegistrationException;
import it.italiangrid.portal.registration.util.RegistrationConfig;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

@Controller(value = "authenticationController")
@RequestMapping(value = "VIEW")
public class AuthenticationController {
	
	private static final Logger log = Logger.getLogger(AuthenticationController.class);
	
	@RenderMapping(params = "myaction=showAuthentication")
	public String showCAForm() {
		log.debug("Show authenticationPage.jsp");
		return "authenticationPage";
	}
	
	@ModelAttribute("loginUrl")
	public String getLoginUrl() {	
		try {
			log.info(RegistrationConfig.getProperties("Registration.properties", "login.url"));
			return RegistrationConfig.getProperties("Registration.properties", "login.url");
		} catch (RegistrationException e) {
			e.printStackTrace();
		}
		return null;
	}

}
