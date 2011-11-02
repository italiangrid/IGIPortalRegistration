package portal.registration.controller;

import javax.portlet.ActionRequest;

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
	
	@Autowired
	private UserInfoService userInfoService;

	@ActionMapping(params="myaction=removeUserInfo")
	public void removeUserInfo(@RequestParam int userId, ActionRequest request) throws PortalException, SystemException {
		
		UserInfo userInfo = userInfoService.findById(userId);
		String username = userInfo.getUsername();
		
		long companyId = PortalUtil.getCompanyId(request);
		
		User user = UserLocalServiceUtil.getUserByScreenName(companyId,
				username);
		
		UserLocalServiceUtil.deleteUser(user);
	
		userInfoService.delete(userId);
		
		SessionMessages.add(request, "user-delated-successufully");
	}
}
