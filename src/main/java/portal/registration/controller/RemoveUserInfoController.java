package portal.registration.controller;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ActionMapping;

//import portal.registration.domain.UserInfo;
//import portal.registration.services.UserInfoService;
import it.italiangrid.portal.dbapi.domain.UserInfo;
import it.italiangrid.portal.dbapi.services.UserInfoService;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

/**
 * RemoveUserInfoController handles removal of UserInfo from the Catalog.
 * 
 * 
 */
@Controller(value = "removeUserInfoController")
@RequestMapping("VIEW")
public class RemoveUserInfoController {

	private static final Logger log = Logger
			.getLogger(RemoveUserInfoController.class);

	@Autowired
	private UserInfoService userInfoService;

	@ActionMapping(params = "myaction=removeUserInfo")
	public void removeUserInfo(@RequestParam int userId, ActionRequest request,
			ActionResponse response) throws PortalException, SystemException {

		UserInfo userInfo = userInfoService.findById(userId);
		String username = userInfo.getUsername();
		log.info("ricevuto userId " + userId + "corrispondente all'utente "
				+ username);
		long companyId = PortalUtil.getCompanyId(request);
		log.info("companyId " + companyId);
		User user = UserLocalServiceUtil.getUserByScreenName(companyId,
				username);
		if (user != null) {
			log.info("recuperato liferay user " + user.getScreenName());
			// user.setActive(false);
			// UserLocalServiceUtil.updateActive(user.getUserId(),false);
			UserLocalServiceUtil.deleteUser(user.getUserId());
			log.info("eliminato utente liferay");
		}
		userInfoService.delete(userId);
		log.info("eliminato utente portalUser");
		response.setRenderParameter("myaction", "userInfos");
		SessionMessages.add(request, "user-delated-successufully");
	}

}
