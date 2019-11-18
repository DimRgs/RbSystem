package system.mapper;

import system.po.User;
import system.po.UserInfo;

public interface UserMapper {
	public int getDepartmentCount() throws Exception;
	public User getUserById(String id, String password) throws Exception;
	public UserInfo getUserInfo(String id, String password) throws Exception;
	public int updateUserTel(String user_id, String telephone) throws Exception;
	public int updateUserPsd(String user_id, String psd, String newPsd) throws Exception;
	public int getLastRbId(String user_id) throws Exception;
//	public int 
}
