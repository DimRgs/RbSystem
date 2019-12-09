package system.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import system.po.Admin;
import system.po.RbDetail;
import system.po.Undo;
import system.po.User;
import system.vo.*;

import static system.util.SystemUtil.*;
import system.util.Des;

@RequestMapping("/admin")
@Controller
public class AdminHandler extends RootHandler {
	
	@RequestMapping("/demo.do")
	@ResponseBody
	public Map<String, Object> demo(RbSearchForm rbsf) throws Exception
	{
		Map<String, Object> map = new HashMap<String, Object>(3);
		Map<String, Object> dataMap = new HashMap<String, Object>(3);
		
		map.put("Data", dataMap);
		return map;
	}
	
	@RequestMapping("/agreeUndo.do")
	@ResponseBody
	public Map<String, Object> agreeUndo(int rb_id, String note3) throws Exception
	{
		Map<String, Object> map = new HashMap<String, Object>(3);
		Map<String, Object> dataMap = new HashMap<String, Object>(3);
		
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		
		if(admin == null)
		{
			setF(map);
		}
		else 
		{
			setS(map);
			us.updateRbState(rb_id, 11, admin.getId());
			Undo undo = new Undo();
			undo.setRb_id(rb_id);
			undo.setNote3(note3);
			us.updateUndo(undo);
		}
		
		map.put("Data", dataMap);
		return map;
	}
	
	@RequestMapping("/argueUndo.do")
	@ResponseBody
	public Map<String, Object> argueUndo(int rb_id, String note3) throws Exception
	{
		Map<String, Object> map = new HashMap<String, Object>(3);
		Map<String, Object> dataMap = new HashMap<String, Object>(3);
		
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		
		if(admin == null)
		{
			setF(map);
		}
		else 
		{
			setS(map);
			us.updateRbState(rb_id, 12, admin.getId());
			Undo undo = new Undo();
			undo.setRb_id(rb_id);
			undo.setNote3(note3);
			us.updateUndo(undo);
		}
		
		map.put("Data", dataMap);
		return map;
	}
	
	@RequestMapping("/rejectedComplaint.do")
	@ResponseBody
	public Map<String, Object> rejectedComplaint(int rb_id, String note2) throws Exception
	{
		Map<String, Object> map = new HashMap<String, Object>(3);
		Map<String, Object> dataMap = new HashMap<String, Object>(3);
		
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		
		if(admin == null)
		{
			setF(map);
		}
		else 
		{
			setS(map);
			us.updateRbState(rb_id, 9, admin.getId());
			Undo undo = new Undo();
			undo.setRb_id(rb_id);
			undo.setNote2(note2);
			us.updateUndo(undo);
		}
		
		map.put("Data", dataMap);
		return map;
	}
	
	@RequestMapping("/requestCancel.do")
	@ResponseBody
	public Map<String, Object> requestCancel(int rb_id, String note2) throws Exception
	{
		Map<String, Object> map = new HashMap<String, Object>(3);
		Map<String, Object> dataMap = new HashMap<String, Object>(3);
		
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		
		if(admin == null)
		{
			setF(map);
		}
		else 
		{
			setS(map);
			us.updateRbState(rb_id, 10, admin.getId());
			Undo undo = new Undo();
			undo.setRb_id(rb_id);
			undo.setNote2(note2);
			us.updateUndo(undo);
		}
		
		map.put("Data", dataMap);
		return map;
	}
	
	@RequestMapping("/updateAdmin.do")
	@ResponseBody
	public Map<String, Object> updateAdmin(Admin a) throws Exception
	{
		Map<String, Object> map = new HashMap<String, Object>(3);
		Map<String, Object> dataMap = new HashMap<String, Object>(3);
		
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		
		if(admin == null || (admin.getLevel() != 3 && admin.getLevel() != 5))
		{
			setF(map);
		}
		else 
		{
			setS(map);
		}
		
		map.put("Data", dataMap);
		return map;
	}
	
	@RequestMapping("/getEList.do")
	@ResponseBody
	public Map<String, Object> getEList(RbSearchForm rbsf) throws Exception
	{
		Map<String, Object> map = new HashMap<String, Object>(3);
		Map<String, Object> dataMap = new HashMap<String, Object>(3);
		
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		
		if(admin == null || (admin.getLevel() != 3 && admin.getLevel() != 5))
		{
			setF(map);
		}
		else 
		{
			dataMap.put("EList", us.getAdminList());
			setS(map);
		}
		
		map.put("Data", dataMap);
		return map;
	}
	
	@RequestMapping("/getRbList1.do")
	@ResponseBody
	public Map<String, Object> getRbList1(RbSearchForm rbsf) throws Exception
	{
		//查看待审核报销单申请表列表
		Map<String, Object> map = new HashMap<String, Object>(3);
		Map<String, Object> dataMap = new HashMap<String, Object>(3);
		
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		if(admin == null)
		{
			setF(map);
		}
		else 
		{
			rbsf.setRb_state(new int[]{2, 3});
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
	
	@RequestMapping("/startRbCheck.do")
	@ResponseBody
	public Map<String, Object> startRbCheck(Integer rb_id) throws Exception
	{
		//如果当前审核状态为审核中， 且审核人员不为当前管理人员，返回failure
		//如果审核中且身份相同，可以审核
		Map<String, Object> map = new HashMap<String, Object>(3);
		Map<String, Object> dataMap = null;
		
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		if(admin == null || admin.getLevel() != 2 || rb_id == null || rb_id < 1)
		{
			setF(map);
			dataMap = new HashMap<String, Object>(3);
			dataMap.put("reason", "Admin is null.");
		}
		else 
		{
			RbDetail rb = us.getRbById(rb_id);
			if(rb.getRb_state() == 3 && !rb.getAdmin().getId().equals(admin.getId()))
			{
				setF(map);
				dataMap = new HashMap<String, Object>(3);
				dataMap.put("reason", "Other is checking.");
			}
			else 
			{
				setS(map);
				us.updateRbToCheck(rb, admin);
				dataMap = rb.getHashMap();
			}
		}
		
		map.put("Data", dataMap);
		return map;
	}
	
	@RequestMapping("/getRbList2.do")
	@ResponseBody
	public Map<String, Object> getRbList2(RbSearchForm rbsf) throws Exception
	{
		//查看已审核报销单申请表列表
		Map<String, Object> map = new HashMap<String, Object>(3);
		Map<String, Object> dataMap = new HashMap<String, Object>(3);

		Admin admin = (Admin)request.getSession().getAttribute("admin");

		if(admin == null)
		{
			setF(map);
		}
		else 
		{
			if(rbsf.getRb_state() == null)
			{
				rbsf.setRb_state(new int[]{4, 5, 6, 7});
			}
			rbsf.setAdmin_id(admin.getId());
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
	
	@RequestMapping("/postRbCheck.do")
	@ResponseBody
	public Map<String, Object> postRbCheck(String rbStr) throws Exception
	{
		Map<String, Object> map = new HashMap<String, Object>(3);
		
		Admin admin = (Admin)request.getSession().getAttribute("admin");

		if(admin == null || admin.getLevel() != 2)
		{
			setF(map);
		}
		else 
		{
			RbAdminPostVO rbap = JSON.parseObject(rbStr, RbAdminPostVO.class);
			rbap.setAdmin_id(admin.getId());
			if(us.updateRbToPost(rbap) != 1)
			{
				setF(map);
			}
			else 
			{
				setS(map);
			}
		}
		return map;
	}
	
	@RequestMapping("/recieve.do")
	@ResponseBody
	public Map<String, Object> recieve(Integer rb_id) throws Exception
	{
		//收单员收单成功
		Map<String, Object> map = new HashMap<String, Object>(3);
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		
		if(admin == null || admin.getLevel() != 4)
		{
			setF(map);
		}
		else if(us.updateRbState(rb_id, 7, admin.getId()) == 1)
		{
			setS(map);
		}
		else 
		{
			setF(map);
		}
		return map;
	}
	
	@RequestMapping("/getRecieve.do")
	@ResponseBody
	public Map<String, Object> getRbDetail(String QRCode) throws Exception
	{
		Map<String, Object> map = new HashMap<String, Object>(3);
		Map<String, Object> dataMap = null;
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		Integer ida = Integer.valueOf(Des.decrypt(QRCode));
		if(admin == null)
		{
			setF(map);
			return map;
		}
		else if(ida == null)
		{
			setF(map);
			return map;
		}
		else
		{
			RbDetail rb = us.getRbById(ida.intValue());
			if(rb == null)
			{
				setF(map);
			}
			else 
			{
				dataMap = rb.getHashMap();
				setS(map);
			}
		}
		//先判断当前rb_id的状态，如果是已完成则创建个新的rb
		
		map.put("Data", dataMap);
		return map;
	}
}
