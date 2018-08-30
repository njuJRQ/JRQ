package njurestaurant.njutakeout.response.user;

import njurestaurant.njutakeout.entity.user.Classification;

public class ClassificationItem {
	private String userLabel; //用户类别信息，可取值：融资租赁，商业保理，地产交易，金融牌照
	private String workClass; //业务所属大类：capital, stock, merge分别代表金融类，股票类，并购类

	public ClassificationItem() {
	}

	public ClassificationItem(Classification classification) {
		this.userLabel = classification.getUserLabel();
		this.workClass = classification.getWorkClass();
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
