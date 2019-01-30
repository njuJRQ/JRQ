package njurestaurant.njutakeout.blservice.count;

import njurestaurant.njutakeout.entity.count.Count;
import njurestaurant.njutakeout.response.count.CountResponse;

public interface CountBlService {
    /**
     * 根据ID获取各模块浏览次数(Admin)
     * @param id ID=1
     * @return 各模块浏览次数
     */
    CountResponse getCount(int id);
}
