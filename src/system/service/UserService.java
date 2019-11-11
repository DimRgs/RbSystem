package system.service;

import system.po.UserInfo;

public interface UserService {
	
	public String testService(int i) throws Exception;
	public int getDepartmentCount() throws Exception;
	public UserInfo getUserInfo(String id) throws Exception;
}
