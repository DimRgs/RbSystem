package system.vo;

import java.util.List;

import system.po.*;

public class RbAdminPostVO {
	private int rb_id;
	private List<Ghf> ghf;
	private List<Yymx> yymx;
	public int getRb_id() {
		return rb_id;
	}
	public void setRb_id(int rb_id) {
		this.rb_id = rb_id;
	}
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
	
	
}
