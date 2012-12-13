package it.italiangrid.portal.registration.controller;

import it.italiangrid.portal.dbapi.domain.Certificate;
import it.italiangrid.portal.dbapi.services.CertificateService;
import it.italiangrid.portal.registration.model.RegistrationModel;
import it.italiangrid.portal.registration.util.CookieUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Properties;
import java.util.UUID;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.globus.gsi.GlobusCredential;
import org.globus.gsi.GlobusCredentialException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import portal.registration.controller.UploadCertController;
import portal.registration.utils.MyValidator;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
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
	private CertificateService certificateService;
	
	private static final String MYPROXY_HOST = "fullback.cnaf.infn.it";
	
	@RenderMapping(params = "myaction=showUploadCertificate")
	public String showAskForCertificate() {
		log.debug("Show uploadCertificate.jsp");
		return "uploadCertificate";
	}
	
	@ActionMapping(params = "myaction=uploadCertificate")
	public void uploadCert(@ModelAttribute RegistrationModel registrationModel, ActionRequest request, ActionResponse response,
			SessionStatus sessionStatus) throws PortalException,
			SystemException {
		log.error(registrationModel.toString());
		ArrayList<String> errors = new ArrayList<String>();
		boolean allOk = true;

		HttpServletRequest req = PortalUtil.getHttpServletRequest(request);

		String ns = response.getNamespace();
		String primaryCert = "";
		String pwd = "";
		String pwd1 = "";
		String pwd2 = "";
		ArrayList<String> files = new ArrayList<String>();
		String certificateUserId = UUID.randomUUID().toString();

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
						log.error(name);
						if (part.isParam()) {
							ParamPart paramPart = (ParamPart) part;
							String value = paramPart.getStringValue();
							if (name.equals(ns + "password"))
								pwd1 = value;
							if (name.equals(ns + "passwordVerify"))
								pwd2 = value;
							if (name.equals(ns + "keyPass"))
								pwd = value;
							if (name.equals(ns + "primaryCert"))
								primaryCert = value;
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

		if (MyValidator.validateCert(pwd, pwd1, pwd2, errors) && allOk) {
			// controllo file

			// esecuzione myproxy

			splitP12(files.get(0), certificateUserId, pwd, pwd1, errors);

			if (errors.isEmpty()) {
				String subject = myOpenssl("subject", "usercert_" + certificateUserId
						+ ".pem", errors);
				String issuer = myOpenssl("issuer", "usercert_" + certificateUserId + ".pem",
						errors);
				String enddate = myOpenssl("enddate", "usercert_" + certificateUserId
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
						cert.setUserInfo(null);
						cert.setCaonline("false");
						cert.setExpirationDate(date);
						cert.setIssuer(issuer);
						cert.setPrimaryCert(primaryCert);
						cert.setSubject(subject);
						cert.setUsernameCert(certificateUserId);
						
						registrationModel.setIssuer(issuer);
						registrationModel.setSubject(subject);
						registrationModel.setCertificateUserId(certificateUserId);

						
						
							int id = certificateService.save(cert);

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
						

						Runtime.getRuntime().exec(
								"/bin/chmod 600 /upload_files/userkey_" + certificateUserId
										+ ".pem");
						String myproxy = "/usr/bin/python /upload_files/myproxy2.py "
								+ registrationModel.getCertificateUserId()
								+ " /upload_files/usercert_"
								+ registrationModel.getCertificateUserId()
								+ ".pem /upload_files/userkey_"
								+ registrationModel.getCertificateUserId()
								+ ".pem \""
								+ pwd1 + "\" \"" + pwd1+"\"";
						log.debug("Myproxy command = " + myproxy);
						
						String[] myproxy2 = {"/usr/bin/python", "/upload_files/myproxy2.py", registrationModel.getCertificateUserId(), "/upload_files/usercert_" + certificateUserId + ".pem", "/upload_files/userkey_" + certificateUserId + ".pem", pwd1, pwd1};
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

		log.info("controllo errori");
		if (allOk && errors.isEmpty()) {

			log.info("tutto ok!!");
			SessionMessages.add(request, "upload-cert-successufully");
			CookieUtil.setCookie(registrationModel, response);
			response.setRenderParameter("myaction", "showAddVoForm");
			request.setAttribute("registrationModel", registrationModel);

		} else {

			log.info("Trovato errori");
			errors.add("error-uploading-certificate");

			for (String error : errors) {
				log.info("Errore: " + error);
				SessionErrors.add(request, error);
			}
			
			response.setRenderParameter("myaction", "showUploadCertificate");
			request.setAttribute("password", pwd1);
			request.setAttribute("passwordVerify", pwd2);
			request.setAttribute("registrationModel", registrationModel);

		}

		if (!files.isEmpty())
			deleteUploadedFile(files, certificateUserId);
		

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
		sessionStatus.setComplete();

	}

	@ActionMapping(params = "myaction=removeCert")
	public void removeCert(ActionRequest request, ActionResponse response,
			SessionStatus sessionStatus) {

		String contextPath = UploadCertController.class.getClassLoader()
				.getResource("").getPath();

		log.info("dove sono:" + contextPath);

		String myproxyHost = MYPROXY_HOST;

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

		File proxy = new File(userPath + "/x509up");

		if (!proxy.exists()) {
			SessionErrors.add(request,
					"error-deleting-certificate-proxy-not-exists");
			return;
		}

		GlobusCredential cred;

		try {
			cred = new GlobusCredential(proxy.toString());

			if (cred.getTimeLeft() <= 0) {
				SessionErrors.add(request,
						"error-deleting-certificate-proxy-expired");
				return;
			}

		} catch (GlobusCredentialException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		log.debug("Move to: " + userPath);
		String[] cmd = new String[] { "/usr/bin/myproxy-destroy", "-s",
				myproxyHost, "-l",
				certificateService.findByIdCert(idCert).getUsernameCert() };
		String allCmd = "";
		for (String string : cmd) {
			allCmd += string + " ";
		}

		log.info("myproxy destroy: " + allCmd);
		String[] env = {"X509_USER_PROXY=x509up", "X509_USER_CERT="+userPath + "/x509up", "X509_USER_KEY="+userPath + "/x509up"};

		try {
			Process p = Runtime.getRuntime()
					.exec(cmd, env, new File(userPath));
			InputStream stdout = p.getInputStream();
			InputStream stderr = p.getErrorStream();

			BufferedReader output = new BufferedReader(new InputStreamReader(
					stdout));
			String line = null;

			while ((line = output.readLine()) != null) {
				log.info("[Stdout] " + line);
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
				log.info("Sto per cancellare il certificato con id = " + idCert);
				certificateService.delete(idCert);
				log.info("Certificato cancellato");

				SessionMessages.add(request,
						"certificate-deleted-successufully");
			}

		} catch (IOException e1) {
			SessionErrors.add(request, "error-deleting-certificate");
			e1.printStackTrace();
		}

		response.setRenderParameter("myaction", "editUserInfoForm");
		response.setRenderParameter("userId", userId);
		sessionStatus.setComplete();

	}

	private void deleteUploadedFile(ArrayList<String> files, String certificateUserId) {
		try {
			String cmd = "rm -f /upload_files/" + files.get(0);
			// + " /upload_files/" + files.get(1);
			log.info("cmd = " + cmd);
			Runtime.getRuntime().exec(cmd);

			File cert = new File("/upload_files/usercert_" + certificateUserId + ".pem");
			if (cert.exists())

				cert.delete();
			File key = new File("/upload_files/userkey_" + certificateUserId + ".pem");
			if (key.exists())
				key.delete();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
