package it.italiangrid.portal.diracregistration.server;

import it.italiangrid.portal.diracregistration.dirac.DiracTask;
import it.italiangrid.portal.diracregistration.dirac.DiracUtil;
import it.italiangrid.portal.registration.exception.RegistrationException;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * This class define a separated thread with a static queue that contain the
 * submitted DirakTask. The thread scan the queue every 10 seconds, when the
 * scanner find a task, it use the information contained in the task and add the
 * user and his proxy in the DIRAC instance using the DiracUtil class.
 * 
 * @author dmichelotto
 * 
 */
public class DiracRegistration implements Runnable {

	/**
	 * Class logger.
	 */
	private static final Logger log = Logger
			.getLogger(DiracRegistrationService.class);

	/**
	 * Queue of DiracTask.
	 */
	private static List<DiracTask> queue = new ArrayList<DiracTask>();

	/**
	 * Default constructor.
	 */
	public DiracRegistration() {
		log.info("Initializing DIRAC registration queue.");
	}

	/**
	 * Add a task in the queue. Check if the task is already in the list.
	 * 
	 * @param diracTask
	 *            - the task to add.
	 */
	public static void addDiracTask(DiracTask diracTask) {
		log.info("Adding Task do DIRAC Queue");

		for (DiracTask dt : queue) {
			if (dt.equals(diracTask)) {
				log.error("DiracTask already added in DIRAC queue.");
			}
		}

		if (queue.add(diracTask)) {
			log.info("Task Successfully Added");
		} else {
			log.error("Task Not Added ");
		}

		log.info(queue);
	}

	/**
	 * This thread scan the queue every 10 seconds if it is empty, otherwise use
	 * the task in order. When the thread find a task, it use the information
	 * contained in the task for Add User in DIRAC, restart the ProxyManager if
	 * DIRAC and upload the user's certificate.
	 */
	public void run() {
		try {
			log.info("Starting Queue Scanner Process.");
			while (true) {

				if (!queue.isEmpty()) {
					DiracUtil util = new DiracUtil(queue.get(0));
					try {
						log.info("Adding User: " + queue.get(0).getDn()
								+ " in DIRAC.");
						util.addUser();
						log.info("Restarting DIRAC Proxy Manager");
						util.restartProxyManager();
						log.info("Waiting fo Restarting DIRAC Proxy Manager");
						Thread.sleep(5000);
						log.info("Uploading proxies for user: "
								+ queue.get(0).getDn());
						util.uploadCert();
					} catch (RegistrationException e) {
						e.printStackTrace();
					}
					queue.remove(0);
				} else {
					log.info("Empty List");

					Thread.sleep(10000);

				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			return;
		}

	}

}
