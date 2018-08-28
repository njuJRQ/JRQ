package njurestaurant.njutakeout.response.article.project;

import java.util.List;

public class ProjectListResponse {
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
