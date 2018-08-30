package njurestaurant.njutakeout.response.user;

import njurestaurant.njutakeout.entity.user.User;

import java.util.List;

public class CardItem {
	private String openid; //用户id
	private String username; //用户名
	private String face; //用户头像
	private List<String> medals; //用户类别提示（可多个）
	private String phone; //电话号码
	private String email; //电子邮件
	private String company; //公司名称
	private String department; //部门
	private String position; //职位
	private String intro; //个人简介

	public CardItem() {
	}

	public CardItem(User user) {
		this.openid = user.getOpenid();
		this.username = user.getUsername();
		this.face = user.getFace();
		this.medals = user.getMedals();
		this.phone = user.getPhone();
		this.email = user.getEmail();
		this.company = user.getCompany();
		this.department = user.getDepartment();
		this.position = user.getPosition();
		this.intro = user.getIntro();
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public List<String> getMedals() {
		return medals;
	}

	public void setMedals(List<String> medals) {
		this.medals = medals;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}
}
