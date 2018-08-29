package njurestaurant.njutakeout.response.user;

public class PersonItem {
	private String username; //用户名
	private String face; //用户头像
	private String company; //公司名称
	private String position; //职位
	private String intro; //个人简介
	private String city; //所在城市
	private String label; //用户类别信息，可取值：融资租赁，商业保理，地产交易，金融牌照

	public PersonItem() {
	}

	public PersonItem(String username, String face, String company, String position, String intro, String city, String label) {
		this.username = username;
		this.face = face;
		this.company = company;
		this.position = position;
		this.intro = intro;
		this.city = city;
		this.label = label;
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
