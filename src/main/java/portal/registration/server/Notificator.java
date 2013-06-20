package portal.registration.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import it.italiangrid.portal.registration.exception.RegistrationException;
import it.italiangrid.portal.registration.util.RegistrationConfig;

import org.apache.log4j.Logger;
import portal.registration.utils.SendMail;


public class Notificator implements Runnable {

	private static final Logger log = Logger.getLogger(Notificator.class);

	public void run() {
		
		log.debug("Check certificate");
		try {
		    Connection conn = null;
		    String url = RegistrationConfig.getProperties("../../spring.properties", "app.jdbc.url");
		    String driver = RegistrationConfig.getProperties("../../spring.properties", "app.jdbc.driverClassName");
		    String userName = RegistrationConfig.getProperties("../../spring.properties", "app.jdbc.username");
		    String password = RegistrationConfig.getProperties("../../spring.properties", "app.jdbc.password");
		    
		    Class.forName(driver).newInstance();
		    conn = DriverManager.getConnection(url,userName,password);
		    log.debug("Connected to the database");
		      
		    Statement statement = conn.createStatement();
		      
		    ResultSet resultSet = statement.executeQuery(RegistrationConfig.getProperties("Registration.properties", "certificate.check.query"));
		    while (resultSet.next()) {
	
		    	String user = resultSet.getString("firstName");
		    	String mail = resultSet.getString("mail");
		    	String expirationDate = resultSet.getString("expirationDate");
		    	String subject = resultSet.getString("subject");
		          
		    	log.debug("Found: " + user + " | " + mail + " | " + subject + " | " + expirationDate);
		          
		    	DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ITALY);
		    	Date date = (Date) formatter.parse(expirationDate);
		    	log.debug("Expiration date = " + date.toString());
		    	
		    	String[] checkDate = RegistrationConfig.getProperties("Registration.properties", "certificate.check.days").split(";");
		    	
		    	for(String s: checkDate){
		    		int days = Integer.parseInt(s);
		    		
		    		Date check = getDate(days);
		    		log.debug("Check: " + check.toString());
		    		
		    		if(date.compareTo(check)==0){
		    			log.debug("####################### Expire in " + days + " days");
						String from = RegistrationConfig.getProperties("Registration.properties", "igiportal.mail");
						String mailSubject = RegistrationConfig.getProperties("Registration.properties", days==0?"certificate.check.subject.expired":"certificate.check.subject.days").replace("##DAYS##", String.valueOf(days));
						String mailContent = RegistrationConfig.getProperties("Registration.properties", days==0?"certificate.check.mail.expired":"certificate.check.mail.days").replaceAll("##DAYS##", String.valueOf(days)).replaceAll("##USER##", user).replaceAll("##SUBJECT##", subject).replaceAll("##HOME##", RegistrationConfig.getProperties("Registration.properties", "home.url"));
						SendMail sm = new SendMail(from, mail, mailSubject, mailContent);
						sm.send();
		    		}
		    	}
	    	}
		    conn.close();
		    log.debug("Disconnected from database");
		} catch(RegistrationException e){
			log.info(e.getMessage());
		} catch (ParseException e) {
			log.info("Parsing exception");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
			
	}
	
	private Date getDate(int days){
		Calendar cal = new GregorianCalendar();
		cal.add(Calendar.DAY_OF_YEAR, days);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		return cal.getTime();
	}

}
