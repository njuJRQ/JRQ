package njurestaurant.njutakeout.entity.purchase;

import javax.persistence.*;

@Entity
@Table(name = "purchaseCourse")
@IdClass(PurchaseCourseKey.class)
public class PurchaseCourse {
	@Id
	@Column(name = "openid", length = 28)
	private String openid; //用户微信ID

	@Id
	@Column(name = "courseId", length = 32)
	private String courseId; //课程ID

	@Id
	@Column
	private boolean isGroup;

	public PurchaseCourse() {
	}

	public PurchaseCourse(String openid, String courseId,boolean isGroup) {
		this.openid = openid;
		this.courseId = courseId;
		this.isGroup=isGroup;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public boolean isGroup() {
		return isGroup;
	}

	public void setGroup(boolean group) {
		isGroup = group;
	}
}
