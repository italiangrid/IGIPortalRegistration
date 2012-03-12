package portal.registration.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderResponse;

import org.apache.log4j.Logger;

import portal.registration.domain.Idp;
import portal.registration.domain.UserInfo;
import portal.registration.services.IdpService;
import portal.registration.services.UserInfoService;
import portal.registration.utils.LongNumberEditor;
import portal.registration.utils.MyValidator;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

@Controller(value = "addUserInfoController")
@RequestMapping(value = "VIEW")
@SessionAttributes(types = UserInfo.class)
public class AddUserInfoController {

	private static final Logger log = Logger
			.getLogger(AddUserInfoController.class);

	@Autowired
	private IdpService idpService;

	@Autowired
	private UserInfoService userInfoService;

	@ModelAttribute("userInfo")
	public UserInfo getCommandObject() {
		return new UserInfo();
	}

	@RenderMapping(params = "myaction=addUserInfoForm")
	public String showUserInfoForm(RenderResponse response) {
		return "addUserInfoForm";
	}

	@InitBinder("userInfo")
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Long.class, new LongNumberEditor());
	}

	@ActionMapping(params = "myaction=addUserInfo")
	public void addUserInfo(@ModelAttribute UserInfo userInfo,
			BindingResult bindingResult, ActionRequest request,
			ActionResponse response, SessionStatus sessionStatus)
			throws PortalException, SystemException {

		log.info("sono dentro");
		if (!bindingResult.hasErrors()) {

			ArrayList<String> errors = new ArrayList<String>();
			boolean allOk = true;

			int userId = 0;
			if (request.getParameter("useCondition").equals("true")) {
				if (MyValidator.validate(userInfo, errors)) {

					log.info("Valore passato idp "
							+ Integer.parseInt(request.getParameter("idpId")));
					log.info("Valore passato havecert "
							+ request.getParameter("haveCert"));

					if (Integer.parseInt(request.getParameter("idpId")) != 0) {
						Idp idp = idpService.findById(Integer.parseInt(request
								.getParameter("idpId")));
						userInfo.setIdp(idp);

						log.info("Settato idp "
								+ Integer.parseInt(request
										.getParameter("idpId")));

						try {

							long companyId = PortalUtil.getCompanyId(request);
							ThemeDisplay themeDisplay = 
								     (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
							long[] groupIds = {themeDisplay.getLayout().getGroupId()};
							log.info("companyid = " + companyId);
							log.info("settate variabili di supporto ora si aggiunge un utenti a liferay!!");

							User u = UserLocalServiceUtil.addUser(0L,
									companyId, false, "settedByPortal",
									"settedByPortal", false, userInfo
											.getUsername(), userInfo.getMail(),
									0L, "", new Locale("en"), userInfo
											.getFirstName(), "", userInfo
											.getLastName(), 0, 0, true,
									Calendar.JANUARY, 1, 1970, "", groupIds, null,
									null, null, true, ServiceContextFactory
											.getInstance(User.class.getName(),
													request));

							if (u == null){
								log.info("nulla di fatto");
							} else {
								UserLocalServiceUtil.deleteRoleUser((long) 10140,
										u.getUserId());
							}

						} catch (Exception e) {

							errors.add("user-liferay-problem");
							log.info("Inserimento utente in liferay" + e);
							allOk = false;
							request.setAttribute("firstReg", "true");
						}

						if (allOk) {
							try {

								log.info("aggiunto utente in liferay, ora aggiungo in PortalUser");
								userInfo.setRegistrationComplete("false");
								userId = userInfoService.save(userInfo,
										Integer.parseInt(request
												.getParameter("idpId")));

								log.info("Utente aggiunto in PortalUsert con UserId = "
										+ userId);

							} catch (Exception e) {

								errors.add("user-PortalUser-problem");
								log.info("Inserimento utente in PortalUser");
								e.printStackTrace();
								allOk = false;
							}
						}
					} else {

						errors.add("user-idp-problem");
						allOk = false;

					}

				} else {

					allOk = false;
				}

				if (allOk) {

					SessionMessages.add(request, "user-saved-successufully");

					if (request.getParameter("haveCert").equals("true")) {
						response.setRenderParameter("myaction",
								"showUploadCert");
						response.setRenderParameter("userId", Integer.toString(userId));
						request.setAttribute("userId", userId);
						response.setRenderParameter("username", userInfo.getUsername());
						request.setAttribute("username", userInfo.getUsername());
						response.setRenderParameter("firstReg", "true");
						request.setAttribute("firstReg", "true");
					} else {
						response.setRenderParameter("myaction",
								"showRequestCertificate");
					}

					sessionStatus.setComplete();

				} else {

					errors.add("error-saving-registration");

					for (String error : errors) {
						log.info("Errore: " + error);
						SessionErrors.add(request, error);
					}

					log.info("non va bene");

					response.setRenderParameter("myaction", "addUserInfoForm");
					request.setAttribute("idpValue",
							Integer.parseInt(request.getParameter("idpId")));
					request.setAttribute("userInfo", userInfo);
					request.setAttribute("checked",
							request.getParameter("haveCert"));

					log.info("non va bene");
				}
			} else {
				log.info("condizioni d'uso non accettate");
				SessionErrors.add(request, "use-condition-not-acepted");
				response.setRenderParameter("myaction", "userInfos");
			}
		} else {

			log.info("Errore errore");
			response.setRenderParameter("myaction", "addUserInfoForm");
		}

	}

	@ModelAttribute("idps")
	public List<Idp> getIdps() {
		return idpService.getAllIdp();
	}

}
