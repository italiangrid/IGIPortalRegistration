package portal.registration.controller;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.bind.annotation.ActionMapping;

@Controller("searchUserController")
@RequestMapping(value = "VIEW")
public class SearchUserController {

	@ActionMapping(params = "myaction=searchUser")
	public void searchVo(ActionRequest request, ActionResponse response,
			SessionStatus sessionStatus) {

		UserInfoController.setSearch(request.getParameter("key"));

		sessionStatus.setComplete();

		response.setRenderParameter("myaction", "userInfos");
	}

	@ActionMapping(params = "myaction=searchResetUser")
	public void searchReset(ActionRequest request, ActionResponse response,
			SessionStatus sessionStatus) {

		UserInfoController.setSearch("");
		sessionStatus.setComplete();

		response.setRenderParameter("myaction", "userInfos");
	}

}
