package it.italiangrid.portal.registration.controller;

import java.util.List;

import it.italiangrid.portal.dbapi.domain.UserInfo;
import it.italiangrid.portal.dbapi.services.UserInfoService;
import it.italiangrid.portal.registration.util.RegistrationConfig;
import it.italiangrid.portal.registration.util.RegistrationUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.JavaConstants;

import portal.registration.utils.SendMail;

@Controller(value = "sendMailController")
@RequestMapping(value = "VIEW")
public class SendMailController {
	
	private static final Logger log = Logger.getLogger(SendMailController.class);
	
	@Autowired
	private UserInfoService userInfoService;

	
	@RenderMapping(params = "myaction=showSendMail")
	public String showSendMail(){
		return "sendMail";
	}
	
	@RenderMapping(params = "myaction=showSendMailSuccess")
	public String showSendMailSuccess(){
		
		return "sendMailSuccess";
	}
	
	
	@ActionMapping(params = "myaction=sendMail")
	public void sendMail(ActionRequest request, ActionResponse response){
		String subject = "IGI Portal: " + request.getParameter("subject");
		String text = request.getParameter("text");
		text = text.replaceAll("\n", "<br/>");
		
		try {
			String mail = RegistrationUtil.readFile(RegistrationConfig.getProperties("Registration.properties", "admin.mail.template"));
		
			mail = mail.replaceAll("##MESSAGE##", text);
			String to = "";
			List<UserInfo> infos = userInfoService.getAllUserInfo();
			
			for (UserInfo userInfo : infos) {
				to += userInfo.getMail()+",";
			}
			
			to = to.substring(0, to.length()-1);
			
			log.info(to);
			
			SendMail sm = new SendMail(RegistrationConfig.getProperties("Registration.properties", "igiportal.mail"), to, subject, mail, true);
			sm.sendList();
				
		
		} catch (Exception e) {
			e.printStackTrace();
			response.setRenderParameter("myaction", "showSendMail");
			SessionErrors.add(request, "send-mail-problem");
			PortletConfig portletConfig = (PortletConfig)request.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
			SessionMessages.add(request, portletConfig.getPortletName() + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
		}
		response.setRenderParameter("myaction", "showSendMailSuccess");
	}
}
