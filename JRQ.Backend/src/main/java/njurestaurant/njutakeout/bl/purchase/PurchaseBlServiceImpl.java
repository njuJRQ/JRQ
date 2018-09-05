package njurestaurant.njutakeout.bl.purchase;

import njurestaurant.njutakeout.blservice.purchase.PurchaseBlService;
import njurestaurant.njutakeout.dataservice.admin.AdminDataService;
import njurestaurant.njutakeout.dataservice.purchase.PurchaseCourseDataService;
import njurestaurant.njutakeout.dataservice.purchase.PurchaseDataService;
import njurestaurant.njutakeout.dataservice.user.EnterpriseDataService;
import njurestaurant.njutakeout.dataservice.user.LevelDataService;
import njurestaurant.njutakeout.dataservice.user.UserDataService;
import njurestaurant.njutakeout.entity.admin.Admin;
import njurestaurant.njutakeout.entity.purchase.Purchase;
import njurestaurant.njutakeout.entity.purchase.PurchaseCourse;
import njurestaurant.njutakeout.entity.user.Enterprise;
import njurestaurant.njutakeout.entity.user.Level;
import njurestaurant.njutakeout.entity.user.User;
import njurestaurant.njutakeout.exception.AlreadyExistException;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.BoolResponse;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.purchase.PurchaseItem;
import njurestaurant.njutakeout.response.purchase.PurchaseListResponse;
import njurestaurant.njutakeout.response.purchase.PurchaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PurchaseBlServiceImpl implements PurchaseBlService {
	private final PurchaseDataService purchaseDataService;
	private final UserDataService userDataService;
	private final LevelDataService levelDataService;
	private final AdminDataService adminDataService;
	private final PurchaseCourseDataService purchaseCourseDataService;
	private final EnterpriseDataService enterpriseDataService;

	@Autowired
	public PurchaseBlServiceImpl(PurchaseDataService purchaseDataService, UserDataService userDataService, LevelDataService levelDataService, AdminDataService adminDataService, PurchaseCourseDataService purchaseCourseDataService, EnterpriseDataService enterpriseDataService) {
		this.purchaseDataService = purchaseDataService;
		this.userDataService = userDataService;
		this.levelDataService = levelDataService;
		this.adminDataService = adminDataService;
		this.purchaseCourseDataService = purchaseCourseDataService;
		this.enterpriseDataService = enterpriseDataService;
	}

	@Override
	public BoolResponse addPurchase(String openid, String type, String detail, int price, String date) {
		User user = null;
		try {
			user = userDataService.getUserByOpenid(openid);
		} catch (NotExistException e) {
			return new BoolResponse(false, e.getMessage());
		}
		if (user.getCredit()>=price) {
			switch (type) {
				case "level":
					int used = 0;
					try {
						used = levelDataService.getLevelByName(user.getLevelName()).getCardLimit() - user.getCardLimit();
					} catch (NotExistException e) {
						return new BoolResponse(false, e.getMessage());
					}
					user.setLevelName(detail);
					try {
						user.setCardLimit(levelDataService.getLevelByName(detail).getCardLimit() - used); //更新权限
					} catch (NotExistException e) {
						return new BoolResponse(false, e.getMessage());
					}
					userDataService.saveUser(user);
					break;
				case "course":
					try {
						purchaseCourseDataService.addPurchaseCourse(new PurchaseCourse(openid, detail));
					} catch (AlreadyExistException e) {
						return new BoolResponse(false, e.getMessage());
					}
					break;
				case "enterprise":
					String adminUsername = UUID.randomUUID().toString();
					while (adminDataService.isAdminExistent(adminUsername)) {
						adminUsername = UUID.randomUUID().toString();
					}
					adminDataService.addAdmin(new Admin(adminUsername, adminUsername, "3", date));
					enterpriseDataService.addEnterprise(new Enterprise(openid, adminUsername));
					break;
				default:
					return new BoolResponse(false, "购买类型不存在");
			}
			user.setCredit(user.getCredit()-price); //付款
			purchaseDataService.addPurchase(new Purchase(openid, type, detail, price, date)); //记录订单
			return new BoolResponse(true, "购买成功");
		} else {
			return new BoolResponse(false, "账户余额不足");
		}
	}

	@Override
	public PurchaseResponse getPurchase(String id) throws NotExistException {
		return new PurchaseResponse(new PurchaseItem(purchaseDataService.getPurchaseById(id)));
	}

	@Override
	public PurchaseListResponse getMyPurchaseList(String openid) {
		List<Purchase> purchases = purchaseDataService.getPurchasesByOpenid(openid);
		List<PurchaseItem> purchaseItems = new ArrayList<>();
		for (Purchase purchase:purchases) {
			purchaseItems.add(new PurchaseItem(purchase));
		}
		return new PurchaseListResponse(purchaseItems);
	}

	@Override
	public PurchaseListResponse getPurchaseList() {
		List<Purchase> purchases = purchaseDataService.getAllPurchases();
		List<PurchaseItem> purchaseItems = new ArrayList<>();
		for (Purchase purchase:purchases) {
			purchaseItems.add(new PurchaseItem(purchase));
		}
		return new PurchaseListResponse(purchaseItems);
	}

	@Override
	public InfoResponse deletePurchase(String id) throws NotExistException {
		purchaseDataService.deletePurchaseById(id);
		return new InfoResponse();
	}
}
