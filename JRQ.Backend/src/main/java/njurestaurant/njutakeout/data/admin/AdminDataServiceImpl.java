package njurestaurant.njutakeout.data.admin;

import njurestaurant.njutakeout.data.dao.admin.AdminDao;
import njurestaurant.njutakeout.dataservice.admin.AdminDataService;
import njurestaurant.njutakeout.entity.admin.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminDataServiceImpl implements AdminDataService {
	private final AdminDao adminDao;

	@Autowired
	public AdminDataServiceImpl(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	@Override
	public void saveAdmin(Admin admin) {
		adminDao.save(admin);
	}
}
