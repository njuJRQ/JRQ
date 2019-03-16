package njurestaurant.njutakeout.data.partnership;

import io.swagger.annotations.ApiImplicitParam;
import njurestaurant.njutakeout.data.dao.partnership.PartnershipDao;
import njurestaurant.njutakeout.dataservice.partnership.PartnershipDataService;
import njurestaurant.njutakeout.entity.partnership.Partnership;
import njurestaurant.njutakeout.exception.NotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartnershipDataServiceImpl implements PartnershipDataService {
    private final PartnershipDao partnershipDao;
    @Autowired
    public PartnershipDataServiceImpl(PartnershipDao partnershipDao){
        this.partnershipDao=partnershipDao;
    }
    @Override
    public void add(Partnership partnership) {
        partnershipDao.save(partnership);
    }

    @Override
    public void update(Partnership partnership) {
        partnershipDao.save(partnership);
    }

    @Override
    public void deleteById(String id) throws NotExistException {
        if(partnershipDao.findById(id).isPresent()){
            partnershipDao.deleteById(id);
        }{
            throw new NotExistException("Partnership ID",id);
        }
    }

    @Override
    public Partnership findById(String id) throws NotExistException {
        Optional<Partnership> optionalPartnership=partnershipDao.findById(id);
        if(optionalPartnership.isPresent()){
            return optionalPartnership.get();
        }else{
            throw new NotExistException("Partnership ID",id);
        }
    }

    @Override
    public List<Partnership> getAll() {
        return partnershipDao.findAll();
    }
}
