package it.italiangrid.portal.registration.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import it.italiangrid.portal.dbapi.domain.Certificate;
import it.italiangrid.portal.dbapi.domain.UserInfo;
import it.italiangrid.portal.dbapi.services.CertificateService;
import it.italiangrid.portal.dbapi.services.UserInfoService;
import it.italiangrid.portal.registration.exception.RegistrationException;
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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

import portal.registration.utils.SendMail;

@Controller(value = "sendMailController")
@RequestMapping(value = "VIEW")
public class SendMailController {
	
	private static final Logger log = Logger.getLogger(SendMailController.class);
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private CertificateService certificateService;

	
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
		
		String sendToAll = request.getParameter("sendToAll");
		log.info("Send To All: " + sendToAll);
		try {
			String mail = RegistrationUtil.readFile(RegistrationConfig.getProperties("Registration.properties", "admin.mail.template"));
		
			mail = mail.replaceAll("##MESSAGE##", text);
			
			log.info(mail);
			
			String to = "";
			
			if(sendToAll.equals("true")){
				to = sendToAll();
			} else {
				to = sendToRecentUser();
			}
			
			if(to.length() > 0){
				to = to.substring(0, to.length()-1);
				log.info(to);
				SendMail sm = new SendMail(RegistrationConfig.getProperties("Registration.properties", "igiportal.mail"), to, subject, mail, true);
				sm.sendList();
				response.setRenderParameter("myaction", "showSendMailSuccess");
				return;
			}
			
			SessionErrors.add(request, "send-mail-empty-list");
		
		} catch (Exception e) {
			e.printStackTrace();
			SessionErrors.add(request, "send-mail-problem");
		}
		response.setRenderParameter("myaction", "showSendMail");
		
		PortletConfig portletConfig = (PortletConfig)request.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
		SessionMessages.add(request, portletConfig.getPortletName() + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
	}

	@SuppressWarnings("unchecked")
	private String sendToRecentUser() {
		
		log.info("Creating recent user mail list.");
		
		String to = "";
		
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(User.class);
		
		Calendar currentDate = Calendar.getInstance(); 
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		log.info("Taday date: " + sdf.format(currentDate.getTime()));
		Date today = currentDate.getTime();
		
		Calendar monthAgo = currentDate;
		try {
			int monthEarly = Integer.parseInt(RegistrationConfig.getProperties("Registration.properties", "admin.mail.month.early"));
			if(monthEarly < currentDate.MONTH){
				monthAgo.roll(Calendar.MONTH, -monthEarly);
			}else{
				
			}
			log.info("A Month Ago date: " + sdf.format(currentDate.getTime()));
			
		    Date compare = monthAgo.getTime();
			
			dynamicQuery.add(PropertyFactoryUtil.forName("lastLoginDate").gt(compare));
		
		
			List<User> users = UserLocalServiceUtil.dynamicQuery(dynamicQuery);
			for (User user : users) {
				UserInfo userInfo = userInfoService.findByMail(user.getEmailAddress());
				if (userInfo.getRegistrationComplete().equals("true")){
					List<Certificate> certifcates = certificateService.findById(userInfo.getUserId());
					boolean isntExpired = false;
					for (Certificate certificate : certifcates) {
						if(certificate.getExpirationDate().after(today)){
							isntExpired = true;
						}
					}
					if(isntExpired){
						to += userInfo.getMail()+",";
					}
				}
			}
			
		} catch (SystemException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (RegistrationException e) {
			e.printStackTrace();
		}
		log.info("List created.");
		return to;
	}

	private String sendToAll() {
		
		log.info("Creating all user mail list.");
		
		String to = "";
		List<UserInfo> infos = userInfoService.getAllUserInfo();
		
		for (UserInfo userInfo : infos) {
			to += userInfo.getMail()+",";
		}
		
		to = to.substring(0, to.length()-1);
		
		log.info("List created.");
		return to;
	}
}
