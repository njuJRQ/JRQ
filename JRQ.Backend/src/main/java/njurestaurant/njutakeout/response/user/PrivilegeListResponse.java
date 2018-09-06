package njurestaurant.njutakeout.response.user;

import njurestaurant.njutakeout.response.Response;

import java.util.List;

public class PrivilegeListResponse extends Response {
	private List<PrivilegeItem> privileges;

	public PrivilegeListResponse(List<PrivilegeItem> privileges) {
		this.privileges = privileges;
	}

	public List<PrivilegeItem> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<PrivilegeItem> privileges) {
		this.privileges = privileges;
	}
}
