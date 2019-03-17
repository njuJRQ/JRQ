package njurestaurant.njutakeout.bl.partnership;

import njurestaurant.njutakeout.blservice.partnership.PartnershipBlService;
import njurestaurant.njutakeout.dataservice.partnership.PartnershipDataService;
import njurestaurant.njutakeout.entity.partnership.Partnership;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.publicdatas.partnership.PartnerType;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.partnership.PartnershipItem;
import njurestaurant.njutakeout.response.partnership.PartnershipListResponse;
import njurestaurant.njutakeout.response.partnership.PartnershipResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PartnershipBlServiceImpl implements PartnershipBlService {
    private final PartnershipDataService partnershipDataService;
    @Autowired
    public PartnershipBlServiceImpl(PartnershipDataService partnershipDataService){
        this.partnershipDataService=partnershipDataService;
    }
    @Override
    public InfoResponse add(String linkMan, String phone, String agencyName, String identityInfo, String type, List<String> img) {
        partnershipDataService.add(new Partnership(linkMan,phone,agencyName,identityInfo,PartnerType.valueOf(type),img));
        return new InfoResponse();
    }

    @Override
    public InfoResponse update(String id, String linkMan, String phone, String agencyName, String identityInfo, String type, List<String> img) throws NotExistException {
        Partnership partnership=partnershipDataService.findById(id);
        partnership.setLinkMan(linkMan);
        partnership.setPhone(phone);
        partnership.setAgencyName(agencyName);
        partnership.setIdentityInfo(identityInfo);
        partnership.setType(PartnerType.valueOf(type));
        partnership.setImages(img);
        partnershipDataService.update(partnership);
        return new InfoResponse();
    }

    @Override
    public PartnershipResponse findById(String id) throws NotExistException {
        return new PartnershipResponse(new PartnershipItem(partnershipDataService.findById(id)));
    }

    @Override
    public PartnershipListResponse getAll() {
        List<Partnership> partnerships=partnershipDataService.getAll();
        List<PartnershipItem> partnershipItems=new ArrayList<>();
        if(partnerships!=null && partnerships.size()>0){
            for(Partnership partnership:partnerships){
                partnershipItems.add(new PartnershipItem(partnership));
            }
        }
        return new PartnershipListResponse(partnershipItems);
    }
}
