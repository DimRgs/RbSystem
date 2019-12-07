package system.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import system.po.Admin;
import system.po.RbDetail;
import system.vo.RbSearchForm;

import static system.util.SystemUtil.*;

@RequestMapping("/admin")
@Controller
public class AdminHandler extends RootHandler {
	
	@RequestMapping("/getRbList1.do")
	@ResponseBody
	public Map<String, Object> getRbList1(RbSearchForm rbsf) throws Exception
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
	
	@RequestMapping("/getRbList2.do")
	@ResponseBody
	public Map<String, Object> getRbList2(RbSearchForm rbsf) throws Exception
	{
		//查看已审核报销单申请表列表
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
}
