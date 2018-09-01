package njurestaurant.njutakeout.response.article.project;

import njurestaurant.njutakeout.response.Response;

public class ProjectResponse extends Response {
	private ProjectItem project;

	public ProjectResponse(){
	}

	public ProjectResponse(ProjectItem project) {
		this.project = project;
	}

	public ProjectItem getProject() {
		return project;
	}

	public void setProject(ProjectItem project) {
		this.project = project;
	}
}
