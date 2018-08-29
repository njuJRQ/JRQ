package njurestaurant.njutakeout.data.admin;

import njurestaurant.njutakeout.data.dao.admin.AdminDao;
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

	@Autowired
	public AdminDataServiceImpl(AdminDao adminDao) {
		this.adminDao = adminDao;
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
			throw new NotExistException("Admin");
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
			System.out.println(admin.getPassword());
		} else {
			throw new NotExistException("Admin");
		}
	}

	@Override
	public void deleteAdminById(String id) {
		adminDao.deleteById(id);
	}
}
