package portal.registration.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletSession;
import javax.portlet.RenderResponse;
import org.apache.log4j.Logger;

import it.italiangrid.portal.dbapi.domain.Idp;
import it.italiangrid.portal.dbapi.domain.Notify;
import it.italiangrid.portal.dbapi.domain.UserInfo;
import it.italiangrid.portal.dbapi.services.IdpService;
import it.italiangrid.portal.dbapi.services.NotifyService;
import it.italiangrid.portal.dbapi.services.UserInfoService;
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
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
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
	
	private static String PASSWORD = "settedByPortal";

	@Autowired
	private IdpService idpService;
	
	@Autowired
	private NotifyService notifyService;

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
			ActionResponse response, SessionStatus sessionStatus,
			PortletSession session) throws PortalException, SystemException {


		log.info("sono dentro");

		
		User u = null;
		long companyId = PortalUtil.getCompanyId(request);
		
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
						
						String newUsername = "";
						char[] chars = userInfo.getUsername().toCharArray();
						
						for(int i=0; i<chars.length; i++){
							if(Character.isLetterOrDigit(chars[i]))
								newUsername+=chars[i];
							else
								newUsername+='_';
						}
						
						userInfo.setUsername(newUsername);
						
						try {


							ThemeDisplay themeDisplay = (ThemeDisplay) request
									.getAttribute(WebKeys.THEME_DISPLAY);
							long[] groupIds = { themeDisplay.getLayout()
									.getGroupId() };

							log.info("companyid = " + companyId);
							log.info("settate variabili di supporto ora si aggiunge un utenti a liferay!!");

							u = UserLocalServiceUtil.addUser(0L, companyId,
									false, PASSWORD, PASSWORD, false, userInfo
											.getUsername(), userInfo.getMail(),
									0L, "", new Locale("en"), userInfo
											.getFirstName(), "", userInfo
											.getLastName(), 0, 0, true,
									Calendar.JANUARY, 1, 1970, "", groupIds,
									null, null, null, true,
									ServiceContextFactory.getInstance(
											User.class.getName(), request));
							

							if (u == null){

								log.info("nulla di fatto");
							} else {
								u.setPasswordReset(false);
								UserLocalServiceUtil.updateUser(u);
								Role rolePowerUser = RoleLocalServiceUtil
										.getRole(companyId, "Power User");

								UserLocalServiceUtil.deleteRoleUser(
										rolePowerUser.getRoleId(),
										u.getUserId());
							}

						} catch (Exception e) {

							errors.add("user-liferay-problem");

							log.info("Inserimento utente in liferay "
									+ e.getMessage());

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
								
								Notify notify = new Notify(userInfo, "false");
								
								notifyService.save(notify);

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
								"showCAOnline");
						response.setRenderParameter("userId",
								Integer.toString(userId));
						request.setAttribute("userId", userId);
						response.setRenderParameter("username",
								userInfo.getUsername());
						request.setAttribute("username", userInfo.getUsername());
						response.setRenderParameter("firstReg", "true");
						request.setAttribute("firstReg", "true");
					} else {
						response.setRenderParameter("myaction",
								"showRequestCertificate");
					}

				} else {

					errors.add("error-saving-registration");

					for (String error : errors) {
						log.info("Errore: " + error);
						SessionErrors.add(request, error);
					}
					
					PortletConfig portletConfig = (PortletConfig)request.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
					SessionMessages.add(request, portletConfig.getPortletName() + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);

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
