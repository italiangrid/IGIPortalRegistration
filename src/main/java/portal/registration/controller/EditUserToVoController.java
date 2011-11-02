package portal.registration.controller;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.liferay.portal.kernel.servlet.SessionMessages;

import portal.registration.domain.Certificate;
import portal.registration.domain.UserInfo;
import portal.registration.domain.UserToVo;
import portal.registration.domain.Vo;
import portal.registration.services.CertificateService;
import portal.registration.services.UserInfoService;
import portal.registration.services.UserToVoService;
import portal.registration.services.VoService;
import portal.registration.utils.VOMSAdminCallOut;

@Controller
@RequestMapping("view")
public class EditUserToVoController {

	private static final Logger log = Logger
			.getLogger(EditUserToVoController.class);

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private VoService voService;

	@Autowired
	private UserToVoService userToVoService;

	@Autowired
	private CertificateService certificateService;

	@RenderMapping(params = "myaction=showEditVo")
	public String showEditUserInfoForm() {
		return "editUserToVo";
	}

	@ModelAttribute("userInfo")
	public UserInfo getUserInfo(@RequestParam int userId) {
		return userInfoService.findById(userId);
	}

	@ModelAttribute("userToVo")
	public UserToVo getUserToVo(@RequestParam int userId, @RequestParam int idVo) {
		return userToVoService.findById(userId, idVo);
	}

	@ModelAttribute("vo")
	public Vo getVO(@RequestParam int idVo) {

		return voService.findById(idVo);
	}

	@ModelAttribute("fqans")
	public String[] getFqans(@RequestParam int userId, @RequestParam int idVo) {

		Vo vo = voService.findById(idVo);
		
		UserToVo utv = userToVoService.findById(userId, idVo);
		
		Certificate cert = certificateService.findByIdCert(utv.getCertificate());

		String[] fqans = null;
			
		fqans = VOMSAdminCallOut.getUserFQANs(cert.getSubject(),
				cert.getIssuer(), vo.getHost());
			


		return fqans;
	}

	@ActionMapping(params = "myaction=addUserToVo")
	public void addUserToVo(ActionRequest request, ActionResponse response,
			SessionStatus sessionStatus) {

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

}