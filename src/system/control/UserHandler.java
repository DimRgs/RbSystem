package system.control;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import system.po.User;
import system.po.UserInfo;
import static system.util.SystemUtil.*;		//导入静态方法

@Controller
public class UserHandler extends RootHandler {

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
		map.put("root", request.getSession().getAttribute("root"));
		
		return map;
	}
	
	@RequestMapping("homepage.do")
	@ResponseBody
	public Map<String, Object> homepage() throws Exception
	{
		Map<String, Object> map = new HashMap<String, Object>(3);
		User user = (User)request.getSession().getAttribute("User");
		if(user == null)
		{
			setF(map);
		}
		else 
		{
			setS(map);
			map.put("rb_state", us.getLastRbId(user.getId())) ;
		}
		return map;
	}
	
	@RequestMapping("login.do")
	@ResponseBody
	public Map<String, Object> login(String id, String password) throws Exception 
	{
		Map<String, Object> map = new HashMap<String, Object>(3);
		UserInfo userInfo = us.getUserInfo(id, password);
		if(userInfo == null)
		{
			//还需要判断是否是管理人员
			setF(map);
		}
		else if(userInfo.getUser().getPassword().equalsIgnoreCase(password))
		{
			setS(map);
			map.put("level", 1);
			map.put("User", userInfo.getUser());
			map.put("Department", userInfo.getDepartment());
			request.getSession().setAttribute("User", userInfo.getUser());
		}
		return map;
	}
	
	@RequestMapping("user/changTel.do")
	@ResponseBody
	public Map<String, Object> changTel(String id, String telephone) throws Exception
	{
		Map<String, Object> map = new HashMap<String, Object>(3);
		String result = us.updateUserTel(id, telephone) == 1 ? "success" : "failure";
		map.put("success", result);
		return map;
	}
	
	@RequestMapping("user/changPsd.do")
	@ResponseBody
	public Map<String, Object> changPsd(String id, String psd, String newpsd) throws Exception
	{
		Map<String, Object> map = new HashMap<String, Object>(3);
		String result = us.updateUserPsd(id, psd, newpsd) == 1 ? "success" : "failure";
		map.put("success", result);
		return map;
	}
}
