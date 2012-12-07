package portal.registration.utils;

import java.util.List;

import org.apache.log4j.Logger;

//import portal.registration.domain.UserInfo;
import it.italiangrid.portal.dbapi.domain.UserInfo;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

public class MyValidator {

	private static final Logger log = Logger.getLogger(MyValidator.class);

	public static boolean validate(UserInfo target, List<String> errors)
			throws SystemException {
		boolean result = true;

		if (Validator.isNull(target.getFirstName())) {
			errors.add("user-first-name-required");
			result = false;
			log.info("nome sbagliato");
		}

		if (Validator.isNull(target.getLastName())) {
			errors.add("user-last-name-required");
			result = false;
			log.info("cognome sbagliato");
		}

		if (Validator.isNull(target.getInstitute())) {
			errors.add("user-institute-required");
			result = false;
			log.info("istituto sbagliato");
		}

		if (Validator.isNull(target.getMail())) {
			errors.add("user-mail-required");
			result = false;
			log.info("mail sbagliato");
		}

		if (Validator.isNotNull(target.getMail())) {
			if (!Validator.isEmailAddress(target.getMail())) {
				errors.add("user-valid-mail-required");
				result = false;
				log.info("mail invalida sbagliato");
			}
		}

		if (Validator.isNull(target.getUsername())) {
			errors.add("user-username-required");
			result = false;
			log.info("usename vuoto sbagliato");
		}

		if (Validator.isNotNull(target.getPhone())) {
			if (!Validator.isPhoneNumber(target.getPhone())) {
				errors.add("user-phone-valid");
				result = false;
				log.info("telefono sbagliato");
			}
		}

		List<User> liferayUsers = UserLocalServiceUtil.getUsers(0,
				UserLocalServiceUtil.getUsersCount());

		for (int i = 0; i < UserLocalServiceUtil.getUsersCount(); i++) {
			if (liferayUsers.get(i).getScreenName()
					.equals(target.getUsername())) {
				errors.add("user-username-duplicate");
				result = false;
				log.info("username duplicato sbagliato");
			}
			if (liferayUsers.get(i).getEmailAddress().equals(target.getMail())) {
				errors.add("user-mail-duplicate");
				result = false;
				log.info("mail duplicato sbagliato " + target.getMail() + " = "
						+ liferayUsers.get(i).getEmailAddress());
			}
		}

		return result;
	}

	public static boolean validateCert(String pwd, String pwd1, String pwd2,
			List<String> errors) throws SystemException {
		boolean result = true;

		if (!pwd1.equals(pwd2)) {
			errors.add("cert-password-incorrect");
			result = false;
			log.info("Le Password devono essere uguali");
		}

		if (Validator.isNull(pwd)) {
			errors.add("key-pass-required");
			result = false;
			log.info("Inserire Password");
		}

		if (Validator.isNull(pwd1)) {
			errors.add("cert-pass1-required");
			result = false;
			log.info("Inserire Password");
		}

		if (Validator.isNull(pwd2)) {
			errors.add("cert-pass2-required");
			result = false;
			log.info("Inserire Password di controllo");
		}

		return result;
	}

	public static boolean validateUpdate(UserInfo target, UserInfo check,
			List<String> errors) throws SystemException {
		boolean result = true;

		if (Validator.isNull(target.getFirstName())) {
			errors.add("user-first-name-required");
			result = false;
			log.info("nome sbagliato");
		}

		if (Validator.isNull(target.getLastName())) {
			errors.add("user-last-name-required");
			result = false;
			log.info("cognome sbagliato");
		}

		if (Validator.isNull(target.getInstitute())) {
			errors.add("user-institute-required");
			result = false;
			log.info("istituto sbagliato");
		}

		if (Validator.isNull(target.getMail())) {
			errors.add("user-mail-required");
			result = false;
			log.info("mail sbagliato");
		}
		if (!target.getMail().equals(check.getMail())) {
			result = false;
			errors.add("user-mail-must-same");
		}

		if (Validator.isNotNull(target.getMail())) {
			if (!Validator.isEmailAddress(target.getMail())) {
				errors.add("user-valid-mail-required");
				result = false;
				log.info("mail invalida sbagliato");
			}
		}

		if (Validator.isNull(target.getUsername())) {
			errors.add("user-username-required");
			result = false;
			log.info("usename vuoto sbagliato");
		}
		if (!target.getUsername().equals(check.getUsername())) {
			result = false;
			errors.add("user-username-must-same");
		}

		if (Validator.isNotNull(target.getPhone())) {
			if (!Validator.isPhoneNumber(target.getPhone())) {
				errors.add("user-phone-valid");
				result = false;
				log.info("telefono sbagliato");
			}
		}

		return result;
	}
}
