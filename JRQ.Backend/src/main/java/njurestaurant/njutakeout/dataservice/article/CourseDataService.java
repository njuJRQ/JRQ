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

	List<Course> getTextualResearchCourse();

	List<Course> getOrdinaryCourse();

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

	/**
	 * 用户获取10条按热度排序的课程，第一次传id为空字符串
	 * @param openid 用户微信openid
	 * @param id 特定课程id
	 * @return 课程列表
	 */
	List<Course> getTop10CoursesOrderByLikeNum(String openid, String id) throws NotExistException;

	/**
	 * 用户用户获取小于某热度值的10条按热度排序的课程
	 * @param openid 用户微信openid
	 * @param likeNum 热度值
	 * @return 课程列表
	 */
	List<Course> getCoursesOrderByLikeNumBefore(String openid ,long likeNum) throws NotExistException;
}
