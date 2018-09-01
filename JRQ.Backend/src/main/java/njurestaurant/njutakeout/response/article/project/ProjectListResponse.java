package njurestaurant.njutakeout.response.article.project;

import njurestaurant.njutakeout.response.Response;

import java.util.List;

public class ProjectListResponse extends Response{
	private List<ProjectItem> projects;

	public ProjectListResponse(){
	}

	public ProjectListResponse(List<ProjectItem> projects) {
		this.projects = projects;
	}

	public List<ProjectItem> getProjects() {
		return projects;
	}

	public void setProjects(List<ProjectItem> projects) {
		this.projects = projects;
	}
}
