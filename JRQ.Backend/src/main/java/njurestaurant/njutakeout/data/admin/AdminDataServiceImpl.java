package njurestaurant.njutakeout.data.admin;

import njurestaurant.njutakeout.data.dao.admin.AdminDao;
import njurestaurant.njutakeout.data.dao.article.CourseDao;
import njurestaurant.njutakeout.data.dao.article.DocumentDao;
import njurestaurant.njutakeout.data.dao.article.ProjectDao;
import njurestaurant.njutakeout.dataservice.admin.AdminDataService;
import njurestaurant.njutakeout.entity.admin.Admin;
import njurestaurant.njutakeout.exception.NotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminDataServiceImpl implements AdminDataService {
	private final AdminDao adminDao;
	private final DocumentDao documentDao;
	private final CourseDao courseDao;
	private final ProjectDao projectDao;

	@Autowired
	public AdminDataServiceImpl(AdminDao adminDao, DocumentDao documentDao, CourseDao courseDao, ProjectDao projectDao) {
		this.adminDao = adminDao;
		this.documentDao = documentDao;
		this.courseDao = courseDao;
		this.projectDao = projectDao;
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
			documentDao.deleteDocumentsByWriterName(admin.getUsername());  //删除此作者发布过的所有文档
			courseDao.deleteCoursesByWriterName(admin.getUsername());  //删除此作者发布过的所有课程
			projectDao.deleteProjectsByWriterName(admin.getUsername());  //删除此作者发布过的所有项目
			adminDao.deleteById(id);
		} else {
			throw new NotExistException("Admin ID", id);
		}
	}
}
