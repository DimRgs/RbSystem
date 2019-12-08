package system.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

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
	public Map<String, Object> getRbForm(Integer rb_id, Integer rb_state) throws Exception
	{
		Map<String, Object> map = new HashMap<String, Object>(3);
		Map<String, Object> dataMap = null;
		User user = (User)request.getSession().getAttribute("User");
		if(user == null)
		{
			setF(map);
			return map;
		}
		else if(rb_id == null || rb_state == null)
		{
			setF(map);
		}
		else if(rb_id == -1 || rb_state == 7)	//如果当前没有报销单申请表记录，新建一个
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
		
		map.put("Data", dataMap);
		return map;
	}
	
	@RequestMapping("/postRbForm.do")
	@ResponseBody
	public Map<String, Object> postRbForm(String rbStr) throws Exception
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
			
			RbDetail rb = JSON.parseObject(rbStr, RbDetail.class);
			if(rb == null)
			{
				setF(map);
				
			}
			else if(rb.getActive() > 0)
			{
				rb.setRb_state(rb.getActive());
				try
				{
					if(us.updateRbDetail(rb) != 1)
					{
						setF(map);
					}
					else 
					{
						setS(map);
						dataMap.put("rb_state", rb.getRb_state());
						dataMap.put("rb_id", rb.getRb_id());
					}
				}
				catch(Exception e)
				{
					System.out.println(e);
					setF(map);
				}
			}
			else 
			{
				setF(map);
			}
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
		User user = (User)request.getSession().getAttribute("User");
		
		if(user == null)
		{
			setF(map);
		}
		else 
		{
			rbsf.setUser_id(user.getId());
			int total = us.getRbCount(rbsf);
			int maxPage = getMaxPageNum(total);
			int curPage = rbsf.getCurPage();
			curPage = curPage < 1 ? 1 : curPage;
			curPage = curPage > maxPage ? maxPage : curPage;
			
			rbsf.setCurPage(curPage);
			rbsf.setStart(getSQLIndexByPageNum(curPage));
			rbsf.setLength(EVERY_PAGE_ITEMS);
			
			List<RbDetail> rblist = us.getRbList(rbsf);
			setS(map);
			dataMap.put("RbList", rblist);
			dataMap.put("totalPage", maxPage);
			dataMap.put("totalNum", total);
		}
		
		map.put("Data", dataMap);
		return map;
	}
	
	@RequestMapping("/confirmRbForm.do")
	@ResponseBody
	public Map<String, Object> confirmRbForm(Integer rb_id, Integer active) throws Exception
	{
		Map<String, Object> map = new HashMap<String, Object>(3);
		Map<String, Object> dataMap = new HashMap<String, Object>(3);
		User user = (User)request.getSession().getAttribute("User");
		
		if(user == null)
		{
			setF(map);
		}
		else 
		{
			int state = active == 1 ? 6 : 1;
			if(us.updateRbState(rb_id, state, null) == 1)
			{
				setS(map);
				dataMap.put("rb_state", state);
				map.put("Data", dataMap);
			}
			else 
			{
				setF(map);
			}
		}

		return map;
	}
}
