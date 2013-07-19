package it.italiangrid.portal.registration.dirac;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.liferay.portal.kernel.util.FileUtil;

import it.italiangrid.portal.registration.exception.RegistrationException;
import it.italiangrid.portal.registration.util.RegistrationConfig;

public class DiracUtil{
	
	private static final Logger log = Logger.getLogger(DiracUtil.class);
	
//	private String username;
//	private String email;
//	private String dn;
	
	private DiracTask diracTask;
	
//	public DiracUtil(UserInfo user, String dn){
//		this.username = user.getUsername();
//		this.email = user.getMail();
//		this.dn = dn;
//	}
	
	public DiracUtil(DiracTask diracTask){
		this.diracTask = diracTask;
	}
	
	public void addUser() throws RegistrationException{
		String diracDir = RegistrationConfig.getProperties("Registration.properties", "dirac.admin.homedir");
		File path = new File(System.getProperty("java.io.tmpdir") + "/" + diracDir);
		String[] vos = RegistrationConfig.getProperties("Registration.properties", "dirac.admin.configuredVo").split(";");
		
		String[] startCmd = {"dirac-admin-add-user", "-N", diracTask.getUsername(), "-D", "\"" + diracTask.getDn() + "\"", "-M", diracTask.getEmail()};
		List<String> cmdlist = new ArrayList<String>();
		
		for (String string : startCmd) {
			cmdlist.add(string);
		}
		
		for (String vo : vos) {
			cmdlist.add("-G");
			cmdlist.add(vo+"_user");
		}

		String[] cmd = cmdlist.toArray(new String[cmdlist.size()]);
		
		log.info(printArray(cmd));
		
		try {
			executeCommand(path, cmd);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RegistrationException("dirac-add-user-exception");
		}
		
	
	}
	
	public void restartProxyManager() throws RegistrationException{
		String diracDir = RegistrationConfig.getProperties("Registration.properties", "dirac.admin.homedir");
		String diracHost = RegistrationConfig.getProperties("Registration.properties", "dirac.admin.host");
		
		String[] cmd = {"/usr/bin/python", "dirac-restart-proxymanager.py", diracHost};
		
		File path = new File(System.getProperty("java.io.tmpdir") + "/" + diracDir);
		
		try {
			executeCommand(path, cmd);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RegistrationException("dirac-add-vo-exception");
		}
		
	}
	
	
	public void uploadCert() throws RegistrationException{
		String diracDir = RegistrationConfig.getProperties("Registration.properties", "dirac.admin.homedir");
		
		String[] vos = RegistrationConfig.getProperties("Registration.properties", "dirac.admin.configuredVo").split(";");
		String[] cmd = {"/usr/bin/python", "../dirac-proxy-upload.py", diracTask.getUserCert(), diracTask.getUserKey(), "vo", diracTask.getPassword()};
		
		String uuid = UUID.randomUUID().toString();
		File path = new File(System.getProperty("java.io.tmpdir") + "/" + diracDir + "/" + uuid);
		
		path.mkdir();
		
		for (String vo : vos) {
			cmd[4] = vo+"_user";
			
			log.info("Uploadind proxy in DIRAC for group: " + cmd[4]);
			
			try {
				executeCommand(path, cmd);
			} catch (IOException e) {
				e.printStackTrace();
				throw new RegistrationException("dirac-add-vo-exception");
			}
			
		}
		
		FileUtil.delete(new File(diracTask.getUserCert()));
		FileUtil.delete(new File(diracTask.getUserKey()));
		FileUtil.deltree(path);
		
	}
	
	public void deleteUser() throws RegistrationException{
		String diracDir = RegistrationConfig.getProperties("Registration.properties", "dirac.admin.homedir");
		log.info("Deleting User: " + diracTask.getUsername());
		
		String[] cmd = {"/usr/bin/python", "dirac-delete-user.py", diracTask.getUsername()};
		
		File path = new File(System.getProperty("java.io.tmpdir") + "/" + diracDir);
		
		try {
			executeCommand(path, cmd);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RegistrationException("dirac-delete-user-exception");
		}
		
		log.info("User " + diracTask.getUsername() + ": DELETED");
	}
	
	private String printArray(String[] array){
		String print = "";
		for (String string : array) {
			print += string + " ";
		}
		return print;
		
	}
	
	
	private void executeCommand(File path, String[] cmd) throws IOException{

		Process p = Runtime.getRuntime().exec(printArray(cmd), null, path);
		InputStream stdout = p.getInputStream();
		InputStream stderr = p.getErrorStream();

		BufferedReader output = new BufferedReader(new InputStreamReader(
				stdout));
		String line = null;

		while (((line = output.readLine()) != null)) {
			if(!line.contains("password"))
				log.info("[Stdout] " + line);
			
		}
		output.close();

		BufferedReader brCleanUp = new BufferedReader(
				new InputStreamReader(stderr));
		while ((line = brCleanUp.readLine()) != null) {

			log.error("[Stderr] " + line);
		}

		brCleanUp.close();
	}
	
}
