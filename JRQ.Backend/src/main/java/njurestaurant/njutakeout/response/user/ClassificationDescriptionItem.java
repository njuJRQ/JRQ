package njurestaurant.njutakeout.response.user;

import njurestaurant.njutakeout.entity.user.ClassificationDescription;

public class ClassificationDescriptionItem {
	private String workClass; //对应Classification表中的workClass，业务所属大类：capital, stock, merge
	private String description; //业务的中文描述：金融类，股票类，并购类

	public ClassificationDescriptionItem(ClassificationDescription classificationDescription) {
		this.workClass = classificationDescription.getWorkClass();
		this.description = classificationDescription.getDescription();
	}

	public String getWorkClass() {
		return workClass;
	}

	public void setWorkClass(String workClass) {
		this.workClass = workClass;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
