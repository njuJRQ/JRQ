package njurestaurant.njutakeout.data.business;

import njurestaurant.njutakeout.data.dao.business.BusinessDao;
import njurestaurant.njutakeout.dataservice.business.BusinessDataService;
import njurestaurant.njutakeout.entity.business.Business;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.publicdatas.business.ProjectRef;
import njurestaurant.njutakeout.publicdatas.business.MarketType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BusinessDataServiceImpl implements BusinessDataService {
    private final BusinessDao businessDao;
    @Autowired
    public BusinessDataServiceImpl(BusinessDao businessDao){
        this.businessDao=businessDao;
    }
    @Override
    public void add(Business business) {
        businessDao.save(business);
    }

    @Override
    public void update(Business business) {
        businessDao.save(business);
    }

    @Override
    public void deleteById(String id) throws NotExistException {
        if(businessDao.findById(id).isPresent()){
            businessDao.deleteById(id);
        }else{
            throw new NotExistException("BusinessBlService ID",id);
        }
    }

    @Override
    public List<Business> getAll() {
        return businessDao.findAll();
    }

    @Override
    public List<Business> findByProjectRef(ProjectRef projectRef) {
        return businessDao.findByProjectRef(projectRef);
    }

    @Override
    public List<Business> findByMarketType(MarketType marketType) {
        return businessDao.findByMarketType(marketType);
    }

    @Override
    public Business findById(String id) throws NotExistException {
        Optional<Business> optionalBusiness=businessDao.findById(id);
        if(optionalBusiness.isPresent()){
            return optionalBusiness.get();
        }else{
            throw  new NotExistException("BusinessBlService ID",id);
        }
    }
}
