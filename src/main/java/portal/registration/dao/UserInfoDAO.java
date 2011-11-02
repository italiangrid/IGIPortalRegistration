package portal.registration.dao;

import java.util.List;

import portal.registration.domain.UserInfo;

public interface UserInfoDAO {

	public Integer save(UserInfo transientInstance);

	public void delete(UserInfo persistentInstance);

	public UserInfo findById(Integer id);

	public List<UserInfo> getAllUserInfo();

	public void edit(UserInfo userInfo);

	public List<UserInfo> getAllUserInfoByName(String search);

}
