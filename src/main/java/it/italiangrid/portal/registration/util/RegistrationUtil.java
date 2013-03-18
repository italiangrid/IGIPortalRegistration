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
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.portlet.ActionRequest;
import javax.portlet.RenderRequest;

import org.apache.log4j.Logger;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
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
			UserInfo userInfo, RegistrationModel registrationModel, boolean verify)
			throws RegistrationException {
		
			long companyId = PortalUtil.getCompanyId(request);

			ThemeDisplay themeDisplay = (ThemeDisplay) request
					.getAttribute(WebKeys.THEME_DISPLAY);

			try {
				addUserToLiferay(companyId, themeDisplay, ServiceContextFactory.getInstance(User.class.getName(), request), userInfo, registrationModel, verify);
			} catch (PortalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
	
	public static void addUserToLiferay(RenderRequest request,
			UserInfo userInfo, RegistrationModel registrationModel, boolean verify)
			throws RegistrationException {
		
			long companyId = PortalUtil.getCompanyId(request);

			ThemeDisplay themeDisplay = (ThemeDisplay) request
					.getAttribute(WebKeys.THEME_DISPLAY);
			
			try {
				addUserToLiferay(companyId, themeDisplay, ServiceContextFactory.getInstance(User.class.getName(), request), userInfo, registrationModel, verify);
			} catch (PortalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


	}
	
	private static void addUserToLiferay(long companyId, ThemeDisplay themeDisplay, ServiceContext scf, UserInfo userInfo, RegistrationModel registrationModel, boolean verify)
			throws RegistrationException{
		
		try {
	
			long[] groupIds = { themeDisplay.getLayout().getGroupId() };

			log.debug("companyid = " + companyId);
			log.debug("settate variabili di supporto ora si aggiunge un utenti a liferay!!");

			User u = UserLocalServiceUtil.addUser(0L, companyId, true,
					PASSWORD, PASSWORD, false, userInfo.getUsername(), userInfo
							.getMail(), 0L, "", new Locale("en"), userInfo
							.getFirstName(), "", userInfo.getLastName(), 0, 0,
					true, Calendar.JANUARY, 1, 1970, "", groupIds, null, null,
					null, true, scf);
			
			log.error("verify = "+verify);
			if(verify){
				UserLocalServiceUtil.sendEmailAddressVerification(u, u.getEmailAddress(), scf);
			}
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

		String persistentId = userInfo.getPersistentId();
		if(persistentId == null){
			userInfo.setPersistentId(userInfo.getUsername());
		}
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

		for (String idVo : registrationModel.getVos().split("#")) {
			log.debug("Aggiunto userToVo");
			userToVoService.save(userInfo.getUserId(), Integer.parseInt(idVo),
					registrationModel.getSubject());
			userToVoService.setDefault(userInfo.getUserId(), Integer.parseInt(idVo));
		}

	}

	public static void activateUser(UserInfo userInfo,
			UserInfoService userInfoService) {
		log.debug("UserActivated");
		userInfo.setRegistrationComplete("true");
		userInfoService.save(userInfo);
		
	}
	
	 public static String INITCTX = "com.sun.jndi.ldap.LdapCtxFactory";

	  public static String MY_HOST = "ldap://idp-portal.italiangrid.it:389";

	  public static String MGR_DN = "cn=Directory Manager";

	  public static String MGR_PW = "02o3708jt0";

	  public static String MY_SEARCHBASE = "ou=People,dc=italiangrid,dc=it";

	public static void insertIntoIDP(UserInfo userInfo,
			RegistrationModel registrationModel) throws RegistrationException {
		log.debug("inserimento utente nell'IDP");
		
	    
	    String[] dnParts = registrationModel.getSubject().split("/");
		String o = "";
		String l = "";
		String cn = "";
		
		for(String value: dnParts){
			if(value.contains("O="))
				o = value.replace("O=", "");
			if(value.contains("L="))
				l = value.replace("L=", "");
			if(value.contains("CN="))
				cn = value.replace("CN=", "");
		}
		
//		cn += " IGP";
	    
	    String uid = (userInfo.getFirstName()+"."+userInfo.getLastName()).toLowerCase();
	    
	    try {
	    	InitialContext ctx = getLDAPContext(  
	    			"cn=Directory Manager", "02o3708jt0", "idp-portal.italiangrid.it:389"); 
		    DirContext dirContext = (DirContext)ctx;
		    
		    Attributes matchAttrs = new BasicAttributes(true);  
		    
	        matchAttrs.put(new BasicAttribute("uid", uid));
	        matchAttrs.put(new BasicAttribute("cn", cn));             
	        matchAttrs.put(new BasicAttribute("givenName", userInfo.getFirstName()));             
	        matchAttrs.put(new BasicAttribute("sn", userInfo.getLastName()));  
	        if(!l.isEmpty()){
	        	matchAttrs.put(new BasicAttribute("l", l)); 
	        }
	        if(!o.isEmpty()){
	        	matchAttrs.put(new BasicAttribute("o", o)); 
	        }
	        matchAttrs.put(new BasicAttribute("mail", userInfo.getMail()));
	        matchAttrs.put(new BasicAttribute("userPassword", "password"));          
	        matchAttrs.put(new BasicAttribute("objectClass","inetOrgPerson"));
//	        matchAttrs.put(new BasicAttribute("objectClass","eduPerson"));
	    
	        String name="cn="+cn;
	        
//			ctx.bind("cn="+cn+"2,"+MY_SEARCHBASE, matchAttrs);
	        InitialDirContext iniDirContext = (InitialDirContext)dirContext;  
	        iniDirContext.bind(name,dirContext,matchAttrs);
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RegistrationException("ldap-error");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RegistrationException("ldap-error");
		}
	}
	
	 private static InitialContext getLDAPContext(String dn,   
             String userpassword, String server) throws Exception  
     {  
         String baseDN = "ou=People,dc=italiangrid,dc=it";  
//		 String baseDN = "";
         Properties props = new Properties();  
         props.put(Context.INITIAL_CONTEXT_FACTORY,   
                 "com.sun.jndi.ldap.LdapCtxFactory");  
         props.put(Context.PROVIDER_URL, "ldap://" + server + "/" + baseDN);  
         props.put(Context.SECURITY_CREDENTIALS, userpassword);  
         props.put(Context.SECURITY_PRINCIPAL, dn);  
         log.error(props.toString());
         return new InitialDirContext(props);  
 } 

}
