package portal.registration.controller;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.RenderResponse;

import org.apache.log4j.Logger;

import it.italiangrid.portal.dbapi.domain.Certificate;
import it.italiangrid.portal.dbapi.domain.UserInfo;
import it.italiangrid.portal.dbapi.domain.UserToVo;
import it.italiangrid.portal.dbapi.domain.Vo;
import it.italiangrid.portal.dbapi.services.CertificateService;
import it.italiangrid.portal.dbapi.services.UserInfoService;
import it.italiangrid.portal.dbapi.services.UserToVoService;
import it.italiangrid.portal.dbapi.services.VoService;
import it.italiangrid.portal.registration.exception.RegistrationException;
import it.italiangrid.portal.registration.util.RegistrationConfig;
import portal.registration.utils.SendMail;
import portal.registration.utils.VOMSAdminCallOut;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

@Controller(value = "addUserToVOController")
@RequestMapping(value = "VIEW")
public class AddUserToVOController {

	private static final Logger log = Logger
			.getLogger(AddUserToVOController.class);

	private static String search = null;

	public static void setSearch(String search2) {
		search = search2;
	}

	@Autowired
	private CertificateService certificateService;

	@Autowired
	private VoService voService;

	@Autowired
	private UserToVoService userToVoService;

	@Autowired
	private UserInfoService userInfoService;

	@ModelAttribute("userInfo")
	public UserInfo getCommandObject() {
		return new UserInfo();
	}

	@RenderMapping(params = "myaction=showAddUserToVO")
	public String showUserInfoForm(RenderResponse response) {
		return "addUserToVOForm";
	}

	@ActionMapping(params = "myaction=addUserToVO")
	public void addUserToVo(ActionRequest request, ActionResponse response) throws PortalException,
			SystemException {

		log.info("sono dentro");

		ArrayList<String> errors = new ArrayList<String>();
		boolean allOk = true;

		int userId = Integer.parseInt(request.getParameter("userId"));
		log.info("Valore passato userId " + userId);

		if (request.getParameterValues("VOids") != null) {
			log.info("recupero idVo");
			int idVo = Integer.parseInt(request.getParameter("VOids"));
			if (idVo != 0) {
				String subject = null;
				if ((subject = checkVO(idVo, userId, errors)) != null) {
					log.info("Salvo sul DB la Vo " + idVo);
					UserInfo ui = userInfoService.findById(userId);
					if (ui.getRegistrationComplete().equals("false")) {
						activateUser(ui, request, errors);
					}
					userToVoService.save(userId, idVo, subject);
					List<UserToVo> utvs = userToVoService.findById(userId);
					if (utvs.size() == 1)
						;
					userToVoService.setDefault(userId, idVo);
					log.info("Salvato sul DB ");
					Vo vo = voService.findById(idVo);
					if(vo.getConfigured().equals("false")){
						
						UserInfo userInfo = userInfoService.findById(userId);
						SessionMessages.add(request, "vo-not-configurated");
						
						try {
							SendMail sm = new SendMail(userInfo.getMail(), RegistrationConfig.getProperties("Registration.properties", "igiportal.mail"), "Please configure "+ vo.getVo(), RegistrationConfig.getProperties("Registration.properties", "request.configre.vo").replaceAll("##VO##", vo.getVo()).replaceAll("##NL##", "\n").replaceAll("##USER##", userInfo.getFirstName()+" "+userInfo.getLastName()).replaceAll("##HOST##", RegistrationConfig.getProperties("Registration.properties", "home.url")));
							sm.send();
							log.error(sm.toString());
						} catch (RegistrationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					
				} else {
					allOk = false;
				}
			}

		} else {
			allOk = false;
			errors.add("user-vo-list-empty");
		}

		if (allOk) {

			SessionMessages.add(request, "userToVo-adding-success");

			if (request.getParameter("firstReg").equals("true")) {
				response.setRenderParameter("myaction",
						"showAddUserToVoPresents");
			} else {
				response.setRenderParameter("myaction", "editUserInfoForm");
			}
			response.setRenderParameter("userId", Integer.toString(userId));
			request.setAttribute("userId", userId);

			AddUserToVOController.setSearch("");

		} else {

			errors.add("error-saving-registration");

			for (String error : errors) {
				log.info("Errore: " + error);
				SessionErrors.add(request, error);
			}

			PortletConfig portletConfig = (PortletConfig)request.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
			SessionMessages.add(request, portletConfig.getPortletName() + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
			
			log.info("Errori in userToVO");

			if (request.getParameter("firstReg").equals("true")) {
				response.setRenderParameter("myaction",
						"showAddUserToVoPresents");
			} else {
				response.setRenderParameter("myaction", "editUserInfoForm");
			}
			response.setRenderParameter("userId", Integer.toString(userId));
			request.setAttribute("userId", userId);

		}

	}
	
	@ActionMapping(params = "myaction=searchVo3")
	public void searchVo(ActionRequest request, ActionResponse response) throws PortalException,
			SystemException {

		log.error("sono dentro");

		ArrayList<String> errors = new ArrayList<String>();
		boolean allOk = true;
		
		Vo vo = voService.findByName(request.getParameter("tags"));

		int userId = Integer.parseInt(request.getParameter("userId"));
		log.error("Valore passato userId " + userId);

		if (vo != null) {
			log.error("recupero idVo");
			int idVo = vo.getIdVo();
			if (idVo != 0) {
				String subject = null;
				if ((subject = checkVO(idVo, userId, errors)) != null) {
					log.error("Salvo sul DB la Vo " + idVo);
					UserInfo ui = userInfoService.findById(userId);
					if (ui.getRegistrationComplete().equals("false")) {
						activateUser(ui, request, errors);
					}
					userToVoService.save(userId, idVo, subject);
					
					log.error("Salvato sul DB ");
					
					if(vo.getConfigured().equals("false")){
						
						UserInfo userInfo = userInfoService.findById(userId);
						SessionMessages.add(request, "vo-not-configurated");
						
						try {
							SendMail sm = new SendMail(userInfo.getMail(), RegistrationConfig.getProperties("Registration.properties", "igiportal.mail"), "Please configure "+ vo.getVo(), RegistrationConfig.getProperties("Registration.properties", "request.configre.vo").replaceAll("##VO##", vo.getVo()).replaceAll("##NL##", "\n").replaceAll("##USER##", userInfo.getFirstName()+" "+userInfo.getLastName()).replaceAll("##HOST##", RegistrationConfig.getProperties("Registration.properties", "home.url")));
							sm.send();
							log.error(sm.toString());
						} catch (RegistrationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}else{
						String defaultVo = userToVoService.findDefaultVo(userId);
						if (defaultVo==null)
								userToVoService.setDefault(userId, idVo);
					}
				} else {
					allOk = false;
				}
			}

		} else {
			allOk = false;
			errors.add("user-vo-list-empty");
		}

		if (allOk) {

			SessionMessages.add(request, "userToVo-adding-success");
			response.setRenderParameter("myaction", "editUserInfoForm");
			response.setRenderParameter("userId", Integer.toString(userId));
			request.setAttribute("userId", userId);

		} else {

			errors.add("error-saving-registration");

			for (String error : errors) {
				log.error("Errore: " + error);
				SessionErrors.add(request, error);
			}
			
			PortletConfig portletConfig = (PortletConfig)request.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
			SessionMessages.add(request, portletConfig.getPortletName() + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);

			log.error("Errori in userToVO");
			response.setRenderParameter("myaction", "editUserInfoForm");
			response.setRenderParameter("userId", Integer.toString(userId));
			request.setAttribute("userId", userId);

		}

	}

	private void activateUser(UserInfo userInfo, ActionRequest request,
			ArrayList<String> errors) {

		String username = userInfo.getUsername();

		long companyId = PortalUtil.getCompanyId(request);

		User user;
		try {
			user = UserLocalServiceUtil
					.getUserByScreenName(companyId, username);

			Role rolePowerUser = RoleLocalServiceUtil.getRole(companyId,
					"Power User");

			List<User> powerUsers = UserLocalServiceUtil
					.getRoleUsers(rolePowerUser.getRoleId());

			long users[] = new long[powerUsers.size() + 1];

			int i;

			for (i = 0; i < powerUsers.size(); i++) {
				users[i] = powerUsers.get(i).getUserId();
			}

			users[i] = user.getUserId();
			// long[] roles = {10140};

			// RoleServiceUtil.addUserRoles(user.getUserId(), roles);

			UserLocalServiceUtil.setRoleUsers(rolePowerUser.getRoleId(), users);

			userInfo.setRegistrationComplete("true");

			userInfoService.edit(userInfo);

			SessionMessages.add(request, "user-activate");

		} catch (PortalException e) {
			errors.add("exception-activation-user");
			e.printStackTrace();
		} catch (SystemException e) {
			errors.add("exception-activation-user");
			e.printStackTrace();
		}

	}

	@ModelAttribute("vos")
	public List<Vo> getVos() {
		if (search == null || search.equals(""))
			return voService.getAllVo();
		else
			return voService.getAllVoByName(search);
	}

	@ModelAttribute("disciplines")
	public List<String> getDisciplines() {
		return voService.getAllDiscplines();
	}

	private String checkVO(int idVo, int userId, List<String> errors) {
		String result = null;

		Vo vo = voService.findById(idVo);

		List<Certificate> certs = certificateService.findById(userId);

		if (certs.size() == 0) {
			errors.add("no-cert-for-user");
			return null;
		}

		if (userToVoService.findById(userId, idVo) != null) {
			errors.add("userToVo-already-exists");
			return null;
		}

		for (int i = 0; i < certs.size(); i++) {
			if (VOMSAdminCallOut.getUser(certs.get(i).getSubject(), certs
					.get(i).getIssuer(), vo.getHost())) {
				result = certs.get(i).getSubject();
			}
		}

		if (result == null)
			errors.add("no-user-found-in-VO");

		return result;
	}

	@ActionMapping(params = "myaction=setDefaultUserToVo")
	public void setDefaultUserToVo(ActionRequest request,
			ActionResponse response) {

		int userId = Integer.parseInt(request.getParameter("userId"));
		int idVo = Integer.parseInt(request.getParameter("idVo"));

		try {
			log.info("Sto per settarea default il userToVo con userId = "
					+ userId + "e idVo = " + idVo);
			if (userToVoService.setDefault(userId, idVo))
				SessionMessages.add(request, "userToVo-updated-successufully");
			else
				SessionErrors.add(request, "error-default-userToVo");
			log.info("UserToVoSettato");
			userToVoService.findVoByUserId(userId);

		} catch (Exception e) {
			SessionErrors.add(request, "error-updating-certificate");
		}

		response.setRenderParameter("myaction", "showAddUserToVoPresents");
		response.setRenderParameter("userId", Integer.toString(userId));
		request.setAttribute("firstReg", request.getParameter("firstReg"));

	}

	@ActionMapping(params = "myaction=removeUserToVo")
	public void removeUserToVo(ActionRequest request, ActionResponse response) {

		int userId = Integer.parseInt(request.getParameter("userId"));
		int idVo = Integer.parseInt(request.getParameter("idVo"));

		try {
			log.info("Sto per cancellare il userToVo con userId = " + userId
					+ "e idVo = " + idVo);
			userToVoService.delete(userId, idVo);
			log.info("userTo Vo cancellato");

			SessionMessages.add(request, "userToVo-deleted-successufully");

		} catch (Exception e) {
			SessionErrors.add(request, "error-deleting-userToVo");
		}

		response.setRenderParameter("myaction", "showAddUserToVoPresents");
		response.setRenderParameter("userId", Integer.toString(userId));
		request.setAttribute("firstReg", request.getParameter("firstReg"));

	}

	@ActionMapping(params = "myaction=setDefaultUserToVoEdit")
	public void setDefaultUserToVoEdit(ActionRequest request,
			ActionResponse response) {

		int userId = Integer.parseInt(request.getParameter("userId"));
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

		response.setRenderParameter("myaction", "editUserInfoForm");
		response.setRenderParameter("userId", Integer.toString(userId));
		request.setAttribute("firstReg", request.getParameter("firstReg"));
		request.setAttribute("userId", userId);

	}

	@ActionMapping(params = "myaction=removeUserToVoEdit")
	public void removeUserToVoEdit(ActionRequest request,
			ActionResponse response) {

		int userId = Integer.parseInt(request.getParameter("userId"));
		int idVo = Integer.parseInt(request.getParameter("idVo"));

		try {
			
			
			
			log.info("Sto per cancellare il userToVo con userId = " + userId
					+ "e idVo = " + idVo);
			userToVoService.delete(userId, idVo);
			log.info("userTo Vo cancellato");
			
			String defaultVo = userToVoService.findDefaultVo(userId);
			if(defaultVo==null){
				List<Vo> vos = userToVoService.findVoByUserId(userId);
				if(vos!=null)
					for(Vo vo: vos){
						log.error(vo.getVo()+" "+vo.getConfigured() + " " +vo.getConfigured().equals("true"));
						if(vo.getConfigured().equals("true")){
							userToVoService.setDefault(userId, vo.getIdVo());
							break;
						}
					}
				
			}

			SessionMessages.add(request, "userToVo-deleted-successufully");
			userToVoService.findVoByUserId(userId);

		} catch (Exception e) {
			e.printStackTrace();
			PortletConfig portletConfig = (PortletConfig)request.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
			SessionMessages.add(request, portletConfig.getPortletName() + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
			SessionErrors.add(request, "error-deleting-userToVo");
		}

		response.setRenderParameter("myaction", "editUserInfoForm");
		response.setRenderParameter("userId", Integer.toString(userId));
		request.setAttribute("userId", userId);
		request.setAttribute("firstReg", request.getParameter("firstReg"));

	}

	@ModelAttribute("searchUserToVo")
	public String getSearch() {

		return search;

	}
	

}
