package system.control;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import system.vo.RbSearchForm;

@RequestMapping("/admin")
@Controller
public class SysAdminHandler extends RootHandler {
	@RequestMapping("/demo.do")
	@ResponseBody
	public Map<String, Object> demo(RbSearchForm rbsf) throws Exception
	{
		Map<String, Object> map = new HashMap<String, Object>(3);
		Map<String, Object> dataMap = new HashMap<String, Object>(3);
		
		map.put("Data", dataMap);
		return map;
	}
}
