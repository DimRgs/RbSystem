package system.util;

import java.util.Map;

public class SystemUtil {

	public static void setS(Map<String, Object> map)
	{
		map.put("success", "success");
	}
	
	public static void setF(Map<String, Object> map)
	{
		map.put("success", "failure");
	}
}
