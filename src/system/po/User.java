package system.po;

import java.util.Date;

public class User {
	private String name;
	private String password;
	private String id;
	private String idCard;
	private String telephone;
	private Date register_date;
	private String type;
	private int gender;
	
	public User()
	{
		
	}
	
	public User(String name, String password, String id, String idCard, String telephone, Date register_date,
			String type, int gender) {
		super();
		this.name = name;
		this.password = password;
		this.id = id;
		this.idCard = idCard;
		this.telephone = telephone;
		this.register_date = register_date;
		this.type = type;
		this.gender = gender;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTelphone() {
		return telephone;
	}
	public void setTelphone(String telphone) {
		this.telephone = telphone;
	}
	public Date getRegister_date() {
		return register_date;
	}
	public void setRegister_date(Date register_date) {
		this.register_date = register_date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
