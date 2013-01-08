package it.italiangrid.portal.registration.controller;

import java.io.IOException;
import java.net.URL;

import it.italiangrid.portal.dbapi.domain.UserInfo;
import it.italiangrid.portal.dbapi.services.UserInfoService;
import it.italiangrid.portal.dbapi.services.UserToVoService;
import it.italiangrid.portal.dbapi.services.VoService;
import it.italiangrid.portal.registration.exception.RegistrationException;
import it.italiangrid.portal.registration.model.RegistrationModel;
import it.italiangrid.portal.registration.util.RegistrationConfig;
import it.italiangrid.portal.registration.util.RegistrationUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
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
	
	@Autowired
	private UserToVoService userToVoService;
	
	@Autowired
	private UserInfoService userInfoService;
	
	@ActionMapping(params = "myaction=searchVo2")
	public void searchVo(@ModelAttribute RegistrationModel registrationModel, ActionRequest request , ActionResponse response){
		log.error(registrationModel.toString());
		
		response.setRenderParameter("myaction", "showAddVoForm");
		request.setAttribute("registrationModel", registrationModel);
	}
	
	@ActionMapping(params = "myaction=addVo")
	public void addVo(@ModelAttribute RegistrationModel registrationModel, ActionRequest request , ActionResponse response){
		log.error("myaction=addVo");
		log.error(registrationModel.toString());
		log.error(request.getParameter("voToAdd"));
		log.error(voService.findById(Integer.valueOf(request.getParameter("voToAdd"))).getHost());
		if(VOMSAdminCallOut.getUser(registrationModel.getSubject(), registrationModel.getIssuer(), voService.findById(Integer.valueOf(request.getParameter("voToAdd"))).getHost())){
			registrationModel.setVos(registrationModel.getVos().isEmpty()?request.getParameter("voToAdd"):registrationModel.getVos()+"#"+request.getParameter("voToAdd"));
			SessionMessages.add(request, "userToVo-adding-success");
		}else{
			PortletConfig portletConfig = (PortletConfig)request.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
			SessionMessages.add(request, portletConfig.getPortletName() + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
			SessionErrors.add(request, "no-user-found-in-VO");
		}
		registrationModel.setSearchVo(null);
		registrationModel.setVoStatus(true);
		log.error(registrationModel.toString());
		response.setRenderParameter("myaction", "showAddVoForm");
		request.setAttribute("registrationModel", registrationModel);
	}
	
	@ActionMapping(params = "myaction=delVo")
	public void delVo(@ModelAttribute RegistrationModel registrationModel, ActionRequest request , ActionResponse response){
		log.error("myaction=delVo");
		log.error(registrationModel.toString());
		log.error(request.getParameter("voToDel"));
		if(registrationModel.getVos().contains(request.getParameter("voToDel")+"#"))
			registrationModel.setVos(registrationModel.getVos().replace(request.getParameter("voToDel")+"#",""));
		if(registrationModel.getVos().contains(request.getParameter("voToDel")))
			registrationModel.setVos(registrationModel.getVos().replace(request.getParameter("voToDel"),""));
		if(registrationModel.getVos().isEmpty())
			registrationModel.setVoStatus(false);
		registrationModel.setSearchVo(null);
		SessionMessages.add(request, "userToVo-removed-success");
		log.error(registrationModel.toString());
		response.setRenderParameter("myaction", "showAddVoForm");
		request.setAttribute("registrationModel", registrationModel);
	}
	
	@ActionMapping(params = "myaction=goToAddUserForm")
	public void goToAddUserForm(@ModelAttribute RegistrationModel registrationModel, ActionRequest request , ActionResponse response){
		log.error("myaction=goToAddUserForm");
		
		log.error(registrationModel.toString());
		
		UserInfo userInfo = userInfoService.findByMail(registrationModel.getEmail());
		
		RegistrationUtil.associateVoToUser(userInfo, registrationModel, userToVoService);
		RegistrationUtil.activateUser(userInfo, userInfoService);
		
		try {
			URL url = new URL(RegistrationConfig.getProperties("Registration.properties", "login.url"));

			log.error(url);
			response.sendRedirect(url.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RegistrationException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("registrationModel", registrationModel);
	}

	
}
