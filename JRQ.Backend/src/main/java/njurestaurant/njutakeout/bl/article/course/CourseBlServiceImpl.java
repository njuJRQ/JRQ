package njurestaurant.njutakeout.bl.article.course;

import njurestaurant.njutakeout.blservice.article.course.CourseBlService;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.article.course.CourseListResponse;
import njurestaurant.njutakeout.response.article.course.CourseResponse;

public class CourseBlServiceImpl implements CourseBlService {
	@Override
	public InfoResponse addCourse(String title, String image, String writerName, String date, long likeNum, String video, int price) {
		return null;
	}

	@Override
	public CourseResponse getCourse(long id) {
		return null;
	}

	@Override
	public CourseListResponse getCourseList() {
		return null;
	}

	@Override
	public InfoResponse updateCourse(long id, String title, String image, String writerName, String date, long likeNum, String video, int price) {
		return null;
	}

	@Override
	public InfoResponse deleteCourse(long id) {
		return null;
	}
}
