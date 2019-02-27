package njurestaurant.njutakeout.bl.count;

import njurestaurant.njutakeout.blservice.count.CountBlService;
import njurestaurant.njutakeout.dataservice.count.CountDataService;
import njurestaurant.njutakeout.entity.count.Count;
import njurestaurant.njutakeout.response.count.CountItem;
import njurestaurant.njutakeout.response.count.CountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountBlServiceImpl implements CountBlService {
    private final CountDataService countDataService;

    @Autowired
    public CountBlServiceImpl(CountDataService countDataService) {
        this.countDataService = countDataService;
    }

    @Override
    public CountResponse getCount(int id) {
        return new CountResponse(new CountItem(countDataService.getCountById(id)));
    }
}
