package system.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import system.mapper.UserMapper;
import system.po.RbDetail;
import system.po.UserInfo;
import system.service.UserService;

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
		if(rb == null || rb.getId() == null)
		{
			String rb_id = "Rb" + user_id + System.nanoTime();
			if(mapper.insertNewRb(rb_id, user_id) == 1)
			{
				rb = new RbDetail();
				rb.setId(rb_id);
				rb.setState(0);
			}
			else 
				rb = null;
		}
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

}
