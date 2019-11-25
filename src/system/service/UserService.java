package system.service;

import system.po.RbDetail;
import system.po.UserInfo;

public interface UserService {
	
	public String testService(int i) throws Exception;
	public int getDepartmentCount() throws Exception;
	public UserInfo getUserInfo(String id, String password) throws Exception;
	public RbDetail getLastRb(String user_id) throws Exception;
	public RbDetail getRbById(String rb_id) throws Exception;
	public int updateUserTel(String user_id, String telephone) throws Exception;
	public int updateUserPsd(String user_id, String psd, String newPsd) throws Exception;
	public RbDetail insertNewRb(String user_id) throws Exception;
	public int updateRbDetail(RbDetail rb) throws Exception;
}
