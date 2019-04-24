package njurestaurant.njutakeout.blservice.business;

import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.business.BusinessImageListResponse;
import njurestaurant.njutakeout.response.business.BusinessImageResponse;

public interface BusinessImageBlService {
    InfoResponse add(String marketType,String position,String image);
    InfoResponse update(String id,String marketType,String position,String image) throws NotExistException;
    InfoResponse delete(String id);
    BusinessImageResponse findById(String id) throws NotExistException;
    BusinessImageListResponse findByMarketType(String marketType);
    BusinessImageListResponse getAll();
}

