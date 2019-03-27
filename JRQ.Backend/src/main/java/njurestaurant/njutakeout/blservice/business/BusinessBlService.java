package njurestaurant.njutakeout.blservice.business;

import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.business.BusinessListResponse;
import njurestaurant.njutakeout.response.business.BusinessResponse;

public interface BusinessBlService {
    InfoResponse add(String linkMan,String phone,String agencyName,String projectInfo,String projectRef,String marketType,String writerOpenid);
    InfoResponse update(String id,String linkMan,String phone,String agencyName,String projectInfo,String projectRef,String marketType) throws NotExistException;
    InfoResponse deleteById(String id) throws NotExistException;
    BusinessListResponse getAll();
    BusinessListResponse findByProjectRef(String projectRef);
    BusinessListResponse findByMarketType(String marketType);
    BusinessResponse findById(String id) throws NotExistException;
}
