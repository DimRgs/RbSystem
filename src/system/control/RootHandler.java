package system.control;

import static system.util.SystemUtil.setF;
import static system.util.SystemUtil.setS;
import static system.util.FileUploadUtil.*;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import system.po.Admin;
import system.po.RbDetail;
import system.po.User;
import system.po.UserInfo;
import system.service.UserService;

@Controller
public class RootHandler {
	
	@Autowired
	protected UserService us;
	@Autowired
	protected HttpServletRequest request;
	
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
	
	@RequestMapping("/upload.do")
	@ResponseBody
	public Map<String, Object> upload(MultipartFile file) throws Exception
	{
		Map<String, Object> map = new HashMap<String, Object>(3);
		Map<String, Object> dataMap = new HashMap<String, Object>(3);
		String path;
		if(file == null)
		{
			setF(map);
		}
		else 
		{
			setS(map);
			if((path = uploadFiles(file, request.getSession().getServletContext().getRealPath(pic_root_path))) != null)
			{
				setS(map);
				dataMap.put("path", path);
				map.put("Data", dataMap);
			}
			else 
			{
				setF(map);
			}
		}
		return map;
	}
	
	@RequestMapping("/homepage.do")
	@ResponseBody
	public Map<String, Object> homepage() throws Exception
	{
		Map<String, Object> map = new HashMap<String, Object>(3);
		Map<String, Object> dataMap = new HashMap<String, Object>(3);
		User user = (User)request.getSession().getAttribute("User");
		if(user == null)
		{
			setF(map);
			//需要判断是否是管理人员
		}
		else 
		{
			setS(map);

			//如果是报销人员，先查询最近报销单申请表状态，如果不是1或6，直接返回id和状态
			RbDetail rb = us.getLastRb(user.getId());
			if(rb == null)
			{
				dataMap.put("rb_state", null) ;
				dataMap.put("rb_id", null);
			}
			else 
			{
				dataMap.put("rb_state", rb.getRb_state()) ;
				dataMap.put("rb_id", rb.getRb_id());
			}
			map.put("Data", dataMap);
		}
		return map;
	}
	
	@RequestMapping("/login.do")
	@ResponseBody
	public Map<String, Object> login(String id, String password) throws Exception 
	{
		Map<String, Object> map = new HashMap<String, Object>(3);
		Map<String, Object> dataMap = new HashMap<String, Object>(3);
		UserInfo userInfo = us.getUserInfo(id, password);
		if(userInfo == null)
		{
			//还需要判断是否是管理人员
			Admin admin = us.getAdminInfo(id, password);
			if(admin == null)
			{
				setF(map);
			}
			else 
			{
				setS(map);
				dataMap.put("level", admin.getLevel());
				dataMap.put("Admin", admin);
				request.getSession().setAttribute("admin", admin);
			}
		}
		else if(userInfo.getUser().getPassword().equalsIgnoreCase(password))
		{
			setS(map);
			dataMap.put("level", 1);
			dataMap.put("User", userInfo.getUser());
			dataMap.put("Department", userInfo.getDepartment());
			map.put("Data", dataMap);
			request.getSession().setAttribute("User", userInfo.getUser());
		}
		return map;
	}
	
	@RequestMapping("/getRbDetail.do")
	@ResponseBody
	public Map<String, Object> getRbDetail(Integer rb_id) throws Exception
	{
		Map<String, Object> map = new HashMap<String, Object>(3);
		Map<String, Object> dataMap = null;
		User user = (User)request.getSession().getAttribute("User");
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		if(user == null && admin == null)
		{
			setF(map);
			return map;
		}
		else if(rb_id == null || rb_id == 0)
		{
			setF(map);
			return map;
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
}
