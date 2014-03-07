package it.italiangrid.portal.registration.util;

import it.italiangrid.portal.dbapi.domain.UserInfo;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

public class LiferayUIDComparator extends OrderByComparator {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8733693396662595005L;

	public static String ORDER_BY_ASC = "status ASC";

	public static String ORDER_BY_DESC = "status DESC";

	private boolean asc;
	private long companyId;

	public LiferayUIDComparator(boolean orderByAsc, long companyId) {
		this.asc = orderByAsc;
		this.companyId = companyId;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		
		UserInfo user1 = (UserInfo) obj1;
		UserInfo user2 = (UserInfo) obj2;
		
		try {
			
			User instance1 = UserLocalServiceUtil.fetchUserByScreenName(companyId, user1.getUsername());
			User instance2 = UserLocalServiceUtil.fetchUserByScreenName(companyId, user2.getUsername());
			
			if(instance1.getUserId() < instance2.getUserId()){
				return asc ? -1 : 1;
			}

			if(instance1.getUserId() > instance2.getUserId()){
				return asc ? 1 : -1;
			}
			
		} catch (SystemException e) {
			e.printStackTrace();
			
		}
		return 0;
	}

	public String getOrderBy() {

		if (this.asc) {
			return ORDER_BY_ASC;
		} else {
			return ORDER_BY_DESC;
		}
	}
}
