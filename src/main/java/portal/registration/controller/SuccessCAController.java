package portal.registration.controller;

import javax.portlet.RenderResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

@Controller(value = "successCAController")
@RequestMapping(value = "VIEW")
public class SuccessCAController {

	private static final Logger log = Logger
			.getLogger(CAOnlineController.class);

	@RenderMapping(params = "myaction=showSuccessCAOnline")
	public String showUploadCert(RenderResponse response) {
		log.error("Registration with CA-online certificate request completed.");
		return "successCAonline";
	}
}
