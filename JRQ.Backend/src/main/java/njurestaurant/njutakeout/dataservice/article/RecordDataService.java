package njurestaurant.njutakeout.dataservice.article;

import njurestaurant.njutakeout.entity.article.Record;

import java.util.List;

public interface RecordDataService {
    void addRecord(Record record);
    List<Record> getAllRecords();
}
