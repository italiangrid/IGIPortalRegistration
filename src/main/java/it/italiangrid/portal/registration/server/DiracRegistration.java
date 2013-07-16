package it.italiangrid.portal.registration.server;

import it.italiangrid.portal.registration.dirac.DiracTask;
import it.italiangrid.portal.registration.dirac.DiracUtil;
import it.italiangrid.portal.registration.exception.RegistrationException;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class DiracRegistration implements Runnable {
	
	private static final Logger log = Logger.getLogger(DiracRegistrationService.class);
	
	private static List<DiracTask> queue = new ArrayList<DiracTask>();
	
	public DiracRegistration(){
		log.info("%%%%%%%%%%%%%%%%%%%%%%%%%% Initializing DIRAC registration queue. %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
//		queue = new ArrayList<DiracTask>();
	}
	
	public static void addDiracTask(DiracTask diracTask){
		log.info("%%%%%%%%%%%%%%%%%%%%%%%%%% adding task %%%%%%%%%%%%%%%%%%%%%%%%%% ");
			
		if(queue.add(diracTask)){
			log.info("%%%%%%%%%%%%%%%%%%%%%%%%%% TASK ADDED %%%%%%%%%%%%%%%%%%%%%%%%%% ");
		}else{
			log.info("%%%%%%%%%%%%%%%%%%%%%%%%%% TASK NOT ADDED %%%%%%%%%%%%%%%%%%%%%%%%%% ");
		}
		
		
		log.info(queue);
	}

	public void run() {
		log.info("%%%%%%%%%%%%%%%%%%%%%%%%%% STRATING PROCESS %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		while(true){
			
			if(!queue.isEmpty()){
				DiracUtil util = new DiracUtil(queue.get(0));
				try {
					log.info("%%%%%%%%%%%%%%%%%%%%%%%%%% Adding User: " + queue.get(0).getDn() + " in DIRAC.");
					util.addUser();
					log.info("%%%%%%%%%%%%%%%%%%%%%%%%%% Restarting DIRAC Proxy Manager");
					util.restartProxyManager();
					log.info("%%%%%%%%%%%%%%%%%%%%%%%%%% Uploading proxies for user: " + queue.get(0).getDn());
					util.uploadCert();
				} catch (RegistrationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				queue.remove(0);
			}else {
				log.info("%%%%%%%%%%%%%%%%%%%%%%%%%% EMPTY LIST %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}		
		}

	}

}
