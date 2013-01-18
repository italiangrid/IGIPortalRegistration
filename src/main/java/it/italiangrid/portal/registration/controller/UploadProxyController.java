package it.italiangrid.portal.registration.controller;

import it.italiangrid.portal.dbapi.domain.Certificate;
import it.italiangrid.portal.dbapi.domain.UserInfo;
import it.italiangrid.portal.dbapi.domain.Vo;
import it.italiangrid.portal.dbapi.services.CertificateService;
import it.italiangrid.portal.dbapi.services.NotifyService;
import it.italiangrid.portal.dbapi.services.UserInfoService;
import it.italiangrid.portal.dbapi.services.UserToVoService;
import it.italiangrid.portal.dbapi.services.VoService;
import it.italiangrid.portal.registration.exception.RegistrationException;
import it.italiangrid.portal.registration.model.RegistrationModel;
import it.italiangrid.portal.registration.util.RegistrationConfig;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.log4j.Logger;
import org.globus.gsi.GlobusCredential;
import org.globus.gsi.gssapi.GlobusGSSCredentialImpl;
import org.globus.myproxy.MyProxy;
import org.globus.myproxy.MyProxyException;
import org.globus.util.Util;
import org.ietf.jgss.GSSCredential;
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
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

@Controller(value = "uploadProxyController")
@RequestMapping(value = "VIEW")
public class UploadProxyController {
	private static final Logger log = Logger
			.getLogger(UploadProxyController.class);
	@Autowired
	private NotifyService notifyService;
	
	@Autowired
	private CertificateService certificateService;
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private UserToVoService userToVoService;
	
	@Autowired
	private VoService voService;
	
	
	@RenderMapping(params = "myaction=showUploadProxy")
	public String showAskForCertificate() {
		log.error("Show uploadCertificate.jsp");
		return "uploadProxy";
	}
	

	
	@ModelAttribute("loginUrl")
	public String getLoginUrl() {	
		try {
			return RegistrationConfig.getProperties("Registration.properties", "login.url");
		} catch (RegistrationException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@ModelAttribute("selectedVos")
	public List<Vo> getSelectedVo(@ModelAttribute RegistrationModel registrationModel){
 
		//RegistrationModel registrationModel = (RegistrationModel) request.getAttribute("registrationModel");
		
		List<Vo> result = new ArrayList<Vo>();
		if(!registrationModel.getVos().isEmpty())
			for(String id : registrationModel.getVos().split("#"))
				result.add(voService.findById(Integer.parseInt(id)));
		
		//request.setAttribute("registrationModel", registrationModel);
		
		return result;
	}
	
	@ActionMapping(params = "myaction=uploadProxy")
	public void uploadCert(@ModelAttribute RegistrationModel registrationModel, ActionRequest request, ActionResponse response,
			SessionStatus sessionStatus) throws PortalException,
			SystemException {
		
		log.error(registrationModel);
		
		
		ArrayList<String> errors = new ArrayList<String>();
		boolean allOk = true;

		
		
		
		
		String pwd1 = request.getParameter("password");
		String pwd2 = request.getParameter("passwordVerify");
		
		Certificate cert = certificateService.findBySubject(registrationModel.getSubject());

		if(pwd1.equals(pwd2)){
	
			

					try {
						
						MyProxy mp = new MyProxy(RegistrationConfig.getProperties("Registration.properties", "myproxy.storage"), 7512);
//						UserInfo userInfo =userInfoService.findByMail(registrationModel.getEmail());
						Long companyId = PortalUtil.getCompanyId(request);
						User user = UserLocalServiceUtil.getUserByEmailAddress(companyId, registrationModel.getEmail());

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
							
							if(registrationModel.isHaveIDP()){
								
								UserInfo userInfo = userInfoService.findByMail(registrationModel.getEmail());
								bytesOfMessage = (userInfo.getPersistentId() + RegistrationConfig.getProperties("Registration.properties", "proxy.secret")).getBytes("UTF-8");
								
							} else {
								bytesOfMessage = ("blablabla"+ RegistrationConfig.getProperties("Registration.properties", "proxy.secret")).getBytes("UTF-8");
							}
							
							MessageDigest md = MessageDigest.getInstance("MD5");
							byte[] thedigest = md.digest(bytesOfMessage);
							
							Formatter formatter = new Formatter();
						    for (byte b : thedigest)
						    {
						        formatter.format("%02x", b);
						    }
						    tmpPwd = formatter.toString();
						    formatter.close();
							
//							tmpPwd = new String(thedigest);
							
							log.error("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
							log.error(tmpPwd);
							log.error("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
						
						GSSCredential proxy = mp.get(registrationModel.getCertificateUserId(), tmpPwd, 608400);
						
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
						

						
						String myproxy = "/usr/bin/python /upload_files/myproxy2.py "
								+ registrationModel.getCertificateUserId()
								+ " "
								+ proxyFile.toString()
								+ " "
								+ proxyFile.toString()
								+ " \""
								+ pwd1 + "\" \"" + pwd1+"\"";
						log.error("Myproxy command = " + myproxy);
						
						String[] myproxy2 = {"/usr/bin/python", "/upload_files/myproxy3.py", registrationModel.getCertificateUserId(), proxyFile.toString(), proxyFile.toString(), pwd1, pwd1};
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
							errors.add("no-valid-key");
						}
						if (!allOk) {
							
							errors.add("myproxy-error");
						}
						brCleanUp.close();
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
					} catch (MyProxyException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		}else{
			allOk=false;
			errors.add("error-password-mismatch");
		}	
			
		

		log.error("controllo errori");
		if (allOk && errors.isEmpty()) {

			log.error("tutto ok!!");
			
			cert.setPasswordChanged("true");
			
			
			log.error("€€€€€€€€€€€€€€€€€€€€€€€€€€€€€"+cert.getPasswordChanged());
			certificateService.save(cert);
			
			Certificate c = certificateService.findByIdCert(cert.getIdCert());
			
			log.error("€€€€€€€€€€€€€€€€€€€€€€€€€€€€€"+c.getPasswordChanged());
			UserInfo userInfo = userInfoService.findByMail(registrationModel.getEmail());
			if ((userToVoService.findById(userInfo.getUserId()).size() > 0))
				activateUser(userInfo, request, errors);
			
			
			try {
				URL url;
				if(registrationModel.isVerifyUser()){
					log.error(RegistrationConfig.getProperties("Registration.properties", "home.url"));
					url = new URL(RegistrationConfig.getProperties("Registration.properties", "home.url"));
				}else{
					log.error(RegistrationConfig.getProperties("Registration.properties", "login.url"));
					url = new URL(RegistrationConfig.getProperties("Registration.properties", "login.url"));
				}
				log.error(url);
				response.sendRedirect(url.toString());
			} catch (IOException e) {
				e.printStackTrace();
			} catch (RegistrationException e) {
				e.printStackTrace();
			}
			
			SessionMessages.add(request, "upload-cert-successufully");
			registrationModel.setCertificateStatus(true);
			request.setAttribute("registrationModel", registrationModel);

		} else {

			log.error("Trovato errori");
			errors.add("error-uploading-certificate");

			for (String error : errors) {
				log.error("Errore: " + error);
				SessionErrors.add(request, error);
			}
			
			PortletConfig portletConfig = (PortletConfig)request.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
			SessionMessages.add(request, portletConfig.getPortletName() + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
			
			response.setRenderParameter("myaction", "showUploadProxy");
			request.setAttribute("password", pwd1);
			request.setAttribute("passwordVerify", pwd2);
			request.setAttribute("registrationModel", registrationModel);

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
}
