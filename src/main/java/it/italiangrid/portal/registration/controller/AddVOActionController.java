package it.italiangrid.portal.registration.controller;

import java.io.IOException;
import java.net.URL;

import it.italiangrid.portal.dbapi.domain.UserInfo;
import it.italiangrid.portal.dbapi.domain.Vo;
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
		
		registrationModel.setSearchVo((String) request.getParameter("tags"));
		
		log.error(registrationModel.toString());
		
		Vo vo = voService.findByName(registrationModel.getSearchVo());
		
		if(vo!=null){
			if(VOMSAdminCallOut.getUser(registrationModel.getSubject(), registrationModel.getIssuer(), vo.getHost())){
				registrationModel.setVos(registrationModel.getVos().isEmpty()?Integer.toString(vo.getIdVo()):registrationModel.getVos()+"#"+Integer.toString(vo.getIdVo()));
				SessionMessages.add(request, "userToVo-adding-success");
				registrationModel.setVoStatus(true);
				UserInfo userInfo = userInfoService.findByMail(registrationModel.getEmail());
				userToVoService.save(userInfo.getUserId(), vo.getIdVo(),registrationModel.getSubject());
				userToVoService.setDefault(userInfo.getUserId(), vo.getIdVo());
			}else{
				PortletConfig portletConfig = (PortletConfig)request.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
				SessionMessages.add(request, portletConfig.getPortletName() + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
				SessionErrors.add(request, "no-user-found-in-VO");
			}
		}else{
			PortletConfig portletConfig = (PortletConfig)request.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
			SessionMessages.add(request, portletConfig.getPortletName() + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
			SessionErrors.add(request, "no-VO-found");
		}
		
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
		UserInfo userInfo = userInfoService.findByMail(registrationModel.getEmail());
		userToVoService.delete(userInfo.getUserId(), Integer.parseInt(request.getParameter("voToDel")));
		
		if(!userToVoService.findById(userInfo.getUserId()).isEmpty()){
			userToVoService.setDefault(userInfo.getUserId(), userToVoService.findById(userInfo.getUserId()).get(0).getId().getIdVo());
		}
		
		if(registrationModel.getVos().contains(request.getParameter("voToDel")+"#"))
			registrationModel.setVos(registrationModel.getVos().replace(request.getParameter("voToDel")+"#",""));
		if(registrationModel.getVos().contains(request.getParameter("voToDel")))
			registrationModel.setVos(registrationModel.getVos().replace(request.getParameter("voToDel"),""));
		if(registrationModel.getVos().endsWith("#"))
			registrationModel.setVos(registrationModel.getVos().substring(0, registrationModel.getVos().length() - 1));
		if(registrationModel.getVos().isEmpty())
			registrationModel.setVoStatus(false);
		registrationModel.setSearchVo(null);
		SessionMessages.add(request, "userToVo-removed-success");
		log.error(registrationModel.toString());
		response.setRenderParameter("myaction", "showAddVoForm");
		request.setAttribute("registrationModel", registrationModel);
	}
	
	@ActionMapping(params = "myaction=deleteVo")
	public void deleteVo(ActionRequest request , ActionResponse response){
		log.error("myaction=delVo");
		log.error(request.getParameter("idVo"));
		
		RegistrationModel registrationModel = getRegistrationModelFromRequest(request);
		
		log.error(registrationModel);
		
		UserInfo userInfo = userInfoService.findByMail(registrationModel.getEmail());
		userToVoService.delete(userInfo.getUserId(), Integer.parseInt(request.getParameter("idVo")));
		
		if(!userToVoService.findById(userInfo.getUserId()).isEmpty()){
			userToVoService.setDefault(userInfo.getUserId(), userToVoService.findById(userInfo.getUserId()).get(0).getId().getIdVo());
		}
		
		if(registrationModel.getVos().contains(request.getParameter("idVo")+"#"))
			registrationModel.setVos(registrationModel.getVos().replace(request.getParameter("idVo")+"#",""));
		if(registrationModel.getVos().contains(request.getParameter("idVo")))
			registrationModel.setVos(registrationModel.getVos().replace(request.getParameter("idVo"),""));
		if(registrationModel.getVos().endsWith("#"))
			registrationModel.setVos(registrationModel.getVos().substring(0, registrationModel.getVos().length() - 1));
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
		
//		RegistrationUtil.associateVoToUser(userInfo, registrationModel, userToVoService);
		
		if(!registrationModel.getVos().isEmpty())
			RegistrationUtil.activateUser(userInfo, userInfoService);
		
		try {
			URL url;
			if(registrationModel.isVerifyUser()){
				log.error(RegistrationConfig.getProperties("Registration.properties", "home.url"));
				url = new URL(RegistrationConfig.getProperties("Registration.properties", "home.url"));
			}else{
				log.error(RegistrationConfig.getProperties("Registration.properties", "login.url"));
				url = new URL(RegistrationConfig.getProperties("Registration.properties", "login.url"));
			}
			log.error(url);
			response.sendRedirect(url.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RegistrationException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("registrationModel", registrationModel);
	}
	
	
	@ActionMapping(params = "myaction=setDefaultVo")
	public void setDefaultUserToVoEdit(ActionRequest request,
			ActionResponse response) {
		
		RegistrationModel registrationModel = getRegistrationModelFromRequest(request);
		UserInfo userInfo = userInfoService.findByMail(registrationModel.getEmail());
		
		int userId = userInfo.getUserId();
		int idVo = Integer.parseInt(request.getParameter("idVo"));

		try {
			log.info("Sto per settare default il userToVo con userId = "
					+ userId + "e idVo = " + idVo);
			if (userToVoService.setDefault(userId, idVo))
				SessionMessages.add(request, "userToVo-default-successufully");
			else
				SessionErrors.add(request, "error-default-userToVo");
			log.info("UserToVoSettato");
			userToVoService.findVoByUserId(userId);

		} catch (Exception e) {
			SessionErrors.add(request, "error-updating-certificate");
		}

		response.setRenderParameter("myaction", "showAddVoForm");
		request.setAttribute("registrationModel", registrationModel);

	}

	private RegistrationModel getRegistrationModelFromRequest(
			ActionRequest request) {
		RegistrationModel result = new RegistrationModel();
		
		result.setCertificateStatus(Boolean.parseBoolean(request.getParameter("certificateStatus")));
		result.setCertificateUserId(request.getParameter("certificateUserId"));
		result.setEmail(request.getParameter("email"));
		result.setExpiration(request.getParameter("expiration"));
		result.setFirstName(request.getParameter("firstName"));
		result.setHaveCertificate(Boolean.parseBoolean(request.getParameter("haveCertificate")));
		result.setHaveIDP(Boolean.parseBoolean(request.getParameter("haveIDP")));
		result.setInstitute(request.getParameter("institute"));
		result.setIssuer(request.getParameter("issuer"));
		result.setLastName(request.getParameter("lastName"));
		result.setMail(request.getParameter("mail"));
		result.setSearchVo(request.getParameter("searchVo"));
		result.setSubject(request.getParameter("subject"));
		result.setUserStatus(Boolean.parseBoolean(request.getParameter("userStatus")));
		result.setVerifyUser(Boolean.parseBoolean(request.getParameter("verifyUser")));
		result.setVos(request.getParameter("vos"));
		result.setVoStatus(Boolean.parseBoolean(request.getParameter("voStatus")));
		
		return result;
	}
	
}
