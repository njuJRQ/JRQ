package njurestaurant.njutakeout.data.dao.business;

import njurestaurant.njutakeout.entity.business.Business;
import njurestaurant.njutakeout.publicdatas.business.ProjectRef;
import njurestaurant.njutakeout.publicdatas.business.MarketType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusinessDao extends JpaRepository<Business,String> {
    List<Business> findByMarketType(MarketType marketType);
    List<Business> findByProjectRef(ProjectRef projectRef);
}
