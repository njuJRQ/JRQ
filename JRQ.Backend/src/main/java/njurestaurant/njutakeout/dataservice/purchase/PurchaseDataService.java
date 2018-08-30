package njurestaurant.njutakeout.dataservice.purchase;

import njurestaurant.njutakeout.entity.purchase.Purchase;
import njurestaurant.njutakeout.exception.NotExistException;

import java.util.List;

public interface PurchaseDataService {

	void addPurchase(Purchase purchase);

	Purchase getPurchaseById(String id) throws NotExistException;

	List<Purchase> getPurchasesByOpenid(String openid);

	List<Purchase> getAllPurchases();

	void deletePurchaseById(String id) throws NotExistException;
}
