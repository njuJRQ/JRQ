package njurestaurant.njutakeout.bl.article.course;

import njurestaurant.njutakeout.blservice.article.course.CourseBlService;
import njurestaurant.njutakeout.dataservice.article.CourseDataService;
import njurestaurant.njutakeout.entity.article.Course;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.article.course.CourseItem;
import njurestaurant.njutakeout.response.article.course.CourseListResponse;
import njurestaurant.njutakeout.response.article.course.CourseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseBlServiceImpl implements CourseBlService {
	private final CourseDataService courseDataService;

	@Autowired
	public CourseBlServiceImpl(CourseDataService courseDataService) {
		this.courseDataService = courseDataService;
	}

	@Override
	public InfoResponse addCourse(String title, String image, String writerName, String date, long likeNum, String video, int price) {
		courseDataService.addCourse(new Course(title, image, writerName, date, likeNum, video, price));
		return new InfoResponse();
	}

	@Override
	public CourseResponse getCourse(String id) throws NotExistException {
		return new CourseResponse(new CourseItem(courseDataService.getCourseById(id)));
	}

	@Override
	public CourseListResponse getCourseList() {
		return null;
	}

	@Override
	public InfoResponse updateCourse(String id, String title, String image, String writerName, String date, long likeNum, String video, int price) throws NotExistException {
		Course course = courseDataService.getCourseById(id);
		course.setTitle(title);
		course.setImage(image);
		course.setWriterName(writerName);
		course.setDate(date);
		course.setLikeNum(likeNum);
		course.setVideo(video);
		course.setPrice(price);
		courseDataService.saveCourse(course);
		return new InfoResponse();
	}

	@Override
	public InfoResponse deleteCourse(String id) throws NotExistException {
		courseDataService.deleteCourseById(id);
		return new InfoResponse();
	}
}
