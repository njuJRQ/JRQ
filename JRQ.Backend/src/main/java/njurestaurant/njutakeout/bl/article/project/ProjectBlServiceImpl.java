package njurestaurant.njutakeout.bl.article.project;

import njurestaurant.njutakeout.blservice.article.project.ProjectBlService;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.article.project.ProjectListResponse;
import njurestaurant.njutakeout.response.article.project.ProjectResponse;

public class ProjectBlServiceImpl implements ProjectBlService {
	@Override
	public InfoResponse addProject(String title, String identity, String phone, String city, String industry, String business, String content, int money, String attachment) {
		return null;
	}

	@Override
	public ProjectResponse getProject(long id) {
		return null;
	}

	@Override
	public ProjectListResponse getProjectList() {
		return null;
	}

	@Override
	public InfoResponse updateProject(long id, String title, String identity, String phone, String city, String industry, String business, String content, int money, String attachment) {
		return null;
	}

	@Override
	public InfoResponse deleteProject(long id) {
		return null;
	}
}
