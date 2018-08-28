package njurestaurant.njutakeout.blservice.article.course;

import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.article.course.CourseListResponse;
import njurestaurant.njutakeout.response.article.course.CourseResponse;

public interface CourseBlService {
	/**
	 *  添加课程
	 * @param title 课程标题
	 * @param image 图片路径
	 * @param writerName 作者名字
	 * @param date 发布日期
	 * @param likeNum 点赞数
	 * @param video 视频路径
	 * @param price 课程价格
	 * @return 是否成功添加
	 */
	InfoResponse addCourse(String title, String image, String writerName, String date, long likeNum, String video, int price);

	/**
	 * 根据课程ID获取课程内容
	 * @param id 课程ID
	 * @return 课程内容
	 */
	CourseResponse getCourse(long id);

	/**
	 * 获取课程列表
	 * @return 课程列表
	 */
	CourseListResponse getCourseList();

	/**
	 * 根据课程ID修改课程内容
	 * @param id 课程ID
	 * @param title 课程标题
	 * @param image 图片路径
	 * @param writerName 作者名字
	 * @param date 发布日期
	 * @param likeNum 点赞数
	 * @param video 视频路径
	 * @param price 课程价格
	 * @return 是否成功
	 */
	InfoResponse updateCourse(long id, String title, String image, String writerName, String date, long likeNum, String video, int price);

	/**
	 * 根据课程ID删除课程
	 * @param id 课程ID
	 * @return 是否成功
	 */
	InfoResponse deleteCourse(long id);
}
