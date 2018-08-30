package njurestaurant.njutakeout.response.user;

import njurestaurant.njutakeout.entity.user.User;

public class PersonItem {
	private String openid; //用户微信openid
	private String username; //用户名
	private String face; //用户头像
	private String company; //公司名称
	private String position; //职位
	private String intro; //个人简介
	private String city; //所在城市
	private String label; //用户类别信息，可取值：融资租赁，商业保理，地产交易，金融牌照

	public PersonItem() {
	}

	public PersonItem(User user) {
		this.openid = user.getOpenid();
		this.username = user.getUsername();
		this.face = user.getFace();
		this.company = user.getCompany();
		this.position = user.getPosition();
		this.intro = user.getIntro();
		this.city = user.getCity();
		this.label = user.getLabel();
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

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
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

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
