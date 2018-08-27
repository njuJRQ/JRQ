package njurestaurant.njutakeout.blservice.article;

import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.article.*;

public interface ArticleBlService {

	/**
	 * 获取首页某一类别文章摘要列表
	 * @param kind 文章类别
	 * @return 文章摘要列表
	 */
	GetAbstractsResponse getAbstracts(String kind);

	/** TODO: 需要注明类别
	 * 获取某一文章详情
	 * @param id 文章ID
	 * @return 文章内容
	 */
	GetOneArticleResponse getOneArticleById(long id);

	/**
	 * 获取首页广告
	 * @return 首页广告信息
	 */
	GetAdResponse getAd();

	/** TODO: 需要注明类别
	 *  文章点赞
	 * @param id 文章ID
	 * @param openId 微信用户ID
	 * @return 点赞状态
	 */
	LikePlusResponse likePlus(long id, String openId);


	/*--- 课程增删改查 ---*/


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


	/*--- 文档增删改查 ---*/


	/**
	 * 添加文档
	 * @param title 文档标题
	 * @param writerName 作者名字
	 * @param date 发布日期
	 * @param likeNum 点赞数
	 * @return 是否成功
	 */
	InfoResponse addDocument(String title, String writerName, String date, long likeNum);

	/**
	 * 根据文档ID获取文档
	 * @param id 文档ID
	 * @return 文档内容
	 */
	DocumentResponse getDocument(long id);

	/**
	 * 获取文档列表
	 * @return 文档列表
	 */
	DocumentListResponse getDocumentList();

	/**
	 * 根据文档ID修改文档
	 * @param id 文档ID
	 * @param title 文档标题
	 * @param writerName 作者名字
	 * @param date 发布日期
	 * @param likeNum 点赞数
	 * @return 是否成功
	 */
	InfoResponse updateDocument(long id, String title, String writerName, String date, long likeNum);

	/**
	 * 根据文档ID删除文档
	 * @param id 文档ID
	 * @return 是否成功
	 */
	InfoResponse deleteDocument(long id);



	/*--- 项目增删改查 ---*/


	/**
	 * 添加项目
	 * @param title 项目标题（简介）
	 * @param identity 身份
	 * @param phone 联系方式
	 * @param city 所在城市
	 * @param industry 所属行业
	 * @param business 业务标签
	 * @param content 项目详情
	 * @param money 项目资金
	 * @param attachment 附件路径
	 * @return 是否成功
	 */
	InfoResponse addProject(String title, String identity, String phone, String city, String industry, String business, String content, int money, String attachment);

	/**
	 * 根据项目ID获取项目
	 * @param id 项目ID
	 * @return 项目内容
	 */
	ProjectResponse getProject(long id);

	/**
	 * 获取项目列表
	 * @return 项目列表
	 */
	ProjectListResponse getProjectList();

	/**
	 * 根据项目ID修改项目
	 * @param id 项目ID
	 * @param title 项目标题
	 * @param identity 身份
	 * @param phone 联系方式
	 * @param city 所在城市
	 * @param industry 所属行业
	 * @param business 业务标签
	 * @param content 项目详情
	 * @param money 项目资金
	 * @param attachment 附件路径
	 * @return 是否成功
	 */
	InfoResponse updateProject(long id, String title, String identity, String phone, String city, String industry, String business, String content, int money, String attachment);

	/**
	 * 根据项目ID删除项目
	 * @param id 项目ID
	 * @return 是否成功
	 */
	InfoResponse deleteProject(long id);
}
