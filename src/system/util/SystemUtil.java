package system.util;

import java.util.Map;


public class SystemUtil {
	
	public static final int EVERY_PAGE_ITEMS = 5;
	/**
	 * 根据参数（页码），计算其在数据库中查询的起始位置
	 * @param pagenum
	 * @return
	 */
	public static int getSQLIndexByPageNum(int pagenum){
		return (pagenum - 1) * EVERY_PAGE_ITEMS;
	}
	
	/**
	 * 根据参数（记录总数）,计算最大页码的值
	 * @param total
	 * @return
	 */
	public static int getMaxPageNum(int total){
		if(total <= 0)
			return 1;
		if(total % EVERY_PAGE_ITEMS == 0 ){
			return total / EVERY_PAGE_ITEMS;
		}else{
			return total / EVERY_PAGE_ITEMS + 1;
		}
	}
	
	public static void setS(Map<String, Object> map)
	{
		map.put("success", "success");
	}
	
	public static void setF(Map<String, Object> map)
	{
		map.put("success", "failure");
	}

	public static int stateToOperation(int state)
	{
		switch(state)
		{
		case 3:
			return 1;
		case 4:
			return 2;
		case 5:
			return 3;
		case 7:
			return 4;
		case 9:
			return 6;
		case 10:
			return 5;
		case 11:
			return 7;
		case 12:
			return 8;
		case 13:
			return 9;
		case 14:
			return 10;
		default:
			return 0;
		}
	}
	
}
