package njurestaurant.njutakeout.response.user;

import njurestaurant.njutakeout.response.Response;

public class PrivilegeResponse extends Response {
	private PrivilegeItem privilege;

	public PrivilegeResponse(PrivilegeItem privilege) {
		this.privilege = privilege;
	}

	public PrivilegeItem getPrivilege() {
		return privilege;
	}

	public void setPrivilege(PrivilegeItem privilege) {
		this.privilege = privilege;
	}
}
