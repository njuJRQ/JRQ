package njurestaurant.njutakeout.response.admin;

import njurestaurant.njutakeout.response.Response;

public class AdminResponse extends Response {
	private AdminItem admin;

	public AdminResponse() {
	}

	public AdminResponse(AdminItem admin) {
		this.admin = admin;
	}

	public AdminItem getAdmin() {
		return admin;
	}

	public void setAdmin(AdminItem admin) {
		this.admin = admin;
	}
}
