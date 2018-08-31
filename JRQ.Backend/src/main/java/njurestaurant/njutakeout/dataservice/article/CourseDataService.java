package njurestaurant.njutakeout.dataservice.article;

import njurestaurant.njutakeout.entity.article.Course;
import njurestaurant.njutakeout.exception.NotExistException;

import java.util.List;

public interface CourseDataService {

	boolean isCourseExistent(String id);

	void saveCourse(Course course);

	void addCourse(Course course);

	Course getCourseById(String id) throws NotExistException;

	List<Course> getAllCourses();

	void deleteCourseById(String id) throws NotExistException;
}
