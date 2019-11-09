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
		// TODO �Զ����ɵķ������
		return "Test Service " + i;
	}

	@Override
	public int getDepartmentCount() throws Exception {
		// TODO �Զ����ɵķ������
		return mapper.getDepartmentCount();
	}

}
