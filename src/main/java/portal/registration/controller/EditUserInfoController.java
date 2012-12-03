package portal.registration.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

import it.italiangrid.portal.dbapi.domain.Notify;
import it.italiangrid.portal.dbapi.domain.UserToVo;
import it.italiangrid.portal.dbapi.domain.UserInfo;
import it.italiangrid.portal.dbapi.domain.Certificate;
import it.italiangrid.portal.dbapi.domain.Vo;
import it.italiangrid.portal.dbapi.services.CertificateService;
import it.italiangrid.portal.dbapi.services.NotifyService;
import it.italiangrid.portal.dbapi.services.UserInfoService;
import it.italiangrid.portal.dbapi.services.UserToVoService;
import portal.registration.utils.GuseNotify;
import portal.registration.utils.GuseNotifyUtil;
import portal.registration.utils.MyValidator;

@Controller
@RequestMapping("view")
@SessionAttributes("userInfo")
public class EditUserInfoController {

	private static final Logger log = Logger
			.getLogger(EditUserInfoController.class);

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private NotifyService notifyService;

	@Autowired
	private CertificateService certificateService;

	@Autowired
	private UserToVoService userToVoService;

	@RenderMapping(params = "myaction=editUserInfoForm")
	public String showEditUserInfoForm(@RequestParam int userId,
			RenderRequest request) {

		if (userToVoService.findById(userId).size() == 0) {
			deactivateUser(userId, request);

		}

		return "editUserInfoForm";
	}

	private void deactivateUser(int userId, RenderRequest request) {

		UserInfo userInfo = userInfoService.findById(userId);

		if (userInfo.getRegistrationComplete().equals("true")) {
			String username = userInfo.getUsername();

			long companyId = PortalUtil.getCompanyId(request);

			User user;
			try {
				user = UserLocalServiceUtil.getUserByScreenName(companyId,
						username);

				Role rolePowerUser = RoleLocalServiceUtil.getRole(companyId,
						"Power User");

				UserLocalServiceUtil.deleteRoleUser(rolePowerUser.getRoleId(),
						user.getUserId());

				userInfo.setRegistrationComplete("false");

				userInfoService.edit(userInfo);

			} catch (PortalException e) {
				SessionErrors.add(request, "exception-deactivation-user");
				e.printStackTrace();
			} catch (SystemException e) {
				SessionErrors.add(request, "exception-deactivation-user");
				e.printStackTrace();
			}
			SessionMessages.add(request, "user-deactivate");
		}

	}

	@ActionMapping(params = "myaction=editUserInfo")
	public void editUserInfo(@ModelAttribute("userInfo") UserInfo userInfo,
			ActionRequest request, ActionResponse response,
			SessionStatus sessionStatus) throws SystemException {

		ArrayList<String> errors = new ArrayList<String>();
		boolean allOk = true;
		UserInfo check = userInfoService.findById(userInfo.getUserId());
		if (MyValidator.validateUpdate(userInfo, check, errors)) {

			try {
				log.info("prendo companyid");
				long companyId = PortalUtil.getCompanyId(request);
				log.info("companyid = " + companyId);
				log.info("prendo user con username = " + userInfo.getUsername());
				User user = UserLocalServiceUtil.getUserByScreenName(companyId,
						userInfo.getUsername());

				log.info("setto: nome = " + userInfo.getFirstName()
						+ " ; Cognome = " + userInfo.getLastName());

				user.setFirstName(userInfo.getFirstName());
				user.setLastName(userInfo.getLastName());

				log.info("controllo: nome = " + user.getFirstName()
						+ " ; Cognome = " + user.getLastName());

				UserLocalServiceUtil.updateUser(user);

				log.info("lifery aggiornato");

			} catch (Exception e) {
				log.info("problema update user liferay");
				errors.add("problem-update-user-liferay");
				allOk = false;
				e.printStackTrace();
			}
			if (allOk) {
				try {
					userInfoService.edit(userInfo);
				} catch (Exception e) {
					errors.add("problem-update-user");
					allOk = false;
				}
			}
		} else {
			allOk = false;
		}
		if (allOk) {
			SessionMessages.add(request, "user-updated-successufully");
		} else {
			errors.add("error-updating-user");

			for (String error : errors) {
				SessionErrors.add(request, error);
			}
		}

		response.setRenderParameter("myaction", "editUserInfoForm");
		response.setRenderParameter("userId",
				Integer.toString(userInfo.getUserId()));
		sessionStatus.setComplete();

	}

	@ModelAttribute("defaultVo")
	public String getDefaultVo(@RequestParam int userId) {
		return userToVoService.findDefaultVo(userId);
	}

	@ModelAttribute("defaultFqan")
	public String getDefaultFqan(@RequestParam int userId) {
		return userToVoService.getDefaultFqan(userId);
	}

	@ModelAttribute("userInfo")
	public UserInfo getUserInfo(@RequestParam int userId) {
		return userInfoService.findById(userId);
	}

	@ModelAttribute("certList")
	public List<Certificate> getListCert(@RequestParam int userId) {
		return certificateService.findById(userId);
	}

	@ModelAttribute("userToVoList")
	public List<Vo> getUserToVoList(@RequestParam int userId) {

		return userToVoService.findVoByUserId(userId);
	}

	/**
	 * Return to the portlet the list of the user's fqans.
	 * 
	 * @param userId
	 *            : the identifier of the user.
	 * @return the list of the user's fqans.
	 */
	@ModelAttribute("userFqans")
	public Map<Object, Object> getUserFqans(@RequestParam int userId) {

		UserInfo userInfo = userInfoService.findById(userId);
		List<UserToVo> utv = userToVoService.findById(userInfo.getUserId());

		Map<Object, Object> x = new Properties();

		String toParse = null;

		for (Iterator<UserToVo> iterator = utv.iterator(); iterator.hasNext();) {
			UserToVo userToVo = iterator.next();
			toParse = userToVo.getFqans();

			if ((toParse != null) && (!toParse.equals(""))) {

				x.put(userToVo.getId().getIdVo(), toParse);

			} else {
				x.put(userToVo.getId().getIdVo(), "No Roles for this VO");
			}

		}

		return x;
	}

	/**
	 * Return to the portlet true if the user certificate was released by the CA
	 * online.
	 * 
	 * @param userId
	 *            : the identifier of the user.
	 * @return true if the user certificate was released by the CA online.
	 */
	@ModelAttribute("certCAonline")
	public boolean getCertCAonline(@RequestParam int userId) {

		UserInfo userInfo = userInfoService.findById(userId);
		List<Certificate> certs = certificateService.findById(userInfo
				.getUserId());

		for (Iterator<Certificate> iterator = certs.iterator(); iterator
				.hasNext();) {
			Certificate cert = iterator.next();
			if (cert.getCaonline().equals("true"))
				return true;

		}

		return false;
	}

	/**
	 * Return to the portlet the advanced configurations of the user.
	 * 
	 * @param userId
	 *            : the identifier of the user.
	 * @return an object that contain the value of the advanced configurations.
	 */
	@ModelAttribute("advOpts")
	public Notify getAdvOpts(@RequestParam int userId) {

		UserInfo userInfo = userInfoService.findById(userId);
		
		if(notifyService.findByUserInfo(userInfo)==null)
			notifyService.save(new Notify(userInfo, "false"));

		return notifyService.findByUserInfo(userInfo);
	}


	@ActionMapping(params = "myaction=uploadComplete")
	public void uploadComplete(ActionResponse response,
			SessionStatus sessionStatus) {

		response.setRenderParameter("myaction", "userInfos");
		sessionStatus.setComplete();

	}
	
	/**
	 * Update the user's row of the table notification.
	 * 
	 * @param notify
	 *            : the object that contain the advanced options
	 * @param request
	 *            : the request of the portlet
	 * @param response
	 *            : the response of the portlet
	 * @param sessionStatus
	 *            : the status of the portlet
	 */
	@ActionMapping(params = "myaction=updateGuseNotify")
	public void updateGuseNotify(@ModelAttribute("notification") GuseNotify guseNotify,
			ActionRequest request, ActionResponse response,
			SessionStatus sessionStatus) {
		
		UserInfo userInfo = userInfoService.findById(Integer.valueOf(request.getParameter("userId")));
		userInfoService.save(userInfo);
		String username = userInfo.getUsername();
		long companyId = PortalUtil.getCompanyId(request);
		
		try {
			User user = UserLocalServiceUtil.getUserByScreenName(companyId,
					username);
			
			GuseNotifyUtil guseNotifyUtil = new GuseNotifyUtil();
			
			guseNotify.setWfchgEnab((request.getParameter("wfchgEnab").equals("true")?"1":"0"));
			guseNotify.setEmailEnab((request.getParameter("wfchgEnab").equals("true")?"1":"0"));
			guseNotify.setQuotaEnab((request.getParameter("quotaEnab").equals("true")?"1":"0"));
			
			guseNotifyUtil.writeNotifyXML(user, guseNotify);
			
			
		} catch (PortalException e) {
			e.printStackTrace();
		} catch (SystemException e) {
			e.printStackTrace();
		}	
		response.setRenderParameter("myaction", "editUserInfoForm");
		response.setRenderParameter("userId", request.getParameter("userId"));
		sessionStatus.setComplete();

	}
	
	/**
	 * Return to the portlet the advanced configurations of the user.
	 * 
	 * @param request
	 *            : session parameter.
	 * @return an object that contain the value of the advanced configurations.
	 */
	@ModelAttribute("notification")
	public GuseNotify getGuseNotifications(@RequestParam int userId) {
		
		UserInfo userInfo = userInfoService.findById(userId);
		String username = userInfo.getUsername();
		long companyId = PortalUtil.getPortal().getCompanyIds()[0];
		
		log.debug("companyId " + companyId);
		
		GuseNotify guseNotify=null;
		
		try {
			User user = UserLocalServiceUtil.getUserByScreenName(companyId,
					username);
			GuseNotifyUtil guseNotifyUtil = new GuseNotifyUtil();
			
			guseNotify = guseNotifyUtil.readNotifyXML(user.getUserId());
		} catch (PortalException e) {
			e.printStackTrace();
		} catch (SystemException e) {
			e.printStackTrace();
		}
		
		
		return guseNotify;
	}
	
	/**
	 * Update the user's row of the table notification.
	 * 
	 * @param notify
	 *            : the object that contain the advanced options
	 * @param request
	 *            : the request of the portlet
	 * @param response
	 *            : the response of the portlet
	 * @param sessionStatus
	 *            : the status of the portlet
	 */
	@ActionMapping(params = "myaction=updateAdvOpts")
	public void updateAdvOpts(@ModelAttribute("advOpts") Notify notify,
			ActionRequest request, ActionResponse response,
			SessionStatus sessionStatus) {
		UserInfo userInfo = userInfoService.findById(Integer.parseInt(request.getParameter("userId")));
		Notify n = notifyService.findByUserInfo(userInfo);
		if(n!=null){
			
			log.debug("session id= " + notify.getIdNotify() + " retrived: "
					+ n.getIdNotify());
			n.setProxyExpire(notify.getProxyExpire());
			log.debug("session value= " + notify.getProxyExpire() + " retrived: "
					+ n.getProxyExpire());
		}else{
			log.debug("New entry");
			n = new Notify(userInfo, notify.getProxyExpire());
			log.debug("session value= " + notify.getProxyExpire() + " retrived: "
					+ n.getProxyExpire());
		}
		notifyService.save(n);
		
		response.setRenderParameter("myaction", "editUserInfoForm");
		response.setRenderParameter("userId", request.getParameter("userId"));

	}

}
