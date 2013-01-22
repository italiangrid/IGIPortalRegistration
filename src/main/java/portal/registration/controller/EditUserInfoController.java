package portal.registration.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import org.apache.log4j.Logger;
import org.globus.gsi.GlobusCredential;
import org.globus.gsi.GlobusCredentialException;
import org.globus.gsi.gssapi.GlobusGSSCredentialImpl;
import org.globus.myproxy.MyProxy;
import org.globus.myproxy.MyProxyException;
import org.globus.util.Util;
import org.ietf.jgss.GSSCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

import it.italiangrid.portal.dbapi.domain.Notify;
import it.italiangrid.portal.dbapi.domain.UserToVo;
import it.italiangrid.portal.dbapi.domain.UserInfo;
import it.italiangrid.portal.dbapi.domain.Certificate;
import it.italiangrid.portal.dbapi.domain.Vo;
import it.italiangrid.portal.dbapi.services.CertificateService;
import it.italiangrid.portal.dbapi.services.NotifyService;
import it.italiangrid.portal.dbapi.services.UserInfoService;
import it.italiangrid.portal.dbapi.services.UserToVoService;
import it.italiangrid.portal.dbapi.services.VoService;
import it.italiangrid.portal.registration.exception.RegistrationException;
import it.italiangrid.portal.registration.util.RegistrationConfig;
import portal.registration.utils.GuseNotify;
import portal.registration.utils.GuseNotifyUtil;
import portal.registration.utils.MyValidator;
import portal.registration.utils.TokenCreator;

@Controller
@RequestMapping("view")
@SessionAttributes("userInfo")
public class EditUserInfoController {

	private static final Logger log = Logger
			.getLogger(EditUserInfoController.class);

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private NotifyService notifyService;

	@Autowired
	private CertificateService certificateService;

	@Autowired
	private UserToVoService userToVoService;
	
	@Autowired
	private VoService voService;

	@RenderMapping(params = "myaction=editUserInfoForm")
	public String showEditUserInfoForm(@RequestParam int userId,
			RenderRequest request) {
		log.error(userId);
		if ((userToVoService.findById(userId).size() == 0)) {
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
		if(active&&(userToVoService.findById(userId).size() > 0)&&(userInfo.getRegistrationComplete().equals("false")))
			activateUser(userInfo, request, errors);

		return "editUserInfoForm";
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

	@ActionMapping(params = "myaction=editUserInfo")
	public void editUserInfo(@ModelAttribute("userInfo") UserInfo userInfo,
			ActionRequest request, ActionResponse response,
			SessionStatus sessionStatus) throws SystemException {

		ArrayList<String> errors = new ArrayList<String>();
		boolean allOk = true;
		UserInfo check = userInfoService.findById(userInfo.getUserId());
		if (MyValidator.validateUpdate(userInfo, check, errors)) {

			try {
				log.info("prendo companyid");
				long companyId = PortalUtil.getCompanyId(request);
				log.info("companyid = " + companyId);
				log.info("prendo user con username = " + userInfo.getUsername());
				User user = UserLocalServiceUtil.getUserByScreenName(companyId,
						userInfo.getUsername());

				log.info("setto: nome = " + userInfo.getFirstName()
						+ " ; Cognome = " + userInfo.getLastName());

				user.setFirstName(userInfo.getFirstName());
				user.setLastName(userInfo.getLastName());

				log.info("controllo: nome = " + user.getFirstName()
						+ " ; Cognome = " + user.getLastName());

				UserLocalServiceUtil.updateUser(user);

				log.info("lifery aggiornato");

			} catch (Exception e) {
				log.info("problema update user liferay");
				errors.add("problem-update-user-liferay");
				allOk = false;
				e.printStackTrace();
			}
			if (allOk) {
				try {
					userInfoService.edit(userInfo);
				} catch (Exception e) {
					errors.add("problem-update-user");
					allOk = false;
				}
			}
		} else {
			allOk = false;
		}
		if (allOk) {
			SessionMessages.add(request, "user-updated-successufully");
		} else {
			errors.add("error-updating-user");

			for (String error : errors) {
				SessionErrors.add(request, error);
			}
		}

		PortletConfig portletConfig = (PortletConfig)request.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
		SessionMessages.add(request, portletConfig.getPortletName() + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
		
		response.setRenderParameter("myaction", "editUserInfoForm");
		response.setRenderParameter("userId",
				Integer.toString(userInfo.getUserId()));

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

	@ModelAttribute("defaultVo")
	public String getDefaultVo(@RequestParam int userId) {
		return userToVoService.findDefaultVo(userId);
	}

	@ModelAttribute("defaultFqan")
	public String getDefaultFqan(@RequestParam int userId) {
		return userToVoService.getDefaultFqan(userId);
	}

	@ModelAttribute("userInfo")
	public UserInfo getUserInfo(@RequestParam int userId) {
		log.error("############### UserInfo : "+ userId);
		return userInfoService.findById(userId);
	}

	@ModelAttribute("certList")
	public List<Certificate> getListCert(@RequestParam int userId) {
		return certificateService.findById(userId);
	}

	@ModelAttribute("userToVoList")
	public List<Vo> getUserToVoList(@RequestParam int userId) {

		return userToVoService.findVoByUserId(userId);
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

	/**
	 * Return to the portlet the list of the user's fqans.
	 * 
	 * @param userId
	 *            : the identifier of the user.
	 * @return the list of the user's fqans.
	 */
	@ModelAttribute("userFqans")
	public Map<Object, Object> getUserFqans(@RequestParam int userId) {

		UserInfo userInfo = userInfoService.findById(userId);
		List<UserToVo> utv = userToVoService.findById(userInfo.getUserId());

		Map<Object, Object> x = new Properties();

		String toParse = null;

		for (Iterator<UserToVo> iterator = utv.iterator(); iterator.hasNext();) {
			UserToVo userToVo = iterator.next();
			toParse = userToVo.getFqans();

			if ((toParse != null) && (!toParse.equals(""))) {

				x.put(userToVo.getId().getIdVo(), toParse);

			} else {
				x.put(userToVo.getId().getIdVo(), "No Roles for this VO");
			}

		}

		return x;
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
	public boolean getCertCAonline(@RequestParam int userId) {

		UserInfo userInfo = userInfoService.findById(userId);
		List<Certificate> certs = certificateService.findById(userInfo
				.getUserId());

		for (Iterator<Certificate> iterator = certs.iterator(); iterator
				.hasNext();) {
			Certificate cert = iterator.next();
			if (cert.getCaonline().equals("true"))
				return true;

		}

		return false;
	}

	/**
	 * Return to the portlet the advanced configurations of the user.
	 * 
	 * @param userId
	 *            : the identifier of the user.
	 * @return an object that contain the value of the advanced configurations.
	 */
	@ModelAttribute("advOpts")
	public Notify getAdvOpts(@RequestParam int userId) {

		UserInfo userInfo = userInfoService.findById(userId);
		
		if(notifyService.findByUserInfo(userInfo)==null)
			notifyService.save(new Notify(userInfo, "false", "12:00"));

		return notifyService.findByUserInfo(userInfo);
	}


	@ActionMapping(params = "myaction=uploadComplete")
	public void uploadComplete(ActionResponse response,
			SessionStatus sessionStatus) {

		response.setRenderParameter("myaction", "userInfos");
		sessionStatus.setComplete();

	}
	
	/**
	 * Update the user's row of the table notification.
	 * 
	 * @param notify
	 *            : the object that contain the advanced options
	 * @param request
	 *            : the request of the portlet
	 * @param response
	 *            : the response of the portlet
	 * @param sessionStatus
	 *            : the status of the portlet
	 */
	@ActionMapping(params = "myaction=updateGuseNotify")
	public void updateGuseNotify(@ModelAttribute("notification") GuseNotify guseNotify, @ModelAttribute("advOpts") Notify notify, @RequestParam int userId,
			ActionRequest request, ActionResponse response,
			SessionStatus sessionStatus) {
		
		UserInfo userInfo = userInfoService.findById(Integer.valueOf(request.getParameter("userId")));
		userInfoService.save(userInfo);
		String username = userInfo.getUsername();
		long companyId = PortalUtil.getCompanyId(request);
		
		try {
			User user = UserLocalServiceUtil.getUserByScreenName(companyId,
					username);
			
			GuseNotifyUtil guseNotifyUtil = new GuseNotifyUtil();
			
			guseNotify.setWfchgEnab((request.getParameter("wfchgEnab").equals("true")?"1":"0"));
			guseNotify.setEmailEnab((request.getParameter("wfchgEnab").equals("true")?"1":"0"));
			guseNotify.setQuotaEnab((request.getParameter("quotaEnab").equals("true")?"1":"0"));
			
			guseNotifyUtil.writeNotifyXML(user, guseNotify);
			
			
			
			
			Notify n = notifyService.findByUserInfo(userInfo);
			if(n!=null){
				
				log.debug("session id= " + notify.getIdNotify() + " retrived: "
						+ n.getIdNotify());
				n.setProxyExpire(notify.getProxyExpire());
				n.setProxyExpireTime(notify.getProxyExpireTime());
				log.debug("session value= " + notify.getProxyExpire() + " retrived: "
						+ n.getProxyExpire());
				log.error("session value= " + notify.getProxyExpireTime() + " retrived: "
						+ n.getProxyExpireTime());
			}else{
				log.debug("New entry");
				n = new Notify(userInfo, notify.getProxyExpire(), notify.getProxyExpireTime());
				log.debug("session value= " + notify.getProxyExpire() + " retrived: "
						+ n.getProxyExpire());
			}
			notifyService.save(n);
			
		} catch (PortalException e) {
			e.printStackTrace();
		} catch (SystemException e) {
			e.printStackTrace();
		}	
		response.setRenderParameter("myaction", "editUserInfoForm");
		response.setRenderParameter("userId", request.getParameter("userId"));
		
		

	}
	
	/**
	 * Return to the portlet the advanced configurations of the user.
	 * 
	 * @param request
	 *            : session parameter.
	 * @return an object that contain the value of the advanced configurations.
	 */
	@ModelAttribute("notification")
	public GuseNotify getGuseNotifications(@RequestParam int userId) {
		
		UserInfo userInfo = userInfoService.findById(userId);
		String username = userInfo.getUsername();
		long companyId = PortalUtil.getPortal().getCompanyIds()[0];
		
		log.debug("companyId " + companyId);
		
		GuseNotify guseNotify=null;
		
		try {
			User user = UserLocalServiceUtil.getUserByScreenName(companyId,
					username);
			GuseNotifyUtil guseNotifyUtil = new GuseNotifyUtil();
			guseNotify = guseNotifyUtil.readNotifyXML(user);
		} catch (PortalException e) {
			e.printStackTrace();
		} catch (SystemException e) {
			e.printStackTrace();
		}
		
		
		return guseNotify;
	}
	
	/**
	 * Update the user's row of the table notification.
	 * 
	 * @param notify
	 *            : the object that contain the advanced options
	 * @param request
	 *            : the request of the portlet
	 * @param response
	 *            : the response of the portlet
	 * @param sessionStatus
	 *            : the status of the portlet
	 */
	@ActionMapping(params = "myaction=updateAdvOpts")
	public void updateAdvOpts(@ModelAttribute("advOpts") Notify notify,
			ActionRequest request, ActionResponse response,
			SessionStatus sessionStatus) {
		UserInfo userInfo = userInfoService.findById(Integer.parseInt(request.getParameter("userId")));
		Notify n = notifyService.findByUserInfo(userInfo);
		if(n!=null){
			
			log.debug("session id= " + notify.getIdNotify() + " retrived: "
					+ n.getIdNotify());
			n.setProxyExpire(notify.getProxyExpire());
			n.setProxyExpireTime(notify.getProxyExpireTime());
			log.debug("session value= " + notify.getProxyExpire() + " retrived: "
					+ n.getProxyExpire());
			log.error("session value= " + notify.getProxyExpireTime() + " retrived: "
					+ n.getProxyExpireTime());
		}else{
			log.debug("New entry");
			n = new Notify(userInfo, notify.getProxyExpire(), notify.getProxyExpireTime());
			log.debug("session value= " + notify.getProxyExpire() + " retrived: "
					+ n.getProxyExpire());
		}
		notifyService.save(n);
		
		response.setRenderParameter("myaction", "editUserInfoForm");
		response.setRenderParameter("userId", request.getParameter("userId"));

	}
	
	@ModelAttribute("tokens")
	public List<String> getTokens(@RequestParam int userId) {

		UserInfo userInfo = userInfoService.findById(userId);	
		List<String> tokens = TokenCreator.getToken(userInfo.getMail());
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

		log.info("dove sono:" + contextPath);
		
		String timesProperties = null;

		File test = new File(contextPath + "/content/Registration.properties");
		log.info("File: " + test.getAbsolutePath());
		if (test.exists()) {
			log.info("ESISTE!!");
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
	
//	changePwd
	@ActionMapping(params = "myaction=changePwd")
	public void updateGuseNotify(ActionRequest request, ActionResponse response) {
		
		Certificate cert = certificateService.findByIdCert(Integer.parseInt(request.getParameter("idCert")));
		
		String pwd = request.getParameter("pwd");
		
		ArrayList<String> errors = new ArrayList<String>();
		boolean allOk = true;
		
		if(pwd.equals(request.getParameter("confirmpwd"))){
			
			
			
			
			try {
				
				MyProxy mp = new MyProxy(RegistrationConfig.getProperties("Registration.properties", "myproxy.storage"), 7512);
				UserInfo userInfo =userInfoService.findById(Integer.parseInt(request.getParameter("userId")));
				Long companyId = PortalUtil.getCompanyId(request);
				User user = UserLocalServiceUtil.getUserByEmailAddress(companyId, userInfo.getMail());

				String dir = System.getProperty("java.io.tmpdir");
				log.error("Directory = " + dir);

				File location = new File(dir + "/users/" + user.getUserId() + "/");
				if (!location.exists()) {
					location.mkdirs();
				}

				OutputStream out = null;
				
				File proxyFile = new File(dir + "/users/" + user.getUserId()
						+ "/x509up");
				
				String tmpPwd ="";
				
				byte[] bytesOfMessage;
				if(userInfo.getPersistentId()!=null)
					bytesOfMessage = (userInfo.getPersistentId() + RegistrationConfig.getProperties("Registration.properties", "proxy.secret")).getBytes("UTF-8");
				else
					bytesOfMessage = ("blablabla" + RegistrationConfig.getProperties("Registration.properties", "proxy.secret")).getBytes("UTF-8");
				
				MessageDigest md = MessageDigest.getInstance("MD5");
				byte[] thedigest = md.digest(bytesOfMessage);
				
				Formatter formatter = new Formatter();
			    for (byte b : thedigest)
			    {
			        formatter.format("%02x", b);
			    }
			    tmpPwd = formatter.toString();
			    formatter.close();
				
				log.error("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
				log.error(tmpPwd);
				log.error("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
				
				GSSCredential proxy;
				
					proxy = mp.get(cert.getUsernameCert(), tmpPwd, 608400);
				
					
				
				log.error("----- All ok -----");
				log.error("Proxy:" + proxy.toString());

				GlobusCredential globusCred = null;
				globusCred = ((GlobusGSSCredentialImpl) proxy)
						.getGlobusCredential();
				log.error("----- Passo per il istanceof GlobusGSSCredentialImpl");

				log.error("Save proxy file: " + globusCred);
				out = new FileOutputStream(proxyFile);
				Util.setFilePermissions(proxyFile.toString(), 600);
				globusCred.save(out);
				

				
//				String myproxy = "/usr/bin/python /upload_files/myproxy2.py "
//						+ cert.getUsernameCert()
//						+ " "
//						+ proxyFile.toString()
//						+ " "
//						+ proxyFile.toString()
//						+ " \""
//						+ pwd + "\" \"" + pwd+"\"";
//				log.error("Myproxy command = " + myproxy);
				
				String[] myproxy2 = {"/usr/bin/python", "/upload_files/myproxy3.py", cert.getUsernameCert(), proxyFile.toString(), proxyFile.toString(), pwd, pwd};
				String[] env = {"GT_PROXY_MODE=old"};
				Process p = Runtime.getRuntime().exec(myproxy2, env, location);
				InputStream stdout = p.getInputStream();
				InputStream stderr = p.getErrorStream();

				BufferedReader output = new BufferedReader(
						new InputStreamReader(stdout));
				String line = null;

				while (((line = output.readLine()) != null)) {

					log.error("[Stdout] " + line);
					if (line.equals("myproxy success")) {
						log.error("myproxy ok");
					} else {
						if (line.equals("myproxy verify password failure")) {
							errors.add("error-password-mismatch");
							log.error(line);
							allOk = false;
						} else {
							if (line.equals("myproxy password userkey failure")) {
								errors.add("error-password-mismatch");
								log.error(line);
								allOk = false;
							} else {
								if (line.equals("too short passphrase")) {
									errors.add("error-password-too-short");
									log.error(line);
									allOk = false;
								} else {
									if (line.equals("key password failure")) {
										errors.add("key-password-failure");
										log.error(line);
										allOk = false;
									} else {
										errors.add("no-valid-key");
										log.error(line);
										allOk = false;
									}
								}
							}
						}
					}
				}
				output.close();

				BufferedReader brCleanUp = new BufferedReader(
						new InputStreamReader(stderr));
				while ((line = brCleanUp.readLine()) != null) {
					allOk = false;
					log.error("[Stderr] " + line);
					
				}
				
				brCleanUp.close();
				if(allOk){
					cert.setPasswordChanged("true");
					
					certificateService.save(cert);
					
					if ((userToVoService.findById(userInfo.getUserId()).size() > 0))
						activateUser(userInfo, request, errors);
					
					response.setRenderParameter("myaction", "home");
					response.setRenderParameter("userId", request.getParameter("userId"));
					
					return;
				}
			} catch (IOException e1) {
				allOk = false;
				errors.add("myproxy-exception");
				e1.printStackTrace();
			} catch (RegistrationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (PortalException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (SystemException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (MyProxyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				errors.add("myproxy-exception");
			} 
			
		}else{
			SessionErrors.add(request, "error-password-mismatch");
			
		}
		
		for(String s: errors)
			SessionErrors.add(request, s);
		PortletConfig portletConfig = (PortletConfig)request.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
		SessionMessages.add(request, portletConfig.getPortletName() + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
		response.setRenderParameter("myaction", "editUserInfoForm");
		response.setRenderParameter("userId", request.getParameter("userId"));

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
	
	@ModelAttribute("proxyDownloaded")
	public boolean getProxyDownloaded(RenderRequest request) {

		User user = (User) request.getAttribute(WebKeys.USER);
		if (user != null) {
			String dir = System.getProperty("java.io.tmpdir");
			log.error("Directory = " + dir);
			
			UserInfo userInfo = userInfoService.findByUsername(user.getScreenName());
			List<Vo> vos = userToVoService.findVoByUserId(userInfo
					.getUserId());
			File proxyVoFile = null;
			for (Iterator<Vo> iterator = vos.iterator(); iterator
					.hasNext();) {
				Vo vo = (Vo) iterator.next();
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
		return false;
	}
	
	@ModelAttribute("caEnabled")
	public String getCaEnabled() {	
		try {
			log.error(RegistrationConfig.getProperties("Registration.properties", "CAOnline.enabled"));
			return RegistrationConfig.getProperties("Registration.properties", "CAOnline.enabled");
		} catch (RegistrationException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@ModelAttribute("proxyEnabled")
	public String getProxyEnabled() {	
		try {
			log.error(RegistrationConfig.getProperties("Registration.properties", "proxy.enabled"));
			return RegistrationConfig.getProperties("Registration.properties", "proxy.enabled");
		} catch (RegistrationException e) {
			e.printStackTrace();
		}
		return null;
	}

}
