package portal.registration.server;

import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.axis.AxisProperties;

import org.apache.log4j.Logger;
import org.glite.security.trustmanager.axis.AXISSocketFactory;

public class PortalContextListener implements ServletContextListener {

	private static final Logger log = Logger.getLogger(PortalContextListener.class);
	private static final String DEFAULT_SSL_CERT_FILE = "/etc/grid-security/hostcert.pem";
	private static final String DEFAULT_SSL_KEY = "/etc/grid-security/hostkey.pem";

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		log.info("Registration Shutdown");
	}

	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		log.info("Registration Startup");
		initializeAxisProperties();

	}

	protected void initializeAxisProperties() {
		log.info("AXIS inizialization");
		AxisProperties.setProperty("axis.socketSecureFactory",
				"org.glite.security.trustmanager.axis.AXISSocketFactory");

		// need to pass property to AXISSocketFactory
		Properties properties = AXISSocketFactory.getCurrentProperties();

		// TODO will get cert and key from the configuration, with those as
		// default

		properties.setProperty("sslCertFile", DEFAULT_SSL_CERT_FILE); // hostcert.pem

		properties.setProperty("sslKey", DEFAULT_SSL_KEY); // hostkey.pem
		
		
		/*
		 * sslCAStore
		 * - The keystore that holds the CA certificates
		 * sslCAStoreType
		 * - The keystore type of the CA keystore "JKS" and "PKCS12" are supported (default "JKS")
		 * sslCAStorePasswd
		 * - The password to use to access the CA keystore 
		 */
		
		AXISSocketFactory.setCurrentProperties(properties);
		System.setProperties(properties);
	}

}
