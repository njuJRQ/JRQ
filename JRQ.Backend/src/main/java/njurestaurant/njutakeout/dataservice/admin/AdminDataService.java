package njurestaurant.njutakeout.dataservice.admin;

import njurestaurant.njutakeout.entity.admin.Admin;
import njurestaurant.njutakeout.exception.NotExistException;

import java.util.List;

public interface AdminDataService {

	void addAdmin(Admin admin);

	Admin getAdminById(String id) throws NotExistException;

	List<Admin> getAllAdmins();

	void updateAdminById(String id, String username, String password, String limits, String date) throws NotExistException;

	void deleteAdminById(String id);
}
