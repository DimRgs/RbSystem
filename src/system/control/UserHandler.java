package system.control;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import system.po.RbDetail;
import system.po.User;
import system.vo.RbSearchForm;

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
	public Map<String, Object> getRbForm(Integer rb_id) throws Exception
	{
		Map<String, Object> map = new HashMap<String, Object>(3);
		Map<String, Object> dataMap = null;
		User user = (User)request.getSession().getAttribute("User");
		if(user == null)
		{
			setF(map);
			return map;
		}
		else if(rb_id == null || rb_id == 0)
		{
			setS(map);
			RbDetail rb = us.insertNewRb(user.getId());
			dataMap = rb.getHashMap();
		}
		else
		{
			
			RbDetail rb = us.getRbById(rb_id);
			if(rb == null)
			{
				setF(map);
			}
			else 
			{
				dataMap = rb.getHashMap();
			}
			setS(map);
		}
		//先判断当前rb_id的状态，如果是已完成则创建个新的rb
		
		map.put("Data", dataMap);
		return map;
	}
	
	@RequestMapping("/postRbForm.do")
	@ResponseBody
	public Map<String, Object> postRbForm(RbDetail rb, Integer active) throws Exception
	{
		Map<String, Object> map = new HashMap<String, Object>(3);
		Map<String, Object> dataMap = new HashMap<String, Object>(3);
		
		User user = (User)request.getSession().getAttribute("User");
		if(user == null)
		{
			setF(map);
			return map;
		}
		else
		{
			setS(map);
			rb.setRb_state(active);
			us.updateRbDetail(rb);
			dataMap.put("rb_state", rb.getRb_state());
			dataMap.put("rb_id", rb.getRb_id());
		}
		
		map.put("Data", dataMap);
		return map;
	}
	
	@RequestMapping("/getMyRbRecord.do")
	@ResponseBody
	public Map<String, Object> getMyRbRecord(RbSearchForm rbsf) throws Exception
	{
		Map<String, Object> map = new HashMap<String, Object>(3);
		Map<String, Object> dataMap = new HashMap<String, Object>(3);
		
		map.put("Data", dataMap);
		return map;
	}
}
