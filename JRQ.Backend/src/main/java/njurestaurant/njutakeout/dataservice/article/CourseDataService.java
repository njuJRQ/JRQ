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

	void deleteCoursesByWriterName(String writerName);

	/**
	 * 用户获取特定课程前的10篇课程，从新到旧排序（不包括这篇课程）
	 * @param openid 用户微信openid
	 * @param id 特定课程ID
	 * @return 课程列表
	 */
	List<Course> getMyCourseListBefore(String openid, String id) throws NotExistException;

	/**
	 * 用户获取特定时间戳前的10篇课程，从新到旧排序（不包括这篇课程）
	 * @param openid 用户微信openid
	 * @param timeStamp 特定时间戳
	 * @return 课程列表
	 */
	List<Course> getMyCourseListBeforeTimeStamp(String openid, long timeStamp) throws NotExistException;
}
