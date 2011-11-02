package portal.registration.controller;

import java.util.List;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;

import portal.registration.domain.UserInfo;
import portal.registration.domain.Vo;
import portal.registration.services.UserInfoService;
import portal.registration.services.UserToVoService;
import portal.registration.services.VoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

@Controller(value = "addUserToVoPresentsController")
@RequestMapping(value = "VIEW")
public class AddUserToVoPresentsController {

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

	@ModelAttribute("userToVoList")
	public List<Vo> getUserToVoList(@RequestParam int userId) {

		return userToVoService.findVoByUserId(userId);
	}

	@RenderMapping(params = "myaction=showAddUserToVoPresents")
	public String showUserInfoForm(@RequestParam int userId,
			RenderRequest request) {

		if (userToVoService.findById(userId).size() == 0) {
			deactivateUser(userId, request);
			SessionMessages.add(request, "user-deactivate");
		}
		return "addUserToVoPresents";
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

				UserLocalServiceUtil.deleteRoleUser((long) 10140,
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
		}

	}

	//
	@ActionMapping(params = "myaction=goToAddUserToVOForm")
	public void goToAddUserToVOForm(ActionRequest request,
			ActionResponse response, SessionStatus sessionStatus) {

		int userId = Integer.parseInt(request.getParameter("userId"));
		response.setRenderParameter("myaction", "showAddUserToVO");
		response.setRenderParameter("userId", Integer.toString(userId));
		// log.info("firstReg = " +
		// ((request.getParameter("firstReg")==null)?"null":request.getParameter("firstReg")));

		request.setAttribute("firstReg", request.getParameter("firstReg"));
		sessionStatus.setComplete();

	}

	@ModelAttribute("vos")
	public List<Vo> getVos() {
		return voService.getAllVo();
	}

	@ActionMapping(params = "myaction=userToVoComplete")
	public void userToVoComplete(ActionResponse response,
			SessionStatus sessionStatus) {

		response.setRenderParameter("myaction", "userInfos");
		sessionStatus.setComplete();

	}

}
