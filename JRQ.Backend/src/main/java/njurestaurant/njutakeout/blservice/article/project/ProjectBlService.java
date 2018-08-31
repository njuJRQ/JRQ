package njurestaurant.njutakeout.blservice.article.project;

import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.article.project.ProjectListResponse;
import njurestaurant.njutakeout.response.article.project.ProjectResponse;


public interface ProjectBlService {
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
	ProjectResponse getProject(String id) throws NotExistException;

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
	InfoResponse updateProject(String id, String title, String identity, String phone, String city, String industry, String business, String content, int money, String attachment) throws NotExistException;

	/**
	 * 根据项目ID删除项目
	 * @param id 项目ID
	 * @return 是否成功
	 */
	InfoResponse deleteProject(String id) throws NotExistException;
}
