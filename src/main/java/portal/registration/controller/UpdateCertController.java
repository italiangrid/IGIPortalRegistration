package portal.registration.controller;

import it.italiangrid.portal.dbapi.domain.Certificate;
import it.italiangrid.portal.dbapi.domain.UserInfo;
import it.italiangrid.portal.dbapi.services.CertificateService;
import it.italiangrid.portal.dbapi.services.UserInfoService;
import it.italiangrid.portal.registration.dirac.DiracTask;
import it.italiangrid.portal.registration.dirac.DiracUtil;
import it.italiangrid.portal.registration.exception.RegistrationException;
import it.italiangrid.portal.registration.server.DiracRegistration;
import it.italiangrid.portal.registration.server.DiracRegistrationService;
import portal.registration.utils.MyValidator;

import java.io.BufferedReader;
import java.io.File;
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
import com.liferay.portal.util.PortalUtil;
import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.ParamPart;
import com.oreilly.servlet.multipart.Part;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

@Controller(value = "updateCertController")
@RequestMapping(value = "VIEW")
public class UpdateCertController {

	private static final Logger log = Logger
			.getLogger(UpdateCertController.class);

	@Autowired
	private CertificateService certificateService;
	
	@Autowired
	private UserInfoService userInfoService;

	@RenderMapping(params = "myaction=showUpdateCert")
	public String showUploadCert(RenderResponse response) {
		return "updateCert";
	}

	@ActionMapping(params = "myaction=updateCert")
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
		ArrayList<String> files = new ArrayList<String>();
		int uid = 0;
		int idCert = 0;

		Certificate backUp = null;

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
						if (part.isParam()) {
							ParamPart paramPart = (ParamPart) part;
							String value = paramPart.getStringValue();
							if (name.equals(ns + "password"))
								pwd1 = value;
							if (name.equals(ns + "passwordVerify"))
								pwd2 = value;
							if (name.equals(ns + "userId"))
								uid = Integer.parseInt(value);
							if (name.equals(ns + "idCert"))
								idCert = Integer.parseInt(value);
							if (name.equals(ns + "keyPass"))
								pwd = value;
							if (name.equals(ns + "primaryCert"))
								primaryCert = value;
							log.info("param; name=" + name + ", value=" + value);
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
			log.info("non è un a directory");
		}

		if (MyValidator.validateCert(pwd, pwd1, pwd2, errors) && allOk) {
			// controllo file

			// esecuzione myproxy

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
								"MMM dd HH:mm:ss yyyy z", Locale.UK); // Oct 17
																		// 13:10:50
																		// 2012
																		// GMT

						log.info(enddate);
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
						e.printStackTrace();
					}
					if (allOk) {
						log.info("idCert = " + idCert);
						backUp = certificateService.findByIdCert(idCert);
						log.info("backup subject = " + backUp.getSubject());
						Certificate cert = certificateService
								.findByIdCert(idCert);
						// cert.setCaonline("false");
						cert.setExpirationDate(date);
						// cert.setIssuer(issuer);
						cert.setPrimaryCert(primaryCert);
						// cert.setSubject(subject);
						if (cert.getSubject().equals(subject)
								&& cert.getIssuer().equals(issuer)) {
							certificateService.update(cert);
							log.info("aggionato il certificato " + idCert);
						} else {
							allOk = false;
							errors.add("error-certificate-to-update");
						}
					}
				} else {
					log.info("errori vari");
					allOk = false;
				}

				if (allOk) {
					try {

						Certificate certificate = certificateService
								.findByIdCert(idCert);

						Runtime.getRuntime().exec(
								"/bin/chmod 600 /upload_files/userkey_" + uid
										+ ".pem");
						String myproxy = "/usr/bin/python /upload_files/myproxy2.py "
								+ certificate.getUsernameCert()
								+ " /upload_files/usercert_"
								+ uid
								+ ".pem /upload_files/userkey_"
								+ uid
								+ ".pem "
								+ pwd1 + " " + pwd1;
						log.info("Myproxy command = " + myproxy);
						
						String[] myproxy2 = {"/usr/bin/python", "/upload_files/myproxy2.py", certificate.getUsernameCert(), "/upload_files/usercert_" + uid + ".pem", "/upload_files/userkey_" + uid + ".pem", pwd1, pwd1};
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
						String[] myproxy3 = {"/usr/bin/python", "/upload_files/myproxy2.py", certificate.getUsernameCert()+"_rfc", "/upload_files/usercert_" + uid + ".pem", "/upload_files/userkey_" + uid + ".pem", pwd1, pwd1};
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
							log.info("[Stderr] " + line);
							errors.add("no-valid-key");
						}
						if (!allOk) {
							// certificateService.delete(certificate);
							certificateService.update(backUp);
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
			response.setRenderParameter("myaction", "editUserInfoForm");
			response.setRenderParameter("userId", Integer.toString(uid));
			
			UserInfo userInfo = userInfoService.findById(uid);
			
			Certificate cert = certificateService.findById(userInfo.getUserId()).get(0);
			
			DiracTask diracTask = new DiracTask("/upload_files/usercert_" + uid + ".pem", "/upload_files/userkey_" + uid + ".pem", pwd1, userInfo.getMail(), cert.getSubject(), userInfo.getUsername());
			DiracRegistration.addDiracTask(diracTask);
			
//			DiracUtil util = new DiracUtil(userInfo, cert.getSubject());
//			
//			try {
//				util.addUser();
//				util.uploadCert("/upload_files/usercert_" + uid + ".pem", "/upload_files/userkey_" + uid + ".pem", pwd1);
//			} catch (RegistrationException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

		} else {

			log.info("Trovato errori");
			errors.add("error-uploading-certificate");

			for (String error : errors) {
				log.info("Errore: " + error);
				SessionErrors.add(request, error);
			}

			PortletConfig portletConfig = (PortletConfig)request.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
			SessionMessages.add(request, portletConfig.getPortletName() + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
			
			
			response.setRenderParameter("myaction", "showUpdateCert");
			request.setAttribute("userId", uid);
			request.setAttribute("idCert", idCert);
			request.setAttribute("password", pwd1);
			request.setAttribute("passwordVerify", pwd2);
			request.setAttribute("primCert", primaryCert);

		}
		
//		if (!files.isEmpty())
//			deleteUploadedFile(files, uid);

	}

	private void splitP12(String filename, int uid, String pwd1, String pwd2,
			ArrayList<String> errors) {

		try {
			
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
					errors.add("too-short-passpharase");
				if (line.equals("p12 passwd error key"))
					errors.add("p12-passwd-error-key");
				if (line.equals("error unrecognized"))
					errors.add("error-unrecognized");
				if (line.equals("p12 passwd error cert"))
					errors.add("p12-passwd-error-cert");
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
}
