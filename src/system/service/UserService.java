package system.service;

import java.util.List;

import system.po.Admin;
import system.po.RbDetail;
import system.po.RbInfo;
import system.po.Undo;
import system.po.UserInfo;
import system.vo.RbAdminPostVO;
import system.vo.RbSearchForm;

public interface UserService {
	
	public String testService(int i) throws Exception;
	
	public RbDetail insertNewRb(String user_id) throws Exception;
	public Undo insertUndo(int rb_id, String note1) throws Exception;
	
	public int updateUserTel(String user_id, String telephone) throws Exception;
	public int updateUserPsd(String user_id, String psd, String newPsd) throws Exception;
	public int updateRbDetail(RbDetail rb) throws Exception;
	public int updateRbToCheck(RbDetail rb, Admin admin) throws Exception;
	public int updateRbToPost(RbAdminPostVO rbap) throws Exception;
	public int insertAdmin(Admin a) throws Exception;
	
	public int updateRbState(int rb_id, int rb_state, String admin_id) throws Exception;
	public int updateUndo(Undo undo) throws Exception;
	public int updateAdmin(Admin a) throws Exception;
//	public int updateRbState(int rb_id, int rb_state) throws Exception;
	
	public int getDepartmentCount() throws Exception;
	public UserInfo getUserInfo(String id, String password) throws Exception;
	public UserInfo getUserInfoById(String id) throws Exception;
	public Admin getAdminInfo(String id, String password) throws Exception;
	public RbDetail getLastRb(String user_id) throws Exception;
	public RbDetail getRbById(Integer rb_id) throws Exception;
	public List<RbInfo> getRbList(RbSearchForm rbsf) throws Exception;
	public int getRbCount(RbSearchForm rbsf) throws Exception;
	public List<Admin> getAdminList() throws Exception;
	public Undo getUndo(int rb_id) throws Exception;
}
