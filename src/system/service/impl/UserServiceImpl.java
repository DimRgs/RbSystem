package system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import system.mapper.UserMapper;
import system.po.Admin;
import system.po.Ghf;
import system.po.RbDetail;
import system.po.Referral;
import system.po.Undo;
import system.po.UserInfo;
import system.po.Wssm;
import system.po.Yymx;
import system.service.UserService;
import system.vo.RbAdminPostVO;
import system.vo.RbSearchForm;

import static system.util.SystemUtil.stateToOperation;

@Transactional
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper mapper;
	
	@Override
	public String testService(int i) throws Exception {
		return "Test Service " + i;
	}

	@Override
	public int getDepartmentCount() throws Exception {
		return mapper.getDepartmentCount();
	}

	@Override
	public UserInfo getUserInfo(String id, String password) throws Exception {
		// TODO 自动生成的方法存根
		return mapper.getUserInfo(id, password);
	}

	@Override
	public RbDetail getLastRb(String user_id) throws Exception {
		// TODO 自动生成的方法存根
		RbDetail rb = mapper.getLastRb(user_id);
		return rb;
	}
	

	@Override
	public int updateUserTel(String user_id, String telephone) throws Exception {
		// TODO 自动生成的方法存根
		return mapper.updateUserTel(user_id, telephone);
	}

	@Override
	public int updateUserPsd(String user_id, String psd, String newPsd) throws Exception {
		// TODO 自动生成的方法存根
		return mapper.updateUserPsd(user_id, psd, newPsd);
	}

	@Override
	public RbDetail getRbById(Integer rb_id) throws Exception {
		// TODO 自动生成的方法存根
		return mapper.getRbById(rb_id);
	}

	@Override
	public RbDetail insertNewRb(String user_id) throws Exception {
		// TODO 自动生成的方法存根
		RbDetail rb = null;
		if(mapper.insertNewRb(user_id) == 1)
		{
			rb = new RbDetail();
			rb.setRb_id(mapper.selectLastInsertId());
		}
		else 
			rb = null;
		return rb;
	}

	@Override
	public int updateRbDetail(RbDetail rb) throws Exception {
		// TODO 自动生成的方法存根
		int ret = 0;
		int rb_id = rb.getRb_id();
//		System.out.println("********************"+rb.getHashMap().toString());
		if(mapper.updateRbDetail(rb) != 1)
		{
			return 0;
		}
		else
		{
			Referral ref = rb.getReferral();
			ref.setRb_id(rb_id);
			if(ref.getId() == 0)
			{
				ret = mapper.insertNewReferral(ref);
			}
			else 
			{
				ret = mapper.updateReferral(ref);
			}
			if(ret != 1)
			{
				return 0;
			}

			for(Ghf ghf : rb.getGhf())
			{
				ghf.setRb_id(rb_id);
				if(ghf.getId() == 0)
				{
					ret = mapper.insertNewGhf(ghf);
				}
				else 
				{
					ret = mapper.updateGhf(ghf);
				}
				
				if(ret != 1)
				{
					return 0;
				}
			}
			for(Yymx yymx : rb.getYymx())
			{
				yymx.setRb_id(rb_id);
				if(yymx.getId() == 0)
				{
					ret = mapper.insertNewYymx(yymx);
				}
				else 
				{
					ret = mapper.updateYymx(yymx);
				}
				
				if(ret != 1)
				{
					return 0;
				}
			}
			Wssm wssm = rb.getWssm();
			wssm.setRb_id(rb_id);
			if(wssm.getId() == 0)
			{
				ret = mapper.insertNewWssm(wssm);
			}
			else 
			{
				ret = mapper.updateWssm(wssm);
			}
			if(ret != 1)
			{
				return 0;
			}
		}
		return 1;
	}

	@Override
	public Admin getAdminInfo(String id, String password) throws Exception {
		// TODO 自动生成的方法存根
		return mapper.getAdminInfo(id, password);
	}

	@Override
	public List<RbDetail> getRbList(RbSearchForm rbsf) throws Exception {
		// TODO 自动生成的方法存根
		return mapper.getRbList(rbsf);
	}

	@Override
	public int getRbCount(RbSearchForm rbsf) throws Exception {
		// TODO 自动生成的方法存根
		return mapper.getRbCount(rbsf);
	}

	@Override
	public int updateRbToCheck(RbDetail rb, Admin admin) throws Exception {
		// TODO 自动生成的方法存根
		rb.setRb_state(3);
		rb.setAdmin(admin);
		mapper.insertRbOp(rb.getRb_id(), admin.getId(), stateToOperation(rb.getRb_state()));
		return mapper.updateRbAdmin(rb);
	}

	@Override
	public int updateRbToPost(RbAdminPostVO rbap) throws Exception {
		// TODO 自动生成的方法存根
		RbDetail rb = new RbDetail();
		rb.setRb_id(rbap.getRb_id());
		rb.setRb_state(rbap.getResult());
		if(mapper.updateRbAdmin(rb) != 1)
		{
			return 0;
		}
		
		for(Ghf g : rbap.getGhf())
		{
			if(mapper.updateGhf(g) != 1)
				return 0;
		}
		
		for(Yymx y : rbap.getYymx())
		{
			if(mapper.updateYymx(y) != 1)
				return 0;
		}
		int op = stateToOperation(rb.getRb_state());
		
		return mapper.insertRbOp(rb.getRb_id(), rbap.getAdmin_id(), op);
	}

	@Override
	public int updateRbState(int rb_id, int rb_state, String admin_id) throws Exception {
		// TODO 自动生成的方法存根
		int op = stateToOperation(rb_state);
		if(op != 0 && admin_id != null)
		{
			mapper.insertRbOp(rb_id, admin_id, op);
		}
		
		if(rb_state == 6)
		{
			calculateTotalCost(rb_id);
		}
		
		return mapper.updateRbState(rb_id, rb_state);
	}
	
	private int calculateTotalCost(int rb_id) throws Exception
	{
		RbDetail rb = this.getRbById(rb_id);
		int totalCost = 0;
		int totalSelfPaid = 0;
		for(Ghf g : rb.getGhf())
		{
			totalCost += g.getCost();
			totalSelfPaid += g.getSelf_paid();
		}
		for(Yymx y : rb.getYymx())
		{
			totalCost += y.getCost();
			totalSelfPaid += y.getPart_paid();
			totalSelfPaid += y.getSpecial_paid();
			totalSelfPaid += y.getSelf_paid();
		}
		
		rb.setTotal_cost(totalCost);
		rb.setTotal_self_paid(totalSelfPaid);
		
		return mapper.updateRbCost(rb);
	}

	@Override
	public UserInfo getUserInfoById(String id) throws Exception {
		// TODO 自动生成的方法存根
		return mapper.getUerInfoById(id);
	}

	@Override
	public List<Admin> getAdminList() throws Exception {
		// TODO 自动生成的方法存根
		return mapper.getAdminList();
	}


	@Override
	public Undo getUndo(int rb_id) throws Exception {
		// TODO 自动生成的方法存根
		return mapper.getUndo(rb_id);
	}

	@Override
	public Undo insertUndo(int rb_id, String note1) throws Exception {
		// TODO 自动生成的方法存根
		mapper.insertUndo(rb_id, note1);
		return mapper.getUndo(rb_id);
	}

	@Override
	public int updateUndo(Undo undo) throws Exception {
		// TODO 自动生成的方法存根
		return mapper.updateUndo(undo);
	}

}
