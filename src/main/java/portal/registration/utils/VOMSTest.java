package portal.registration.utils;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import javax.xml.rpc.ServiceException;

import org.glite.security.voms.VOMSException;

public class VOMSTest {

	public static void main(String[] args) throws VOMSException, MalformedURLException, RemoteException, ServiceException {
		
//		VOMSAdminCallOut.test();
		
		
//		String[] roles = VOMSAdminCallOut.getUserFQANs("/C=IT/O=INFN/OU=Personal Certificate/L=Ferrara/CN=Enrico Vianello", "/C=IT/O=INFN/CN=INFN CA", "voms-02.pd.infn.it:8443/voms/superbvo.org");
//		if(roles!=null){
//		for (int i = 0; i < roles.length; i++) {
//
//			System.out.println("roles " + i + " = " + roles[i]);
//
//		}
//		}else{
//			System.out.println("utente non trovato");
//		}
//		
//		
//		
//			System.out.println("Utente vo trovato? " + VOMSAdminCallOut.getUser("/C=IT/O=INFN/OU=Personal Certificate/L=Ferrara/CN=Enrico Vianello", "/C=IT/O=INFN/CN=INFN CA", "voms-02.pd.infn.it:8443/voms/superbvo.org"));
//		
//	
		/*Date date = null;

		try {
			DateFormat formatter = new SimpleDateFormat(
					"MMM dd HH:mm:ss yyyy z", Locale.UK); //Oct 17 13:10:50 2012 GMT
			
			String enddate ="Oct 17 13:10:50 2012 GMT";
			date = (Date) formatter.parse(enddate);

			
			GregorianCalendar c = new GregorianCalendar();
			Date oggi = c.getTime();
			

			if (date.before(oggi)) {
				System.out.println("scatuto");
			} else {
				System.out.println("valido");
			}

		} catch (ParseException e) {
			System.out.println("Eccezzione data");
			e.printStackTrace();
		}*/
		
//		String[] values = {"wfchg_mess","wfchg_enab","quota_mess","quota_enab","email_addr","email_enab","email_subj"};
//		
//		List<String> keys = new ArrayList<String>(); //;
//		
//		keys = Arrays.asList(values);
//		
//		for (String string : keys) {
//			System.out.println(string);
//		}
	}

}
