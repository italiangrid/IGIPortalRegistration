package portal.registration.controller;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderResponse;

//import portal.registration.domain.Vo;
//import portal.registration.services.VoService;
import it.italiangrid.portal.dbapi.domain.Vo;
import it.italiangrid.portal.dbapi.services.VoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

@Controller(value = "showVOListController")
@RequestMapping(value = "VIEW")
public class VOListController {

	private static String search = null;

	public static void setSearch(String search2) {
		search = search2;
	}

	@ModelAttribute("searchVo")
	public String getSearch() {

		return search;
		

	}

	@Autowired
	private VoService voService;

	@RenderMapping(params = "myaction=showVOList")
	public String showUserInfoForm(RenderResponse response) {
		return "VOList";
	}

	@ModelAttribute("vos")
	public List<Vo> getVos(RenderResponse response) {
		if (search == null || search.equals(""))
			return voService.getAllVo();
		else
			return voService.getAllVoByName(search);
	}

	@ActionMapping(params = "myaction=listComplete")
	public void listComplete(ActionRequest request, ActionResponse response,
			SessionStatus sessionStatus) {

		String userId = request.getParameter("userId");
		String waif = request.getParameter("waif");

		response.setRenderParameter("myaction", waif);
		response.setRenderParameter("userId", userId);
		sessionStatus.setComplete();

	}

}
