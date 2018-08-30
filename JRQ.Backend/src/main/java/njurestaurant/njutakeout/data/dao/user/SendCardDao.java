package njurestaurant.njutakeout.data.dao.user;

import njurestaurant.njutakeout.entity.user.SendCard;
import njurestaurant.njutakeout.entity.user.SendCardKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SendCardDao extends JpaRepository<SendCard, SendCardKey> {
	List<SendCard> findAllByReceiverOpenid(String openid);
	List<SendCard> findAllBySenderOpenid(String openid);
}
