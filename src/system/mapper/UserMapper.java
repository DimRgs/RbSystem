package system.mapper;

import system.po.User;
import system.po.UserInfo;

public interface UserMapper {
	public int getDepartmentCount() throws Exception;
	public User getUserById(String id, String password) throws Exception;
	public UserInfo getUserInfo(String id, String password) throws Exception;
}
