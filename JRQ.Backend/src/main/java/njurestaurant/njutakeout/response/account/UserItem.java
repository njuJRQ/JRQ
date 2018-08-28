package njurestaurant.njutakeout.response.account;

import njurestaurant.njutakeout.entity.account.User;

import java.util.List;

public class UserItem {
	private String openid;//用户微信编号
	private String username; //用户名
	private String face; //用户头像
	private List<String> medals; //用户类别提示（可多个）
	private String phone; //电话号码
	private String email; //电子邮件
	private String company; //公司名称
	private String department; //部门
	private String position; //职位
	private String intro; //个人简介
	private String city; //所在城市
	private int credit; //账户余额
	private String label; //用户类别信息，可取值：融资租赁，商业保理，地产交易，金融牌照
	private boolean valid;//是否冻结/启用，true代表启用

	public UserItem(User user){
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
		this.city = user.getCity();
		this.credit = user.getCredit();
		this.label = user.getLabel();
		this.valid = user.isValid();
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
}
