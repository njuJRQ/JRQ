package njurestaurant.njutakeout.response.user;

import njurestaurant.njutakeout.response.Response;

import java.util.List;

public class EnterpriseListResponse extends Response{
	private List<EnterpriseItem> enterprises;

	public EnterpriseListResponse(List<EnterpriseItem> enterprises) {
		this.enterprises = enterprises;
	}

	public List<EnterpriseItem> getEnterprises() {
		return enterprises;
	}

	public void setEnterprises(List<EnterpriseItem> enterprises) {
		this.enterprises = enterprises;
	}
}
