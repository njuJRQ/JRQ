package njurestaurant.njutakeout.bl.admin;

import njurestaurant.njutakeout.blservice.admin.AdminBlService;
import njurestaurant.njutakeout.dataservice.admin.AdminDataService;
import njurestaurant.njutakeout.entity.admin.Admin;
import njurestaurant.njutakeout.exception.AlreadyExistException;
import njurestaurant.njutakeout.exception.DuplicateUsernameException;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.exception.WrongPasswordException;
import njurestaurant.njutakeout.response.BoolResponse;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.admin.AdminItem;
import njurestaurant.njutakeout.response.admin.AdminListResponse;
import njurestaurant.njutakeout.response.admin.AdminResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminBlServiceImpl implements AdminBlService {
	private final AdminDataService adminDataService;

	@Autowired
	public AdminBlServiceImpl(AdminDataService adminDataService) {
		this.adminDataService = adminDataService;
	}

	@Override
	public BoolResponse isAdminUsernameExistent(String username) {
		return new BoolResponse(adminDataService.isAdminExistent(username), "ok");
	}

	@Override
	public boolean loginAdmin(String username, String password) {
		try {
			return adminDataService.isAdminExistent(username)
					&& adminDataService.getAdminByUsername(username).getPassword().equals(password);
		} catch (NotExistException exception) {
			return false;
		}
	}

	@Override
	public InfoResponse addAdmin(String username, String password, String limits, String date) throws DuplicateUsernameException {
		if (!adminDataService.isAdminExistent(username)) {
			adminDataService.addAdmin(new Admin(username, password, limits, date));
			return new InfoResponse();
		} else {
			throw new DuplicateUsernameException(username);
		}
	}

	@Override
	public AdminResponse getAdmin(String id) throws NotExistException {
		return new AdminResponse(new AdminItem(adminDataService.getAdminById(id)));
	}

	@Override
	public AdminResponse getAdminByUsername(String username) throws NotExistException {
		return new AdminResponse(new AdminItem(adminDataService.getAdminByUsername(username)));
	}

	@Override
	public AdminListResponse getAdminList() {
		List<Admin> adminList = adminDataService.getAllAdmins();
		List<AdminItem> adminItemList = new ArrayList<>();
		for(Admin admin:adminList) {
			adminItemList.add(new AdminItem(admin));
		}
		return new AdminListResponse(adminItemList);
	}

	@Override
	public InfoResponse updateAdmin(String id, String username, String password, String limits, String date) throws NotExistException {
		adminDataService.updateAdminById(id, username, password, limits, date);
		return new InfoResponse();
	}

	@Override
	public InfoResponse deleteAdmin(String id) throws NotExistException {
		adminDataService.deleteAdminById(id);
		return new InfoResponse();
	}
}
