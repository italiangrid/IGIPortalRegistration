package it.italiangrid.portal.registration.controller;

import it.italiangrid.portal.dbapi.domain.UserInfo;
import it.italiangrid.portal.dbapi.services.CertificateService;
import it.italiangrid.portal.dbapi.services.NotifyService;
import it.italiangrid.portal.dbapi.services.UserInfoService;
import it.italiangrid.portal.dbapi.services.UserToVoService;
import it.italiangrid.portal.registration.exception.RegistrationException;
import it.italiangrid.portal.registration.model.RegistrationModel;
import it.italiangrid.portal.registration.util.CookieUtil;
import it.italiangrid.portal.registration.util.RegistrationConfig;
import it.italiangrid.portal.registration.util.RegistrationUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.liferay.portal.kernel.servlet.SessionErrors;

import portal.registration.utils.MyValidator;

@Controller(value = "addUserController")
@RequestMapping(value = "VIEW")
public class AddUserController {

	private static final Logger log = Logger.getLogger(AddVOActionController.class);
	
	@Autowired
	private NotifyService notifyService;

	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private UserToVoService userToVoService;
	
	@Autowired
	private CertificateService certificateService;
	
	@RenderMapping(params = "myaction=showAddUserForm")
	public String showAskForCertificate(RenderRequest request) {
		log.debug("Show addUserForm.jsp");
		String[] array = {"l","o", "givenName", "sn", "uid", "mail", "persistent-id", "org-dn"};
		List<String> attributes = Arrays.asList(array);
		
		UserInfo userInfo = new UserInfo();
		
		for (Enumeration<String> e = request.getParameterNames() ; e.hasMoreElements() ;) {
			String name = e.nextElement();
	        log.error(name);
	        
	        switch(attributes.indexOf(name)){
	        case 0:
//	        	l
	        	userInfo.setInstitute(request.getParameter(name).replaceAll("%20", " ").toUpperCase());
	        	break;
	        case 1:
//	        	o
	        	userInfo.setInstitute(request.getParameter(name).replaceAll("%20", " ").toUpperCase());
	        	break;
	        case 2:
//	        	givenName
	        	userInfo.setFirstName(request.getParameter(name).replaceAll("%20", " "));
	        	break;
	        case 3:
//	        	sn
	        	userInfo.setLastName(request.getParameter(name).replaceAll("%20", " "));
	        	break;
	        case 4:
//	        	uid
	        	userInfo.setUsername(request.getParameter(name).replaceAll("%20", " "));
	        	break;
	        case 5:
//	        	mail
	        	userInfo.setMail(request.getParameter(name).replaceAll("%20", " "));
	        	break;
	        case 6:
	        	userInfo.setUsername(request.getParameter(name).replaceAll("%20", " "));
//	        	persistent-id
	        	break;
	        case 7:
//	        	org-dn
	        	userInfo.setInstitute(request.getParameter(name).replaceAll("%20", " ").replaceAll("dc.", "").replaceAll(",", " ").toUpperCase());
	        	break;
	        }
	        

	     }
		
		request.setAttribute("userInfo", userInfo);
		
		return "addUserForm";
	}
	
	@ActionMapping(params="myaction=addUser")
	public void addUser(@ModelAttribute UserInfo userInfo, ActionRequest request, ActionResponse response){
		
		RegistrationModel registrationModel = CookieUtil.getCookie(request);
		log.error(registrationModel);
		
		List<String> errors = new ArrayList<String>();
		
		//Validate user
		if(!MyValidator.validate(userInfo, errors)){
			
			for(String error: errors)
				SessionErrors.add(request, error);
			
			request.setAttribute("userInfo", userInfo);
			CookieUtil.setCookie(registrationModel, response);
			response.setRenderParameter("myaction", "showAddUserForm");
			return;
		
		}
		
		try{
			//AddUser into Liferay
			RegistrationUtil.addUserToLiferay(request, userInfo, registrationModel);
			
			//AddUser into DB
			userInfo=RegistrationUtil.addUserToDB(userInfo, userInfoService, notifyService);
			
			//Associate certificate to the user
			RegistrationUtil.associateUserToCertificate(userInfo, registrationModel, certificateService);
			
			//Associate Vo to the user
			RegistrationUtil.associateVoToUser(userInfo, registrationModel, userToVoService);
			
			response.sendRedirect(RegistrationConfig.getProperties("Registration.properties", "login.url"));
		
		}catch(RegistrationException e){
			e.printStackTrace();
			SessionErrors.add(request, e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("userInfo", userInfo);
		CookieUtil.setCookie(registrationModel, response);
		response.setRenderParameter("myaction", "showAddUserForm");
		
	}
}
