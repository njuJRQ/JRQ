package njurestaurant.njutakeout.bl.admin;


import njurestaurant.njutakeout.blservice.admin.AdminBlService;
import njurestaurant.njutakeout.dataservice.admin.AdminDataService;
import njurestaurant.njutakeout.dataservice.user.EnterpriseDataService;
import njurestaurant.njutakeout.entity.admin.Admin;
import njurestaurant.njutakeout.exception.DuplicateUsernameException;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.BoolResponse;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.admin.AdminItem;
import njurestaurant.njutakeout.response.admin.AdminListResponse;
import njurestaurant.njutakeout.response.admin.AdminResponse;
import njurestaurant.njutakeout.security.jwt.JwtService;
import njurestaurant.njutakeout.security.jwt.JwtUser;
import njurestaurant.njutakeout.security.jwt.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AdminBlServiceImpl implements AdminBlService {
	private final AdminDataService adminDataService;
	private final JwtUserDetailsService jwtUserDetailsService;
	private final EnterpriseDataService enterpriseDataService;
	private final JwtService jwtService;
	private final static long EXPIRATION = 604800;
	@Autowired
	public AdminBlServiceImpl(AdminDataService adminDataService, JwtUserDetailsService jwtUserDetailsService, EnterpriseDataService enterpriseDataService, JwtService jwtService) {
		this.adminDataService = adminDataService;
		this.jwtUserDetailsService = jwtUserDetailsService;
		this.enterpriseDataService = enterpriseDataService;
		this.jwtService = jwtService;
	}

	@Override
	public BoolResponse isAdminUsernameExistent(String username) {
		return new BoolResponse(adminDataService.isAdminExistent(username), "ok");
	}

	@Override
	public String loginAdmin(String username, String password) {
		try {
			JwtUser jwtUser = (JwtUser) jwtUserDetailsService.loadUserByUsername(username);
			String token="";
			token = jwtService.generateToken(jwtUser, EXPIRATION);
			if(adminDataService.isAdminExistent(username)
					&& adminDataService.getAdminByUsername(username).getPassword().equals(password)){
				return token;
			}

			return null;
		} catch (NotExistException exception) {
			return null;
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
	public BoolResponse deleteAdmin(String id) throws NotExistException {
		if (enterpriseDataService.isAdminInEnterprise(id)) { //企业管理员不能直接被删除，否则会在删除企业时出错
			return new BoolResponse(false, "企业管理员不能直接删除。若要删除企业管理员，请删除企业账号");
		}
		adminDataService.deleteAdminById(id);
		return new BoolResponse(true, "");
	}
}
