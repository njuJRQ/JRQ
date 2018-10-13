package njurestaurant.njutakeout.dataservice.article;

import njurestaurant.njutakeout.entity.article.Project;
import njurestaurant.njutakeout.exception.NotExistException;

import java.util.List;

public interface ProjectDataService {

	boolean isProjectExistent(String id);

	void saveProject(Project project);

	void addProject(Project project);

	Project getProjectById(String id) throws NotExistException;

	List<Project> getAllProjects();

	void deleteProjectById(String id) throws NotExistException;

	/**
	 * 用户获取特定项目前的10篇项目，从新到旧排序（不包括这篇项目）
	 * @param openid 用户微信openid
	 * @param id 特定项目ID
	 * @return 项目列表
	 */
	List<Project> getMyProjectListBefore(String openid, String id) throws NotExistException;

	/**
	 * 用户获取特定时间戳前的10篇项目，从新到旧排序（不包括这篇项目）
	 * @param openid 用户微信openid
	 * @param timeStamp 特定时间戳
	 * @return 项目列表
	 */
	List<Project> getMyProjectListBeforeTimeStamp(String openid, long timeStamp) throws NotExistException;
}
