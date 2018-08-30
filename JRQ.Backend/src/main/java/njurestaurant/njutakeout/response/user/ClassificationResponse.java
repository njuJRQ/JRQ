package njurestaurant.njutakeout.response.user;

import njurestaurant.njutakeout.response.Response;

public class ClassificationResponse extends Response {
	private ClassificationItem classification;

	public ClassificationResponse() {
	}

	public ClassificationResponse(ClassificationItem classification) {
		this.classification = classification;
	}

	public ClassificationItem getClassification() {
		return classification;
	}

	public void setClassification(ClassificationItem classification) {
		this.classification = classification;
	}
}
