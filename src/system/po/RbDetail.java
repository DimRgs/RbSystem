package system.po;

import java.sql.Date;
import java.util.List;

public class RbDetail {
	private String id;
	private User user;
	private int state;
	private String hospital;
	private Date date;
	private Referral referral;
	private List<Ghf> ghf;
	private List<Yymx> yymx;
	
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getHospital() {
		return hospital;
	}
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Referral getReferral() {
		return referral;
	}
	public void setReferral(Referral referral) {
		this.referral = referral;
	}

}
