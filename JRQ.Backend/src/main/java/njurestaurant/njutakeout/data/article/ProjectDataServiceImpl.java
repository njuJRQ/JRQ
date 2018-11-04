package njurestaurant.njutakeout.data.article;

import njurestaurant.njutakeout.data.dao.article.ProjectDao;
import njurestaurant.njutakeout.dataservice.article.ProjectDataService;
import njurestaurant.njutakeout.entity.article.Project;
import njurestaurant.njutakeout.exception.NotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
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
		Optional<Project> optionalProject = projectDao.findById(id);
		if (optionalProject.isPresent()) {
			Project project = optionalProject.get();
			if (! new File(project.getAttachment()).delete()) {
				System.err.println("项目附件"+project.getAttachment()+"删除失败");
			}
			projectDao.delete(project);
		} else {
			throw new NotExistException("Project ID", id);
		}
	}

	@Override
	public void deleteProjectsByWriterName(String writerName) {
		List<Project> projects = projectDao.findProjectsByWriterName(writerName);
		for (Project project:projects) {
			if (! new File(project.getAttachment()).delete()) {
				System.err.println("项目附件"+project.getAttachment()+"删除失败");
			}
			projectDao.delete(project);
		}
	}

	@Override
	public List<Project> getMyProjectListBefore(String openid, String id) throws NotExistException {
		return getMyProjectListBeforeTimeStamp(openid,
				id.equals("")?-1:getProjectById(id).getTimeStamp());
	}

	@Override
	public List<Project> getMyProjectListBeforeTimeStamp(String openid, long timeStamp) throws NotExistException {
		List<Project> projects = null;
		if (timeStamp<0) {
			projects = projectDao.findTop10ByOrderByTimeStampDesc();
		} else {
			projects = projectDao.findTop10ByTimeStampBeforeOrderByTimeStampDesc(timeStamp);
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
