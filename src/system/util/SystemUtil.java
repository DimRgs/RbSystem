package system.util;

import java.util.Map;


public class SystemUtil {
	
	public static final int EVERY_PAGE_ITEMS = 5;
	/**
	 * ���ݲ�����ҳ�룩�������������ݿ��в�ѯ����ʼλ��
	 * @param pagenum
	 * @return
	 */
	public static int getSQLIndexByPageNum(int pagenum){
		return (pagenum - 1) * EVERY_PAGE_ITEMS;
	}
	
	/**
	 * ���ݲ�������¼������,�������ҳ���ֵ
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

	
	
}
