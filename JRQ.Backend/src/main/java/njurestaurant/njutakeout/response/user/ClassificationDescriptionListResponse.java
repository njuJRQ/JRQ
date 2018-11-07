package njurestaurant.njutakeout.response.user;

import njurestaurant.njutakeout.response.Response;

import java.util.List;

public class ClassificationDescriptionListResponse extends Response {
	private List<ClassificationDescriptionItem> classificationDescriptionItems;

	public ClassificationDescriptionListResponse(List<ClassificationDescriptionItem> classificationDescriptionItems) {
		this.classificationDescriptionItems = classificationDescriptionItems;
	}

	public List<ClassificationDescriptionItem> getClassificationDescriptionItems() {
		return classificationDescriptionItems;
	}

	public void setClassificationDescriptionItems(List<ClassificationDescriptionItem> classificationDescriptionItems) {
		this.classificationDescriptionItems = classificationDescriptionItems;
	}
}
