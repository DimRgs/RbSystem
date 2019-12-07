package system.service;

import java.util.List;

import system.po.Admin;
import system.po.RbDetail;
import system.po.UserInfo;
import system.vo.RbAdminPostVO;
import system.vo.RbSearchForm;

public interface UserService {
	
	public String testService(int i) throws Exception;
	
	public RbDetail insertNewRb(String user_id) throws Exception;
	
	public int updateUserTel(String user_id, String telephone) throws Exception;
	public int updateUserPsd(String user_id, String psd, String newPsd) throws Exception;
	public int updateRbDetail(RbDetail rb) throws Exception;
	public int updateRbToCheck(RbDetail rb, Admin admin) throws Exception;
	public int updateRbToPost(RbAdminPostVO rbap) throws Exception;
	
	public int getDepartmentCount() throws Exception;
	public UserInfo getUserInfo(String id, String password) throws Exception;
	public Admin getAdminInfo(String id, String password) throws Exception;
	public RbDetail getLastRb(String user_id) throws Exception;
	public RbDetail getRbById(Integer rb_id) throws Exception;
	public List<RbDetail> getRbList(RbSearchForm rbsf) throws Exception;
	public int getRbCount(RbSearchForm rbsf) throws Exception;
}
