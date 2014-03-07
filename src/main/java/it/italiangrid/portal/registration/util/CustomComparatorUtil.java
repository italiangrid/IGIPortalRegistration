package it.italiangrid.portal.registration.util;

import org.apache.log4j.Logger;

import com.liferay.portal.kernel.util.OrderByComparator;

public class CustomComparatorUtil {
	
	private static final Logger log = Logger.getLogger(CustomComparatorUtil.class);
	
	public static OrderByComparator getUserOrderByComparator(String orderByCol,
			String orderByType, long companyId) {

		boolean orderByAsc = false;

		if (orderByType.equals("asc")) {
			orderByAsc = true;
		}
		
		log.info("Order type is: " + orderByType + " --> orderByAsc is: " + orderByAsc);
		log.info("Order column: " + orderByCol);
		OrderByComparator orderByComparator = null;

		if (orderByCol.equalsIgnoreCase("firstName")) {
			log.info("Order by First Name");
			orderByComparator = new FirstNameComparator(orderByAsc);
		} else if (orderByCol.equalsIgnoreCase("lastName")) {
			log.info("Order by Last Name");
			orderByComparator = new LastNameComparator(orderByAsc);
		} else if (orderByCol.equalsIgnoreCase("institute")) {
			log.info("Order by Institute");
			orderByComparator = new InstituteComparator(orderByAsc);
		} else if (orderByCol.equalsIgnoreCase("mail")) {
			log.info("Order by e-Mail");
			orderByComparator = new EMailComparator(orderByAsc);
		} else if (orderByCol.equalsIgnoreCase("Registration Date")) {
			log.info("Order by Registration Date");
			orderByComparator = new RegistrationDateComparator(orderByAsc, companyId);
		} else if (orderByCol.equalsIgnoreCase("Liferay UID")) {
			log.info("Order by Liferay UID");
			orderByComparator = new LiferayUIDComparator(orderByAsc, companyId);
		} else if (orderByCol.equalsIgnoreCase("Group")) {
			log.info("Order by Group");
			orderByComparator = new GroupComparator(orderByAsc, companyId);
		} else {
			log.info("No order class found");
		}

		return orderByComparator;
	}
}
