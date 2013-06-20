package portal.registration.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.log4j.Logger;
import org.globus.gsi.GlobusCredential;
import org.globus.gsi.GlobusCredentialException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import portal.registration.utils.GuseNotify;
import portal.registration.utils.GuseNotifyUtil;
import portal.registration.utils.TokenCreator;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

import it.italiangrid.portal.dbapi.domain.Certificate;
import it.italiangrid.portal.dbapi.domain.Idp;
import it.italiangrid.portal.dbapi.domain.Notify;
import it.italiangrid.portal.dbapi.domain.UserInfo;
import it.italiangrid.portal.dbapi.domain.UserToVo;
import it.italiangrid.portal.dbapi.domain.Vo;
import it.italiangrid.portal.dbapi.services.CertificateService;
import it.italiangrid.portal.dbapi.services.IdpService;
import it.italiangrid.portal.dbapi.services.NotifyService;
import it.italiangrid.portal.dbapi.services.UserInfoService;
import it.italiangrid.portal.dbapi.services.UserToVoService;
import it.italiangrid.portal.dbapi.services.VoService;
import it.italiangrid.portal.registration.exception.RegistrationException;
import it.italiangrid.portal.registration.model.RegistrationModel;
import it.italiangrid.portal.registration.util.RegistrationConfig;

@Controller("userInfoController")
@RequestMapping(value = "VIEW")
public class UserInfoController {
	
	private static final Logger log = Logger
			.getLogger(UserInfoController.class);

	private static String search = null;

	public static void setSearch(String search2) {
		search = search2;
	}

	@ModelAttribute("search")
	public String getSearch() {

		return search;

	}

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private NotifyService notifyService;
	
	@Autowired
	private IdpService idpService;

	@Autowired
	private UserToVoService userToVoService;

	@Autowired
	private CertificateService certificateService;
	
	@Autowired
	private VoService voService;

	@RenderMapping
	public String showUserInfos(RenderRequest request, RenderResponse response) {
		
//		try {
//			User user = PortalUtil.getUser(request);
//			if(user!=null){
//				activateUser(user, request);
//			}
//		} catch (PortalException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SystemException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		log.info("home controller");
		User user = (User) request.getAttribute(WebKeys.USER);
		if(user!=null){
			long companyId = PortalUtil.getCompanyId(request);
			
			Role roleAdmin;
			try {
				roleAdmin = RoleLocalServiceUtil.getRole(companyId, "Administrator");
				
				List<Role> roles = user.getRoles();
				
				for (Role role : roles) {
					if (role.equals(roleAdmin)){
						log.info("home.jsp");
						return "home";
					}
				}
				log.info("mando a editUserInfoForm.jsp");
				return "editUserInfoForm";
			
			} catch (PortalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return "home";
	}

	@RenderMapping(params = "myaction=home")
	public String showUserInfos2(RenderRequest request) {
		User user = (User) request.getAttribute(WebKeys.USER);
		if(user!=null){
			long companyId = PortalUtil.getCompanyId(request);
			
			Role roleAdmin;
			try {
				roleAdmin = RoleLocalServiceUtil.getRole(companyId, "Administrator");
				
				List<Role> roles = user.getRoles();
				
				for (Role role : roles) {
					if (role.equals(roleAdmin)){
						return "home";
					}
				}
				
				return "editUserInfoForm";
			
			} catch (PortalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return "home";
	}
	

	@ModelAttribute("goToWAYF")
	public String goToWAYF(){
		try {
			return RegistrationConfig.getProperties("Registration.properties", "retrieve.user.information");
		} catch (RegistrationException e) {
			e.printStackTrace();
		}
		return null;
	}
	@ModelAttribute("goToHome")
	public String goToHome(){
		try {
			return RegistrationConfig.getProperties("Registration.properties", "home.url");
		} catch (RegistrationException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@ModelAttribute("registrationModel")
	public RegistrationModel getRegistrationModel() {
		log.info("Initialize registration process.");	
		return new RegistrationModel();
	} 

	@ModelAttribute("userInfos")
	public List<UserInfo> getUserInfos() {
		if (search == null || search.equals(""))
			return userInfoService.getAllUserInfo();
		else
			return userInfoService.getAllUserInfoByName(search);
	}

	@ModelAttribute("idps")
	public List<Idp> getIdps() {
		return idpService.getAllIdp();
	}

	@ModelAttribute("userId")
	public int getUserId(RenderRequest request) {

		User user = (User) request.getAttribute(WebKeys.USER);
		if (user != null) {
			return userInfoService.findByUsername(user.getScreenName())
					.getUserId();
		}
		return 0;
	}
	
	@ModelAttribute("voList")
	public String getVoList() {
		
		List<Vo> vos = voService.getAllVo();
		
		String result="";
		
		for(int i=0; i<vos.size()-1; i++){
			result += "\""+vos.get(i).getVo()+"\", ";
		}
		result += "\""+vos.get(vos.size()-1).getVo()+"\"";
		return result;
	}

	@ModelAttribute("idpsName")
	public Map<Object, Object> getIdpsName() {

		Map<Object, Object> x = new Properties();

		List<UserInfo> users = userInfoService.getAllUserInfo();

		for (int i = 0; i < users.size(); i++) {

			x.put(users.get(i).getUserId(),
					idpService.findByIdp(users.get(i).getIdp()));
		}
		return x;
	}
	
	@ModelAttribute("isUserActive")
	public String getIsUserActive(RenderRequest request) {
		User user = (User) request.getAttribute(WebKeys.USER);
		if (user != null) {
			int userId = userInfoService.findByUsername(user.getScreenName())
					.getUserId();
		
			log.info(userId);
			
			
			List<Vo> vos = userToVoService.findVoByUserId(userId);
			int activeVo = 0;
			for(Vo vo: vos){
				if(vo.getConfigured().equals("true"))
					activeVo++;
			}
			
			if (activeVo == 0) {
				deactivateUser(userId, request);
	
			}
			
			boolean active = true;
			for(Certificate c: certificateService.findById(userId)){
				if(c.getPasswordChanged().equals("false")){
					deactivateUser(userId, request);
					active= false;
				}
			}
			List<String> errors = new ArrayList<String>();
			UserInfo userInfo = userInfoService.findById(userId);
			log.info(active);
			log.info(activeVo > 0);
			log.info(userInfo.getRegistrationComplete().equals("false"));
			log.info(active&&(activeVo > 0)&&(userInfo.getRegistrationComplete().equals("false")));
			if(active&&(activeVo > 0)&&(userInfo.getRegistrationComplete().equals("false")))
				activateUser(userInfo, request, errors);
			
			return userInfoService.findById(userId).getRegistrationComplete();
		}
		return null;
	}
	
	private void deactivateUser(int userId, RenderRequest request) {

		UserInfo userInfo = userInfoService.findById(userId);

		if (userInfo.getRegistrationComplete().equals("true")) {
			String username = userInfo.getUsername();

			long companyId = PortalUtil.getCompanyId(request);

			User user;
			try {
				user = UserLocalServiceUtil.getUserByScreenName(companyId,
						username);

				Role rolePowerUser = RoleLocalServiceUtil.getRole(companyId,
						"Power User");

				UserLocalServiceUtil.deleteRoleUser(rolePowerUser.getRoleId(),
						user.getUserId());

				userInfo.setRegistrationComplete("false");

				userInfoService.edit(userInfo);

			} catch (PortalException e) {
				PortletConfig portletConfig = (PortletConfig)request.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
				SessionMessages.add(request, portletConfig.getPortletName() + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
				SessionErrors.add(request, "exception-deactivation-user");
				e.printStackTrace();
			} catch (SystemException e) {
				PortletConfig portletConfig = (PortletConfig)request.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
				SessionMessages.add(request, portletConfig.getPortletName() + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
				SessionErrors.add(request, "exception-deactivation-user");
				e.printStackTrace();
			}
			SessionMessages.add(request, "user-deactivate");
		}

	}
	
	private void activateUser(UserInfo userInfo, RenderRequest request,
			List<String> errors) {
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

	@ModelAttribute("defaultVo")
	public String getDefaultVo(RenderRequest request) {

		User user = (User) request.getAttribute(WebKeys.USER);
		if (user != null) {
			int userId = userInfoService.findByUsername(user.getScreenName())
					.getUserId();
			return userToVoService.findDefaultVo(userId);
		}
		return null;
	}

	@ModelAttribute("defaultFqan")
	public String getDefaultFqan(RenderRequest request) {
		User user = (User) request.getAttribute(WebKeys.USER);
		if (user != null) {
			int userId = userInfoService.findByUsername(user.getScreenName())
					.getUserId();
			return userToVoService.getDefaultFqan(userId);
		}
		return null;
	}

	@ModelAttribute("selectedUser")
	public UserInfo getSelectedUser(RenderRequest request) {
		User user = (User) request.getAttribute(WebKeys.USER);
		if (user != null) {
			int userId = userInfoService.findByUsername(user.getScreenName())
					.getUserId();
			return userInfoService.findById(userId);
		}
		return null;
	}

	@ModelAttribute("certList")
	public List<Certificate> getListCert(RenderRequest request) {
		User user = (User) request.getAttribute(WebKeys.USER);
		if (user != null) {
			int userId = userInfoService.findByUsername(user.getScreenName())
					.getUserId();
			return certificateService.findById(userId);
		}
		return null;
	}

	@ModelAttribute("userToVoList")
	public List<Vo> getUserToVoList(RenderRequest request) {
		User user = (User) request.getAttribute(WebKeys.USER);
		if (user != null) {
			int userId = userInfoService.findByUsername(user.getScreenName())
					.getUserId();
			return userToVoService.findVoByUserId(userId);
		}
		return null;
	}

	/**
	 * Return to the portlet the list of the user's fqans.
	 * 
	 * @param request
	 *            : session parameter.
	 * @return the list of the user's fqans.
	 */
	@ModelAttribute("userFqans")
	public Map<Object, Object> getUserFqans(RenderRequest request) {

		User user = (User) request.getAttribute(WebKeys.USER);
		if (user != null) {
			UserInfo userInfo = userInfoService.findByUsername(user
					.getScreenName());
			List<UserToVo> utv = userToVoService.findById(userInfo.getUserId());

			Map<Object, Object> x = new Properties();

			String toParse = null;

			for (Iterator<UserToVo> iterator = utv.iterator(); iterator
					.hasNext();) {
				UserToVo userToVo = iterator.next();
				toParse = userToVo.getFqans();
				if (toParse != null) {
					x.put(userToVo.getId().getIdVo(), toParse);

				} else {
					x.put(userToVo.getId().getIdVo(), "No Roles for this VO");
				}

			}

			return x;
		}
		return null;
	}

	/**
	 * Return to the portlet true if the user certificate was released by the CA
	 * online.
	 * 
	 * @param userId
	 *            : the identifier of the user.
	 * @return true if the user certificate was released by the CA online.
	 */
	@ModelAttribute("certCAonline")
	public boolean getCertCAonline(RenderRequest request) {
		User user = (User) request.getAttribute(WebKeys.USER);
		if (user != null) {
			int userId = userInfoService.findByUsername(user.getScreenName())
					.getUserId();
			UserInfo userInfo = userInfoService.findById(userId);
			List<Certificate> certs = certificateService.findById(userInfo
					.getUserId());
	
			for (Iterator<Certificate> iterator = certs.iterator(); iterator
					.hasNext();) {
				Certificate cert = iterator.next();
				if (cert.getCaonline().equals("true"))
					return true;
	
			}
		}

		return false;
	}
	
	/**
	 * Return to the portlet the advanced configurations of the user.
	 * 
	 * @param request
	 *            : session parameter.
	 * @return an object that contain the value of the advanced configurations.
	 */
	@ModelAttribute("notification")
	public GuseNotify getGuseNotifications(RenderRequest request) {
		
		GuseNotify guseNotify=null;
		
		User user = (User) request.getAttribute(WebKeys.USER);
		if(user!=null){
			GuseNotifyUtil guseNotifyUtil = new GuseNotifyUtil();
			guseNotify = guseNotifyUtil.readNotifyXML(user);
		}
		return guseNotify;
	}
	

	/**
	 * Return to the portlet the advanced configurations of the user.
	 * 
	 * @param userId
	 *            : the identifier of the user.
	 * @return an object that contain the value of the advanced configurations.
	 */
	@ModelAttribute("advOpts")
	public Notify getAdvOpts(RenderRequest request) {
		User user = (User) request.getAttribute(WebKeys.USER);
		if (user != null) {
			UserInfo userInfo = userInfoService.findByUsername(user.getScreenName());
			if(notifyService.findByUserInfo(userInfo)==null){
				notifyService.save(new Notify(userInfo, "false", "12:00"));
			}
			return notifyService.findByUserInfo(userInfo);
		}
		return null;
	}
	
	@ModelAttribute("tokens")
	public List<String> getTokens(RenderRequest request) {
		List<String> tokens = null;
		User user = (User) request.getAttribute(WebKeys.USER);
		if (user != null) {	
			tokens = TokenCreator.getToken(user.getEmailAddress());
		}
		return tokens;
	}
	
	@ModelAttribute("expirationTime")
	public String[] getExpirationTime() {

		/*
		 * 1 prendi file
		 * 2 leggi prop proxy.expiration.times
		 * 3 parsa e metti in array
		 */
		
		
		//1
		
		String contextPath = UploadCertController.class.getClassLoader()
				.getResource("").getPath();
		
		String timesProperties = null;

		File test = new File(contextPath + "/content/Registration.properties");
		if (test.exists()) {
			
			try {
				FileInputStream inStream = new FileInputStream(contextPath
						+ "/content/Registration.properties");

				Properties prop = new Properties();

				prop.load(inStream);

				inStream.close();
				
				//2
				timesProperties = prop.getProperty("proxy.expiration.times");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//3
		String[] results = timesProperties.trim().split(",");
 		
		return results;
	}
	
	@ModelAttribute("usePortalURL")
	public String getUsePortalURL() {

		try {
			return RegistrationConfig.getProperties("Registration.properties", "jobs.url");
		} catch (RegistrationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}
	
	
	@ModelAttribute("idpEnabled")
	public String getIdpEnabled(){
		try {
			return RegistrationConfig.getProperties("Registration.properties", "idp.enabled");
		} catch (RegistrationException e) {
			e.printStackTrace();
		}
		return "false";
	}
	
	@ModelAttribute("caEnabled")
	public String getCaEnabled() {	
		try {
			log.info(RegistrationConfig.getProperties("Registration.properties", "CAOnline.enabled"));
			return RegistrationConfig.getProperties("Registration.properties", "CAOnline.enabled");
		} catch (RegistrationException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@ModelAttribute("proxyEnabled")
	public String getProxyEnabled() {	
		try {
			log.info(RegistrationConfig.getProperties("Registration.properties", "proxy.enabled"));
			return RegistrationConfig.getProperties("Registration.properties", "proxy.enabled");
		} catch (RegistrationException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@ModelAttribute("proxyDownloaded")
	public boolean getProxyDownloaded(RenderRequest request) {

		User user = (User) request.getAttribute(WebKeys.USER);
		if (user != null) {
			String dir = System.getProperty("java.io.tmpdir");
			log.info("Directory = " + dir);
			
			UserInfo userInfo = userInfoService.findByUsername(user.getScreenName());
			List<Vo> vos = userToVoService.findVoByUserId(userInfo
					.getUserId());
			File proxyVoFile = null;
			for (Vo vo: vos) {
				if(vo.getConfigured().equals("true")){
					log.info(vo.getVo() + " enabled");
					proxyVoFile = new File(dir + "/users/"
							+ user.getUserId() + "/x509up."
							+ vo.getVo());
	
					if (proxyVoFile.exists()&&vo.getConfigured().equals("true")) {
						try {
							GlobusCredential cred = new GlobusCredential(
									proxyVoFile.toString());
							if (cred.getTimeLeft() > 0) {
								return true;
							} else {
								proxyVoFile.delete();
								SessionMessages.add(request, "proxy-expired-deleted");
							}
						} catch (GlobusCredentialException e) {
							e.printStackTrace();
							log.info("e mò sono cazzi amari");
						}
					}
				}
			}

		}
		return false;
	}

}
