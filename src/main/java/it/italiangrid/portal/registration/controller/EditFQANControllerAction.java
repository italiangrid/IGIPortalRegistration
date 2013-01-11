package it.italiangrid.portal.registration.controller;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import com.liferay.portal.kernel.servlet.SessionMessages;

import it.italiangrid.portal.dbapi.domain.UserInfo;
import it.italiangrid.portal.dbapi.domain.UserToVo;
import it.italiangrid.portal.dbapi.services.UserInfoService;
import it.italiangrid.portal.dbapi.services.UserToVoService;
import it.italiangrid.portal.registration.model.RegistrationModel;

@Controller(value = "editFQANControllerAction")
@RequestMapping("view")
public class EditFQANControllerAction {

	private static final Logger log = Logger
			.getLogger(EditFQANControllerAction.class);

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private UserToVoService userToVoService;

	@ActionMapping(params = "myaction=editRoleVO")
	public void addUserToVo(@ModelAttribute RegistrationModel registrationModel, ActionRequest request, ActionResponse response,
			SessionStatus sessionStatus) {

		String[] fqans = request.getParameterValues("resultList");
		UserInfo userInfo = userInfoService.findByMail(registrationModel.getEmail());
		int userId = userInfo.getUserId();
		int idVo = Integer.parseInt(request.getParameter("idVo"));
		
		log.error(registrationModel);
		log.error(idVo + " " + userId);
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

		response.setRenderParameter("myaction", "showAddVoForm");
		request.setAttribute("registrationModel", registrationModel);

	}

}
