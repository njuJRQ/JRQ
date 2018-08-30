package njurestaurant.njutakeout.bl.purchase;

import njurestaurant.njutakeout.blservice.purchase.PurchaseBlService;
import njurestaurant.njutakeout.dataservice.purchase.PurchaseDataService;
import njurestaurant.njutakeout.entity.purchase.Purchase;
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

	@Autowired
	public PurchaseBlServiceImpl(PurchaseDataService purchaseDataService) {
		this.purchaseDataService = purchaseDataService;
	}

	@Override
	public InfoResponse addPurchase(String openid, String type, String detail, int price, String date) {
		purchaseDataService.addPurchase(new Purchase(openid, type, detail, price, date));
		return new InfoResponse();
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
