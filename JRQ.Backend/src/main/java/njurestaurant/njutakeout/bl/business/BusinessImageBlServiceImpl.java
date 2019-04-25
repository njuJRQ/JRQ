package njurestaurant.njutakeout.bl.business;

import njurestaurant.njutakeout.blservice.business.BusinessImageBlService;
import njurestaurant.njutakeout.dataservice.business.BusinessImageDataService;
import njurestaurant.njutakeout.entity.business.BusinessImage;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.publicdatas.business.MarketType;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.business.BusinessImageItem;
import njurestaurant.njutakeout.response.business.BusinessImageListResponse;
import njurestaurant.njutakeout.response.business.BusinessImageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessImageBlServiceImpl implements BusinessImageBlService {
    private final BusinessImageDataService businessImageDataService;
    @Autowired
    public BusinessImageBlServiceImpl(BusinessImageDataService businessImageDataService){
        this.businessImageDataService=businessImageDataService;
    }

    @Override
    public InfoResponse add(String marketType, String position, String image) {
        businessImageDataService.add(new BusinessImage(MarketType.valueOf(marketType),position,image));
        return new InfoResponse();
    }

    @Override
    public InfoResponse update(String id, String marketType,String position,String image) throws NotExistException {
        BusinessImage businessImage=businessImageDataService.findById(id);
        MarketType m=MarketType.valueOf(marketType);
        businessImage.setMarketType(m);
        businessImage.setPosition(position);
        businessImage.setImage(image);
        businessImageDataService.update(businessImage);
        return new InfoResponse();
    }

    @Override
    public InfoResponse delete(String id) {
        businessImageDataService.delete(id);
        return new InfoResponse();
    }

    @Override
    public BusinessImageResponse findById(String id) throws NotExistException {
       return new BusinessImageResponse(new BusinessImageItem(businessImageDataService.findById(id)));
    }

    @Override
    public BusinessImageListResponse findByMarketType(String marketType){
        List<BusinessImage> businessImages=businessImageDataService.findByMarketType(MarketType.valueOf(marketType));
        List<BusinessImageItem> businessImageItems=new ArrayList<>();
        if(businessImages!=null && businessImages.size()>0){
            for(BusinessImage businessImage:businessImages){
                businessImageItems.add(new BusinessImageItem(businessImage));
            }
        }
        return new BusinessImageListResponse(businessImageItems);
    }

    @Override
    public BusinessImageListResponse getAll() {
        List<BusinessImage> businessImageList=businessImageDataService.getAll();
        List<BusinessImageItem> businessImageItems=new ArrayList<>();
        if(businessImageList!=null && businessImageList.size()>0){
            for(BusinessImage businessImage:businessImageList){
                businessImageItems.add(new BusinessImageItem(businessImage));
            }
        }
        return new BusinessImageListResponse(businessImageItems);
    }
}
