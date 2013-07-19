package it.italiangrid.portal.registration.controller;

import it.italiangrid.portal.dbapi.domain.Certificate;
import it.italiangrid.portal.dbapi.domain.UserInfo;
import it.italiangrid.portal.dbapi.services.CertificateService;
import it.italiangrid.portal.dbapi.services.NotifyService;
import it.italiangrid.portal.dbapi.services.UserInfoService;
import it.italiangrid.portal.registration.dirac.DiracTask;
import it.italiangrid.portal.registration.exception.RegistrationException;
import it.italiangrid.portal.registration.model.RegistrationModel;
import it.italiangrid.portal.registration.server.DiracRegistration;
import it.italiangrid.portal.registration.util.RegistrationConfig;
import it.italiangrid.portal.registration.util.RegistrationUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.UUID;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import portal.registration.utils.MyValidator;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.util.PortalUtil;
import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.ParamPart;
import com.oreilly.servlet.multipart.Part;

@Controller(value = "uploadCertificateController")
@RequestMapping(value = "VIEW")
public class UploadCertificateController {
	private static final Logger log = Logger
			.getLogger(UploadCertificateController.class);
	@Autowired
	private NotifyService notifyService;
	
	@Autowired
	private CertificateService certificateService;
	
	@Autowired
	private UserInfoService userInfoService;
	
	@RenderMapping(params = "myaction=showUploadCertificate")
	public String showAskForCertificate() {
		log.info("Show uploadCertificate.jsp");
		return "uploadCertificate";
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
	
	
	
	@ActionMapping(params = "myaction=uploadCertificate")
	public void uploadCert(ActionRequest request, ActionResponse response,
			SessionStatus sessionStatus) throws PortalException,
			SystemException {
		
		RegistrationModel registrationModel = new RegistrationModel();
		UserInfo userInfo = new UserInfo();
		
		ArrayList<String> errors = new ArrayList<String>();
		boolean allOk = true;

		HttpServletRequest req = PortalUtil.getHttpServletRequest(request);

		String ns = response.getNamespace();
		String primaryCert = "";
		String pwd = "";
		ArrayList<String> files = new ArrayList<String>();
		String certificateUserId = UUID.randomUUID().toString();
		
		boolean goToAddUser = false;

		File dir = new File("/upload_files");
		if (dir.isDirectory()) {

			if (ServletFileUpload.isMultipartContent(req)) {
				log.info("yessssss" + ServletFileUpload.isMultipartContent(req));
				try {
					MultipartParser mp = new MultipartParser(req,
							1 * 1024 * 1024); // 10MB
					Part part;
					while ((part = mp.readNextPart()) != null) {
						String name = part.getName();
						log.info(name);
						if (part.isParam()) {
							ParamPart paramPart = (ParamPart) part;
							String value = paramPart.getStringValue();
							if (name.equals(ns + "keyPass"))
								pwd = value;
							if (name.equals(ns + "primaryCert"))
								primaryCert = value;
							if (name.equals(ns + "firstName"))
								registrationModel.setFirstName(value);
							if (name.equals(ns + "lastName"))
								registrationModel.setLastName(value);
							if (name.equals(ns + "institute"))
								registrationModel.setInstitute(value);
							if (name.equals(ns + "email"))
								registrationModel.setEmail(value);
							if (name.equals(ns + "userStatus"))
								if(value.equals("true"))
									registrationModel.setUserStatus(true);
							if (name.equals(ns + "haveIDP"))
								if(value.equals("true"))
									registrationModel.setHaveIDP(true);
							if (name.equals(ns + "haveCertificate")){
								if(value.equals("true"))
									registrationModel.setHaveCertificate(true);
							}
							log.info("param; name=" + name + ", value= *******");
						} else if (part.isFile()) {
							// it's a file part
							FilePart filePart = (FilePart) part;
							String fileName = filePart.getFileName();
							if (fileName != null) {
								// the part actually contained a file
								long size = filePart.writeTo(dir);
								log.info("file; name=" + name + "; filename="
										+ fileName + ", filePath="
										+ filePart.getFilePath()
										+ ", content type="
										+ filePart.getContentType() + ", size="
										+ size);
								files.add(fileName);
							} else {
								// the field did not contain a file
								log.info("file; name=" + name + "; EMPTY");
							}
						}
					}

				} catch (IOException lEx) {
					allOk = false;
					log.info("error reading or saving file");
				}

			} else {
				allOk = false;
				log.info("nooooooo "
						+ ServletFileUpload.isMultipartContent(req));
			}

		} else {
			allOk = false;
			log.info("non è una directory");
		}
		String tmpPwd ="";
		if (MyValidator.validateCert(pwd, errors) && allOk) {
			// controllo file

			// esecuzione myproxy
			
			try {
			
				byte[] bytesOfMessage;
				
				if(registrationModel.isHaveIDP()){
					
					userInfo = userInfoService.findByMail(registrationModel.getEmail());
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
				
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (RegistrationException e) {
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			log.info(tmpPwd);
			log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			String myproxyPass = tmpPwd;
			splitP12(files.get(0), certificateUserId, pwd, tmpPwd, errors);
			
			

			if (errors.isEmpty()) {
				String subject = myOpenssl("subject", "usercert_" + certificateUserId
						+ ".pem", errors);
				String issuer = myOpenssl("issuer", "usercert_" + certificateUserId + ".pem",
						errors);
				String enddate = myOpenssl("enddate", "usercert_" + certificateUserId
						+ ".pem", errors);
				String mail = myOpenssl("email", "usercert_" + certificateUserId
						+ ".pem", errors);
				log.info("mail is: " +  mail);
				if ((subject != null) && (issuer != null) && (enddate != null)
						&& allOk) {
					Date date = null;

					try {
						DateFormat formatter = new SimpleDateFormat(
								"MMM dd HH:mm:ss yyyy z", Locale.UK);
						date = (Date) formatter.parse(enddate);

						log.info("data formattata = " + date.toString());
						GregorianCalendar c = new GregorianCalendar();
						Date oggi = c.getTime();
						log.info("data oggi = " + oggi.toString());

						if (date.before(oggi)) {
							log.info("Certificato scaduto");
							errors.add("user-certificate-expired");
							allOk = false;
						} else {
							log.info("Certificato valido");
						}

					} catch (ParseException e) {
						allOk = false;
						log.info("Eccezzione data");
					}
					if (allOk) {
						
						registrationModel.setIssuer(issuer);
						registrationModel.setSubject(subject);
						registrationModel.setCertificateUserId(certificateUserId);
						if(mail!=null)
							if(!mail.isEmpty())
								registrationModel.setMail(mail);
						registrationModel.setExpiration(enddate);
						
						
						
						if(!registrationModel.isHaveIDP()){
							
							
							
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
							
							String institute = o + (((!o.isEmpty())&&(!l.isEmpty()))? " - " : "") + l;
							userInfo.setInstitute(institute);
							
							String[] cnParts = cn.split(" ");
							String firstName = cnParts[0];
							
							String lastName = cnParts[1];
							
							for(int i=2; i < cnParts.length; i++ ){
								if(!cnParts[i].contains("@"))
									lastName += " " + cnParts[i];
							}
								
							String username = firstName + "." + lastName.trim() + ".IGI.IDP";
							if(!firstName.isEmpty()){
								userInfo.setFirstName(firstName);
								registrationModel.setFirstName(firstName);
							}
							if(!lastName.isEmpty()){
								userInfo.setLastName(lastName);
								registrationModel.setLastName(lastName);
							}
							userInfo.setUsername(username.toLowerCase());
							registrationModel.setInstitute(institute);
							
							if(!registrationModel.getMail().split("/")[0]
									.isEmpty()){
								userInfo.setMail(registrationModel.getMail().split("/")[0]);
								registrationModel.setEmail(registrationModel.getMail().split("/")[0]);
							}
							
							if(!registrationModel.getEmail().isEmpty()&&!registrationModel.getInstitute().isEmpty()){
								registrationModel.setUserStatus(true);
								try {
									RegistrationUtil.insertIntoIDP(userInfo, registrationModel);
									boolean verify = registrationModel.getEmail().isEmpty();
									log.info("Verify??? " + verify);
									RegistrationUtil.addUserToLiferay(request, userInfo, registrationModel, verify);
									userInfo=RegistrationUtil.addUserToDB(userInfo, userInfoService, notifyService);
									log.info("PersistentID: " + userInfo.getPersistentId());
										
									try {
										
										byte[] bytesOfMessage = (userInfo.getPersistentId() + RegistrationConfig.getProperties("Registration.properties", "proxy.secret")).getBytes("UTF-8");
										
										MessageDigest md = MessageDigest.getInstance("MD5");
										byte[] thedigest = md.digest(bytesOfMessage);
										
										Formatter formatter = new Formatter();
									    for (byte b : thedigest)
									    {
									        formatter.format("%02x", b);
									    }
									    myproxyPass = formatter.toString();
									    formatter.close();
										
									} catch (UnsupportedEncodingException e) {
										e.printStackTrace();
									} catch (RegistrationException e) {
										e.printStackTrace();
									} catch (NoSuchAlgorithmException e) {
										e.printStackTrace();
									}
									
									
								} catch (RegistrationException e) {
									e.printStackTrace();
									
									allOk = false;
									errors.add("ldap-error");
								}
							} else {
								registrationModel.setVerifyUser(true);
								goToAddUser = true;
							}
						}
						
						log.info(registrationModel);
						
						log.info("goToAddUser: " + goToAddUser);
						
						Certificate cert = new Certificate();
						cert.setIdCert(0);
						cert.setUserInfo(null);
						cert.setCaonline("false");
						cert.setExpirationDate(date);
						cert.setIssuer(issuer);
						cert.setPrimaryCert(primaryCert);
						cert.setSubject(subject);
						cert.setUsernameCert(certificateUserId);
						cert.setPasswordChanged("false");
						if(!goToAddUser)
							cert.setUserInfo(userInfoService.findByMail(registrationModel.getEmail()));
						
						log.info("@@@@@@@@"+registrationModel);
						
						int id = certificateService.save(cert);
						
						Certificate cert2 = certificateService.findByIdCert(id);
						
						registrationModel.setExpiration(cert2.getExpirationDate().toString());

						if (id != -1) {
							log.info("inserito il certificato");
						} else {
							allOk = false;
						}
						
					}
				} else {
					log.info("errori vari");
					allOk = false;
				}

				if (allOk) {

					try {
						

						
						String myproxy = "/usr/bin/python /upload_files/myproxy2.py "
								+ registrationModel.getCertificateUserId()
								+ " /upload_files/usercert_"
								+ registrationModel.getCertificateUserId()
								+ ".pem /upload_files/userkey_"
								+ registrationModel.getCertificateUserId()
								+ ".pem \""
								+ tmpPwd + "\" \"" + myproxyPass+"\"";
						log.debug("Myproxy command = " + myproxy);
						
						String[] myproxy2 = {"/usr/bin/python", "/upload_files/myproxy2.py", registrationModel.getCertificateUserId(), "/upload_files/usercert_" + certificateUserId + ".pem", "/upload_files/userkey_" + certificateUserId + ".pem", tmpPwd, myproxyPass};
						String[] env = {"GT_PROXY_MODE=old"};
						Process p = Runtime.getRuntime().exec(myproxy2, env, new File("/upload_files"));
						InputStream stdout = p.getInputStream();
						InputStream stderr = p.getErrorStream();

						BufferedReader output = new BufferedReader(
								new InputStreamReader(stdout));
						String line = null;

						while (((line = output.readLine()) != null)) {

							log.info("[Stdout] " + line);
							if (line.equals("myproxy success")) {
								log.info("myproxy ok");
								pwd += "\n";
							} else {
								if (line.equals("myproxy verify password failure")) {
									errors.add("error-password-mismatch");
									log.info(line);
									allOk = false;
								} else {
									if (line.equals("myproxy password userkey failure")) {
										errors.add("error-password-mismatch");
										log.info(line);
										allOk = false;
									} else {
										if (line.equals("too short passphrase")) {
											errors.add("error-password-too-short");
											log.info(line);
											allOk = false;
										} else {
											if (line.equals("key password failure")) {
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
							Certificate certificate= certificateService.findBySubject(subject);
							certificateService.delete(certificate);
							errors.add("myproxy-error");
						}
						brCleanUp.close();
						
						String[] myproxy3 = {"/usr/bin/python", "/upload_files/myproxy2.py", registrationModel.getCertificateUserId()+"_rfc", "/upload_files/usercert_" + certificateUserId + ".pem", "/upload_files/userkey_" + certificateUserId + ".pem", tmpPwd, myproxyPass};
						String[] envRFC = {"GT_PROXY_MODE=rfc"};
						p = Runtime.getRuntime().exec(myproxy3, envRFC, new File("/upload_files"));
						stdout = p.getInputStream();
						stderr = p.getErrorStream();

						output = new BufferedReader(
								new InputStreamReader(stdout));
						line = null;

						while (((line = output.readLine()) != null)) {

							log.info("[Stdout] " + line);
							if (line.equals("myproxy success")) {
								log.info("myproxy ok");
								pwd += "\n";
							} else {
								if (line.equals("myproxy verify password failure")) {
									errors.add("error-password-mismatch");
									log.info(line);
									allOk = false;
								} else {
									if (line.equals("myproxy password userkey failure")) {
										errors.add("error-password-mismatch");
										log.info(line);
										allOk = false;
									} else {
										if (line.equals("too short passphrase")) {
											errors.add("error-password-too-short");
											log.info(line);
											allOk = false;
										} else {
											if (line.equals("key password failure")) {
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
							Certificate certificate= certificateService.findBySubject(subject);
							certificateService.delete(certificate);
							errors.add("myproxy-error");
						}
						brCleanUp.close();
					} catch (IOException e1) {
						allOk = false;
						errors.add("myproxy-exception");
						e1.printStackTrace();
					}
				}
			}
		}

		log.info("controllo erroriiii22");
		if (allOk && errors.isEmpty()) {

			log.info("tutto ok!!");
			SessionMessages.add(request, "upload-cert-successufully");
			registrationModel.setCertificateStatus(true);
			request.setAttribute("registrationModel", registrationModel);
			
			Certificate cert = certificateService.findById(userInfo.getUserId()).get(0);
			
			DiracTask diracTask = new DiracTask("/upload_files/usercert_" + certificateUserId + ".pem", "/upload_files/userkey_" + certificateUserId + ".pem", tmpPwd, userInfo.getMail(), cert.getSubject(), userInfo.getUsername());
			DiracRegistration.addDiracTask(diracTask);
			
			if(goToAddUser){
				registrationModel.setHaveIDP(false);
				request.setAttribute("registrationModel", registrationModel);
				request.setAttribute("userInfo", userInfo);
				response.setRenderParameter("myaction", "showAddUserForm");
			}else{
				response.setRenderParameter("myaction", "showAddVoForm");
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
			
			response.setRenderParameter("myaction", "showUploadCertificate");
			request.setAttribute("registrationModel", registrationModel);

		}

//		if (!files.isEmpty())
//			deleteUploadedFile(files, certificateUserId);
		

	}

	private void splitP12(String filename, String certificateUserId, String pwd1, String pwd2,
			ArrayList<String> errors) {

		try {
			String cmd = "/usr/bin/python /upload_files/splitP12.py /upload_files/"
					+ filename + " " + certificateUserId + " \"" + pwd1 + "\" \"" + pwd2+"\"";
			log.debug("cmd = " + cmd);
			
			
			String[] cmd2 ={"/usr/bin/python", "/upload_files/splitP12.py", "/upload_files/"+filename, certificateUserId, pwd1, pwd2};
			Process p = Runtime.getRuntime().exec(cmd2);
			InputStream stdout = p.getInputStream();
			InputStream stderr = p.getErrorStream();

			BufferedReader output = new BufferedReader(new InputStreamReader(
					stdout));
			String line = null;

			while ((line = output.readLine()) != null) {
				log.info("[Stdout] " + line);
				if (line.equals("too short passphrase"))
					errors.add("error-password-too-short");
				if (line.equals("p12 passwd error key"))
					errors.add("key-password-failure");
				if (line.equals("error unrecognized"))
					errors.add("error-unrecognized");
				if (line.equals("p12 passwd error cert"))
					errors.add("key-password-failure");
			}
			output.close();

			BufferedReader brCleanUp = new BufferedReader(
					new InputStreamReader(stderr));
			while ((line = brCleanUp.readLine()) != null) {
				errors.add("error-unrecognized");
				log.info("[Stderr] " + line);
			}
			brCleanUp.close();
			
			Runtime.getRuntime().exec(
					"/bin/chmod 600 /upload_files/userkey_" + certificateUserId
							+ ".pem");

		} catch (IOException e) {

			e.printStackTrace();
			errors.add("error-unrecognized-exception");
		}

	}

	private String myOpenssl(String opt, String filename,
			ArrayList<String> errors) {
		String result = null;

		try {
			String cmd = "/usr/bin/openssl x509 -in /upload_files/" + filename
					+ " -" + opt + " -noout";
			log.info("cmd = " + cmd);
			Process p = Runtime.getRuntime().exec(cmd);
			InputStream stdout = p.getInputStream();
			InputStream stderr = p.getErrorStream();

			BufferedReader output = new BufferedReader(new InputStreamReader(
					stdout));
			String line = null;
			int posizione;

			int cursore = (opt.equals("subject") || opt.equals("issuer")) ? 2
					: 1;

			while ((line = output.readLine()) != null) {
				log.info("[Stdout] " + line);
				if(opt.equals("email")){
					result = line;
					log.info(opt + " = " + result);
				}
					
				if ((posizione = line.indexOf("=")) != -1) {
					result = line.substring(posizione + cursore);
					log.info(opt + " = " + result);
				}
			}
			output.close();

			BufferedReader brCleanUp = new BufferedReader(
					new InputStreamReader(stderr));
			while ((line = brCleanUp.readLine()) != null) {
				errors.add("no-valid-cert-" + opt);
				log.info("[Stderr] " + line);
			}
			brCleanUp.close();

		} catch (IOException e) {

			e.printStackTrace();
		}

		return result;
	}

	@ActionMapping(params = "myaction=setDefaultCert")
	public void setDefaultCert(ActionRequest request, ActionResponse response) {

		int idCert = Integer.parseInt(request.getParameter("idCert"));
		String userId = request.getParameter("userId");

		try {
			log.info("Sto per cancellare il certificato con id = " + idCert);
			if (certificateService.setDefault(idCert))
				SessionMessages.add(request,
						"certificate-updated-successufully");
			else
				SessionErrors.add(request, "error-default-certificate");
			log.info("Certificato cancellato");

		} catch (Exception e) {
			SessionErrors.add(request, "error-updating-certificate");
		}

		response.setRenderParameter("myaction", "editUserInfoForm");
		response.setRenderParameter("userId", userId);

	}
}
