package njurestaurant.njutakeout.response.user;

import njurestaurant.njutakeout.response.Response;

import java.util.List;

public class ClassificationListResponse extends Response {
	private List<ClassificationItem> classifications;

	public ClassificationListResponse() {
	}

	public ClassificationListResponse(List<ClassificationItem> classifications) {
		this.classifications = classifications;
	}

	public List<ClassificationItem> getClassifications() {
		return classifications;
	}

	public void setClassifications(List<ClassificationItem> classifications) {
		this.classifications = classifications;
	}
}
