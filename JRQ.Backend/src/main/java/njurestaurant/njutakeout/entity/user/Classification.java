package njurestaurant.njutakeout.entity.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Classification {
	@Id
	@Column
	private String userLabel; //用户类别信息，可取值：融资租赁，商业保理，地产交易，金融牌照

	@Column
	private String workClass; //业务所属大类：capital, stock, merge分别代表金融类，股票类，并购类

	public Classification() {
	}

	public Classification(String userLabel, String workClass) {
		this.userLabel = userLabel;
		this.workClass = workClass;
	}

	public String getUserLabel() {
		return userLabel;
	}

	public void setUserLabel(String userLabel) {
		this.userLabel = userLabel;
	}

	public String getWorkClass() {
		return workClass;
	}

	public void setWorkClass(String workClass) {
		this.workClass = workClass;
	}
}
