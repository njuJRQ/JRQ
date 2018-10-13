package njurestaurant.njutakeout.data.article;

import njurestaurant.njutakeout.data.dao.article.ProjectDao;
import njurestaurant.njutakeout.dataservice.article.ProjectDataService;
import njurestaurant.njutakeout.entity.article.Project;
import njurestaurant.njutakeout.exception.NotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectDataServiceImpl implements ProjectDataService {
	private final ProjectDao projectDao;

	@Autowired
	public ProjectDataServiceImpl(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	@Override
	public boolean isProjectExistent(String id) {
		return projectDao.existsById(id);
	}

	@Override
	public void saveProject(Project project) {
		projectDao.save(project);
	}

	@Override
	public void addProject(Project project) {
		projectDao.save(project);
	}

	@Override
	public Project getProjectById(String id) throws NotExistException {
		Optional<Project> optionalProject = projectDao.findById(id);
		if (optionalProject.isPresent()) {
			return optionalProject.get();
		} else {
			throw new NotExistException("Project ID", id);
		}
	}

	@Override
	public List<Project> getAllProjects() {
		return projectDao.findAll();
	}

	@Override
	public void deleteProjectById(String id) throws NotExistException {
		if (projectDao.existsById(id)) {
			projectDao.deleteById(id);
		} else {
			throw new NotExistException("Project ID", id);
		}
	}

	@Override
	public List<Project> getMyProjectListBefore(String openid, String id) throws NotExistException {
		List<Project> projects = null;
		if (id.equals("")) {
			projects = projectDao.findTop10ByOrderByTimeStampDesc();
		} else {
			Project project = getProjectById(id);
			projects = projectDao.findTop10ByTimeStampBeforeOrderByTimeStampDesc(project.getTimeStamp());
		}

		if (!projects.isEmpty()) {
			List<Project> sameStampProjects = projectDao.findProjectsByTimeStamp(projects.get(projects.size()-1).getTimeStamp());
			for(Project ssp:sameStampProjects) {
				boolean flag = false; //标记ssp是否在projects中
				for(Project p:projects){
					if(ssp.getId().equals(p.getId())){
						flag = true;
						break;
					}
				}
				if(!flag){ //ssp不在projects里面，加入进去
					projects.add(ssp);
				}
			}
		}
		return projects;
	}
}
