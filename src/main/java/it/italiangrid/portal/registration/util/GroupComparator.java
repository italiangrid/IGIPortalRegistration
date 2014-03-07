package it.italiangrid.portal.registration.util;

import java.util.List;

import it.italiangrid.portal.dbapi.domain.UserInfo;

import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

public class GroupComparator extends OrderByComparator {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9156562812669532156L;

	public static String ORDER_BY_ASC = "status ASC";

	public static String ORDER_BY_DESC = "status DESC";

	private boolean asc;
	private long companyId;

	public GroupComparator(boolean orderByAsc, long companyId) {
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
			
			if(instance1==null || instance2==null){
				return 0;
			}
			
			String groups1 = groupToString(instance1.getGroups());
			String groups2 = groupToString(instance2.getGroups());
			
			int value = groups1.toLowerCase()
					.compareTo(groups2.toLowerCase());

			if (this.asc) {
				return value;
			} else {
				return -value;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	private String groupToString(List<Group> groups) {
		String groupList = "";
		for (Group group : groups) {
			groupList += group.getName() + ", ";
		}
		
		return groupList.substring(0, groupList.length() -2);
	}

	public String getOrderBy() {

		if (this.asc) {
			return ORDER_BY_ASC;
		} else {
			return ORDER_BY_DESC;
		}
	}

}
