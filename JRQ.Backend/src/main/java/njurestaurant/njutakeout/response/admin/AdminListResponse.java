package njurestaurant.njutakeout.response.admin;

import njurestaurant.njutakeout.response.Response;

import java.util.List;

public class AdminListResponse extends Response {
	private List<AdminItem> admins;

	public AdminListResponse() {
	}

	public AdminListResponse(List<AdminItem> admins) {
		this.admins = admins;
	}

	public List<AdminItem> getAdmins() {
		return admins;
	}

	public void setAdmins(List<AdminItem> admins) {
		this.admins = admins;
	}
}
