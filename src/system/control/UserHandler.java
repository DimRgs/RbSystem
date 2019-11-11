package system.control;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import system.po.UserInfo;
import system.service.UserService;

@Controller
public class UserHandler {
	
	@Autowired
	private UserService us;
	@Autowired
	private HttpServletRequest request;
	
	@RequestMapping("/remove.do")
	@ResponseBody
	public String removeSession() throws Exception
	{
		request.getSession().removeAttribute("user");
		return "remove";
	}
	
	@RequestMapping("/test.do")
	@ResponseBody
	public Map<String, Object> jsonTest(String name, String password) throws Exception
	{
		Map<String, Object> map = new HashMap<String, Object>(3);
		map.put("test1", us.testService(1));
		map.put("test2", us.testService(2));
		map.put("test3", us.testService(3));
		map.put("name", name);
		
		if(request.getSession().getAttribute("count") == null)
		{
			map.put("count", 0);
			request.getSession().setAttribute("count", us.getDepartmentCount());
		}
		else 
		{
			map.put("count", request.getSession().getAttribute("count"));
			
		}
		
		return map;
	}
	
	@RequestMapping("login.do")
	@ResponseBody
	public Map<String, Object> login(String id, String password) throws Exception 
	{
		Map<String, Object> map = new HashMap<String, Object>(3);
		UserInfo userInfo = us.getUserInfo(id);
		if(userInfo.getUser().getPassword().equalsIgnoreCase(password))
		{
			
		}
		return map;
	}
}
