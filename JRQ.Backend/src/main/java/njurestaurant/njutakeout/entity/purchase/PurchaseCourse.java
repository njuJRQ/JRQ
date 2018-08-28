package njurestaurant.njutakeout.entity.purchase;

import javax.persistence.*;

@Entity
@Table(name = "purchaseCourse")
@IdClass(PurchaseCourseKey.class)
public class PurchaseCourse {
	@Id
	@Column(name = "openid")
	private String openid; //用户微信ID

	@Id
	@Column(name = "courseId")
	private long courseId; //课程ID

	public PurchaseCourse() {
	}

	public PurchaseCourse(String openid, long courseId) {
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
