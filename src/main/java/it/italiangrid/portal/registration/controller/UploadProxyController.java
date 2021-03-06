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
import it.italiangrid.portal.registration.util.CookieUtil;
import it.italiangrid.portal.registration.util.RegistrationConfig;
import it.italiangrid.portal.registration.util.RegistrationUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
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

import portal.registration.utils.SendMail;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.JavaConstants;
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
		log.info("Show uploadCertificate.jsp");
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
		
		List<Vo> result = new ArrayList<Vo>();
		if(!registrationModel.getVos().isEmpty())
			for(String id : registrationModel.getVos().split("#"))
				result.add(voService.findById(Integer.parseInt(id)));
		
		return result;
	}
	
	@ActionMapping(params = "myaction=abortRegistration")
	public void abortRegistration(@ModelAttribute RegistrationModel registrationModel, ActionRequest request, ActionResponse response, SessionStatus sessionStatus){
		
		log.info(registrationModel);
		if(registrationModel.isHaveIDP()==true){
			CookieUtil.setCookieSession("JSESSIONID", "", response);
			
			
			
			try {
				URL url = new URL(RegistrationConfig.getProperties("Registration.properties", "login.url"));
				
				log.info(url);
				response.sendRedirect(url.toString());
			} catch (IOException e) {
				e.printStackTrace();
			} catch (RegistrationException e) {
				e.printStackTrace();
			}
		}else{
			Certificate cert = certificateService.findBySubject(registrationModel.getSubject());
			
			if(cert!=null){
				log.info("Controllo user");
				if(cert.getUserInfo()==null){
					log.info("Erase cert!!!!!");
					certificateService.delete(cert);
					try {
						response.sendRedirect(RegistrationConfig.getProperties("Registration.properties", "home.url"));
					} catch (IOException e) {
						e.printStackTrace();
					} catch (RegistrationException e) {
						e.printStackTrace();
					}
					
				}else{
					
					try {
						String from = RegistrationConfig.getProperties("Registration.properties", "igiportal.mail");
						String mailSubject = "[IGP - IGI Grid Portal] Authentication Instructions";
						String mailContent = RegistrationUtil.readFile(RegistrationConfig.getProperties("Registration.properties", "authentication.mail.template"));
						boolean isHtml = true;
						mailContent = mailContent.replaceAll("##USER##", registrationModel.getFirstName());
						mailContent = mailContent.replaceAll("##HOST##", RegistrationConfig.getProperties("Registration.properties", "home.url"));
						
						SendMail sm = new SendMail(from, registrationModel.getEmail(), mailSubject, mailContent, isHtml);
						sm.send();
					} catch (RegistrationException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					response.setRenderParameter("myaction", "showAuthentication");
				}
			}else{
				log.info("no cert");
				try {
					response.sendRedirect(RegistrationConfig.getProperties("Registration.properties", "home.url"));
				} catch (IOException e) {
					e.printStackTrace();
				} catch (RegistrationException e) {
					e.printStackTrace();
				}
			}
			
		}
		
	}
	
	@ActionMapping(params = "myaction=uploadProxy")
	public void uploadCert(@ModelAttribute RegistrationModel registrationModel, ActionRequest request, ActionResponse response,
			SessionStatus sessionStatus) throws PortalException,
			SystemException {
		
		log.info(registrationModel);
		
		
		ArrayList<String> errors = new ArrayList<String>();
		boolean allOk = true;

		String pwd1 = request.getParameter("password");
		String pwd2 = request.getParameter("passwordVerify");
		
		Certificate cert = certificateService.findByCertificateUsername(registrationModel.getCertificateUserId());
		
		if(pwd1.equals(pwd2)){
	
			

					try {
						
						MyProxy mp = new MyProxy(RegistrationConfig.getProperties("Registration.properties", "myproxy.storage"), 7512);
						Long companyId = PortalUtil.getCompanyId(request);
						User user = UserLocalServiceUtil.getUserByEmailAddress(companyId, registrationModel.getEmail());

						String dir = System.getProperty("java.io.tmpdir");
						log.info("Directory = " + dir);

						File location = new File(dir + "/users/" + user.getUserId() + "/");
						if (!location.exists()) {
							location.mkdirs();
						}

						OutputStream out = null;
						
						File proxyFile = new File(dir + "/users/" + user.getUserId()
								+ "/x509up");
						
						String tmpPwd ="";
						
						
						byte[] bytesOfMessage;
						
						UserInfo userInfo = userInfoService.findByMail(registrationModel.getEmail());
						bytesOfMessage = (userInfo.getPersistentId() + RegistrationConfig.getProperties("Registration.properties", "proxy.secret")).getBytes("UTF-8");
						
						
						MessageDigest md = MessageDigest.getInstance("MD5");
						byte[] thedigest = md.digest(bytesOfMessage);
						
						Formatter formatter = new Formatter();
					    for (byte b : thedigest)
					    {
					        formatter.format("%02x", b);
					    }
					    tmpPwd = formatter.toString();
					    formatter.close();
						
						log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
						log.info(tmpPwd);
						log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
						
						GSSCredential proxy = mp.get(registrationModel.getCertificateUserId(), tmpPwd, 608400);
						
						log.info("----- All ok -----");
						log.info("Proxy:" + proxy.toString());

						GlobusCredential globusCred = null;
						globusCred = ((GlobusGSSCredentialImpl) proxy)
								.getGlobusCredential();
						log.info("----- Passo per il istanceof GlobusGSSCredentialImpl");

						log.info("Save proxy file: " + globusCred);
						out = new FileOutputStream(proxyFile);
						Util.setFilePermissions(proxyFile.toString(), 600);
						globusCred.save(out);
						
						String[] myproxy2 = {"/usr/bin/python", "/upload_files/myproxy3-change.py", RegistrationConfig.getProperties("Registration.properties", "myproxy.storage"), cert.getUsernameCert(), tmpPwd, pwd1};
						Process p = Runtime.getRuntime().exec(myproxy2, null, location);
						InputStream stdout = p.getInputStream();
						InputStream stderr = p.getErrorStream();

						BufferedReader output = new BufferedReader(
						new InputStreamReader(stdout));
						String line = null;

						while (((line = output.readLine()) != null)) {

							log.info("[Stdout] " + line);
							if (line.equals("myproxy password changed")) {
								log.info("myproxy ok");
							} else {
								if (line.equals("password too short")) {
									errors.add("error-password-too-short");
									log.info(line);
									allOk = false;
								} else {
									if (line.equals("myproxy password not changed")) {
										errors.add("key-password-failure");
										log.info(line);
										allOk = false;
									} else {
										errors.add("no-valid-key");
										log.info(line);
										allOk = false;
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
						String[] myproxy3 = {"/usr/bin/python", "/upload_files/myproxy3-change.py", RegistrationConfig.getProperties("Registration.properties", "myproxy.storage"), cert.getUsernameCert()+"_rfc", tmpPwd, pwd1};
						p = Runtime.getRuntime().exec(myproxy3, null, location);
						stdout = p.getInputStream();
						stderr = p.getErrorStream();

						output = new BufferedReader(
						new InputStreamReader(stdout));
						line = null;

						while (((line = output.readLine()) != null)) {

							log.info("[Stdout] " + line);
							if (line.equals("myproxy password changed")) {
								log.info("myproxy ok");
							} else {
								if (line.equals("password too short")) {
									errors.add("error-password-too-short");
									log.info(line);
									allOk = false;
								} else {
									if (line.equals("myproxy password not changed")) {
										errors.add("key-password-failure");
										log.info(line);
										allOk = false;
									} else {
										errors.add("no-valid-key");
										log.info(line);
										allOk = false;
									}
								}
							}

						}
						output.close();

						brCleanUp = new BufferedReader(
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
						e.printStackTrace();
					} catch (NoSuchAlgorithmException e) {
						e.printStackTrace();
					} catch (MyProxyException e) {
						e.printStackTrace();
					}
		}else{
			allOk=false;
			errors.add("error-password-mismatch");
		}	
			
		log.info("controllo errori");
		if (allOk && errors.isEmpty()) {

			log.info("tutto ok!!");
			
			cert.setPasswordChanged("true");
			
			log.info("€€€€€€€€€€€€€€€€€€€€€€€€€€€€€"+cert.getPasswordChanged());
			certificateService.save(cert);
			certificateService.update(cert);
			
			Certificate c = certificateService.findByIdCert(cert.getIdCert());
			
			log.info("€€€€€€€€€€€€€€€€€€€€€€€€€€€€€"+c.getPasswordChanged());
			
			long companyId = PortalUtil.getCompanyId(request);
			
			try {
				User user = UserLocalServiceUtil.getUserByEmailAddress(companyId,
						registrationModel.getEmail());
				
				if(registrationModel.isVoStatus()){
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
	
					UserLocalServiceUtil.setRoleUsers(rolePowerUser.getRoleId(), users);
					
					UserInfo userInfo = userInfoService.findByMail(registrationModel.getEmail());
					String selectedVo = userToVoService.findDefaultVo(userInfo.getUserId());
					if(selectedVo!=null){
						myVomsProxyInit(user, selectedVo, "norole", RegistrationConfig.getProperties("Registration.properties", "proxy.expiration.times.default"), request);
					}
				}
			} catch (PortalException e) {
				e.printStackTrace();
			} catch (SystemException e) {
				e.printStackTrace();
			} catch (RegistrationException e) {
				e.printStackTrace();
			}	
			
			try {
				URL url;
				if(registrationModel.isVerifyUser()){
					log.info(RegistrationConfig.getProperties("Registration.properties", "home.url"));
					url = new URL(RegistrationConfig.getProperties("Registration.properties", "home.url"));
				}else{
					log.info(RegistrationConfig.getProperties("Registration.properties", "login.url"));
					url = new URL(RegistrationConfig.getProperties("Registration.properties", "login.url"));
				}
				log.info(url);
				
				SessionMessages.add(request, "upload-cert-successufully");
				registrationModel.setCertificateStatus(true);
				request.setAttribute("registrationModel", registrationModel);
				if(registrationModel.isHaveIDP()==true){
					CookieUtil.setCookieSession("JSESSIONID", "", response);
					response.sendRedirect(url.toString());
				}else{
					
					try {
						String from = RegistrationConfig.getProperties("Registration.properties", "igiportal.mail");
						String mailSubject = "[IGP - IGI Grid Portal] Authentication Instructions";
						String mailContent = RegistrationUtil.readFile(RegistrationConfig.getProperties("Registration.properties", "authentication.mail.template"));
						boolean isHtml = true;
						mailContent = mailContent.replaceAll("##USER##", registrationModel.getFirstName());
						mailContent = mailContent.replaceAll("##HOST##", RegistrationConfig.getProperties("Registration.properties", "home.url"));
						
						SendMail sm = new SendMail(from, registrationModel.getEmail(), mailSubject, mailContent, isHtml);
						sm.send();
					} catch (RegistrationException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					response.setRenderParameter("myaction", "showAuthentication");
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (RegistrationException e) {
				e.printStackTrace();
			}
			
		} else {

			log.info("Trovato errori");
			errors.add("error-uploading-certificate");

			for (String error : errors) {
				log.info("Errore: " + error);
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
	
	private void myVomsProxyInit(User user, String voms, String role, String valid, ActionRequest request){
		try {
			
			String cloudVo = RegistrationConfig.getProperties("Registration.properties", "cloud.vo");

			String dir = System.getProperty("java.io.tmpdir");
			log.debug("Directory = " + dir);

			String proxy = dir + "/users/" + user.getUserId() + "/x509up";
			String proxyFile = dir + "/users/" + user.getUserId() + "/x509up." + voms;
			
			String cmd = "voms-proxy-init -noregen -cert " + proxy + " -key " + proxy + " -out " + proxyFile + " -valid " + valid  + " -voms " + voms;
			
			log.debug(cmd);
			if(!role.equals("norole")){
				cmd += ":" + role;
			}
			
			if(voms.equals(cloudVo))
				cmd += " -rfc";
			log.error("cmd = " + cmd);
			Process p = Runtime.getRuntime().exec(cmd);
			InputStream stdout = p.getInputStream();
			InputStream stderr = p.getErrorStream();

			BufferedReader output = new BufferedReader(new InputStreamReader(
					stdout));
			String line = null;

			while ((line = output.readLine()) != null) {
				log.debug("[Stdout] " + line);
			}
			output.close();

			BufferedReader brCleanUp = new BufferedReader(
					new InputStreamReader(stderr));
			while ((line = brCleanUp.readLine()) != null) {
				if(!line.contains("....")){
					log.error("[Stderr] " + line);
				}
			}
			brCleanUp.close();
			

		} catch (IOException e) {
			
			SessionErrors.add(request, "voms-proxy-init-exception");
			e.printStackTrace();
		} catch (RegistrationException e) {
			e.printStackTrace();
		}
	}
}
