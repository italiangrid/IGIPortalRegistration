package portal.registration.controller;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.bind.annotation.ActionMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;


import it.italiangrid.portal.dbapi.domain.UserInfo;
import it.italiangrid.portal.dbapi.domain.UserToVo;
import it.italiangrid.portal.dbapi.services.UserInfoService;
import it.italiangrid.portal.dbapi.services.UserToVoService;

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
	public void addUserInfo(ActionRequest request, ActionResponse response,
			SessionStatus sessionStatus) {

		boolean firstReg = false;
		String[] fqans = request.getParameterValues("resultList");
		int userId = Integer.parseInt(request.getParameter("userId"));
		int idVo = Integer.parseInt(request.getParameter("idVo"));

		if (request.getParameter("firstReg") != null)
			firstReg = Boolean.parseBoolean(request.getParameter("firstReg"));

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

		if (firstReg) {
			response.setRenderParameter("myaction", "showAddUserToVoPresents");
		} else {
			response.setRenderParameter("myaction", "editUserInfoForm");
		}

		response.setRenderParameter("userId", Integer.toString(userId));
		request.setAttribute("userId", userId);
		sessionStatus.setComplete();

	}

	@ActionMapping(params = "myaction=deleteByUser")
	public void removeUserInfo(@RequestParam int userId, ActionRequest request,
			ActionResponse response) throws PortalException, SystemException,
			IOException {

		UserInfo userInfo = userInfoService.findById(userId);
		String username = userInfo.getUsername();
		log.error("ricevuto userId " + userId + "corrispondente all'utente "
				+ username);
		long companyId = PortalUtil.getCompanyId(request);
		log.error("companyId " + companyId);
		User user = UserLocalServiceUtil.getUserByScreenName(companyId,
				username);
		if (user != null) {
			log.error("recuperato liferay user " + user.getScreenName());
			// user.setActive(false);
			// UserLocalServiceUtil.updateActive(user.getUserId(),false);
			UserLocalServiceUtil.deleteUser(user.getUserId());
			log.error("eliminato utente liferay");
		}
		userInfoService.delete(userId);
		log.error("eliminato utente portalUser");
		response.sendRedirect(PortalUtil.getPortalURL(request)
				+ "/c/portal/logout");

	}

}
