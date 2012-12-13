package it.italiangrid.portal.registration.controller;

import java.io.IOException;
import java.net.URL;

import it.italiangrid.portal.dbapi.services.VoService;
import it.italiangrid.portal.registration.model.RegistrationModel;
import it.italiangrid.portal.registration.util.CookieUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.servlet.http.Cookie;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;

import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.JavaConstants;
import portal.registration.utils.VOMSAdminCallOut;

@Controller(value = "addVOActionController")
@RequestMapping(value = "VIEW")
public class AddVOActionController {

	
	private static final Logger log = Logger.getLogger(AddVOActionController.class);
	
	@Autowired
	private VoService voService;
	
	@ActionMapping(params = "myaction=searchVo2")
	public void searchVo(@ModelAttribute RegistrationModel registrationModel, ActionRequest request , ActionResponse response){
		log.error(registrationModel.toString());
		
		response.setRenderParameter("myaction", "showAddVoForm");
		request.setAttribute("registrationModel", registrationModel);
	}
	
	@ActionMapping(params = "myaction=addVo")
	public void addVo(@ModelAttribute RegistrationModel registrationModel, ActionRequest request , ActionResponse response){
		log.error(registrationModel.toString());
		log.error(request.getParameter("voToAdd"));
		log.error(voService.findById(Integer.valueOf(request.getParameter("voToAdd"))).getHost());
		if(VOMSAdminCallOut.getUser(registrationModel.getSubject(), registrationModel.getIssuer(), voService.findById(Integer.valueOf(request.getParameter("voToAdd"))).getHost())){
			registrationModel.setVos(registrationModel.getVos().isEmpty()?request.getParameter("voToAdd"):registrationModel.getVos()+";"+request.getParameter("voToAdd"));
			SessionMessages.add(request, "userToVo-adding-success");
		}else{
			PortletConfig portletConfig = (PortletConfig)request.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
			SessionMessages.add(request, portletConfig.getPortletName() + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
			SessionErrors.add(request, "no-user-found-in-VO");
		}
		registrationModel.setSearchVo(null);
		
		response.setRenderParameter("myaction", "showAddVoForm");
		request.setAttribute("registrationModel", registrationModel);
	}
	
	@ActionMapping(params = "myaction=delVo")
	public void delVo(@ModelAttribute RegistrationModel registrationModel, ActionRequest request , ActionResponse response){
		log.error(registrationModel.toString());
		log.error(request.getParameter("voToDel"));
		if(registrationModel.getVos().contains(request.getParameter("voToDel")+";"))
			registrationModel.setVos(registrationModel.getVos().replace(request.getParameter("voToDel")+";",""));
		if(registrationModel.getVos().contains(request.getParameter("voToDel")))
			registrationModel.setVos(registrationModel.getVos().replace(request.getParameter("voToDel"),""));
		
		registrationModel.setSearchVo(null);
		SessionMessages.add(request, "userToVo-removed-success");
		response.setRenderParameter("myaction", "showAddVoForm");
		request.setAttribute("registrationModel", registrationModel);
	}
	
	@ActionMapping(params = "myaction=goToAddUserForm")
	public void goToAddUserForm(@ModelAttribute RegistrationModel registrationModel, ActionRequest request , ActionResponse response){
		log.error(registrationModel.toString());
		
		//response.setRenderParameter("myaction", "getShibbolethHeader");
		request.setAttribute("registrationModel", registrationModel);
		
		CookieUtil.setCookie(registrationModel, response);
		
		try {
			URL url = new URL("https://halfback.cnaf.infn.it/app1/index.jsp");
			
			log.error(url);
			response.sendRedirect(url.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		CookieUtil.setCookie("haveCertificate", Boolean.toString(registrationModel.isHaveCertificate()), response);
//		CookieUtil.setCookie("issuer",  registrationModel.getIssuer(), response);
//		CookieUtil.setCookie("subject",  registrationModel.getSubject(), response);
//		CookieUtil.setCookie("certificateUserId",  registrationModel.getCertificateUserId(), response);
//		CookieUtil.setCookie("vos",  registrationModel.getVos(), response);
//		CookieUtil.setCookie("searchVo", registrationModel.getSearchVo(), response);
	}

	
}
