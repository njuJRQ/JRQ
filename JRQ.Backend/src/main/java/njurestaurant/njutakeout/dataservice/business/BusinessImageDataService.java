package njurestaurant.njutakeout.dataservice.business;

import njurestaurant.njutakeout.entity.business.Business;
import njurestaurant.njutakeout.entity.business.BusinessImage;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.publicdatas.business.MarketType;

import java.util.List;

public interface BusinessImageDataService {
    void add(BusinessImage businessImage);
    void update(BusinessImage businessImage);
    void delete(String id);
    BusinessImage findById(String id) throws NotExistException;
    List<BusinessImage> findByMarketType(MarketType marketType);
    List<BusinessImage> getAll();
}
