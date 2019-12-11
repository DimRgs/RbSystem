package system.po;

import java.util.Date;

public class RbInfo {
	private int rb_id;
	private int rb_state;
	private String user_type;
	private String user_name;
	private String admin_id;
	private Admin admin;
	private String hospital;
	private Date s_time;
	private int isUndo;
	
	public int getRb_id() {
		return rb_id;
	}
	public void setRb_id(int rb_id) {
		this.rb_id = rb_id;
	}
	public int getRb_state() {
		return rb_state;
	}
	public void setRb_state(int rb_state) {
		this.rb_state = rb_state;
	}
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
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
	public String getHospital() {
		return hospital;
	}
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
	public Date getS_time() {
		return s_time;
	}
	public void setS_time(Date s_time) {
		this.s_time = s_time;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public int getIsUndo() {
		return isUndo;
	}
	public void setIsUndo(int isUndo) {
		this.isUndo = isUndo;
	}
	
	
}
