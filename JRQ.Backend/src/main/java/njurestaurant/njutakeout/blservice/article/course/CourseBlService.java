package njurestaurant.njutakeout.blservice.article.course;

import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.article.course.CourseListResponse;
import njurestaurant.njutakeout.response.article.course.CourseResponse;
import org.springframework.stereotype.Service;

public interface CourseBlService {
	/**
	 *  添加课程(Admin)
	 * @param title 课程标题
	 * @param image 图片路径
	 * @param writerName 作者名字
	 * @param likeNum 点赞数
	 * @param video 视频路径
	 * @param price 课程价格
	 * @return 是否成功添加
	 */
	InfoResponse addCourse(String title, String image, String writerName, long likeNum, String video, int price);

	/**
	 * 根据课程ID获取课程内容(Admin)
	 * @param id 课程ID
	 * @return 课程内容
	 */
	CourseResponse getCourse(String id) throws NotExistException;

	/**
	 * 获取课程列表(Admin)
	 * @return 课程列表
	 */
	CourseListResponse getCourseList();

	/**
	 * 根据课程ID修改课程内容(Admin)
	 * @param id 课程ID
	 * @param title 课程标题
	 * @param image 图片路径
	 * @param writerName 作者名字
	 * @param likeNum 点赞数
	 * @param video 视频路径
	 * @param price 课程价格
	 * @return 是否成功
	 */
	InfoResponse updateCourse(String id, String title, String image, String writerName, long likeNum, String video, int price) throws NotExistException;

	/**
	 * 根据课程ID删除课程(Admin)
	 * @param id 课程ID
	 * @return 是否成功
	 */
	InfoResponse deleteCourse(String id) throws NotExistException;

	/**
	 * 用户根据课程ID获取课程信息，未购买则video属性为空串(User)
	 * @param openid 用户openid
	 * @param courseId 课程ID
	 * @return 课程信息
	 */
	CourseResponse getMyCourse(String openid, String courseId) throws NotExistException;

	/**
	 * 用户获取所有课程信息列表，video属性为空串(User)
	 * @param openid 用户openid
	 * @return 去掉video信息的课程列表
	 */
	CourseListResponse getMyCourseList(String openid) throws NotExistException;

	/**
	 * 获取某一篇课程文章时间戳前的10篇文章
	 * 文章列表按照新旧排序，最新的在最前面，最旧的在最后面，如果有时间戳完全相同的，则不管10篇的限制，全部加入列表中
	 * @param id 课程文章ID
	 * @return 课程文章信息列表
	 */
	CourseListResponse getMyCourseListBefore(String openid, String id) throws NotExistException;
}
