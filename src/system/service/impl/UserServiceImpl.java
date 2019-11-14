package system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import system.mapper.UserMapper;
import system.po.User;
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
	public int getLastRbId(String user_id) throws Exception {
		// TODO 自动生成的方法存根
		return 0;
	}

}
