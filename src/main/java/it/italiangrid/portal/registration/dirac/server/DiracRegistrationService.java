package it.italiangrid.portal.registration.dirac.server;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

/**
 * This class manage the Dirac Registration separated thread.
 * 
 * @author dmichelotto
 * 
 */
public class DiracRegistrationService implements ServletContextListener {

	/**
	 * Class logger.
	 */
	private static final Logger log = Logger
			.getLogger(DiracRegistrationService.class);

	/**
	 * The executor for the thread management.
	 */
	private ScheduledExecutorService scheduler  = Executors.newSingleThreadScheduledExecutor();

	/**
	 * Kill the Dirac Registration thread.
	 */
	public void contextDestroyed(ServletContextEvent arg0) {

		log.info("Shutting down of DIRAC registration queue");
		
		DiracRegistration.closeConnection();
		
		scheduler.shutdownNow();

	}

	/**
	 * Launch the Dirac Registration thread.
	 */
	public void contextInitialized(ServletContextEvent arg0) {

		log.info("Starting of DIRAC registration queue.");

		scheduler.scheduleAtFixedRate(new DiracRegistration(), 0, 10, TimeUnit.SECONDS);

		log.info("DIRAC registration queue Started.");

	}

}
