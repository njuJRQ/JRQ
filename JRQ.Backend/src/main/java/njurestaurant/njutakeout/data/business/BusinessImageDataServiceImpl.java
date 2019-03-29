package njurestaurant.njutakeout.data.business;

import njurestaurant.njutakeout.data.dao.business.BusinessImageDao;
import njurestaurant.njutakeout.dataservice.business.BusinessDataService;
import njurestaurant.njutakeout.dataservice.business.BusinessImageDataService;
import njurestaurant.njutakeout.entity.business.BusinessImage;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.publicdatas.business.MarketType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.util.List;
import java.util.Optional;

@Service
public class BusinessImageDataServiceImpl implements BusinessImageDataService {
    private final BusinessImageDao businessImageDao;
    @Autowired
    public BusinessImageDataServiceImpl(BusinessImageDao businessImageDao){
        this.businessImageDao=businessImageDao;
    }
    @Override
    public void add(BusinessImage businessImage) {
        businessImageDao.save(businessImage);
    }

    @Override
    public void update(BusinessImage businessImage) {
        businessImageDao.save(businessImage);
    }

    @Override
    public void delete(String id) {
        businessImageDao.deleteById(id);
    }

    @Override
    public BusinessImage findById(String id) throws NotExistException {
        Optional<BusinessImage> optionalBusinessImage=businessImageDao.findById(id);
        if(optionalBusinessImage.isPresent()){
            return optionalBusinessImage.get();
        }else{
            throw new NotExistException("Business Image ID:",id);
        }
    }

    @Override
    public List<BusinessImage> findByMarketType(MarketType marketType){
        return businessImageDao.findByMarketType(marketType);
    }

    @Override
    public List<BusinessImage> getAll() {
        return businessImageDao.findAll();
    }
}
