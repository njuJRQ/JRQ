package njurestaurant.njutakeout.response.admin;

import njurestaurant.njutakeout.entity.admin.Admin;

public class AdminItem {
	private String id;//编号
	private String username;//用户名
	private String password; //密码
	private String limits; //权限
	private String date; //创建时间

	public AdminItem(Admin admin) {
		this.id = admin.getId();
		this.username = admin.getUsername();
		this.password = admin.getPassword();
		this.limits = admin.getLimits();
		this.date = admin.getDate();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLimits() {
		return limits;
	}

	public void setLimits(String limits) {
		this.limits = limits;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}