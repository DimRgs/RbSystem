package system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import system.mapper.UserMapper;
import system.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper mapper;
	
	@Override
	public String testService(int i) throws Exception {
		// TODO 自动生成的方法存根
		return "Test Service " + i;
	}

	@Override
	public int getDepartmentCount() throws Exception {
		// TODO 自动生成的方法存根
		return mapper.getDepartmentCount();
	}

}
