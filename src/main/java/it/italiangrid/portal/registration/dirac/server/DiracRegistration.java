package it.italiangrid.portal.registration.dirac.server;

import it.italiangrid.portal.registration.dirac.util.DiracTask;
import it.italiangrid.portal.registration.dirac.util.DiracUtil;
import it.italiangrid.portal.registration.exception.RegistrationException;
import it.italiangrid.portal.registration.util.RegistrationConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * This class define a separated thread with a static queue that contain the
 * submitted DirakTask. The thread scan the queue every 10 seconds, when the
 * scanner find a task, it use the information contained in the task and add the
 * user and his proxy in the DIRAC instance using the DiracUtil class.
 * 
 * @author dmichelotto
 * 
 */
public class DiracRegistration implements Runnable {

	/**
	 * Class logger.
	 */
	private static final Logger log = Logger
			.getLogger(DiracRegistrationService.class);

	/**
	 * Queue of DiracTask.
	 */
	private static List<DiracTask> queue = new ArrayList<DiracTask>();
	
	/**
	 * Connection to DB.
	 */
	private static Connection conn = null;

	/**
	 * Default constructor.
	 */
	public DiracRegistration() {
		log.info("Initializing DIRAC registration queue.");
	}

	/**
	 * Add a task in the queue. Check if the task is already in the list.
	 * 
	 * @param diracTask
	 *            - the task to add.
	 */
	public static void addDiracTask(DiracTask diracTask) {
		log.info("Adding Task do DIRAC Queue");

		for (DiracTask dt : queue) {
			if (dt.equals(diracTask)) {
				log.error("DiracTask already added in DIRAC queue.");
			}
		}

		if (queue.add(diracTask)) {
			log.info("Task Successfully Added");
		} else {
			log.error("Task Not Added ");
		}

		log.info(queue);
	}

	/**
	 * This thread scan the queue every 10 seconds if it is empty, otherwise use
	 * the task in order. When the thread find a task, it use the information
	 * contained in the task for Add User in DIRAC, restart the ProxyManager if
	 * DIRAC and upload the user's certificate.
	 */
	public void run() {
		try {
			log.info("Starting Queue Scanner Process.");
			
			conn = openConnetion();
			
			while (true) {

				if (!queue.isEmpty()) {
					
					List<DiracTask> scanList = new ArrayList<DiracTask>(queue);
					for (DiracTask diracTask : scanList) {
						switch (diracTask.getOperation()) {
						case DiracTask.ADD_TASK:
							log.info("Add task");
							DiracUtil util = new DiracUtil(diracTask);
							try {
								log.info("Adding User: " + diracTask.getDn()
										+ " in DIRAC.");
								util.addUser();
								log.info("Restarting DIRAC Proxy Manager");
								util.restartProxyManager();
								log.info("Waiting fo Restarting DIRAC Proxy Manager");
								Thread.sleep(5000);
								log.info("Uploading proxies for user: "
										+ diracTask.getDn());
								util.uploadCert();
							} catch (RegistrationException e) {
								e.printStackTrace();
							}
							break;
						
						case DiracTask.REMOVE_TASK:
							log.info("Delete task");
							removeUser(diracTask);
							break;

						default: log.info("Operation Not Permided. Removing Task.");
							
							break;
						}
						queue.remove(diracTask);
					}
					
				} else {
					log.info("Empty List");
				}
				Thread.sleep(10000);
			}
		} catch (Exception e) {
			e.printStackTrace();
			closeConnection();
			return;
		}

	}

	
	/**
	 * Delete user and respective certificate from DIRAC Proxy DB.
	 * 
	 * @param diracTask - The user information.
	 * @throws SQLException
	 * @throws RegistrationException
	 */
	private void removeUser(DiracTask diracTask) throws SQLException, RegistrationException {
		Statement statement = conn.createStatement();
		
		DiracUtil util = new DiracUtil(diracTask);
		util.deleteUser();
		log.info("User deleted");
		
	    String[] vos = RegistrationConfig.getProperties("Registration.properties", "dirac.admin.configuredVo").split(";");
	    
	    for (String vo : vos) {
	    	
	    	String query = "DELETE FROM `ProxyDB_Proxies` WHERE UserDN='" + diracTask.getDn() + "' AND UserGroup='" + vo + "_user';";
		    
		    log.info("Quering JobDB: " + query);
		    
		    statement.execute(query);
		    
		    String queryvoms = "DELETE FROM `ProxyDB_VOMSProxies` WHERE UserDN='" + diracTask.getDn() + "' AND UserGroup='" + vo + "_user';";
		    
		    log.info("Quering JobDB: " + queryvoms);
		    
		    statement.execute(queryvoms);
		    
	    }
	    
	}
	
	/**
	 * Open the connection to the DIRAC Proxy DB using the configuration file.
	 * 
	 * @return The connection.
	 * @throws DiracException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private Connection openConnetion() throws RegistrationException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		
		String url = RegistrationConfig.getProperties("../../spring.properties", "app2.jdbc.url");
	    String driver = RegistrationConfig.getProperties("../../spring.properties", "app2.jdbc.driverClassName");
	    String userName = RegistrationConfig.getProperties("../../spring.properties", "app2.jdbc.username");
	    String password = RegistrationConfig.getProperties("../../spring.properties", "app2.jdbc.password");
	    
	    Class.forName(driver).newInstance();
	    log.debug("Connected to the database");
	    
	    return DriverManager.getConnection(url,userName,password);
	    
	}
	
	/**
	 * Close the DIRAC Job DB connetion.
	 */
	public static void closeConnection(){
		try {
			if(conn!=null)
				conn.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

}
