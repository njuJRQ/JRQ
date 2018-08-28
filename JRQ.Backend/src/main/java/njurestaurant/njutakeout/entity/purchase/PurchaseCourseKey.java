package njurestaurant.njutakeout.entity.purchase;

import java.io.Serializable;

public class PurchaseCourseKey implements Serializable {
	private String openid; //用户微信ID
	private long courseId; //课程ID

	public PurchaseCourseKey() {
	}

	public PurchaseCourseKey(String openid, long courseId) {
		this.openid = openid;
		this.courseId = courseId;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}
}
