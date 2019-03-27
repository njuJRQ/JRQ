package njurestaurant.njutakeout.dataservice.business;

import njurestaurant.njutakeout.entity.business.Business;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.publicdatas.business.ProjectRef;
import njurestaurant.njutakeout.publicdatas.business.MarketType;

import java.util.List;

public interface BusinessDataService {
    void add(Business business);
    void update(Business business);
    void deleteById(String id) throws NotExistException;
    List<Business> getAll();
    List<Business> findByProjectRef(ProjectRef projectRef);
    List<Business> findByMarketType(MarketType marketType);
    Business findById(String id) throws NotExistException;
}
