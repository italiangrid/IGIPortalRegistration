package it.italiangrid.portal.registration.server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

public class DiracRegistrationService  implements ServletContextListener{
	
	private static final Logger log = Logger.getLogger(DiracRegistrationService.class);
	
	ExecutorService executor = Executors.newSingleThreadExecutor();
	
//	private static List<DiracTask> queue;
	
//	public static void addDiracTask(DiracTask diracTask){
//		log.info("%%%%%%%%%%%%%%%%%%%%%%%%%% adding task %%%%%%%%%%%%%%%%%%%%%%%%%% ");
//		if(queue == null){
//			log.info("%%%%%%%%%%%%%%%%%%%%%%%%%% INIT %%%%%%%%%%%%%%%%%%%%%%%%%% ");
//			queue = new ArrayList<DiracTask>();
//		}
//			
//		queue.add(diracTask);
//	}

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
		log.info("%%%%%%%%%%%%%%%%%%%%%%%%%% MORTE %%%%%%%%%%%%%%%%%%%%%%%%%% ");
		executor.shutdown();
	}

	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
		log.info("%%%%%%%%%%%%%%%%%%%%%%%%%% Starting DIRAC registration queue. %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
//		queue = new ArrayList<DiracTask>();
//		
//		while(true){
//			
//			if(!queue.isEmpty()){
//				DiracUtil util = new DiracUtil(queue.get(0));
//				try {
//					log.info("%%%%%%%%%%%%%%%%%%%%%%%%%% Adding User: " + queue.get(0).getDn() + " in DIRAC.");
//					util.addUser();
//					log.info("%%%%%%%%%%%%%%%%%%%%%%%%%% Restarting DIRAC Proxy Manager");
//					util.restartProxyManager();
//					log.info("%%%%%%%%%%%%%%%%%%%%%%%%%% Uploading proxies for user: " + queue.get(0).getDn());
//					util.uploadCert();
//				} catch (RegistrationException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				queue.remove(0);
//			}
//			
//		}
		
		
		
		executor.execute(new DiracRegistration());
		
		log.info("%%%%%%%%%%%%%%%%%%%%%%%%%% DIRAC registration queue Started. %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		
	}
	
	

}
