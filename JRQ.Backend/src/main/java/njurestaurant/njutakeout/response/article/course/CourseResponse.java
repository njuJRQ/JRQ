package njurestaurant.njutakeout.response.article.course;

import njurestaurant.njutakeout.response.Response;
import njurestaurant.njutakeout.response.article.leaveWord.LeaveWordViewItem;

import java.util.List;

public class CourseResponse extends Response {
	private CourseItem course;
	private List<LeaveWordViewItem> leaveWordViewItems;

	public CourseResponse(){
	}

	public CourseResponse(CourseItem course) {
		this.course = course;
	}

	public CourseResponse(CourseItem course, List<LeaveWordViewItem> leaveWordViewItems) {
		this.course = course;
		this.leaveWordViewItems = leaveWordViewItems;
	}

	public CourseItem getCourse() {
		return course;
	}

	public void setCourse(CourseItem course) {
		this.course = course;
	}

	public List<LeaveWordViewItem> getLeaveWordViewItems() {
		return leaveWordViewItems;
	}

	public void setLeaveWordViewItems(List<LeaveWordViewItem> leaveWordViewItems) {
		this.leaveWordViewItems = leaveWordViewItems;
	}
}
