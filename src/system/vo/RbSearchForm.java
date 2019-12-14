package system.vo;

import java.lang.reflect.Field;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import system.po.Hashable;

public class RbSearchForm implements Hashable {
	private String user_name;	
	private String user_id;
	private int[] rb_state;
	private String admin_id;
	private int[] user_type; 
	private int isWssm;
	private Date start_date;
	private Date end_date;
	private int curPage;
	private int start;
	private int length;
	
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getIsWssm() {
		return isWssm;
	}
	public void setIsWssm(int isWssm) {
		this.isWssm = isWssm;
	}

	public int[] getUser_type() {
		return user_type;
	}
	public void setUser_type(int[] user_type) {
		this.user_type = user_type;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int[] getRb_state() {
		return rb_state;
	}
	public void setRb_state(int[] rb_state) {
		this.rb_state = rb_state;
	}
	@Override
	public Map<String, Object> getHashMap() {
		// TODO 自动生成的方法存根
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Field[] declaredFields = this.getClass().getDeclaredFields();
			for (Field field : declaredFields) {
				field.setAccessible(true);
				map.put(field.getName(), field.get(this));
			}
		} catch (Exception e) {
			
		}
		return map;
	}
	
}
