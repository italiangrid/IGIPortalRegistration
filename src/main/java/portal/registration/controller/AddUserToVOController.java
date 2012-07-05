package portal.registration.controller;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderResponse;

import org.apache.log4j.Logger;

import portal.registration.domain.Certificate;
import portal.registration.domain.UserInfo;
import portal.registration.domain.UserToVo;
import portal.registration.domain.Vo;
import portal.registration.services.CertificateService;
import portal.registration.services.UserInfoService;
import portal.registration.services.UserToVoService;
import portal.registration.services.VoService;
import portal.registration.utils.VOMSAdminCallOut;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

@Controller(value = "addUserToVOController")
@RequestMapping(value = "VIEW")
public class AddUserToVOController {

	private static final Logger log = Logger
			.getLogger(AddUserToVOController.class);

	private static String search = null;

	public static void setSearch(String search2) {
		search = search2;
	}

	@Autowired
	private CertificateService certificateService;

	@Autowired
	private VoService voService;

	@Autowired
	private UserToVoService userToVoService;

	@Autowired
	private UserInfoService userInfoService;

	@ModelAttribute("userInfo")
	public UserInfo getCommandObject() {
		return new UserInfo();
	}

	@RenderMapping(params = "myaction=showAddUserToVO")
	public String showUserInfoForm(RenderResponse response) {
		return "addUserToVOForm";
	}

	@ActionMapping(params = "myaction=addUserToVO")
	public void addUserToVo(ActionRequest request, ActionResponse response,
			SessionStatus sessionStatus) throws PortalException,
			SystemException {

		log.info("sono dentro");

		ArrayList<String> errors = new ArrayList<String>();
		boolean allOk = true;

		int userId = Integer.parseInt(request.getParameter("userId"));
		log.info("Valore passato userId " + userId);

		if (request.getParameterValues("VOids") != null) {
			log.info("recupero idVo");
			int idVo = Integer.parseInt(request.getParameter("VOids"));
			if (idVo != 0) {
				String subject = null;
				if ((subject = checkVO(idVo, userId, errors)) != null) {
					log.info("Salvo sul DB la Vo " + idVo);
					UserInfo ui = userInfoService.findById(userId);
					if (ui.getRegistrationComplete().equals("false")) {
						activateUser(ui, request, errors);
					}
					userToVoService.save(userId, idVo, subject);
					List<UserToVo> utvs = userToVoService.findById(userId);
					if (utvs.size() == 1)
						;
					userToVoService.setDefault(userId, idVo);
					log.info("Salvato sul DB ");
				} else {
					allOk = false;
				}
			}

		} else {
			allOk = false;
			errors.add("user-vo-list-empty");
		}

		if (allOk) {

			SessionMessages.add(request, "userToVo-adding-success");

			if (request.getParameter("firstReg").equals("true")) {
				response.setRenderParameter("myaction",
						"showAddUserToVoPresents");
			} else {
				response.setRenderParameter("myaction", "editUserInfoForm");
			}
			response.setRenderParameter("userId", Integer.toString(userId));
			request.setAttribute("userId", userId);
			sessionStatus.setComplete();

			AddUserToVOController.setSearch("");

		} else {

			errors.add("error-saving-registration");

			for (String error : errors) {
				log.info("Errore: " + error);
				SessionErrors.add(request, error);
			}

			log.info("Errori in userToVO");

			if (request.getParameter("firstReg").equals("true")) {
				response.setRenderParameter("myaction",
						"showAddUserToVoPresents");
			} else {
				response.setRenderParameter("myaction", "editUserInfoForm");
			}
			response.setRenderParameter("userId", Integer.toString(userId));
			request.setAttribute("userId", userId);

		}

	}

	private void activateUser(UserInfo userInfo, ActionRequest request,
			ArrayList<String> errors) {

		String username = userInfo.getUsername();

		long companyId = PortalUtil.getCompanyId(request);

		User user;
		try {
			user = UserLocalServiceUtil
					.getUserByScreenName(companyId, username);

			Role rolePowerUser = RoleLocalServiceUtil.getRole(companyId,
					"Power User");

			List<User> powerUsers = UserLocalServiceUtil
					.getRoleUsers(rolePowerUser.getRoleId());

			long users[] = new long[powerUsers.size() + 1];

			int i;

			for (i = 0; i < powerUsers.size(); i++) {
				users[i] = powerUsers.get(i).getUserId();
			}

			users[i] = user.getUserId();
			// long[] roles = {10140};

			// RoleServiceUtil.addUserRoles(user.getUserId(), roles);

			UserLocalServiceUtil.setRoleUsers(rolePowerUser.getRoleId(), users);

			userInfo.setRegistrationComplete("true");

			userInfoService.edit(userInfo);

			SessionMessages.add(request, "user-activate");

		} catch (PortalException e) {
			errors.add("exception-activation-user");
			e.printStackTrace();
		} catch (SystemException e) {
			errors.add("exception-activation-user");
			e.printStackTrace();
		}

	}

	@ModelAttribute("vos")
	public List<Vo> getVos() {
		if (search == null || search.equals(""))
			return voService.getAllVo();
		else
			return voService.getAllVoByName(search);
	}

	@ModelAttribute("disciplines")
	public List<String> getDisciplines() {
		return voService.getAllDiscplines();
	}

	private String checkVO(int idVo, int userId, List<String> errors) {
		String result = null;

		Vo vo = voService.findById(idVo);

		List<Certificate> certs = certificateService.findById(userId);

		if (certs.size() == 0) {
			errors.add("no-cert-for-user");
			return null;
		}

		if (userToVoService.findById(userId, idVo) != null) {
			errors.add("userToVo-already-exists");
			return null;
		}

		for (int i = 0; i < certs.size(); i++) {
			if (VOMSAdminCallOut.getUser(certs.get(i).getSubject(), certs
					.get(i).getIssuer(), vo.getHost())) {
				result = certs.get(i).getSubject();
			}
		}

		if (result == null)
			errors.add("no-user-found-in-VO");

		return result;
	}

	@ActionMapping(params = "myaction=setDefaultUserToVo")
	public void setDefaultUserToVo(ActionRequest request,
			ActionResponse response, SessionStatus sessionStatus) {

		int userId = Integer.parseInt(request.getParameter("userId"));
		int idVo = Integer.parseInt(request.getParameter("idVo"));

		try {
			log.info("Sto per settarea default il userToVo con userId = "
					+ userId + "e idVo = " + idVo);
			if (userToVoService.setDefault(userId, idVo))
				SessionMessages.add(request, "userToVo-updated-successufully");
			else
				SessionErrors.add(request, "error-default-userToVo");
			log.info("UserToVoSettato");
			userToVoService.findVoByUserId(userId);

		} catch (Exception e) {
			SessionErrors.add(request, "error-updating-certificate");
		}

		response.setRenderParameter("myaction", "showAddUserToVoPresents");
		response.setRenderParameter("userId", Integer.toString(userId));
		request.setAttribute("firstReg", request.getParameter("firstReg"));
		sessionStatus.setComplete();

	}

	@ActionMapping(params = "myaction=removeUserToVo")
	public void removeUserToVo(ActionRequest request, ActionResponse response,
			SessionStatus sessionStatus) {

		int userId = Integer.parseInt(request.getParameter("userId"));
		int idVo = Integer.parseInt(request.getParameter("idVo"));

		try {
			log.info("Sto per cancellare il userToVo con userId = " + userId
					+ "e idVo = " + idVo);
			userToVoService.delete(userId, idVo);
			log.info("userTo Vo cancellato");

			SessionMessages.add(request, "userToVo-deleted-successufully");

		} catch (Exception e) {
			SessionErrors.add(request, "error-deleting-userToVo");
		}

		response.setRenderParameter("myaction", "showAddUserToVoPresents");
		response.setRenderParameter("userId", Integer.toString(userId));
		request.setAttribute("firstReg", request.getParameter("firstReg"));
		sessionStatus.setComplete();

	}

	@ActionMapping(params = "myaction=setDefaultUserToVoEdit")
	public void setDefaultUserToVoEdit(ActionRequest request,
			ActionResponse response, SessionStatus sessionStatus) {

		int userId = Integer.parseInt(request.getParameter("userId"));
		int idVo = Integer.parseInt(request.getParameter("idVo"));

		try {
			log.info("Sto per settare default il userToVo con userId = "
					+ userId + "e idVo = " + idVo);
			if (userToVoService.setDefault(userId, idVo))
				SessionMessages.add(request, "userToVo-updated-successufully");
			else
				SessionErrors.add(request, "error-default-userToVo");
			log.info("UserToVoSettato");
			userToVoService.findVoByUserId(userId);

		} catch (Exception e) {
			SessionErrors.add(request, "error-updating-certificate");
		}

		response.setRenderParameter("myaction", "editUserInfoForm");
		response.setRenderParameter("userId", Integer.toString(userId));
		request.setAttribute("firstReg", request.getParameter("firstReg"));
		sessionStatus.setComplete();

	}

	@ActionMapping(params = "myaction=removeUserToVoEdit")
	public void removeUserToVoEdit(ActionRequest request,
			ActionResponse response, SessionStatus sessionStatus) {

		int userId = Integer.parseInt(request.getParameter("userId"));
		int idVo = Integer.parseInt(request.getParameter("idVo"));

		try {
			log.info("Sto per cancellare il userToVo con userId = " + userId
					+ "e idVo = " + idVo);
			userToVoService.delete(userId, idVo);
			log.info("userTo Vo cancellato");

			SessionMessages.add(request, "userToVo-deleted-successufully");
			userToVoService.findVoByUserId(userId);

		} catch (Exception e) {
			SessionErrors.add(request, "error-deleting-userToVo");
		}

		response.setRenderParameter("myaction", "editUserInfoForm");
		response.setRenderParameter("userId", Integer.toString(userId));
		request.setAttribute("userId", userId);
		request.setAttribute("firstReg", request.getParameter("firstReg"));
		sessionStatus.setComplete();

	}

	@ModelAttribute("searchUserToVo")
	public String getSearch() {

		return search;

	}

}
