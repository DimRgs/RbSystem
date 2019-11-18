package system.control;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import system.po.User;
import static system.util.SystemUtil.*;		//导入静态方法

@RequestMapping("/user")
@Controller
public class UserHandler extends RootHandler {

	@RequestMapping("/changTel.do")
	@ResponseBody
	public Map<String, Object> changTel(String id, String telephone) throws Exception
	{
		Map<String, Object> map = new HashMap<String, Object>(3);
		User user = (User)request.getSession().getAttribute("User");
		if(us.updateUserTel(user.getId(), telephone) == 1)
		{
			setS(map);
		}
		else 
		{
			setF(map);
		}
		return map;
	}
	
	@RequestMapping("/changPsd.do")
	@ResponseBody
	public Map<String, Object> changPsd(String id, String psd, String newpsd) throws Exception
	{
		Map<String, Object> map = new HashMap<String, Object>(3);
		User user = (User)request.getSession().getAttribute("User");
		if(us.updateUserPsd(user.getId(), psd, newpsd) == 1)
		{
			setS(map);
		}
		else 
		{
			setF(map);
		}
		return map;
	}
}
