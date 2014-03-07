package it.italiangrid.portal.registration.util;

import it.italiangrid.portal.dbapi.domain.UserInfo;

import com.liferay.portal.kernel.util.OrderByComparator;

public class FirstNameComparator extends OrderByComparator {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4309055636202140132L;

	public static String ORDER_BY_ASC = "status ASC";

	public static String ORDER_BY_DESC = "status DESC";

	private boolean asc;

	public FirstNameComparator(boolean orderByAsc) {
		this.asc = orderByAsc;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		UserInfo instance1 = (UserInfo) obj1;
		UserInfo instance2 = (UserInfo) obj2;

		int value = instance1.getFirstName().toLowerCase()
				.compareTo(instance2.getFirstName().toLowerCase());

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
