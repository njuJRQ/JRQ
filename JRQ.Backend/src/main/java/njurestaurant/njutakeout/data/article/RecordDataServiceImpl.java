package njurestaurant.njutakeout.data.article;

import njurestaurant.njutakeout.data.dao.article.RecordDao;
import njurestaurant.njutakeout.dataservice.article.RecordDataService;
import njurestaurant.njutakeout.entity.article.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordDataServiceImpl implements RecordDataService {
    private final RecordDao recordDao;

    @Autowired
    public RecordDataServiceImpl(RecordDao recordDao) {
        this.recordDao = recordDao;
    }

    @Override
    public void addRecord(Record record) {
        recordDao.save(record);
    }

    @Override
    public List<Record> getAllRecords() {
        return recordDao.findAll();
    }
}
