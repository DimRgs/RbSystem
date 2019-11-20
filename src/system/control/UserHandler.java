package system.control;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import system.po.RbDetail;
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
		if(user != null && us.updateUserTel(user.getId(), telephone) == 1)
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
		if(user != null && us.updateUserPsd(user.getId(), psd, newpsd) == 1)
		{
			setS(map);
		}
		else 
		{
			setF(map);
		}
		return map;
	}
	
	@RequestMapping("/subRbForm.do")
	@ResponseBody
	public Map<String, Object> subRbForm() throws Exception
	{
		Map<String, Object> map = new HashMap<String, Object>(3);
		Map<String, Object> dataMap = new HashMap<String, Object>(3);
		User user = (User)request.getSession().getAttribute("User");
		if(user == null)
		{
			setF(map);
		}
		
		map.put("Data", dataMap);
		return map;
	}
	
	@RequestMapping("/getRbForm.do")
	@ResponseBody
	public Map<String, Object> getRbForm(String rb_id, Integer rb_state) throws Exception
	{
		Map<String, Object> map = new HashMap<String, Object>(3);
		Map<String, Object> dataMap = null;
		User user = (User)request.getSession().getAttribute("User");
		if(user == null)
		{
			setF(map);
			return map;
		}
		else 
		{
			setS(map);
			RbDetail rb = us.getRbById(rb_id);
			dataMap = rb.getHashMap();
		}
		//先判断当前rb_id的状态，如果是已完成则创建个新的rb
		
		map.put("Data", dataMap);
		return map;
	}
}
