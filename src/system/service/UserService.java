package system.service;

import org.springframework.beans.factory.annotation.Autowired;

public interface UserService {
	
	public String testService(int i) throws Exception;
	public int getDepartmentCount() throws Exception;
}
