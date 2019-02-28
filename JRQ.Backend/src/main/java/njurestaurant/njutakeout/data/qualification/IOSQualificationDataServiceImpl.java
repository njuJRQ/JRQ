package njurestaurant.njutakeout.data.qualification;

import njurestaurant.njutakeout.data.dao.count.CountDao;
import njurestaurant.njutakeout.data.dao.qualification.IOSQualificationDao;
import njurestaurant.njutakeout.dataservice.count.CountDataService;
import njurestaurant.njutakeout.dataservice.qualification.IOSQualificationDataService;
import njurestaurant.njutakeout.entity.qualification.IOSQualification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IOSQualificationDataServiceImpl implements IOSQualificationDataService {
    private final IOSQualificationDao iosQualificationDao;

    @Autowired
    public IOSQualificationDataServiceImpl(IOSQualificationDao iosQualificationDao) {
        this.iosQualificationDao = iosQualificationDao;
    }

    @Override
    public IOSQualification getIOSQualificationById(int id)  {
        return iosQualificationDao.getIOSQualificationById(id);
    }
}
