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
}
