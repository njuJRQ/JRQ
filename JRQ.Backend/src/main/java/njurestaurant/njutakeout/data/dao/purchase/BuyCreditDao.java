package njurestaurant.njutakeout.data.dao.purchase;

import njurestaurant.njutakeout.entity.purchase.BuyCredit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuyCreditDao extends JpaRepository<BuyCredit, String> {
	List<BuyCredit> findBuyCreditsByOpenid(String openid);
}
