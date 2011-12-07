package portal.registration.controller;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.bind.annotation.ActionMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

import portal.registration.domain.UserInfo;
import portal.registration.domain.UserToVo;
import portal.registration.services.UserInfoService;
import portal.registration.services.UserToVoService;


@Controller(value = "addFqansController")
@RequestMapping(value = "VIEW")
public class AddFqansController {
	
	private static final Logger log = Logger
			.getLogger(AddFqansController.class);
	
	@Autowired
	private UserToVoService userToVoService;
	
	@Autowired
	private UserInfoService userInfoService;
	
	@ActionMapping(params = "myaction=addFqans")
	public void addUserInfo(ActionRequest request,
			ActionResponse response, SessionStatus sessionStatus){
		
		String[] fqans = request.getParameterValues("resultList");
		int userId = Integer.parseInt(request.getParameter("userId"));
		int idVo = Integer.parseInt(request.getParameter("idVo"));
		
		UserToVo utv = userToVoService.findById(userId, idVo);
		
		if (fqans != null && !fqans[0].equals("")) {

			log.info("prova =" + fqans[0] + ";fine");

			String toSave = "";

			for (int i = 0; i < fqans.length; i++)
				toSave += fqans[i] + ";";

			log.info("Stampa FQANS: " + toSave);

			utv.setFqans(toSave);

		} else {
			utv.setFqans("");
		}

		userToVoService.update(utv);
		
		SessionMessages.add(request, "userToVo-updated-successufully");

		
		response.setRenderParameter("myaction", "editUserInfoForm");
		response.setRenderParameter("userId", Integer.toString(userId));
		request.setAttribute("userId", userId);
		sessionStatus.setComplete();
		
	}
	
	@ActionMapping(params = "myaction=deleteByUser")
	public void deleteUserInfo(ActionRequest request,
			ActionResponse response, SessionStatus sessionStatus) throws PortalException, SystemException, IOException{
		
		
		int userId = Integer.parseInt(request.getParameter("userId"));
		
		UserInfo userInfo = userInfoService.findById(userId);
		String username = userInfo.getUsername();
		log.info("ricevuto userId " + userId + "corrispondente all'utente " + username);
		long companyId = PortalUtil.getCompanyId(request);
		log.info("companyId " + companyId);
		User user = UserLocalServiceUtil.getUserByScreenName(companyId,
				username);
		log.info("recuperato liferay user " + user.getScreenName());
		
		UserLocalServiceUtil.deleteUser(user.getUserId());
		log.info("eliminato utente liferay");
		userInfoService.delete(userId);
		log.info("eliminato utente portalUser");
		response.sendRedirect(PortalUtil.getPortalURL(request)+"/c/portal/logout"); 
		
	}

}
