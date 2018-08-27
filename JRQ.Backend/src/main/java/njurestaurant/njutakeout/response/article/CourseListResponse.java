package njurestaurant.njutakeout.response.article;

import njurestaurant.njutakeout.entity.article.CourseItem;
import njurestaurant.njutakeout.response.Response;

import java.util.List;

public class CourseListResponse extends Response {
	private List<CourseItem> courseList;

	public CourseListResponse(){
	}

	public CourseListResponse(List<CourseItem> courseList) {
		this.courseList = courseList;
	}

	public List<CourseItem> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<CourseItem> courseList) {
		this.courseList = courseList;
	}
}
