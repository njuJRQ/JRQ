package njurestaurant.njutakeout.bl.admin;

import njurestaurant.njutakeout.blservice.admin.AdminBlService;
import njurestaurant.njutakeout.dataservice.admin.AdminDataService;
import njurestaurant.njutakeout.entity.admin.Admin;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.admin.AdminListResponse;
import njurestaurant.njutakeout.response.admin.AdminResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminBlServiceImpl implements AdminBlService {
	private final AdminDataService adminDataService;

	@Autowired
	public AdminBlServiceImpl(AdminDataService adminDataService) {
		this.adminDataService = adminDataService;
	}

	@Override
	public InfoResponse addAdmin(String username, String password, String limits, String date) {
		adminDataService.saveAdmin(new Admin(username, password, limits, date));
		return new InfoResponse();
	}

	@Override
	public AdminResponse getAdmin(int id) {
		return null;
	}

	@Override
	public AdminListResponse getAdminList() {
		return null;
	}

	@Override
	public InfoResponse updateAdmin(int id, String username, String password, String limits, String date) {
		return null;
	}

	@Override
	public InfoResponse deleteAdmin(int id) {
		return null;
	}
}
