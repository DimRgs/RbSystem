package system.service;

import system.po.UserInfo;

public interface UserService {
	
	public String testService(int i) throws Exception;
	public int getDepartmentCount() throws Exception;
	public UserInfo getUserInfo(String id, String password) throws Exception;
	public int getLastRbId(String user_id) throws Exception;
}
