package portal.registration.controller;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.bind.annotation.ActionMapping;

@Controller("searchController")
@RequestMapping(value = "VIEW")
public class SearchController {

	@ActionMapping(params = "myaction=searchVo")
	public void searchVo(ActionRequest request, ActionResponse response,
			SessionStatus sessionStatus) {

		VOListController.setSearch(request.getParameter("key"));

		String userId = request.getParameter("userId");
		String waif = request.getParameter("waif");

		// request.setAttribute("waif", waif);
		// request.setAttribute("userId", userId);

		response.setRenderParameter("myaction", "showVOList");
		response.setRenderParameter("waif", waif);
		response.setRenderParameter("userId", userId);

		// sessionStatus.setComplete();

	}

	@ActionMapping(params = "myaction=searchReset")
	public void searchReset(ActionRequest request, ActionResponse response,
			SessionStatus sessionStatus) {

		VOListController.setSearch("");
		sessionStatus.setComplete();

		String userId = request.getParameter("userId");
		String waif = request.getParameter("waif");

		response.setRenderParameter("myaction", "showVOList");
		response.setRenderParameter("waif", waif);
		response.setRenderParameter("userId", userId);
	}

}
