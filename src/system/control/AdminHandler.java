package system.control;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import system.po.Admin;
import system.vo.RbSearchForm;

import static system.util.SystemUtil.*;

@RequestMapping("/admin")
@Controller
public class AdminHandler extends RootHandler {
	
	@RequestMapping("/getRbList1.do")
	@ResponseBody
	public Map<String, Object> homepage(RbSearchForm rbsf) throws Exception
	{
		//查看待审核报销单申请表列表
		Map<String, Object> map = new HashMap<String, Object>(3);
		Map<String, Object> dataMap = new HashMap<String, Object>(3);
		
		Admin admin = (Admin)request.getSession().getAttribute("Admin");
		
		if(admin == null)
		{
			setF(map);
		}
		else 
		{
			rbsf.setRb_state(2);
			
		}
		
		map.put("Data", dataMap);
		return map;
	}
	
	
}
