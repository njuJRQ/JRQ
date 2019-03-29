package njurestaurant.njutakeout.data.dao.business;

import njurestaurant.njutakeout.entity.business.BusinessImage;
import njurestaurant.njutakeout.publicdatas.business.MarketType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BusinessImageDao extends JpaRepository<BusinessImage,String> {
    List<BusinessImage> findByMarketType(MarketType marketType);
}
