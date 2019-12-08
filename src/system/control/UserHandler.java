package system.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import system.po.Ghf;
import system.po.RbDetail;
import system.po.User;
import system.po.UserInfo;
import system.po.Yymx;
import system.vo.RbSearchForm;

import static system.util.SystemUtil.*;		//导入静态方法
import static system.util.Des.*;

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
	
	@RequestMapping("/getQR.do")
	@ResponseBody
	public Map<String, Object> getQR(Integer rb_id) throws Exception
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
			
			RbDetail rb = us.getRbById(rb_id);
			if(rb.getRb_state() < 6 || rb.getRb_state() > 7)
			{
				setF(map);
			}
			else 
			{
				setS(map);
				UserInfo ui = us.getUserInfoById(user.getId());
				dataMap.put("rb_id", rb_id);
				dataMap.put("QRCode", encrypt(rb_id.toString()));
				dataMap.put("u_id", ui.getUser().getId());
				dataMap.put("u_name", ui.getUser().getName());
				dataMap.put("u_type", ui.getUser().getType());
				dataMap.put("u_department", ui.getDepartment().getName());
				dataMap.put("c_time", rb.getS_time());
				dataMap.put("hospital", rb.getHospital());
				dataMap.put("total_cost", rb.getTotal_cost());
				dataMap.put("total_self_paid", rb.getTotal_self_paid());

				Map<String, Object> ghf = new HashMap<String, Object>(3);
				Map<String, Object> yymx = new HashMap<String, Object>(3);
				int cost = 0;
				int self_paid = 0;
				
				for(Ghf g : rb.getGhf())
				{
					cost += g.getCost();
					self_paid += g.getSelf_paid();
				}
				ghf.put("total_cost", cost);
				ghf.put("self_paid", self_paid);
				ghf.put("self_pro", (float)self_paid/cost);
				ghf.put("count", rb.getGhf().size());
				dataMap.put("ghf", ghf);
				
				cost = 0;
				self_paid = 0;
				
				for(Yymx y : rb.getYymx())
				{
					cost += y.getCost();
					self_paid += y.getSelf_paid();
				}
				yymx.put("total_cost", cost);
				yymx.put("self_paid", self_paid);
				yymx.put("self_pro", (float)self_paid/cost);
				yymx.put("count", rb.getYymx().size());
				dataMap.put("yymx", yymx);
				map.put("Data", dataMap);
			}
		}
		
		return map;
	}
}
