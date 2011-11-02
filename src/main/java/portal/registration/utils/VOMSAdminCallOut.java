package portal.registration.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;
import org.glite.security.voms.User;
import org.glite.security.voms.VOMSException;
import org.glite.security.voms.service.admin.VOMSAdmin;
import org.glite.security.voms.service.admin.VOMSAdminServiceLocator;

public class VOMSAdminCallOut {

	private static final Logger log = Logger.getLogger(VOMSAdminCallOut.class);

	public static String[] getUserFQANs(String subject, String issuer,
			String voHost)  {
		String[] roles = null;
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
			
			if (roles == null) {
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
			//return null;
		} catch (RemoteException e) {
			log.info("RemoteException");
			e.printStackTrace();
			//return null;
		} catch (MalformedURLException e) {
			log.info("MalformedURLException");
			e.printStackTrace();
			//return null;
		} catch (ServiceException e) {
			log.info("ServiceException");
			e.printStackTrace();
			//return null;
		}
		
		return roles;

	}
	
	public static boolean getUser(String subject, String issuer,
			String voHost)  {
		
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
				log.info("risultato con subject = " + subject
						+ " e issuer = " + issuer);
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
		}
		
		
		return false;
		
	}
}
