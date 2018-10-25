package njurestaurant.njutakeout.response.user;

import java.util.List;

public class EnterpriseListResponse {
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
