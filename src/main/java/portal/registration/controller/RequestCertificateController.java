package portal.registration.controller;

import javax.portlet.RenderResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

@Controller(value = "showRequestCertificateController")
@RequestMapping(value = "VIEW")
public class RequestCertificateController {

	@RenderMapping(params = "myaction=showRequestCertificate")
	public String showUserInfoForm(RenderResponse response) {
		return "requestCertificate";
	}


}
