package it.italiangrid.portal.registration.controller;

import it.italiangrid.portal.dbapi.domain.Certificate;
import it.italiangrid.portal.dbapi.domain.UserInfo;
import it.italiangrid.portal.dbapi.services.CertificateService;
import it.italiangrid.portal.dbapi.services.NotifyService;
import it.italiangrid.portal.dbapi.services.UserInfoService;
import it.italiangrid.portal.dbapi.services.UserToVoService;
import it.italiangrid.portal.registration.exception.RegistrationException;
import it.italiangrid.portal.registration.model.RegistrationModel;
import it.italiangrid.portal.registration.util.RegistrationConfig;
import it.italiangrid.portal.registration.util.RegistrationUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Formatter;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
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
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

import portal.registration.utils.GuseNotify;
import portal.registration.utils.GuseNotifyUtil;
import portal.registration.utils.MyValidator;

@Controller(value = "addUserController")
@RequestMapping(value = "VIEW")
public class AddUserController {

	private static final Logger log = Logger.getLogger(AddUserController.class);
	
	@Autowired
	private NotifyService notifyService;

	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private UserToVoService userToVoService;
	
	@Autowired
	private CertificateService certificateService;
	
	@RenderMapping(params = "myaction=showAddUserForm")
	public String showAddUserForm(RenderRequest request, RenderResponse response) {
		log.info("Show addUserForm.jsp");
		String[] array = {"l","o", "givenName", "sn", "uid", "mail", "persistent-id", "org-dn", "fromIDP"};
		List<String> attributes = Arrays.asList(array);
		
		UserInfo userInfo = (UserInfo) request.getAttribute("userInfo");
		

		
		if(userInfo==null){
			userInfo = new UserInfo();
		
			RegistrationModel registrationModel = new RegistrationModel();
			
			String o = "";
			String l = "";
			
			for (Enumeration<String> e = request.getParameterNames() ; e.hasMoreElements() ;) {
				String name = e.nextElement();
		        log.info(name + ": "+request.getParameter(name));
		        
		        switch(attributes.indexOf(name)){
		        case 0:
	//	        	l
		        	l=request.getParameter(name).replaceAll("%20", " ").toUpperCase();
		        	break;
		        case 1:
	//	        	o
		        	o = request.getParameter(name).replaceAll("%20", " ").toUpperCase();
		        	break;
		        case 2:
	//	        	givenName
		        	userInfo.setFirstName(request.getParameter(name).replaceAll("%20", " "));
		        	registrationModel.setFirstName(userInfo.getFirstName());
		        	break;
		        case 3:
	//	        	sn
		        	userInfo.setLastName(request.getParameter(name).replaceAll("%20", " "));
		        	registrationModel.setLastName(userInfo.getLastName());
		        	break;
		        case 4:
	//	        	uid
		        	//userInfo.setUsername(request.getParameter(name).replaceAll("%20", " "));
		        	break;
		        case 5:
	//	        	mail
		        	userInfo.setMail(request.getParameter(name).replaceAll("%20", " ").toLowerCase());
		        	registrationModel.setEmail(userInfo.getMail());
		        	break;
		        case 6:
		        	log.info("PersistentID: "+request.getParameter(name).replaceAll("%20", " "));
		        	userInfo.setPersistentId(request.getParameter(name).replaceAll("%20", " "));
		        	
		        	
		        	String newUsername = "";
					char[] chars = request.getParameter(name).replaceAll("%20", " ").toCharArray();
					
					for(int i=0; i<chars.length; i++){
						if(Character.isLetterOrDigit(chars[i]))
							newUsername+=chars[i];
						else
							newUsername+='_';
					}
		        	
					userInfo.setUsername(newUsername.toLowerCase());
		        	
	//	        	persistent-id
		        	break;
		        case 7:
	//	        	org-dn
		        	o= request.getParameter(name).replaceAll("%20", " ").replaceAll("dc.", "").replaceAll(",", " ").toUpperCase();
		        	break;
		        }
		        
	
		     }
			
			
			
			String institute = l + (((!l.isEmpty())&&(!o.isEmpty()))? " - " : "") + o;
			userInfo.setInstitute(institute);
			registrationModel.setInstitute(institute);
			
			log.info(registrationModel.toString());
			registrationModel.setHaveIDP(true);
			request.setAttribute("fromIDP", "true");
			request.setAttribute("userInfo", userInfo);
			
			if(registrationModel.getEmail().isEmpty())
				registrationModel.setVerifyUser(true);
			
			
			if((!registrationModel.getFirstName().isEmpty())&&(!registrationModel.getLastName().isEmpty())&&(!registrationModel.getInstitute().isEmpty())&&(!registrationModel.getEmail().isEmpty())){
				registrationModel.setUserStatus(true);
				registrationModel.setHaveIDP(true);
				log.info("###########"+registrationModel);
				request.setAttribute("registrationModel", registrationModel);
				try {
					log.info(RegistrationConfig.getProperties("Registration.properties", "login.url"));
					request.setAttribute("loginUrl", RegistrationConfig.getProperties("Registration.properties", "login.url"));
					log.info(RegistrationConfig.getProperties("Registration.properties", "CAOnline.enabled"));
					request.setAttribute("caEnabled", RegistrationConfig.getProperties("Registration.properties", "CAOnline.enabled"));
					log.info(RegistrationConfig.getProperties("Registration.properties", "proxy.enabled"));
					request.setAttribute("proxyEnabled", RegistrationConfig.getProperties("Registration.properties", "proxy.enabled"));
				} catch (RegistrationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return addUser(userInfo, registrationModel, request, response);
			}
			
			request.setAttribute("registrationModel", registrationModel);
		}else{
			
			RegistrationModel registrationModel = (RegistrationModel) request.getAttribute("registrationModel");
			request.setAttribute("registrationModel", registrationModel);
			request.setAttribute("userInfo", userInfo);
			
		}
		return "addUserForm";
	}
	
	@RenderMapping(params = "myaction=showAddUserFormNoIDP")
	public String showAddUserFormNoIDP(RenderRequest request, RenderResponse response) {
		
		RegistrationModel registrationModel = new RegistrationModel();
		request.setAttribute("registrationModel", registrationModel);
		
		try {
			if(RegistrationConfig.getProperties("Registration.properties", "idp.enabled").equals("true")){
				log.info(RegistrationConfig.getProperties("Registration.properties", "login.url"));
				request.setAttribute("loginUrl", RegistrationConfig.getProperties("Registration.properties", "login.url"));
				request.setAttribute("caEnabled", RegistrationConfig.getProperties("Registration.properties", "CAOnline.enabled"));
				request.setAttribute("proxyEnabled", RegistrationConfig.getProperties("Registration.properties", "proxy.enabled"));
				if(RegistrationConfig.getProperties("Registration.properties", "proxy.enabled").equals("true"))
					return "askForCertificate";
				
				return "uploadCertificate";
				
			}
		} catch (RegistrationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		return "error";
	}
	
	@ActionMapping(params="myaction=addUser")
	public void addUser(@ModelAttribute UserInfo userInfo, ActionRequest request, ActionResponse response){
		
		RegistrationModel registrationModel = new RegistrationModel();
		
		registrationModel.setSubject(request.getParameter("subject"));
		registrationModel.setIssuer(request.getParameter("issuer"));
		registrationModel.setExpiration(request.getParameter("expiration"));
		registrationModel.setHaveCertificate(Boolean.parseBoolean(request.getParameter("haveCertificate")));
		registrationModel.setCertificateUserId(request.getParameter("certificateUserId"));
		registrationModel.setMail(request.getParameter("certmail"));
		registrationModel.setHaveIDP(Boolean.parseBoolean(request.getParameter("haveIDP")));
		registrationModel.setCertificateStatus(Boolean.parseBoolean(request.getParameter("certificateStatus")));
		registrationModel.setVerifyUser(Boolean.parseBoolean(request.getParameter("verifyUser")));
		
		
		log.info("##########################");
		log.info(registrationModel);
		log.info("##########################");
		
		if (!userInfo.getFirstName().isEmpty())
			registrationModel.setFirstName(userInfo.getFirstName());
		if (!userInfo.getLastName().isEmpty())
			registrationModel.setLastName(userInfo.getLastName());
		if (!userInfo.getInstitute().isEmpty())
			registrationModel.setInstitute(userInfo.getInstitute());
		if (!userInfo.getMail().isEmpty())
			registrationModel.setEmail(userInfo.getMail());
		
		if(userInfo.getUsername().isEmpty()){
			String username = userInfo.getFirstName() + (!userInfo.getLastName().isEmpty()&&!userInfo.getFirstName().isEmpty()?".":"") + userInfo.getLastName();
			userInfo.setUsername(username.toLowerCase());
		}
		
		
		log.info("##########################");
		log.info(registrationModel);
		log.info("##########################");
		
		List<String> errors = new ArrayList<String>();
		
		//Validate user
		if(!MyValidator.validate(userInfo, errors)){
			
			for(String error: errors)
				SessionErrors.add(request, error);
			
			request.setAttribute("userInfo", userInfo);
			request.setAttribute("registrationModel", registrationModel);
			response.setRenderParameter("myaction", "showAddUserForm");
			return;
		
		}
		
		try{
			
			//AddUser into Liferay
			boolean verify = registrationModel.isVerifyUser();
			log.info("Verify??? " + verify);
			RegistrationUtil.addUserToLiferay(request, userInfo, registrationModel, verify);
			
			//AddUser into DB
			userInfo=RegistrationUtil.addUserToDB(userInfo, userInfoService, notifyService);
			
			request.setAttribute("userInfo", userInfo);
			registrationModel.setUserStatus(true);
			registrationModel.setHaveIDP(true);
			request.setAttribute("registrationModel", registrationModel);
			
			log.info(RegistrationConfig.getProperties("Registration.properties", "login.url"));
			request.setAttribute("loginUrl", RegistrationConfig.getProperties("Registration.properties", "login.url"));
			
			if(registrationModel.isCertificateStatus()){
				RegistrationUtil.insertIntoIDP(userInfo, registrationModel);
				
				Certificate certificate = certificateService.findBySubject(registrationModel.getSubject());
				
				certificate.setUserInfo(userInfo);
				
				certificateService.save(certificate);
				
				
				try {
					
					MyProxy mp = new MyProxy(RegistrationConfig.getProperties("Registration.properties", "myproxy.storage"), 7512);
//					UserInfo userInfo =userInfoService.findByMail(registrationModel.getEmail());
					Long companyId = PortalUtil.getCompanyId(request);
					User user = UserLocalServiceUtil.getUserByEmailAddress(companyId, userInfo.getMail());

					String dir = System.getProperty("java.io.tmpdir");
					log.info("Directory = " + dir);

					File location = new File(dir + "/users/" + user.getUserId() + "/");
					if (!location.exists()) {
						location.mkdirs();
					}

					OutputStream out = null;
					
					File proxyFile = new File(dir + "/users/" + user.getUserId()
							+ "/x509up");
					
					
					
					
					
					
						byte[] bytesOfMessage;
						
//						if(registrationModel.isHaveIDP()){
							
							userInfo = userInfoService.findByMail(registrationModel.getEmail());
							bytesOfMessage = (userInfo.getPersistentId() + RegistrationConfig.getProperties("Registration.properties", "proxy.secret")).getBytes("UTF-8");
							
//						} else {
//							bytesOfMessage = ("blablabla"+ RegistrationConfig.getProperties("Registration.properties", "proxy.secret")).getBytes("UTF-8");
//						}
						
						MessageDigest md = MessageDigest.getInstance("MD5");
						byte[] thedigest = md.digest(bytesOfMessage);
						
						Formatter formatter = new Formatter();
					    for (byte b : thedigest)
					    {
					        formatter.format("%02x", b);
					    }
					    String newPwd = formatter.toString();
					    formatter.close();
					    
					    byte[] bytesOfMessage2 = ("blablabla"+ RegistrationConfig.getProperties("Registration.properties", "proxy.secret")).getBytes("UTF-8");
					    
					    MessageDigest md2 = MessageDigest.getInstance("MD5");
					    byte[] thedigest2 = md2.digest(bytesOfMessage2);
					    
					    Formatter formatter2 = new Formatter();
					    for (byte b2 : thedigest2)
					    {
					        formatter2.format("%02x", b2);
					    }
					    String oldPwd = formatter2.toString();
					    formatter2.close();
//						tmpPwd = new String(thedigest);
						
						log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
						log.info(newPwd);
						log.info(oldPwd);
						log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
					
					GSSCredential proxy = mp.get(registrationModel.getCertificateUserId(), oldPwd, 608400);
					
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
					
					String[] myproxy2 = {"/usr/bin/python", "/upload_files/myproxy3.py", registrationModel.getCertificateUserId(), proxyFile.toString(), proxyFile.toString(), newPwd, newPwd};
					String[] env = {"GT_PROXY_MODE=old"};
					Process p = Runtime.getRuntime().exec(myproxy2, env, location);
					InputStream stdout = p.getInputStream();
					InputStream stderr = p.getErrorStream();

					BufferedReader output = new BufferedReader(
							new InputStreamReader(stdout));
					String line = null;

					while (((line = output.readLine()) != null)) {

						log.info("[Stdout] " + line);
						if (line.equals("myproxy success")) {
							log.info("myproxy ok");
						} else {
							if (line.equals("myproxy verify password failure")) {
								errors.add("error-password-mismatch");
								log.info(line);
							} else {
								if (line.equals("myproxy password userkey failure")) {
									errors.add("error-password-mismatch");
									log.info(line);
								} else {
									if (line.equals("too short passphrase")) {
										errors.add("error-password-too-short");
										log.info(line);
									} else {
										if (line.equals("key password failure")) {
											errors.add("key-password-failure");
											log.info(line);
										} else {
											errors.add("no-valid-key");
											log.info(line);
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
						
						log.error("[Stderr] " + line);
						errors.add("no-valid-key");
					}
					
					brCleanUp.close();
				} catch (IOException e1) {
					errors.add("myproxy-exception");
					e1.printStackTrace();
				} catch (RegistrationException e) {
					e.printStackTrace();
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				} catch (MyProxyException e) {
					e.printStackTrace();
				} catch (PortalException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SystemException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				
				
				
				
				registrationModel.setHaveIDP(false);
				request.setAttribute("registrationModel", registrationModel);
				response.setRenderParameter("myaction", "showAddVoForm");
				return;
			}else{
				
				if(RegistrationConfig.getProperties("Registration.properties", "CAOnline.enabled").equals("false")&&RegistrationConfig.getProperties("Registration.properties", "proxy.enabled").equals("false"))
					response.setRenderParameter("myaction", "showUploadCertificate");
				else
					response.setRenderParameter("myaction", "askForCertificate");
				
	//			response.sendRedirect(RegistrationConfig.getProperties("Registration.properties", "login.url"));
				return;
			}
		
		}catch(RegistrationException e){
			e.printStackTrace();
			SessionErrors.add(request, e.getMessage());
		}

		request.setAttribute("userInfo", userInfo);
		response.setRenderParameter("myaction", "showAddUserForm");
		
		
	}
	
private String addUser(UserInfo userInfo, RegistrationModel registrationModel, RenderRequest request, RenderResponse response){
		
		List<String> errors = new ArrayList<String>();
		
		if(userInfo.getUsername()==null){
			String username = userInfo.getFirstName() + (!userInfo.getLastName().isEmpty()&&!userInfo.getFirstName().isEmpty()?".":"") + userInfo.getLastName();
			userInfo.setUsername(username.toLowerCase());
		}
		
		//Validate user
		if(!MyValidator.validate(userInfo, errors)){
			
			for(String error: errors)
				SessionErrors.add(request, error);
			
			return "addUserForm";
		}
		
		try{
			
			RegistrationUtil.addUserToLiferay(request, userInfo, registrationModel, false);
			
			//AddUser into DB
			userInfo=RegistrationUtil.addUserToDB(userInfo, userInfoService, notifyService);
			
			long companyId = PortalUtil.getCompanyId(request);
			
			User user = UserLocalServiceUtil.getUserByEmailAddress(companyId,
					registrationModel.getEmail());
			
			/* 1 */
			
			GuseNotifyUtil guseNotifyUtil = new GuseNotifyUtil();
			GuseNotify guseNotify = new GuseNotify(registrationModel.getFirstName());
			
			guseNotify.setWfchgEnab("1");
			guseNotify.setEmailEnab("1");
			guseNotify.setQuotaEnab("0");
			
			guseNotifyUtil.writeNotifyXML(user, guseNotify);
			
			if(RegistrationConfig.getProperties("Registration.properties", "CAOnline.enabled").equals("false")&&RegistrationConfig.getProperties("Registration.properties", "proxy.enabled").equals("false"))
				return "uploadCertificate";
			return "askForCertificate";
		
		}catch(RegistrationException e){
			e.printStackTrace();
			SessionErrors.add(request, e.getMessage());
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "addUserForm";
	}
}
