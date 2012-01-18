package portal.registration.controller;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ActionMapping;

import portal.registration.domain.UserInfo;
import portal.registration.services.UserInfoService;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

/**
 * RemoveUserInfoController handles removal of UserInfo from the Catalog.
 * 
 * @author asarin
 *
 */
@Controller
@RequestMapping("VIEW")
public class RemoveUserInfoController {
	
	private static final Logger log = Logger
			.getLogger(UploadCertController.class);
	
	@Autowired
	private UserInfoService userInfoService;

	@ActionMapping(params="myaction=removeUserInfo")
	public void removeUserInfo(@RequestParam int userId, ActionRequest request, ActionResponse response) throws PortalException, SystemException {
		
		UserInfo userInfo = userInfoService.findById(userId);
		String username = userInfo.getUsername();
		log.error("ricevuto userId " + userId + "corrispondente all'utente " + username);
		long companyId = PortalUtil.getCompanyId(request);
		log.error("companyId " + companyId);
		User user = UserLocalServiceUtil.getUserByScreenName(companyId,
				username);
		if(user!=null){
			log.error("recuperato liferay user " + user.getScreenName());
			user.setActive(false);
			UserLocalServiceUtil.deleteUser(user);
			log.error("eliminato utente liferay");
		}
		userInfoService.delete(userId);
		log.error("eliminato utente portalUser");
		response.setRenderParameter("myaction", "userInfos");
		SessionMessages.add(request, "user-delated-successufully");
	}
	
}
