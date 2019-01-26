package njurestaurant.njutakeout.data.count;

import njurestaurant.njutakeout.data.dao.count.CountDao;
import njurestaurant.njutakeout.dataservice.count.CountDataService;
import njurestaurant.njutakeout.entity.count.Count;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountDataServiceImpl implements CountDataService {
    private final CountDao countDao;

    @Autowired
    public CountDataServiceImpl(CountDao countDao) {
        this.countDao = countDao;
    }

    @Override
    public void saveCount(Count count) {
        countDao.save(count);
    }

    @Override
    public Count getCountById(int id)  {
        return countDao.getCountById(id);
    }
}
