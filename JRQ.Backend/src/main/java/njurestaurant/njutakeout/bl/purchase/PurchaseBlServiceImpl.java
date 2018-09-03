package njurestaurant.njutakeout.bl.purchase;

import njurestaurant.njutakeout.blservice.purchase.PurchaseBlService;
import njurestaurant.njutakeout.dataservice.purchase.PurchaseDataService;
import njurestaurant.njutakeout.dataservice.user.LevelDataService;
import njurestaurant.njutakeout.dataservice.user.UserDataService;
import njurestaurant.njutakeout.entity.purchase.Purchase;
import njurestaurant.njutakeout.entity.user.Level;
import njurestaurant.njutakeout.entity.user.User;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.purchase.PurchaseItem;
import njurestaurant.njutakeout.response.purchase.PurchaseListResponse;
import njurestaurant.njutakeout.response.purchase.PurchaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseBlServiceImpl implements PurchaseBlService {
	private final PurchaseDataService purchaseDataService;
	private final UserDataService userDataService;
	private final LevelDataService levelDataService;

	@Autowired
	public PurchaseBlServiceImpl(PurchaseDataService purchaseDataService, UserDataService userDataService, LevelDataService levelDataService) {
		this.purchaseDataService = purchaseDataService;
		this.userDataService = userDataService;
		this.levelDataService = levelDataService;
	}

	@Override
	public boolean addPurchase(String openid, String type, String detail, int price, String date) throws NotExistException {
		User user = userDataService.getUserByOpenid(openid);
		if (user.getCredit()>=price) {
			user.setCredit(user.getCredit()-price); //付款
			purchaseDataService.addPurchase(new Purchase(openid, type, detail, price, date)); //记录订单
			switch (type) {
				case "level":
					int used = levelDataService.getLevelByName(user.getLevelName()).getCardLimit() - user.getCardLimit();
					user.setLevelName(detail);
					user.setCardLimit(levelDataService.getLevelByName(detail).getCardLimit() - used); //更新权限
					userDataService.saveUser(user);
					break;
				case "course":
					//这里只需记录订单，之后显示course时会查订单表
					break;
				default:
					throw new NotExistException("Purchase type", type);
			}
			return true;
		} else {
			return false;
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
