package it.italiangrid.portal.registration.util;

import it.italiangrid.portal.dbapi.domain.UserInfo;

import com.liferay.portal.kernel.util.OrderByComparator;

public class EMailComparator extends OrderByComparator {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5727023403877865426L;

	public static String ORDER_BY_ASC = "status ASC";

	public static String ORDER_BY_DESC = "status DESC";

	private boolean asc;

	public EMailComparator(boolean orderByAsc) {
		this.asc = orderByAsc;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		UserInfo instance1 = (UserInfo) obj1;
		UserInfo instance2 = (UserInfo) obj2;

		int value = instance1.getMail().toLowerCase()
				.compareTo(instance2.getMail().toLowerCase());

		if (this.asc) {
			return value;
		} else {
			return -value;
		}

	}

	public String getOrderBy() {

		if (this.asc) {
			return ORDER_BY_ASC;
		} else {
			return ORDER_BY_DESC;
		}
	}

}
