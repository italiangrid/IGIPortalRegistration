package portal.registration.controller;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.bind.annotation.ActionMapping;

@Controller("searchVoController")
@RequestMapping(value = "VIEW")
public class SearchVOController {

	@ActionMapping(params = "myaction=searchUserToVo")
	public void searchVo(ActionRequest request, ActionResponse response,
			SessionStatus sessionStatus) {

		AddUserToVOController.setSearch(request.getParameter("key"));

		String userId = request.getParameter("userId");
		String firstReg = request.getParameter("firstReg");

		// request.setAttribute("waif", waif);
		// request.setAttribute("userId", userId);

		response.setRenderParameter("myaction", "showAddUserToVO");
		response.setRenderParameter("firstReg", firstReg);
		response.setRenderParameter("userId", userId);
		request.setAttribute("firstReg", request.getParameter("firstReg"));

		// sessionStatus.setComplete();

	}

	@ActionMapping(params = "myaction=searchResetUserToVo")
	public void searchReset(ActionRequest request, ActionResponse response,
			SessionStatus sessionStatus) {

		AddUserToVOController.setSearch("");
		sessionStatus.setComplete();

		String userId = request.getParameter("userId");
		String firstReg = request.getParameter("firstReg");

		response.setRenderParameter("myaction", "showAddUserToVO");
		response.setRenderParameter("firstReg", firstReg);
		response.setRenderParameter("userId", userId);
		request.setAttribute("firstReg", request.getParameter("firstReg"));
	}

}
