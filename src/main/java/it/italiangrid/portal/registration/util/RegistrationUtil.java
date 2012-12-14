package it.italiangrid.portal.registration.util;

import it.italiangrid.portal.dbapi.domain.Certificate;
import it.italiangrid.portal.dbapi.domain.Notify;
import it.italiangrid.portal.dbapi.domain.UserInfo;
import it.italiangrid.portal.dbapi.services.CertificateService;
import it.italiangrid.portal.dbapi.services.NotifyService;
import it.italiangrid.portal.dbapi.services.UserInfoService;
import it.italiangrid.portal.dbapi.services.UserToVoService;
import it.italiangrid.portal.registration.exception.RegistrationException;
import it.italiangrid.portal.registration.model.RegistrationModel;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.portlet.ActionRequest;

import org.apache.log4j.Logger;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

public class RegistrationUtil {

	private static final Logger log = Logger.getLogger(RegistrationUtil.class);

	private static String PASSWORD = "settedByPortal";

	public static boolean validate(UserInfo target, List<String> errors)
			throws SystemException {
		boolean result = true;

		if (Validator.isNull(target.getFirstName())) {
			errors.add("user-first-name-required");
			result = false;
			log.debug("nome sbagliato");
		}

		if (Validator.isNull(target.getLastName())) {
			errors.add("user-last-name-required");
			result = false;
			log.debug("cognome sbagliato");
		}

		if (Validator.isNull(target.getInstitute())) {
			errors.add("user-institute-required");
			result = false;
			log.debug("istituto sbagliato");
		}

		if (Validator.isNull(target.getMail())) {
			errors.add("user-mail-required");
			result = false;
			log.debug("mail sbagliato");
		}

		if (Validator.isNotNull(target.getMail())) {
			if (!Validator.isEmailAddress(target.getMail())) {
				errors.add("user-valid-mail-required");
				result = false;
				log.debug("mail invalida sbagliato");
			}
		}

		if (Validator.isNull(target.getUsername())) {
			errors.add("user-username-required");
			result = false;
			log.debug("usename vuoto sbagliato");
		}

		if (Validator.isNotNull(target.getPhone())) {
			if (!Validator.isPhoneNumber(target.getPhone())) {
				errors.add("user-phone-valid");
				result = false;
				log.debug("telefono sbagliato");
			}
		}

		List<User> liferayUsers = UserLocalServiceUtil.getUsers(0,
				UserLocalServiceUtil.getUsersCount());

		for (int i = 0; i < UserLocalServiceUtil.getUsersCount(); i++) {
			if (liferayUsers.get(i).getScreenName()
					.equals(target.getUsername())) {
				errors.add("user-username-duplicate");
				result = false;
				log.debug("username duplicato sbagliato");
			}
			if (liferayUsers.get(i).getEmailAddress().equals(target.getMail())) {
				errors.add("user-mail-duplicate");
				result = false;
				log.debug("mail duplicato sbagliato " + target.getMail() + " = "
						+ liferayUsers.get(i).getEmailAddress());
			}
		}

		return result;
	}

	public static void addUserToLiferay(ActionRequest request,
			UserInfo userInfo, RegistrationModel registrationModel)
			throws RegistrationException {
		try {
			long companyId = PortalUtil.getCompanyId(request);

			ThemeDisplay themeDisplay = (ThemeDisplay) request
					.getAttribute(WebKeys.THEME_DISPLAY);
			long[] groupIds = { themeDisplay.getLayout().getGroupId() };

			log.debug("companyid = " + companyId);
			log.debug("settate variabili di supporto ora si aggiunge un utenti a liferay!!");

			User u = UserLocalServiceUtil.addUser(0L, companyId, false,
					PASSWORD, PASSWORD, false, userInfo.getUsername(), userInfo
							.getMail(), 0L, "", new Locale("en"), userInfo
							.getFirstName(), "", userInfo.getLastName(), 0, 0,
					true, Calendar.JANUARY, 1, 1970, "", groupIds, null, null,
					null, true, ServiceContextFactory.getInstance(
							User.class.getName(), request));

			if (!registrationModel.isHaveCertificate())
				UserLocalServiceUtil.sendEmailAddressVerification(u, userInfo
						.getMail(), ServiceContextFactory.getInstance(
						User.class.getName(), request));

			if (u == null) {
				throw new RegistrationException("no-user-inserted");
			} else {
				u.setPasswordReset(false);
				UserLocalServiceUtil.updateUser(u);
				Role rolePowerUser = RoleLocalServiceUtil.getRole(companyId,
						"Power User");

				UserLocalServiceUtil.deleteRoleUser(rolePowerUser.getRoleId(),
						u.getUserId());
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RegistrationException("user-liferay-problem");
		}

	}

	public static UserInfo addUserToDB(UserInfo userInfo,
			UserInfoService userInfoService, NotifyService notifyService)
			throws RegistrationException {

		userInfo.setRegistrationComplete("false");
		int userId = userInfoService.save(userInfo, 1);

		log.debug("Utente aggiunto in PortalUsert con UserId = " + userId);

		Notify notify = new Notify(userInfo, "false");

		notifyService.save(notify);

		UserInfo newUser = userInfoService.findById(userId);

		if (newUser != null)
			return newUser;
		else
			throw new RegistrationException("user-db-problem");
	}

	public static void associateUserToCertificate(UserInfo userInfo,
			RegistrationModel registrationModel,
			CertificateService certificateService) throws RegistrationException {

		Certificate selectedCert = certificateService
				.findBySubject(registrationModel.getSubject());

		if (selectedCert != null) {
			log.debug("Aggiunto userToCertificate");
			selectedCert.setUserInfo(userInfo);
			certificateService.save(selectedCert);
		} else {
			throw new RegistrationException("certificate-not-found");
		}

	}

	public static void associateVoToUser(UserInfo userInfo,
			RegistrationModel registrationModel, UserToVoService userToVoService) {

		for (String idVo : registrationModel.getVos().split(";")) {
			log.debug("Aggiunto userToVo");
			userToVoService.save(userInfo.getUserId(), Integer.parseInt(idVo),
					registrationModel.getSubject());
		}

	}

	public static void activateUser(UserInfo userInfo,
			UserInfoService userInfoService) {
		log.debug("UserActivated");
		userInfo.setRegistrationComplete("true");
		userInfoService.save(userInfo);
		
	}

	public static void insertIntoIDP(UserInfo userInfo,
			RegistrationModel registrationModel) {
		log.debug("inserimento utente nell'IDP");
		
	}

}
