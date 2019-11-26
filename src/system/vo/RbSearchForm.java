package system.vo;

import java.sql.Date;

public class RbSearchForm {
	private int user_name;
	private int user_id;
	private int rb_state;
	private int admin_id;
	private int[] user_type; 
	private int isWssm;
	private Date start_date;
	private Date end_date;
	private int curPage;
	

	public int getUser_name() {
		return user_name;
	}
	public void setUser_name(int user_name) {
		this.user_name = user_name;
	}

	public int getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(int admin_id) {
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
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getIsWssm() {
		return isWssm;
	}
	public void setIsWssm(int isWssm) {
		this.isWssm = isWssm;
	}
	public int getRb_state() {
		return rb_state;
	}
	public void setRb_state(int rb_state) {
		this.rb_state = rb_state;
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
	
	
}
