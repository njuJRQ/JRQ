package njurestaurant.njutakeout.entity.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class ClassificationDescription {
	@Id
	@Column
	private String workClass; //对应Classification表中的workClass，业务所属大类：capital, stock, merge

	@Column
	private String description; //业务的中文描述：金融类，股票类，并购类

	public ClassificationDescription() {
	}

	public ClassificationDescription(String workClass, String description) {
		this.workClass = workClass;
		this.description = description;
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
