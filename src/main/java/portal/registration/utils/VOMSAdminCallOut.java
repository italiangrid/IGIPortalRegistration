package portal.registration.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Properties;

import javax.xml.rpc.ServiceException;

import org.apache.axis.AxisProperties;
import org.apache.log4j.Logger;
import org.glite.security.trustmanager.axis.AXISSocketFactory;
import org.glite.security.voms.User;
import org.glite.security.voms.VOMSException;
import org.glite.security.voms.service.admin.VOMSAdmin;
import org.glite.security.voms.service.admin.VOMSAdminServiceLocator;

import org.apache.commons.lang3.ArrayUtils;

public class VOMSAdminCallOut {

	// -Daxis.socketSecureFactory=org.glite.security.trustmanager.axis.AXISSocketFactory
	// -DsslCertFile=/etc/grid-security/hostcert.pem
	// -DsslKey=/etc/grid-security/hostkey.pem

	private static final Logger log = Logger.getLogger(VOMSAdminCallOut.class);

	private static final String DEFAULT_SSL_CERT_FILE = "/etc/grid-security/hostcert.pem";
	private static final String DEFAULT_SSL_KEY = "/etc/grid-security/hostkey.pem";

	public static String[] getUserFQANs(String subject, String issuer,
			String voHost) {
		
		String cert = DEFAULT_SSL_CERT_FILE;
		String key = DEFAULT_SSL_KEY;

		String contextPath = VOMSAdminCallOut.class.getClassLoader().getResource("").getPath();
		
		log.info("dove sono:" + contextPath);
		
		File test = new File(contextPath + "/content/Registration.properties");
		log.info("File: " + test.getAbsolutePath());
		if(test.exists()){
			log.info("ESISTE!!");
			try {
				FileInputStream inStream =
			    new FileInputStream(contextPath + "/content/Registration.properties");
		
				Properties prop = new Properties();
			
				prop.load(inStream);
			
				inStream.close();
				
				cert = prop.getProperty("SSL_CERT_FILE");
				key = prop.getProperty("SSL_KEY");
				
				log.info("try cert: "+ cert + " key: "+key);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(AxisProperties.getProperty("axis.socketSecureFactory") == null){
			AxisProperties.setProperty("axis.socketSecureFactory",
			"org.glite.security.trustmanager.axis.AXISSocketFactory");
		}else{
			log.info("axis già settato");
		}
		
		log.info("porpietà axis: " + AxisProperties.getProperty("axis.socketSecureFactory").toString());
		
		Properties properties = AXISSocketFactory.getCurrentProperties();
		
		log.info("set cert: "+ cert + " key: "+key);

		properties.setProperty("sslCertFile", cert); 

		properties.setProperty("sslKey", key); 

		String[] roles = null;
		String[] groups = null;
		try {

			log.info("Contatto VOMSAdmin con URL = https://" + voHost
					+ "/services/VOMSAdmin");

			String url = "https://" + voHost + "/services/VOMSAdmin";

			log.info("Trovo VOMSAdmin Service");

			VOMSAdminServiceLocator locator = new VOMSAdminServiceLocator();

			log.info("Prendo VOMSAdmin Service");

			VOMSAdmin adminService;

			adminService = locator.getVOMSAdmin(new URL(url));

			log.info("Uso VOMSAdmin Service con subject = " + subject
					+ " e issuer = " + issuer);

			roles = adminService.listRoles(subject, issuer);
			groups = adminService.listGroups(subject, issuer);

			if ((roles == null)&&(groups == null)) {
				log.info("nessun risultato con subject = " + subject
						+ " e issuer = " + issuer);
				log.info("Contatto VOMSAdmin con URL = https://" + voHost
						+ "/services/VOMSAdmin");
				return null;
			}

			log.info("roles = " + roles);

		} catch (VOMSException e) {
			log.info("VOMSexception");
			e.printStackTrace();
			// return null;
		} catch (RemoteException e) {
			log.info("RemoteException");
			e.printStackTrace();
			// return null;
		} catch (MalformedURLException e) {
			log.info("MalformedURLException");
			e.printStackTrace();
			// return null;
		} catch (ServiceException e) {
			log.info("ServiceException");
			e.printStackTrace();
			// return null;
		} finally {
			properties.remove("sslCertFile");
			properties.remove("sslKey");
		}

		
		return ArrayUtils.addAll(roles, groups); 
	}

	public static boolean getUser(String subject, String issuer, String voHost) {
		
		String cert = DEFAULT_SSL_CERT_FILE;
		String key = DEFAULT_SSL_KEY;

		String contextPath = VOMSAdminCallOut.class.getClassLoader().getResource("").getPath();
		
		log.info("dove sono:" + contextPath);
		
		File test = new File(contextPath + "/content/Registration.properties");
		log.info("File: " + test.getAbsolutePath());
		if(test.exists()){
			log.info("ESISTE!!");
			try {
				FileInputStream inStream =
			    new FileInputStream(contextPath + "/content/Registration.properties");
		
				Properties prop = new Properties();
			
				prop.load(inStream);
			
				inStream.close();
				
				cert = prop.getProperty("SSL_CERT_FILE");
				key = prop.getProperty("SSL_KEY");
				
				log.info("try cert: "+ cert + " key: "+key);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

		if(AxisProperties.getProperty("axis.socketSecureFactory") == null){
			AxisProperties.setProperty("axis.socketSecureFactory",
			"org.glite.security.trustmanager.axis.AXISSocketFactory");
		}

		log.info("porpietà axis: " + AxisProperties.getProperty("axis.socketSecureFactory").toString());
		
		Properties properties = AXISSocketFactory.getCurrentProperties();

		// log.info(properties);

		// Properties old = AXISSocketFactory.getCurrentProperties();

		// log.info(old);

		log.info("set cert: "+ cert + " key: "+key);

		properties.setProperty("sslCertFile", cert); //
		// hostcert.pem

		properties.setProperty("sslKey", key); // hostkey.pem

		// AXISSocketFactory.setCurrentProperties(properties);
		// System.setProperties(properties);

		/*
		 * if(properties.equals(old)){ log.info("***** UGUALI *****"); } else {
		 * log.info("***** DIVERSI *****"); }
		 */

		// Properties properties2 = AXISSocketFactory.getCurrentProperties();

		// log.info(properties2);

		try {

			log.info("Contatto VOMSAdmin con URL = https://" + voHost
					+ "/services/VOMSAdmin");

			String url = "https://" + voHost + "/services/VOMSAdmin";

			log.info("Trovo VOMSAdmin Service");

			VOMSAdminServiceLocator locator = new VOMSAdminServiceLocator();

			log.info("Prendo VOMSAdmin Service");

			VOMSAdmin adminService;

			adminService = locator.getVOMSAdmin(new URL(url));

			log.info("Uso VOMSAdmin Service con subject = " + subject
					+ " e issuer = " + issuer);

			User user = adminService.getUser(subject, issuer);

			if (user != null) {
				log.info("risultato con subject = " + subject + " e issuer = "
						+ issuer);
				log.info("Contatto VOMSAdmin con URL = https://" + voHost
						+ "/services/VOMSAdmin");
				return true;
			}

		} catch (VOMSException e) {
			log.info("VOMSexception");
			e.printStackTrace();

		} catch (RemoteException e) {
			log.info("RemoteException");
			e.printStackTrace();
		} catch (MalformedURLException e) {
			log.info("MalformedURLException");
			e.printStackTrace();
		} catch (ServiceException e) {
			log.info("ServiceException");
			e.printStackTrace();
		} finally {
			properties.remove("sslCertFile");
			properties.remove("sslKey");
		}

		return false;

	}
	
	static void test() {
		try {
			
			String cert = DEFAULT_SSL_CERT_FILE;
			String key = DEFAULT_SSL_KEY;
			
			
			
			if(AxisProperties.getProperty("axis.socketSecureFactory") == null){
				AxisProperties.setProperty("axis.socketSecureFactory",
				"org.glite.security.trustmanager.axis.AXISSocketFactory");
			}

			log.info("porpietà axis: " + AxisProperties.getProperty("axis.socketSecureFactory").toString());
			
			Properties properties = AXISSocketFactory.getCurrentProperties();

			// log.info(properties);

			// Properties old = AXISSocketFactory.getCurrentProperties();

			// log.info(old);

			log.info("set cert: "+ cert + " key: "+key);

			properties.setProperty("sslCertFile", cert); //
			// hostcert.pem

			properties.setProperty("sslKey", key); // hostkey.pem

			AXISSocketFactory.setCurrentProperties(properties);
			System.setProperties(properties);

			/*
			 * if(properties.equals(old)){ log.info("***** UGUALI *****"); } else {
			 * log.info("***** DIVERSI *****"); }
			 */

			Properties properties2 = AXISSocketFactory.getCurrentProperties();

			// log.info(properties2);
			
			log.info("AXIS cert: " + properties2.getProperty("sslCertFile"));
			log.info("AXIS key: " + properties2.getProperty("sslKey"));
			

			log.info("Contatto VOMSAdmin con URL = https://voms.cnaf.infn.it:8443/voms/gridit/services/VOMSAdmin");

			String url = "https://voms.cnaf.infn.it:8443/voms/gridit/services/VOMSAdmin";

			log.info("Trovo VOMSAdmin Service: "+ url);

			VOMSAdminServiceLocator locator = new VOMSAdminServiceLocator();

			log.info("Prendo VOMSAdmin Service");

			VOMSAdmin adminService;
			
			URL vomsUrl = new URL(url);
			
			log.info("Protocol: " + vomsUrl.getProtocol() + " Host: " + vomsUrl.getHost() + " Port: " + vomsUrl.getPort() + " Path: " + vomsUrl.getPath());

			adminService = locator.getVOMSAdmin(vomsUrl);

			log.info("TEST: " + adminService.getVOName());

		} catch (VOMSException e) {
			log.info("VOMSexception");
			e.printStackTrace();

		} catch (RemoteException e) {
			log.info("RemoteException");
			e.printStackTrace();
		} catch (MalformedURLException e) {
			log.info("MalformedURLException");
			e.printStackTrace();
		} catch (ServiceException e) {
			log.info("ServiceException");
			e.printStackTrace();
		}
		
	}
}

