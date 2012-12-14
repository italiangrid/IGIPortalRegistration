package it.italiangrid.portal.registration.controller;

import it.italiangrid.portal.dbapi.domain.UserInfo;
import it.italiangrid.portal.registration.exception.RegistrationException;
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
	
	@ModelAttribute("tokens")
	public List<String> getTokens(RenderRequest request) {
		
		UserInfo userInfo = (UserInfo) request.getAttribute("userInfo");	
		return TokenCreator.getToken(userInfo.getMail());
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
