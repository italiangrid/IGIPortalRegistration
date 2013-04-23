package portal.registration.controller;

import it.italiangrid.portal.dbapi.domain.Certificate;
import it.italiangrid.portal.dbapi.domain.UserInfo;
import it.italiangrid.portal.dbapi.services.CertificateService;
import it.italiangrid.portal.dbapi.services.UserInfoService;
import it.italiangrid.portal.dbapi.services.UserToVoService;
import it.italiangrid.portal.registration.exception.RegistrationException;
import it.italiangrid.portal.registration.util.RegistrationConfig;
import portal.registration.utils.MyValidator;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.RenderResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.ParamPart;
import com.oreilly.servlet.multipart.Part;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.globus.gsi.GlobusCredential;
import org.globus.gsi.gssapi.GlobusGSSCredentialImpl;
import org.globus.myproxy.MyProxy;
import org.globus.myproxy.MyProxyException;
import org.globus.util.Util;
import org.ietf.jgss.GSSCredential;

@Controller(value = "uploadCertController")
@RequestMapping(value = "VIEW")
public class UploadCertController {

	private static final String MYPROXY_HOST = "fullback.cnaf.infn.it";

	private static final Logger log = Logger
			.getLogger(UploadCertController.class);

	@Autowired
	private CertificateService certificateService;
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private UserToVoService userToVoService;

	@RenderMapping(params = "myaction=showUploadCert")
	public String showUploadCert(RenderResponse response) {
		return "uploadCert";
	}

	@ActionMapping(params = "myaction=uploadCert")
	public void uploadCert(ActionRequest request, ActionResponse response,
			SessionStatus sessionStatus) throws PortalException,
			SystemException {

		ArrayList<String> errors = new ArrayList<String>();
		boolean allOk = true;

		HttpServletRequest req = PortalUtil.getHttpServletRequest(request);

		if (req != null) {
			log.info("yessssss req");
		} else {
			log.info("noooooo disastro req");
		}
		

		String ns = response.getNamespace();
		String primaryCert = "";
		String pwd = "";
		String pwd1 = "";
		String pwd2 = "";
		String username = "";
		String firstReg = "";
		ArrayList<String> files = new ArrayList<String>();
		int uid = 0;

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
						log.info("parameter name: " + name);
						if (part.isParam()) {
							ParamPart paramPart = (ParamPart) part;
							String value = paramPart.getStringValue();
							if (name.equals(ns + "password"))
								pwd1 = value;
							if (name.equals(ns + "passwordVerify"))
								pwd2 = value;
							if (name.equals(ns + "username"))
								username = value;
							if (name.equals(ns + "userId"))
								uid = Integer.parseInt(value);
							if (name.equals(ns + "keyPass"))
								pwd = value;
							if (name.equals(ns + "primaryCert"))
								primaryCert = value;
							if (name.equals(ns + "firstReg"))
								firstReg = value;
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
					lEx.printStackTrace();
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

		uid = Integer.parseInt(request.getParameter("userId"));
		username = request.getParameter("username");
		firstReg = request.getParameter("firstReg");

		if (MyValidator.validateCert(pwd, pwd1, pwd2, errors) && allOk) {
			// controllo file

			// esecuzione myproxy
			String usernameCert = UUID.randomUUID().toString();
			
			splitP12(files.get(0), uid, pwd, pwd1, errors);

			if (errors.isEmpty()) {
				String subject = myOpenssl("subject", "usercert_" + uid
						+ ".pem", errors);
				String issuer = myOpenssl("issuer", "usercert_" + uid + ".pem",
						errors);
				String enddate = myOpenssl("enddate", "usercert_" + uid
						+ ".pem", errors);

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
						Certificate cert = new Certificate();
						cert.setIdCert(0);
						cert.setCaonline("false");
						cert.setExpirationDate(date);
						cert.setIssuer(issuer);
						cert.setPrimaryCert(primaryCert);
						cert.setSubject(subject);
						cert.setPasswordChanged("true");
						
						cert.setUsernameCert(usernameCert);
						cert.setUserInfo(userInfoService.findById(uid));

						List<Certificate> lc = certificateService.findById(uid);
						if (lc.size() != 0) {
							int i = 0;
							for (i = 0; i < lc.size(); i++) {
								if (cert.equals(lc.get(i))) {
									allOk = false;
									errors.add("certificate-duplicate");
									break;
								}

							}
						}
						if (allOk) {
							int id = certificateService.save(cert);

							if (id != -1) {
								log.info("inserito il certificato per l'utente con userId = "
										+ uid);
							} else {
								allOk = false;
							}
						}
					}
				} else {
					log.info("errori vari");
					allOk = false;
				}

				if (allOk) {

					try {
						String usrnm = null;
						Certificate certificate = null;
						List<Certificate> certs = certificateService
								.findById(uid);
						for (Iterator<Certificate> iterator = certs.iterator(); iterator
								.hasNext();) {
							certificate = (Certificate) iterator.next();
							if (certificate.getSubject().equals(subject)) {
								usrnm = certificate.getUsernameCert();
								break;
							}

						}

						Runtime.getRuntime().exec(
								"/bin/chmod 600 /upload_files/userkey_" + uid
										+ ".pem");
						String myproxy = "/usr/bin/python /upload_files/myproxy2.py "
								+ usrnm
								+ " /upload_files/usercert_"
								+ uid
								+ ".pem /upload_files/userkey_"
								+ uid
								+ ".pem \""
								+ pwd1 + "\" \"" + pwd1+"\"";
						log.debug("Myproxy command = " + myproxy);
						
						log.error("Username: "+usrnm);
						
						String[] myproxy2 = {"/usr/bin/python", "/upload_files/myproxy2.py", usrnm, "/upload_files/usercert_" + uid + ".pem", "/upload_files/userkey_" + uid + ".pem", pwd1, pwd1};
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
							log.info("[Stderr] " + line);
							errors.add("no-valid-key");
						}
						String[] myproxy3 = {"/usr/bin/python", "/upload_files/myproxy2.py", usrnm+"_rfc", "/upload_files/usercert_" + uid + ".pem", "/upload_files/userkey_" + uid + ".pem", pwd1, pwd1};
						String[] envRFC = {"GT_PROXY_MODE=rfc"};
						p = Runtime.getRuntime().exec(myproxy3 , envRFC, new File("/upload_files"));
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
							log.info("[Stderr] " + line);
							errors.add("no-valid-key");
						}
						if (!allOk) {
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

		log.info("controllo errori");
		if (allOk && errors.isEmpty()) {

			log.info("tutto ok!!");
			SessionMessages.add(request, "upload-cert-successufully");

			if (firstReg.equals("true")) {
				response.setRenderParameter("myaction",
						"showAddUserToVoPresents");
				response.setRenderParameter("userId", Integer.toString(uid));
				request.setAttribute("userId", uid);

			} else {
				response.setRenderParameter("myaction", "editUserInfoForm");
				response.setRenderParameter("userId", Integer.toString(uid));
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

			response.setRenderParameter("myaction", "showUploadCert");
			response.setRenderParameter("userId", String.valueOf(uid));
			request.setAttribute("userId", uid);
			// response.setRenderParameter("username", username);
			request.setAttribute("username", username);
			request.setAttribute("password", pwd1);
			request.setAttribute("passwordVerify", pwd2);
			if (firstReg.equals("true")) {
				response.setRenderParameter("firstReg", "true");
				request.setAttribute("firstReg", "true");
			}

		}

		if (!files.isEmpty())
			deleteUploadedFile(files, uid);

	}

	private void splitP12(String filename, int uid, String pwd1, String pwd2,
			ArrayList<String> errors) {

		try {
			String cmd = "/usr/bin/python /upload_files/splitP12.py /upload_files/"
					+ filename + " " + uid + " \"" + pwd1 + "\" \"" + pwd2+"\"";
			log.debug("cmd = " + cmd);
			
			
			String[] cmd2 ={"/usr/bin/python", "/upload_files/splitP12.py", "/upload_files/"+filename, Integer.toString(uid), pwd1, pwd2};
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
	public void setDefaultCert(ActionRequest request, ActionResponse response,
			SessionStatus sessionStatus) {

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

	@ActionMapping(params = "myaction=removeCert")
	public void removeCert(ActionRequest request, ActionResponse response,
			SessionStatus sessionStatus) {

		String contextPath = UploadCertController.class.getClassLoader()
				.getResource("").getPath();

		log.error("Sto per cancellare il proxy dove sono:" + contextPath);

		String myproxyHost = MYPROXY_HOST;

		File test = new File(contextPath + "/content/Registration.properties");
		log.error("File: " + test.getAbsolutePath());
		if (test.exists()) {
			log.error("ESISTE!!");
			try {
				FileInputStream inStream = new FileInputStream(contextPath
						+ "/content/Registration.properties");

				Properties prop = new Properties();

				prop.load(inStream);

				inStream.close();
				if (prop.getProperty("myproxy.storage") != null)
					myproxyHost = prop.getProperty("myproxy.storage");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		int idCert = Integer.parseInt(request.getParameter("idCert"));
		String userId = request.getParameter("userId");

		User user = (User) request.getAttribute(WebKeys.USER);

		String userPath = System.getProperty("java.io.tmpdir") + "/users/"
				+ user.getUserId() + "/";

//		File proxy = new File(userPath + "/x509up");
//
//		if (!proxy.exists()) {
//			SessionErrors.add(request,
//					"error-deleting-certificate-proxy-not-exists");
//			return;
//		}
//
//		GlobusCredential cred;
//
//		try {
//			cred = new GlobusCredential(proxy.toString());
//
//			if (cred.getTimeLeft() <= 0) {
//				SessionErrors.add(request,
//						"error-deleting-certificate-proxy-expired");
//				return;
//			}
//
//		} catch (GlobusCredentialException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}

		log.debug("Move to: " + userPath);
		String[] cmd = new String[] { "/usr/bin/myproxy-destroy", "-s",
				myproxyHost, "-l",
				certificateService.findByIdCert(idCert).getUsernameCert() };
		String allCmd = "";
		for (String string : cmd) {
			allCmd += string + " ";
		}

		log.error("myproxy destroy: " + allCmd);
//		String[] env = {"X509_USER_PROXY=x509up", "X509_USER_CERT="+userPath + "/x509up", "X509_USER_KEY="+userPath + "/x509up"};

		try {
			Process p = Runtime.getRuntime()
					.exec(cmd, null, new File(userPath));
			InputStream stdout = p.getInputStream();
			InputStream stderr = p.getErrorStream();

			BufferedReader output = new BufferedReader(new InputStreamReader(
					stdout));
			String line = null;

			while ((line = output.readLine()) != null) {
				log.error("[Stdout] " + line);
			}
			output.close();

			boolean isWrong = false;

			BufferedReader brCleanUp = new BufferedReader(
					new InputStreamReader(stderr));
			while ((line = brCleanUp.readLine()) != null) {
				log.error("[Stderr] " + line);
				if (!isWrong)
					SessionErrors.add(request,
							"error-deleting-certificate-wrong-proxy");
				isWrong = true;
			}
			brCleanUp.close();

			if (!isWrong) {
				log.error("Sto per cancellare il certificato con id = " + idCert);
				certificateService.delete(idCert);
				log.error("Certificato cancellato");

				SessionMessages.add(request,
						"certificate-deleted-successufully");
			}

		} catch (IOException e1) {
			SessionErrors.add(request, "error-deleting-certificate");
			e1.printStackTrace();
		}

		response.setRenderParameter("myaction", "editUserInfoForm");
		response.setRenderParameter("userId", userId);

	}

	private void deleteUploadedFile(ArrayList<String> files, int uid) {
		try {
			String cmd = "rm -f /upload_files/" + files.get(0);
			// + " /upload_files/" + files.get(1);
			log.info("cmd = " + cmd);
			Runtime.getRuntime().exec(cmd);

			File cert = new File("/upload_files/usercert_" + uid + ".pem");
			if (cert.exists())

				cert.delete();
			File key = new File("/upload_files/userkey_" + uid + ".pem");
			if (key.exists())
				key.delete();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@ActionMapping(params = "myaction=goBack")
	public void haveCert(ActionRequest request, ActionResponse response) {

		log.debug("Torna Indietro");

		String userId = request.getParameter("userId");
		String username = request.getParameter("username");
		String firstReg = request.getParameter("firstReg");
		boolean choice = Boolean.parseBoolean(firstReg);

		log.debug("Torna alla scelta del certificato? " + firstReg);

		String destination = "home";
		if(choice)
			destination = "showCAOnline";


		response.setRenderParameter("userId", userId);
		response.setRenderParameter("username", username);
		response.setRenderParameter("firstReg", firstReg);
		response.setRenderParameter("myaction", destination);

	}
	
	@ActionMapping(params = "myaction=changePwd2")
	public void updateGuseNotify(ActionRequest request, ActionResponse response) {
		
		Certificate cert = certificateService.findByIdCert(Integer.parseInt(request.getParameter("idCert")));
		log.error(Integer.parseInt(request.getParameter("idCert")));
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
//				if(userInfo.getPersistentId()!=null)
					bytesOfMessage = (userInfo.getPersistentId() + RegistrationConfig.getProperties("Registration.properties", "proxy.secret")).getBytes("UTF-8");
//				else
//					bytesOfMessage = ("blablabla" + RegistrationConfig.getProperties("Registration.properties", userInfo.getPersistentId())).getBytes("UTF-8");
				
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
				
				String[] myproxy2 = {"/usr/bin/python", "/upload_files/myproxy3-change.py", RegistrationConfig.getProperties("Registration.properties", "myproxy.storage"), cert.getUsernameCert(), tmpPwd, pwd};
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
							log.error(line);
							allOk = false;
						} else {
							if (line.equals("myproxy password not changed")) {
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
		request.setAttribute("userId", request.getParameter("userId"));

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
