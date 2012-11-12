package portal.registration.controller;

import it.italiangrid.portal.dbapi.domain.UserInfo;
import it.italiangrid.portal.dbapi.services.UserInfoService;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import org.apache.log4j.Logger;

import portal.registration.utils.TokenCreator;

@Controller(value = "caOnlineController")
@RequestMapping(value = "VIEW")
public class CAOnlineController {

	private static final Logger log = Logger
			.getLogger(CAOnlineController.class);

	@Autowired
	private UserInfoService userInfoService;

	@RenderMapping(params = "myaction=showCAOnline")
	public String showUploadCert(RenderResponse response) {
		return "caOnline";
	}

	@ActionMapping(params = "myaction=haveCert")
	public void haveCert(ActionRequest request, ActionResponse response) {
		
		log.debug("Scelta UploadCert o CAonline");
		
		String userId = request.getParameter("userId");
		String username = request.getParameter("username");
		String firstReg = request.getParameter("firstReg");
		boolean haveCert = Boolean.parseBoolean((String) request.getParameter("haveCert"));
		
		log.debug("Ha il certificato? " + haveCert + " = "+ request.getParameter("haveCert") +" UFFA UFFA");

		String destination = "showSuccessCAOnline";
		if(haveCert)
			destination = "showUploadCert";
			
		log.debug("destination: " + destination);
		
		response.setRenderParameter("userId", userId);
		response.setRenderParameter("username", username);
		response.setRenderParameter("firstReg", firstReg);
		response.setRenderParameter("myaction", destination);
		

	}
	
	@ModelAttribute("tokens")
	public List<String> getTokens(@RequestParam int userId) {
		
		UserInfo userInfo = userInfoService.findById(userId);	
		return TokenCreator.getToken(userInfo.getMail());
	}
}
