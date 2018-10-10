package njurestaurant.njutakeout.dataservice.purchase;

import njurestaurant.njutakeout.entity.purchase.BuyCredit;
import njurestaurant.njutakeout.exception.NotExistException;

import java.util.List;

public interface BuyCreditDataService {

	void saveBuyCredit(BuyCredit buyCredit);

	void addBuyCredit(BuyCredit buyCredit);

	BuyCredit getBuyCreditById(String id) throws NotExistException;

	List<BuyCredit> getBuyCreditByOpenid(String openid);

	List<BuyCredit> getAllBuyCredits();

	void deleteBuyCreditById(String id) throws NotExistException;
}
