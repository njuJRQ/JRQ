package njurestaurant.njutakeout.response.user;

import njurestaurant.njutakeout.response.Response;

public class EnterpriseResponse extends Response {
	private EnterpriseItem enterprise;

	public EnterpriseResponse(EnterpriseItem enterprise) {
		this.enterprise = enterprise;
	}

	public EnterpriseItem getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(EnterpriseItem enterprise) {
		this.enterprise = enterprise;
	}
}
