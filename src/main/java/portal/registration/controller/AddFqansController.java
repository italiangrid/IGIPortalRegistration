package portal.registration.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ActionMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;


import it.italiangrid.portal.dbapi.domain.Certificate;
import it.italiangrid.portal.dbapi.domain.UserInfo;
import it.italiangrid.portal.dbapi.domain.UserToVo;
import it.italiangrid.portal.dbapi.services.CertificateService;
import it.italiangrid.portal.dbapi.services.UserInfoService;
import it.italiangrid.portal.dbapi.services.UserToVoService;

import it.italiangrid.portal.registration.dirac.server.DiracRegistration;
import it.italiangrid.portal.registration.dirac.util.DiracTask;

@Controller(value = "addFqansController")
@RequestMapping(value = "VIEW")
public class AddFqansController {

	private static final Logger log = Logger
			.getLogger(AddFqansController.class);
	
	private static final String MYPROXY_HOST = "fullback.cnaf.infn.it";

	@Autowired
	private UserToVoService userToVoService;

	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private CertificateService certificateService;

	@ActionMapping(params = "myaction=addFqans")
	public void addUserInfo(ActionRequest request, ActionResponse response) {

		boolean firstReg = false;
		String[] fqans = request.getParameterValues("resultList");
		int userId = Integer.parseInt(request.getParameter("userId"));
		int idVo = Integer.parseInt(request.getParameter("idVo"));

		if (request.getParameter("firstReg") != null)
			firstReg = Boolean.parseBoolean(request.getParameter("firstReg"));

		UserToVo utv = userToVoService.findById(userId, idVo);

		if (fqans != null && !fqans[0].equals("")) {

			log.info("prova =" + fqans[0] + ";fine");

			String toSave = "";

			for (int i = 0; i < fqans.length; i++)
				toSave += fqans[i] + ";";

			log.info("Stampa FQANS: " + toSave);

			utv.setFqans(toSave);

		} else {
			utv.setFqans("");
		}

		userToVoService.update(utv);

		SessionMessages.add(request, "userToVo-updated-successufully");

		if (firstReg) {
			response.setRenderParameter("myaction", "showAddUserToVoPresents");
		} else {
			response.setRenderParameter("myaction", "editUserInfoForm");
		}

		response.setRenderParameter("userId", Integer.toString(userId));
		request.setAttribute("userId", userId);

	}

	@ActionMapping(params = "myaction=deleteByUser")
	public void removeUserInfo(@RequestParam int userId, ActionRequest request,
			ActionResponse response) throws PortalException, SystemException,
			IOException {

		UserInfo userInfo = userInfoService.findById(userId);
		String username = userInfo.getUsername();
		log.info("ricevuto userId " + userId + "corrispondente all'utente "
				+ username);
		long companyId = PortalUtil.getCompanyId(request);
		log.info("companyId " + companyId);
		User user = UserLocalServiceUtil.getUserByScreenName(companyId,
				username);
		if (user != null) {
			log.info("recuperato liferay user " + user.getScreenName());
			// user.setActive(false);
			// UserLocalServiceUtil.updateActive(user.getUserId(),false);
			UserLocalServiceUtil.deleteUser(user.getUserId());
			log.info("eliminato utente liferay");
		}
		
		List<Certificate> certs = certificateService.findById(userId);
		
		for (Certificate certificate : certs) {
			removeCert(request, response, certificate.getIdCert(), user);
			DiracTask diracTask = new DiracTask(null, null, null, null, certificate.getSubject() , userInfo.getUsername(), DiracTask.REMOVE_TASK);
			DiracRegistration.addDiracTask(diracTask);
		}

		userInfoService.delete(userId);
		log.info("eliminato utente portalUser");
		
		String userPath = System.getProperty("java.io.tmpdir") + "/users/"+ user.getUserId() + "/";
		FileUtil.deltree(userPath);
		log.info("Deleted User's Home Directory");
		
		response.sendRedirect(PortalUtil.getPortalURL(request)
				+ "/c/portal/logout");

	}
	
	public void removeCert(ActionRequest request, ActionResponse response,
			int idCert, User user) {

		String contextPath = UploadCertController.class.getClassLoader()
				.getResource("").getPath();

		log.info("Sto per cancellare il proxy dove sono:" + contextPath);

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
				e.printStackTrace();
			}
		}

		String userPath = System.getProperty("java.io.tmpdir") + "/users/"
				+ user.getUserId() + "/";
		
		File userPathFile = new File(userPath);
		if(!userPathFile.exists());
			userPathFile.mkdirs();

		log.info("Move to: " + userPath);
		String[] cmd = new String[] { "/usr/bin/myproxy-destroy", "-s",
				myproxyHost, "-l",
				certificateService.findByIdCert(idCert).getUsernameCert() };
		String allCmd = "";
		for (String string : cmd) {
			allCmd += string + " ";
		}

		log.info("myproxy destroy: " + allCmd);
		
		boolean isWrong = false;
		
		try {
//			String[] envp = {"X509_USER_CERT="+RegistrationConfig.getProperties("Registration.properties", "SSL_CERT_FILE"), "X509_USER_KEY="+RegistrationConfig.getProperties("Registration.properties", "SSL_KEY")};
			String[] envp = {"X509_USER_CERT=x509up", "X509_USER_KEY=x509up"};
			Process p = Runtime.getRuntime()
					.exec(cmd, envp, userPathFile);
			InputStream stdout = p.getInputStream();
			InputStream stderr = p.getErrorStream();

			BufferedReader output = new BufferedReader(new InputStreamReader(
					stdout));
			String line = null;

			while ((line = output.readLine()) != null) {
				log.info("[Stdout] " + line);
			}
			output.close();

			

			BufferedReader brCleanUp = new BufferedReader(
					new InputStreamReader(stderr));
			while ((line = brCleanUp.readLine()) != null) {
				log.info("[Stderr] " + line);
				isWrong = true;
			}
			brCleanUp.close();

		} catch (IOException e1) {
			e1.printStackTrace();
		} 
//		catch (RegistrationException e) {
//			e.printStackTrace();
//		}
		
		log.debug("Move to: " + userPath);
		String[] cmd2 = cmd;
		cmd2[4] = cmd[4]+"_rfc";
		String allCmd2 = "";
		for (String string : cmd2) {
			allCmd2 += string + " ";
		}

		log.info("myproxy destroy: " + allCmd2);

		try {
//			String[] envp = {"X509_USER_CERT="+RegistrationConfig.getProperties("Registration.properties", "SSL_CERT_FILE"), "X509_USER_KEY="+RegistrationConfig.getProperties("Registration.properties", "SSL_KEY")};
			String[] envp = {"X509_USER_CERT=x509up", "X509_USER_KEY=x509up"};
			Process p = Runtime.getRuntime()
					.exec(cmd2, envp, userPathFile);
			InputStream stdout = p.getInputStream();
			InputStream stderr = p.getErrorStream();

			BufferedReader output = new BufferedReader(new InputStreamReader(
					stdout));
			String line = null;

			while ((line = output.readLine()) != null) {
				log.info("[Stdout] " + line);
			}
			output.close();

			BufferedReader brCleanUp = new BufferedReader(
					new InputStreamReader(stderr));
			while ((line = brCleanUp.readLine()) != null) {
				log.info("[Stderr] " + line);
				isWrong = true;
			}
			brCleanUp.close();

		} catch (IOException e1) {
			e1.printStackTrace();
		} 
//		catch (RegistrationException e) {
//			e.printStackTrace();
//		}
		
		log.info("Move to: " + userPath);
		String[] cmd3 = new String[] { "/usr/bin/myproxy-destroy", "-s",
				myproxyHost, "-d" };
		String allCmd3 = "";
		for (String string : cmd3) {
			allCmd3 += string + " ";
		}

		log.info("myproxy destroy: " + allCmd3);

//		Map<String,String> sysEnv = System.getenv();
//		
//		Set<String> list  = sysEnv.keySet();
//		Iterator<String> iter = list.iterator();
//		String enviroment = "";			
//		while(iter.hasNext()) {
//		     String key = iter.next();
//		     String value = sysEnv.get(key);
//		     enviroment +="@@"+key+"="+value;
//		}
//		String[] envp = enviroment.split("@@");
//		
//		for(int i=0; i<envp.length; i++)
//				log.info("ENVP: " + envp[i]);
		
		try {
//			String[] envp = {"X509_USER_CERT="+RegistrationConfig.getProperties("Registration.properties", "SSL_CERT_FILE"), "X509_USER_KEY="+RegistrationConfig.getProperties("Registration.properties", "SSL_KEY")};
			String[] envp = {"X509_USER_PROXY=x509up"};
			Process p = Runtime.getRuntime().exec(cmd3, envp, userPathFile);
			InputStream stdout = p.getInputStream();
			InputStream stderr = p.getErrorStream();

			BufferedReader output = new BufferedReader(new InputStreamReader(
					stdout));
			String line = null;

			while ((line = output.readLine()) != null) {
				log.info("[Stdout] " + line);
			}
			output.close();

			

			BufferedReader brCleanUp = new BufferedReader(
					new InputStreamReader(stderr));
			while ((line = brCleanUp.readLine()) != null) {
				log.info("[Stderr] " + line);
				if (!isWrong)
					SessionErrors.add(request,
							"error-deleting-certificate-wrong-proxy");
//				isWrong = true;
			}
			brCleanUp.close();

		} catch (IOException e1) {
			e1.printStackTrace();
		}
//		catch (RegistrationException e) {
//			e.printStackTrace();
//		}
		
		if (!isWrong) {
			log.info("Sto per cancellare il certificato con id = " + idCert);
			certificateService.delete(idCert);
			log.info("Certificato cancellato");
		}

	}

}
