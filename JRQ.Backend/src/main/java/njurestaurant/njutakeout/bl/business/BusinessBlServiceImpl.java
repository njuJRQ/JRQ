package njurestaurant.njutakeout.bl.business;

import njurestaurant.njutakeout.blservice.business.BusinessBlService;
import njurestaurant.njutakeout.dataservice.business.BusinessDataService;
import njurestaurant.njutakeout.entity.business.Business;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.publicdatas.business.MarketType;
import njurestaurant.njutakeout.publicdatas.business.ProjectRef;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.business.BusinessItem;
import njurestaurant.njutakeout.response.business.BusinessListResponse;
import njurestaurant.njutakeout.response.business.BusinessResponse;
import org.apache.commons.collections.list.AbstractLinkedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class BusinessBlServiceImpl implements BusinessBlService {
    private BusinessDataService businessDataService;
    @Autowired
    public BusinessBlServiceImpl(BusinessDataService businessDataService){
        this.businessDataService=businessDataService;
    }
    @Override
    public InfoResponse add(String linkMan, String phone, String agencyName, String projectInfo,String projectRef,String marketType, String writerOpenid) {
        businessDataService.add(new Business(linkMan,phone,agencyName,projectInfo,ProjectRef.valueOf(projectRef),MarketType.valueOf(marketType),writerOpenid));
        return new InfoResponse();
    }

    @Override
    public InfoResponse update(String id, String linkMan, String phone, String agencyName, String projectInfo, String projectRef,String marketType) throws NotExistException {
        Business business=businessDataService.findById(id);
        business.setLinkMan(linkMan);
        business.setPhone(phone);
        business.setAgencyName(agencyName);
        business.setProjectInfo(projectInfo);
        business.setProjectRef(ProjectRef.valueOf(projectRef));
        business.setMarketType(MarketType.valueOf(marketType));
        businessDataService.update(business);
        return new InfoResponse();
    }

    @Override
    public InfoResponse deleteById(String id) throws NotExistException {
        businessDataService.deleteById(id);
        return new InfoResponse();
    }

    @Override
    public BusinessListResponse getAll() {
        List<Business> businessList=businessDataService.getAll();
        List<BusinessItem> businessItems=new ArrayList<>();
        if(businessList!=null && businessList.size()>0){
            for(Business temp:businessList){
                businessItems.add(new BusinessItem(temp));
            }
        }
        return new BusinessListResponse(businessItems);
    }

    @Override
    public BusinessListResponse findByProjectRef(String projectRef) {
        List<Business> businessList=businessDataService.findByProjectRef(ProjectRef.valueOf(projectRef));
        List<BusinessItem> businessItems=new ArrayList<>();
        if(businessList!=null && businessList.size()>0){
            for(Business temp:businessList){
                businessItems.add(new BusinessItem(temp));
            }
        }
        return new BusinessListResponse(businessItems);
    }

    @Override
    public BusinessListResponse findByMarketType(String marketType) {
        List<Business> businessList=businessDataService.findByMarketType(MarketType.valueOf(marketType));
        List<BusinessItem> businessItems=new ArrayList<>();
        if(businessList!=null && businessList.size()>0){
            for(Business temp:businessList){
                businessItems.add(new BusinessItem(temp));
            }
        }
        return new BusinessListResponse(businessItems);
    }

    @Override
    public BusinessResponse findById(String id) throws NotExistException {
        return new BusinessResponse(new BusinessItem(businessDataService.findById(id)));
    }
}
