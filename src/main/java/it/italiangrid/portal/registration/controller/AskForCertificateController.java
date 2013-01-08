package it.italiangrid.portal.registration.controller;

import javax.portlet.ActionRequest;
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

import com.liferay.portal.kernel.servlet.SessionErrors;

@Controller(value = "askForCertificateController")
@RequestMapping(value = "VIEW")
public class AskForCertificateController {
	
	private static final Logger log = Logger
			.getLogger(AskForCertificateController.class);
	
	
	@RenderMapping(params = "myaction=askForCertificate")
	public String showAskForCertificate() {
		log.debug("Show askForCertificate.jsp");
		return "askForCertificate";
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
	
	@ActionMapping(params = "myaction=askForCertificateRedirect")
	public void doRedirect(@ModelAttribute RegistrationModel registrationModel, ActionResponse response, ActionRequest request){
		log.error("Elaborate response for \"Do you have certificate?\" response is: " + registrationModel.isHaveCertificate());
		log.error(registrationModel.toString());
		
		if(registrationModel.isHaveCertificate()){
			
			log.debug("Redirect to certificate uploader");
			response.setRenderParameter("myaction", "showUploadCertificate");
			request.setAttribute("registrationModel", registrationModel);
		}else{
//			CookieUtil.setCookie(registrationModel, response);
//			try {
//				URL url = new URL("https://halfback.cnaf.infn.it/app1/index.jsp");
//				
//				log.error(url);
//				response.sendRedirect(url.toString());
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			log.debug("Redirect to IDP");
			try {
				if(Boolean.parseBoolean(RegistrationConfig.getProperties("Registration.properties", "CAOnline.enabled"))){
					request.setAttribute("registrationModel", registrationModel);
					response.setRenderParameter("myaction", "showCAForm");
					log.error(RegistrationConfig.getProperties("Registration.properties", "login.url"));
					request.setAttribute("loginUrl", RegistrationConfig.getProperties("Registration.properties", "login.url"));
					return;
				}
			} catch (RegistrationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				SessionErrors.add(request, e.getMessage());
			}
		}
	}
	
	

}
