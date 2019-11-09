package system.control;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import system.service.UserService;

@Controller
public class UserHandler {
	
	@Autowired
	private UserService us;
	
	@RequestMapping("/test.do")
	@ResponseBody
	public Map<String, Object> JsonTest(String name, String password) throws Exception
	{
		Map<String, Object> map = new HashMap<String, Object>(3);
		map.put("test1", us.testService(1));
		map.put("test2", us.testService(2));
		map.put("test3", us.testService(3));
		map.put("name", name);
		map.put("count", us.getDepartmentCount());
		return map;
	}
}
