package portal.registration.controller;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import org.apache.log4j.Logger;

@Controller(value = "caOnlineController")
@RequestMapping(value = "VIEW")
public class CAOnlineController {

	private static final Logger log = Logger
			.getLogger(CAOnlineController.class);

	

	@RenderMapping(params = "myaction=showCAOnline")
	public String showUploadCert(RenderResponse response) {
		return "caOnline";
	}

	@ActionMapping(params = "myaction=haveCert")
	public void haveCert(ActionRequest request, ActionResponse response) {
		
		log.error("Scelta UploadCert o CAonline");
		
		String userId = request.getParameter("userId");
		String username = request.getParameter("username");
		String firstReg = request.getParameter("firstReg");
		boolean haveCert = Boolean.parseBoolean((String) request.getParameter("haveCert"));
		
		log.error("Ha il certificato? " + haveCert + " = "+ request.getParameter("haveCert") +" UFFA");

		String destination = "home";
		if(haveCert)
			destination = "showUploadCert";
			
		
		response.setRenderParameter("userId", userId);
		response.setRenderParameter("username", username);
		response.setRenderParameter("firstReg", firstReg);
		response.setRenderParameter("myaction", destination);
		

	}
}
