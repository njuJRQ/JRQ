package njurestaurant.njutakeout.dataservice.count;

import njurestaurant.njutakeout.entity.count.Count;

public interface CountDataService {
    void saveCount(Count count);
    Count getCountById(int id);
}
