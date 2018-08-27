package njurestaurant.njutakeout.response.article;

import njurestaurant.njutakeout.response.Response;

public class CourseResponse extends Response {
	private CourseItem course;

	public CourseResponse(){
	}

	public CourseResponse(CourseItem course) {
		this.course = course;
	}

	public CourseItem getCourse() {
		return course;
	}

	public void setCourse(CourseItem course) {
		this.course = course;
	}
}
