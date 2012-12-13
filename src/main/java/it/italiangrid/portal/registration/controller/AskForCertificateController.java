package it.italiangrid.portal.registration.controller;

import java.io.IOException;
import java.net.URL;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import it.italiangrid.portal.registration.model.RegistrationModel;
import it.italiangrid.portal.registration.util.CookieUtil;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

@Controller(value = "askForCertificateController")
@RequestMapping(value = "VIEW")
public class AskForCertificateController {
	
	private static final Logger log = Logger
			.getLogger(AskForCertificateController.class);
	
	@ModelAttribute("registrationModel")
	public RegistrationModel getRegistrationModel() {
		log.debug("Initialize registration process.");	
		return new RegistrationModel();
	}
	
	
	@RenderMapping(params = "myaction=askForCertificate")
	public String showAskForCertificate() {
		log.debug("Show askForCertificate.jsp");
		return "askForCertificate";
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
			CookieUtil.setCookie(registrationModel, response);
			try {
				URL url = new URL("https://halfback.cnaf.infn.it/app1/index.jsp");
				
				log.error(url);
				response.sendRedirect(url.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			log.debug("Redirect to IDP");
		}
	}

}
