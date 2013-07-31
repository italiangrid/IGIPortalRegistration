package it.italiangrid.portal.registration.dirac.server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
	private ExecutorService executor = Executors.newSingleThreadExecutor();

	/**
	 * Kill the Dirac Registration thread.
	 */
	public void contextDestroyed(ServletContextEvent arg0) {

		log.info("Shutting down of DIRAC registration queue");
		executor.shutdownNow();

	}

	/**
	 * Launch the Dirac Registration thread.
	 */
	public void contextInitialized(ServletContextEvent arg0) {

		log.info("Starting of DIRAC registration queue.");

		executor.execute(new DiracRegistration());

		log.info("DIRAC registration queue Started.");

	}

}
