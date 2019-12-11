package system.mapper;

import java.util.List;

import system.po.Admin;
import system.po.Ghf;
import system.po.RbDetail;
import system.po.RbInfo;
import system.po.Referral;
import system.po.Undo;
import system.po.User;
import system.po.UserInfo;
import system.po.Wssm;
import system.po.Yymx;
import system.vo.RbSearchForm;

public interface UserMapper {
	//insert
	public int insertNewRb(String user_id) throws Exception;
	public int insertNewReferral(Referral ref) throws Exception;
	public int insertNewGhf(Ghf ghf) throws Exception;
	public int insertNewYymx(Yymx yymx) throws Exception;
	public int insertNewWssm(Wssm wssm) throws Exception;
	public int insertRbOp(int rb_id, String admin_id, int operation) throws Exception;
	public int insertUndo(int rb_id, String note1) throws Exception;
	public int insertAdmin(Admin a) throws Exception;
	//update
	public int updateUserTel(String user_id, String telephone) throws Exception;
	public int updateUserPsd(String user_id, String psd, String newPsd) throws Exception;
	public int updateRbDetail(RbDetail rb) throws Exception;
	public int updateRbAdmin(RbDetail rb) throws Exception;
	public int updateReferral(Referral ref) throws Exception;
	public int updateGhf(Ghf ghf) throws Exception;
	public int updateYymx(Yymx yymx) throws Exception;
	public int updateWssm(Wssm wssm) throws Exception;
	public int updateRbState(int rb_id, int rb_state) throws Exception;
	public int updateRbCost(RbDetail rb) throws Exception;
	public int updateAdmin(Admin a) throws Exception;
	public int updateUndo(Undo undo) throws Exception;
	//select
	public int getDepartmentCount() throws Exception;
	public User getUserById(String id, String password) throws Exception;
	public UserInfo getUserInfo(String id, String password) throws Exception;
	public UserInfo getUerInfoById(String id) throws Exception;
	public Admin getAdminInfo(String id, String password) throws Exception;
	public RbDetail getLastRb(String user_id) throws Exception;
	public RbDetail getRbById(Integer rb_id) throws Exception;
	public int selectLastInsertId() throws Exception;
	public int getRbCount(RbSearchForm rbsf) throws Exception;
	public List<RbInfo> getRbList(RbSearchForm rbsf) throws Exception;
	public List<Admin> getAdminList() throws Exception;
	public Undo getUndo(int rb_id) throws Exception;
//	public int 
}
