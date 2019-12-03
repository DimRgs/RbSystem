package system.po;

import java.util.Date;

public class Yymx {
	private int id;
	private int rb_id;
	private Date date;
	private int cost;
	private int special_paid;
	private int part_paid;
	private int self_paid;
	private String note;
	private String detailed_pic;
	private String pspt_pic;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getSpecial_paid() {
		return special_paid;
	}
	public void setSpecial_paid(int special_paid) {
		this.special_paid = special_paid;
	}
	public int getPart_paid() {
		return part_paid;
	}
	public void setPart_paid(int part_paid) {
		this.part_paid = part_paid;
	}
	public int getSelf_paid() {
		return self_paid;
	}
	public void setSelf_paid(int self_paid) {
		this.self_paid = self_paid;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getDetailed_pic() {
		return detailed_pic;
	}
	public void setDetailed_pic(String detailed_pic) {
		this.detailed_pic = detailed_pic;
	}
	public String getPspt_pic() {
		return pspt_pic;
	}
	public void setPspt_pic(String pspt_pic) {
		this.pspt_pic = pspt_pic;
	}
	public int getRb_id() {
		return rb_id;
	}
	public void setRb_id(int rb_id) {
		this.rb_id = rb_id;
	}
}
