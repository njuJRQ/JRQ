package njurestaurant.njutakeout.bl.article.project;

import njurestaurant.njutakeout.blservice.article.project.ProjectBlService;
import njurestaurant.njutakeout.dataservice.article.LikeDataService;
import njurestaurant.njutakeout.dataservice.article.ProjectDataService;
import njurestaurant.njutakeout.entity.article.Project;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.article.project.ProjectItem;
import njurestaurant.njutakeout.response.article.project.ProjectListResponse;
import njurestaurant.njutakeout.response.article.project.ProjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectBlServiceImpl implements ProjectBlService {
	private final ProjectDataService projectDataService;
	private final LikeDataService likeDataService;

	@Autowired
	public ProjectBlServiceImpl(ProjectDataService projectDataService, LikeDataService likeDataService) {
		this.projectDataService = projectDataService;
		this.likeDataService = likeDataService;
	}

	@Override
	public InfoResponse addProject(String title, String writerName, String identity, String phone, String city, String industry, String business, String content, int money, String attachment) {
		projectDataService.addProject(new Project(title, writerName, identity, phone, city, industry, business, content, money, attachment, System.currentTimeMillis(), 0));
		return new InfoResponse();
	}

	@Override
	public ProjectResponse getProject(String id) throws NotExistException {
		return new ProjectResponse(new ProjectItem(projectDataService.getProjectById(id)));
	}

	@Override
	public ProjectListResponse getProjectList() {
		List<Project> projects = projectDataService.getAllProjects();
		List<ProjectItem> projectItems = new ArrayList<>();
		for(Project project:projects) {
			projectItems.add(new ProjectItem(project));
		}
		return new ProjectListResponse(projectItems);
	}

	@Override
	public InfoResponse updateProject(String id, String title, String writerName, String identity, String phone, String city, String industry, String business, String content, int money, String attachment) throws NotExistException {
		Project project = projectDataService.getProjectById(id);
		project.setTitle(title);
		project.setWriterName(writerName);
		project.setIdentity(identity);
		project.setPhone(phone);
		project.setCity(city);
		project.setIndustry(industry);
		project.setBusiness(business);
		project.setContent(content);
		project.setMoney(money);
		project.setAttachment(attachment);
		project.setTimeStamp(System.currentTimeMillis());
		projectDataService.saveProject(project);
		return new InfoResponse();
	}

	@Override
	public InfoResponse deleteProject(String id) throws NotExistException {
		projectDataService.deleteProjectById(id);
		return new InfoResponse();
	}

	@Override
	public ProjectResponse getMyProject(String openid, String projectId) throws NotExistException {
		boolean hasLiked = likeDataService.isLikeExistent(openid, "project", projectId);
		return new ProjectResponse(new ProjectItem(projectDataService.getProjectById(projectId), hasLiked));
	}

	@Override
	public ProjectListResponse getMyProjectList(String openid) {
		List<Project> projects = projectDataService.getAllProjects();
		List<ProjectItem> projectItems = new ArrayList<>();
		for(Project project:projects) {
			boolean hasLiked = likeDataService.isLikeExistent(openid, "project", project.getId());
			projectItems.add(new ProjectItem(project, hasLiked));
		}
		return new ProjectListResponse(projectItems);
	}

	@Override
	public ProjectListResponse getMyProjectListBefore(String openid, String id) throws NotExistException {
		List<Project> projects = projectDataService.getMyProjectListBefore(openid, id);
		List<ProjectItem> projectItems = new ArrayList<>();
		for(Project project:projects){
			boolean hasLiked = likeDataService.isLikeExistent(openid, "project", project.getId());
			projectItems.add(new ProjectItem(project, hasLiked));
		}
		return new ProjectListResponse(projectItems);
	}
}
