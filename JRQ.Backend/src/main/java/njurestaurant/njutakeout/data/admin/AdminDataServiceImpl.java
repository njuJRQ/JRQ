package njurestaurant.njutakeout.data.admin;

import njurestaurant.njutakeout.data.dao.admin.AdminDao;
import njurestaurant.njutakeout.dataservice.admin.AdminDataService;
import njurestaurant.njutakeout.dataservice.article.CourseDataService;
import njurestaurant.njutakeout.dataservice.article.DocumentDataService;
import njurestaurant.njutakeout.dataservice.article.ProjectDataService;
import njurestaurant.njutakeout.entity.admin.Admin;
import njurestaurant.njutakeout.exception.NotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminDataServiceImpl implements AdminDataService {
	private final AdminDao adminDao;
	private final DocumentDataService documentDataService;
	private final CourseDataService courseDataService;
	private final ProjectDataService projectDataService;

	@Autowired
	public AdminDataServiceImpl(AdminDao adminDao, DocumentDataService documentDataService, CourseDataService courseDataService, ProjectDataService projectDataService) {
		this.adminDao = adminDao;
		this.documentDataService = documentDataService;
		this.courseDataService = courseDataService;
		this.projectDataService = projectDataService;
	}

	@Override
	public boolean isAdminExistent(String username) {
		return !adminDao.findAdminByUsername(username).isEmpty();
	}

	@Override
	public void addAdmin(Admin admin) {
		adminDao.save(admin);
	}

	@Override
	public Admin getAdminById(String id) throws NotExistException {
		Optional<Admin> optionalAdmin = adminDao.findById(id);
		if(optionalAdmin.isPresent()) {
			return optionalAdmin.get();
		}else {
			throw new NotExistException("Admin ID", id);
		}
	}

	@Override
	public Admin getAdminByUsername(String username) throws NotExistException {
		if (!adminDao.findAdminByUsername(username).isEmpty()) {
			return adminDao.findAdminByUsername(username).get(0);
		} else {
			throw new NotExistException("Admin username", username);
		}
	}

	@Override
	public List<Admin> getAllAdmins() {
		return adminDao.findAll();
	}

	@Override
	public void updateAdminById(String id, String username, String password, String limits, String date) throws NotExistException {
		Optional<Admin> optionalAdmin = adminDao.findById(id);
		if(optionalAdmin.isPresent()) {
			Admin admin = optionalAdmin.get();
			admin.setUsername(username);
			admin.setPassword(password);
			admin.setLimits(limits);
			admin.setDate(date);
			adminDao.save(admin);
		} else {
			throw new NotExistException("Admin ID", id);
		}
	}

	@Override
	public void deleteAdminById(String id) throws NotExistException {
		Optional<Admin> optionalAdmin = adminDao.findById(id);
		if (optionalAdmin.isPresent()) {
			Admin admin = optionalAdmin.get();
			documentDataService.deleteDocumentsByWriterName(admin.getUsername());  //删除此作者发布过的所有文档
			courseDataService.deleteCoursesByWriterName(admin.getUsername());  //删除此作者发布过的所有课程
			projectDataService.deleteProjectsByWriterName(admin.getUsername());  //删除此作者发布过的所有项目
			adminDao.deleteById(id);
		} else {
			throw new NotExistException("Admin ID", id);
		}
	}
}
