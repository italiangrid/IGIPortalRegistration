package it.italiangrid.portal.registration.dirac.util;

import it.italiangrid.portal.registration.dirac.server.DiracRegistration;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * The RESTful controller for the add task web service.
 * 
 * @author dmichelotto
 * 
 */
@Controller(value = "diracTaskController")
@RequestMapping("rest")
public class DiracTaskController {

	/**
	 * Class logger.
	 */
	private static final Logger log = Logger
			.getLogger(DiracTaskController.class);

	/**
	 * POST RESTful method. Get the DiracTask in JSON format, transform it and
	 * add the DiracTask to the queue.
	 * 
	 * @param addtask
	 *            - the DiracTask transformed from JSON.
	 * @return
	 */
	@RequestMapping(value = "addtask", method = RequestMethod.POST)
	@ResponseBody
	public String saveString(@RequestBody DiracTask addtask) {
		log.info(addtask);

		DiracRegistration.addDiracTask(addtask);
		
		log.debug("Task added: " + addtask);

		return "Task successfully added.";
	}

}
