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

//import portal.registration.domain.UserToVo;
//import portal.registration.domain.UserInfo;
//import portal.registration.domain.Certificate;
//import portal.registration.domain.Vo;
//import portal.registration.services.CertificateService;
//import portal.registration.services.UserInfoService;
//import portal.registration.services.UserToVoService;
import it.italiangrid.portal.dbapi.domain.UserToVo;
import it.italiangrid.portal.dbapi.domain.UserInfo;
import it.italiangrid.portal.dbapi.domain.Certificate;
import it.italiangrid.portal.dbapi.domain.Vo;
import it.italiangrid.portal.dbapi.services.CertificateService;
import it.italiangrid.portal.dbapi.services.UserInfoService;
import it.italiangrid.portal.dbapi.services.UserToVoService;
import portal.registration.utils.MyValidator;

@Controller
@RequestMapping("view")
@SessionAttributes("userInfo")
public class EditUserInfoController {
	
	private static final Logger log = Logger
	.getLogger(AddUserInfoController.class);

	@Autowired
	private UserInfoService userInfoService;

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

				Role rolePowerUser = RoleLocalServiceUtil.getRole(companyId, "Power User");

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
				
				log.info("setto: nome = " + userInfo.getFirstName() + " ; Cognome = " + userInfo.getLastName());
				
				user.setFirstName(userInfo.getFirstName());
				user.setLastName(userInfo.getLastName());
				
				log.info("controllo: nome = " + user.getFirstName() + " ; Cognome = " + user.getLastName());

				UserLocalServiceUtil.updateUser(user);
				
				
				log.info("lifery aggiornato");

			} catch (Exception e) {
				log.info("problema update user liferay");
				errors.add("problem-update-user-liferay");
				allOk = false;
				e.printStackTrace();
			}
			if(allOk){
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

	@ActionMapping(params = "myaction=uploadComplete")
	public void uploadComplete(ActionResponse response,
			SessionStatus sessionStatus) {

		response.setRenderParameter("myaction", "userInfos");
		sessionStatus.setComplete();

	}
	
	/**
	 * Return to the portlet the list of the user's fqans.
	 * @param request: session parameter.
	 * @return the list of the user's fqans.
	 */
	@ModelAttribute("userFqans")
	public Map<Object,Object> getUserFqans(@RequestParam int userId) {
		
		UserInfo userInfo = userInfoService.findById(userId);
		List<UserToVo> utv = userToVoService.findById(userInfo.getUserId());
		
		Map<Object, Object> x = new Properties();
		
		String toParse = null;
		
		for (Iterator<UserToVo> iterator = utv.iterator(); iterator.hasNext();) {
			UserToVo userToVo = iterator.next();
			toParse = userToVo.getFqans();
			if(toParse != null){
				x.put(userToVo.getId().getIdVo(), toParse);
				
			}else{
				x.put(userToVo.getId().getIdVo(), "No Roles for this VO");
			}
			
		}
		
		return x;
	}

}