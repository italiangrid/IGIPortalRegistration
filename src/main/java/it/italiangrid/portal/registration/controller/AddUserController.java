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
import javax.portlet.RenderResponse;

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

	private static final Logger log = Logger.getLogger(AddUserController.class);
	
	@Autowired
	private NotifyService notifyService;

	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private UserToVoService userToVoService;
	
	@Autowired
	private CertificateService certificateService;
	
	@RenderMapping(params = "myaction=showAddUserForm")
	public String showAddUserForm(RenderRequest request, RenderResponse response) {
		log.debug("Show addUserForm.jsp");
		String[] array = {"l","o", "givenName", "sn", "uid", "mail", "persistent-id", "org-dn", "fromIDP"};
		List<String> attributes = Arrays.asList(array);
		
		UserInfo userInfo = new UserInfo();
		RegistrationModel registrationModel = new RegistrationModel();
		
		String o = "";
		String l = "";
		
		for (Enumeration<String> e = request.getParameterNames() ; e.hasMoreElements() ;) {
			String name = e.nextElement();
	        log.error(name);
	        
	        switch(attributes.indexOf(name)){
	        case 0:
//	        	l
	        	l=request.getParameter(name).replaceAll("%20", " ").toUpperCase();
	        	break;
	        case 1:
//	        	o
	        	o = request.getParameter(name).replaceAll("%20", " ").toUpperCase();
	        	break;
	        case 2:
//	        	givenName
	        	userInfo.setFirstName(request.getParameter(name).replaceAll("%20", " "));
	        	registrationModel.setFirstName(userInfo.getFirstName());
	        	break;
	        case 3:
//	        	sn
	        	userInfo.setLastName(request.getParameter(name).replaceAll("%20", " "));
	        	registrationModel.setLastName(userInfo.getLastName());
	        	break;
	        case 4:
//	        	uid
	        	userInfo.setUsername(request.getParameter(name).replaceAll("%20", " "));
	        	break;
	        case 5:
//	        	mail
	        	userInfo.setMail(request.getParameter(name).replaceAll("%20", " "));
	        	registrationModel.setEmail(userInfo.getMail());
	        	break;
	        case 6:
	        	userInfo.setUsername(request.getParameter(name).replaceAll("%20", " "));
//	        	persistent-id
	        	break;
	        case 7:
//	        	org-dn
	        	o= request.getParameter(name).replaceAll("%20", " ").replaceAll("dc.", "").replaceAll(",", " ").toUpperCase();
	        	break;
	        }
	        

	     }
		
		String institute = o + (((!o.isEmpty())&&(!l.isEmpty()))? " - " : "") + l;
		userInfo.setInstitute(institute);
		registrationModel.setInstitute(institute);
		
		
		
		log.error(registrationModel.toString());
		request.setAttribute("fromIDP", "true");
		request.setAttribute("userInfo", userInfo);
		
		
		if((!registrationModel.getFirstName().isEmpty())&&(!registrationModel.getLastName().isEmpty())&&(!registrationModel.getInstitute().isEmpty())&&(!registrationModel.getEmail().isEmpty())){
			registrationModel.setUserStatus(true);
			request.setAttribute("registrationModel", registrationModel);
			return addUser(userInfo, registrationModel, request, response);
		}
		
		request.setAttribute("registrationModel", new RegistrationModel());
		
		return "addUserForm";
	}
	
	@RenderMapping(params = "myaction=showAddUserFormNoIDP")
	public String showAddUserFormNoIDP(RenderRequest request, RenderResponse response) {
		log.error("Show addUserForm.jsp");
		
		UserInfo userInfo = new UserInfo();
		
		RegistrationModel registrationModel = CookieUtil.getCookie(request);
		log.error(registrationModel.toString());
		
		if(registrationModel.isHaveCertificate()){
			
			String[] dnParts = registrationModel.getSubject().split("/");
			String o = "";
			String l = "";
			String cn = "";
			
			for(String value: dnParts){
				if(value.contains("O="))
					o = value.replace("O=", "");
				if(value.contains("L="))
					l = value.replace("L=", "");
				if(value.contains("CN="))
					cn = value.replace("CN=", "");
			}
			
			String institute = o + (((!o.isEmpty())&&(!l.isEmpty()))? " - " : "") + l;
			userInfo.setInstitute(institute);
			
			String[] cnParts = cn.split(" ");
			String firstName = cnParts[0];
			
			String lastName = cnParts[1];
			
			for(int i=2; i < cnParts.length; i++ ){
				if(!cnParts[i].contains("@"))
					lastName += " " + cnParts[i];
			}
				
			String username = firstName + "." + lastName.trim();
			
			userInfo.setFirstName(firstName);
			userInfo.setLastName(lastName);
			userInfo.setUsername(username.toLowerCase());
			userInfo.setMail(registrationModel.getMail());
			
			registrationModel.setHaveIDP(false);
			CookieUtil.setCookie("haveIDP", Boolean.toString(registrationModel.isHaveIDP()), response);
			CookieUtil.setCookie(registrationModel, response);
			request.setAttribute("fromIDP", "false");
			request.setAttribute("userInfo", userInfo);
			
			return "addUserForm";
		}
		return "noRegistration";
	}
	
	@ActionMapping(params="myaction=addUser")
	public void addUser(@ModelAttribute UserInfo userInfo, ActionRequest request, ActionResponse response){
		
		RegistrationModel registrationModel = CookieUtil.getCookie(request);
		log.error(registrationModel.toString());
		
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
			boolean verify = registrationModel.getMail().isEmpty();
			log.error("Verify??? " + verify);
			RegistrationUtil.addUserToLiferay(request, userInfo, registrationModel, verify);
			
			//AddUser into DB
			userInfo=RegistrationUtil.addUserToDB(userInfo, userInfoService, notifyService);
			
			response.sendRedirect(RegistrationConfig.getProperties("Registration.properties", "login.url"));
			return;
		
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
	
private String addUser(UserInfo userInfo, RegistrationModel registrationModel, RenderRequest request, RenderResponse response){
		
		List<String> errors = new ArrayList<String>();
		
		//Validate user
		if(!MyValidator.validate(userInfo, errors)){
			
			for(String error: errors)
				SessionErrors.add(request, error);
			
			return "addUserForm";
		}
		
		try{
			
			RegistrationUtil.addUserToLiferay(request, userInfo, registrationModel, false);
			
			//AddUser into DB
			userInfo=RegistrationUtil.addUserToDB(userInfo, userInfoService, notifyService);
			
			return "askForCertificate";
		
		}catch(RegistrationException e){
			e.printStackTrace();
			SessionErrors.add(request, e.getMessage());
		}
		
		return "addUserForm";
	}
}
