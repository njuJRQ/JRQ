package njurestaurant.njutakeout.blservice.article;

import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.article.record.RecordListResponse;

public interface RecordBlService {
    /**
     * 增加某条日志记录
     * @param content 记录内容
     * @return 成功与否
     */
    InfoResponse addRecord(String content);

    /**
     * 获取日志记录列表
     * @return
     */
    RecordListResponse getRecordList();
}
