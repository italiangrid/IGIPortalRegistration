package portal.registration.server;

import org.apache.log4j.Logger;

public class Notificator implements Runnable{
	
	private static final Logger log = Logger
			.getLogger(Notificator.class);
	
	public void run(){
		try{
			log.error("############## NOTIFICATOR ##############");
			
			/*
			 * Recupera cose da notificate
			 * 
			 * notifica se il tempo del proxy rimante è inferiore ad un'ora.
			 * 
			 */
			
		}catch (Exception e) {
			// TODO: handle exception
			return;
		}
	}

}
