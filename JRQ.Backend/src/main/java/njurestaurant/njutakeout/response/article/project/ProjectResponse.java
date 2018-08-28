package njurestaurant.njutakeout.response.article.project;

public class ProjectResponse {
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
