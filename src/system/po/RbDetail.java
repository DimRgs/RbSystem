package system.po;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RbDetail implements Hashable {
	private int rb_id;
	private int rb_state;
	private String hospital;
	private Date s_time;
	private Admin admin;
	private User user;
	private Referral referral;
	private List<Ghf> ghf;
	private List<Yymx> yymx;
	private Wssm wssm;
	private int active;
	
	public List<Ghf> getGhf() {
		return ghf;
	}
	public void setGhf(List<Ghf> ghf) {
		this.ghf = ghf;
	}
	public List<Yymx> getYymx() {
		return yymx;
	}
	public void setYymx(List<Yymx> yymx) {
		this.yymx = yymx;
	}
	
	public Date getS_time() {
		return s_time;
	}
	public void setS_time(Date s_time) {
		this.s_time = s_time;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getHospital() {
		return hospital;
	}
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public Referral getReferral() {
		return referral;
	}
	public void setReferral(Referral referral) {
		this.referral = referral;
	}
	public Wssm getWssm() {
		return wssm;
	}
	public void setWssm(Wssm wssm) {
		this.wssm = wssm;
	}
	
	@Override
	public Map<String, Object> getHashMap() {
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
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	
}
