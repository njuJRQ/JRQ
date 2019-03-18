package njurestaurant.njutakeout.dataservice.partnership;

import njurestaurant.njutakeout.entity.partnership.Partnership;
import njurestaurant.njutakeout.exception.NotExistException;

import java.util.List;

public interface PartnershipDataService {
    void add(Partnership partnership);
    void update(Partnership partnership);
    void deleteById(String id) throws NotExistException;
    Partnership findById(String id) throws NotExistException;
    List<Partnership> getAll();
}
