package njurestaurant.njutakeout.bl.user;

import njurestaurant.njutakeout.blservice.user.EnterpriseBlService;
import njurestaurant.njutakeout.dataservice.admin.AdminDataService;
import njurestaurant.njutakeout.dataservice.purchase.PurchaseDataService;
import njurestaurant.njutakeout.dataservice.user.EnterpriseDataService;
import njurestaurant.njutakeout.dataservice.user.PrivilegeDataService;
import njurestaurant.njutakeout.dataservice.user.UserDataService;
import njurestaurant.njutakeout.entity.admin.Admin;
import njurestaurant.njutakeout.entity.purchase.Purchase;
import njurestaurant.njutakeout.entity.user.Enterprise;
import njurestaurant.njutakeout.entity.user.User;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.BoolResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class EnterpriseBlServiceImpl implements EnterpriseBlService {
	private final EnterpriseDataService enterpriseDataService;
	private final UserDataService userDataService;
	private final PurchaseDataService purchaseDataService;
	private final AdminDataService adminDataService;
	private final PrivilegeDataService privilegeDataService;

	@Autowired
	public EnterpriseBlServiceImpl(EnterpriseDataService enterpriseDataService, UserDataService userDataService, PurchaseDataService purchaseDataService, AdminDataService adminDataService, PrivilegeDataService privilegeDataService) {
		this.enterpriseDataService = enterpriseDataService;
		this.userDataService = userDataService;
		this.purchaseDataService = purchaseDataService;
		this.adminDataService = adminDataService;
		this.privilegeDataService = privilegeDataService;
	}

	@Override
	public BoolResponse setMyUserAsEnterprise(String openid) {
		if (enterpriseDataService.isUserEnterprise(openid)) {
			return new BoolResponse(false, "已经是企业用户，无需重复购买");
		}
		User user = null;
		try {
			user = userDataService.getUserByOpenid(openid);
		} catch (NotExistException e) {
			return new BoolResponse(false, e.getMessage());
		}
		int price = 0; //企业认证价格
		try {
			price = privilegeDataService.getPrivilegeByName("enterprise").getPrice();
		} catch (NotExistException e) {
			return new BoolResponse(false, e.getMessage());
		}
		if (user.getCredit()<price) {
			return new BoolResponse(false, "账户余额不足");
		} else {
			String adminUsername = UUID.randomUUID().toString();
			while (adminDataService.isAdminExistent(adminUsername)) {
				adminUsername = UUID.randomUUID().toString();
			}
			SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");//设置日期格式
			String date=df.format(new Date());// new Date()为获取当前系统时间
			adminDataService.addAdmin(new Admin(adminUsername, adminUsername, "3", date));
			String adminId = null;
			try {
				adminId = adminDataService.getAdminByUsername(adminUsername).getId();
			} catch (NotExistException e) {
				return new BoolResponse(false, "系统内部错误："+e.getMessage());
			}
			enterpriseDataService.addEnterprise(new Enterprise(openid, adminUsername));
			user.setCredit(user.getCredit()-price);
			purchaseDataService.addPurchase(new Purchase(openid, "enterprise", adminId, price, date));
			return new BoolResponse(true, "企业用户升级成功，用户名与密码均为"+adminUsername);
		}
	}
}
