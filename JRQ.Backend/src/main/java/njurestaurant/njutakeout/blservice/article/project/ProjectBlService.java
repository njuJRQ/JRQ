package njurestaurant.njutakeout.blservice.article.project;

import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.article.project.ProjectListResponse;
import njurestaurant.njutakeout.response.article.project.ProjectResponse;


public interface ProjectBlService {
	/**
	 * 添加项目(Admin)
	 * @param title 项目标题（简介）
	 * @param writerName 作者名字
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
	InfoResponse addProject(String title, String writerName, String identity, String phone, String city, String industry, String business, String content, int money, String attachment);

	/**
	 * 根据项目ID获取项目(Admin)
	 * @param id 项目ID
	 * @return 项目内容
	 */
	ProjectResponse getProject(String id) throws NotExistException;

	/**
	 * 获取项目列表(Admin)
	 * @return 项目列表
	 */
	ProjectListResponse getProjectList();

	/**
	 * 根据项目ID修改项目(Admin)
	 * @param id 项目ID
	 * @param title 项目标题
	 * @param writerName 作者名字
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
	InfoResponse updateProject(String id, String title, String writerName, String identity, String phone, String city, String industry, String business, String content, int money, String attachment) throws NotExistException;

	/**
	 * 根据项目ID删除项目(Admin)
	 * @param id 项目ID
	 * @return 是否成功
	 */
	InfoResponse deleteProject(String id) throws NotExistException;

	/**
	 * 用户查看特定项目(Admin)
	 * @param openid 用户的openid
	 * @param projectId 项目ID
	 * @return 项目的详细信息（包括是否点赞）
	 */
	ProjectResponse getMyProject(String openid, String projectId) throws NotExistException;

	/**
	 * 用户查看项目列表(Admin)
	 * @param openid 用户的openid
	 * @return 项目详细信息的列表（包括是否点赞）
	 */
	ProjectListResponse getMyProjectList(String openid);

	/**
	 * 获取某一篇项目文章时间戳前的10篇文章
	 * 文章列表按照新旧排序，最新的在最前面，最旧的在最后面，如果有时间戳完全相同的，则不管10篇的限制，全部加入列表中
	 * @param openid 用户的微信openid
	 * @param id 项目文章ID
	 * @return 项目文章信息列表
	 */
	ProjectListResponse getMyProjectListBefore(String openid, String id) throws NotExistException;
}
