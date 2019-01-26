package njurestaurant.njutakeout.bl.article;

import njurestaurant.njutakeout.blservice.article.RecordBlService;
import njurestaurant.njutakeout.dataservice.article.RecordDataService;
import njurestaurant.njutakeout.entity.article.Course;
import njurestaurant.njutakeout.entity.article.Record;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.article.course.CourseItem;
import njurestaurant.njutakeout.response.article.course.CourseListResponse;
import njurestaurant.njutakeout.response.article.record.RecordItem;
import njurestaurant.njutakeout.response.article.record.RecordListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecordBlServiceImpl implements RecordBlService {
    private final RecordDataService recordDataService;

    @Autowired
    public RecordBlServiceImpl(RecordDataService recordDataService) {
        this.recordDataService = recordDataService;
    }

    @Override
    public InfoResponse addRecord(String content) {
        recordDataService.addRecord(new Record(content));
        return new InfoResponse();
    }

    @Override
    public RecordListResponse getRecordList() {
        List<Record> records = recordDataService.getAllRecords();
        List<RecordItem> recordItems = new ArrayList<>();
        for (Record record:records) {
            recordItems.add(new RecordItem(record));
        }
        return new RecordListResponse(recordItems);
    }
}
