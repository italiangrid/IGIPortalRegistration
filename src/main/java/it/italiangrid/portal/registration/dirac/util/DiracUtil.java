package it.italiangrid.portal.registration.dirac.util;

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

/**
 * This class use the DIRAC clients and some Python scripts for manage the DIRAC
 * instance.
 * 
 * @author dmichelotto
 * 
 */
public class DiracUtil {

	/**
	 * Class logger.
	 */
	private static final Logger log = Logger.getLogger(DiracUtil.class);

	/**
	 * The task to use.
	 */
	private DiracTask diracTask;
	
	private List<String> vos = null;

	/**
	 * The class constriuctor.
	 * 
	 * @param diracTask
	 *            - The task to use.
	 * @throws RegistrationException 
	 */
	public DiracUtil(DiracTask diracTask) throws RegistrationException {
		this.diracTask = diracTask;
		this.vos = getVo();
	}

	/**
	 * Add User in DIRAC instance, and associate this user with all the
	 * configured VO and groups.
	 * 
	 * @throws RegistrationException
	 */
	public void addUser() throws RegistrationException {
		String diracDir = RegistrationConfig.getProperties(
				"Registration.properties", "dirac.admin.homedir");
		File path = new File(System.getProperty("java.io.tmpdir") + "/"
				+ diracDir);

		String[] startCmd = { "dirac-admin-add-user", "-N",
				diracTask.getUsername(), "-D", "\"" + diracTask.getDn() + "\"",
				"-M", diracTask.getEmail() };
		List<String> cmdlist = new ArrayList<String>();

		for (String string : startCmd) {
			cmdlist.add(string);
		}

		for (String vo : vos) {
			cmdlist.add("-G");
			cmdlist.add(vo + "_user");
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

	/**
	 * Restart the ProxyManager of the configured DIRAC instance.
	 * 
	 * @throws RegistrationException
	 */
	public void restartProxyManager() throws RegistrationException {
		String diracDir = RegistrationConfig.getProperties(
				"Registration.properties", "dirac.admin.homedir");
		String diracHost = RegistrationConfig.getProperties(
				"Registration.properties", "dirac.admin.host");

		String[] cmd = { "/usr/bin/python", "dirac-restart-proxymanager.py",
				diracHost };

		File path = new File(System.getProperty("java.io.tmpdir") + "/"
				+ diracDir);

		try {
			executeCommand(path, cmd);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RegistrationException("dirac-add-vo-exception");
		}

	}

	/**
	 * Upload the user's certificate for all the VO and group configured. At the
	 * and delete the user's certificate and key.
	 * 
	 * @throws RegistrationException
	 */
	public void uploadCert() throws RegistrationException {
		String diracDir = RegistrationConfig.getProperties(
				"Registration.properties", "dirac.admin.homedir");
		
		String[] cmd = { "/usr/bin/python", "../dirac-proxy-upload.py",
				diracTask.getUserCert(), diracTask.getUserKey(), "vo",
				diracTask.getPassword() };

		String uuid = UUID.randomUUID().toString();
		File path = new File(System.getProperty("java.io.tmpdir") + "/"
				+ diracDir + "/" + uuid);

		path.mkdir();

		for (String vo : vos) {
			cmd[4] = vo + "_user";

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

	/**
	 * Delete the user.
	 * 
	 * @throws RegistrationException
	 */
	public void deleteUser() throws RegistrationException {
		String diracDir = RegistrationConfig.getProperties(
				"Registration.properties", "dirac.admin.homedir");
		log.info("Deleting User: " + diracTask.getUsername());

		String[] cmd = { "/usr/bin/python", "dirac-delete-user.py",
				diracTask.getUsername() };

		File path = new File(System.getProperty("java.io.tmpdir") + "/"
				+ diracDir);

		try {
			executeCommand(path, cmd);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RegistrationException("dirac-delete-user-exception");
		}

		log.info("User " + diracTask.getUsername() + ": DELETED");
	}

	/**
	 * Print an String Array
	 * 
	 * @param array
	 * @return
	 */
	private String printArray(String[] array) {
		String print = "";
		for (String string : array) {
			print += string + " ";
		}
		return print;

	}
	
	/**
	 * Execute the specified command.
	 * 
	 * @param path
	 *            - The locatio of the execution.
	 * @param cmd
	 *            - The command.
	 * @throws IOException
	 */
	private void executeCommand(File path, String[] cmd) throws IOException {

		executeCommand(path, cmd, null);
	}

	/**
	 * Execute the specified command.
	 * 
	 * @param path
	 *            - The locatio of the execution.
	 * @param cmd
	 *            - The command.
	 * @throws IOException
	 */
	private void executeCommand(File path, String[] cmd, List<String> outputs) throws IOException {

		Process p = Runtime.getRuntime().exec(printArray(cmd), null, path);
		InputStream stdout = p.getInputStream();
		InputStream stderr = p.getErrorStream();

		BufferedReader output = new BufferedReader(
				new InputStreamReader(stdout));
		String line = null;

		while (((line = output.readLine()) != null)) {
			if (!line.contains("password")){
				log.info("[Stdout] " + line);
				if(outputs!=null){
					outputs.add(line);
				}
			}	
		}
		output.close();

		BufferedReader brCleanUp = new BufferedReader(new InputStreamReader(
				stderr));
		while ((line = brCleanUp.readLine()) != null) {

			log.error("[Stderr] " + line);
		}

		brCleanUp.close();
	}
	
	public List<String> getVo() throws RegistrationException {
		if(vos == null){
			List<String> outputs = new ArrayList<String>();
		
			String diracDir = RegistrationConfig.getProperties(
					"Registration.properties", "dirac.admin.homedir");
			File exeDir = new File(System.getProperty("java.io.tmpdir") + "/" + diracDir);
			
			log.info("Getting VO list");
			
			String[] cmd = { "/usr/bin/python", "dirac-get-vo.py"};
			
			log.info("Execute command: " + printArray(cmd));
			
			try {
				executeCommand(exeDir, cmd, outputs);
			} catch (IOException e) {
				e.printStackTrace();
				throw new RegistrationException("error-retrieving-sites");
			}
			
			List<String> voList = new ArrayList<String>();
			
			for (String line : outputs) {
				if(line.contains("Registry/VO/")){
					line = line.split("#")[0];
					line = line.replace("Registry/VO/", "");
					line = line.replaceAll(" ", "");
					voList.add(line);
				}
			}
			
			log.info("VOs: " + voList);
			
			return voList;
		} else {
			return vos;
		}
	}

}
