package njurestaurant.njutakeout.blservice.partnership;

import njurestaurant.njutakeout.entity.partnership.IdentityImage;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.partnership.PartnershipListResponse;
import njurestaurant.njutakeout.response.partnership.PartnershipResponse;

import java.util.List;
import java.util.Map;

public interface PartnershipBlService {
    InfoResponse add(String linkMan, String phone, String agencyName, String identityInfo, String type,List<IdentityImage> img);
    InfoResponse update(String id,String linkMan, String phone, String agencyName, String identityInfo, String type, List<IdentityImage> img) throws NotExistException;
    PartnershipResponse findById(String id) throws NotExistException;
    PartnershipListResponse getAll();

}
