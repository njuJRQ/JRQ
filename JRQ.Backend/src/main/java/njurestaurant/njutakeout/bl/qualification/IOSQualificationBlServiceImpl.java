package njurestaurant.njutakeout.bl.qualification;

import njurestaurant.njutakeout.blservice.qualification.IOSQualificationBlService;
import njurestaurant.njutakeout.dataservice.qualification.IOSQualificationDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IOSQualificationBlServiceImpl implements IOSQualificationBlService {
    private final IOSQualificationDataService iosQualificationDataService;

    @Autowired
    public IOSQualificationBlServiceImpl(IOSQualificationDataService iosQualificationDataService) {
        this.iosQualificationDataService = iosQualificationDataService;
    }

    @Override
    public boolean getIOSQualification() {
        return iosQualificationDataService.getIOSQualificationById(1).isStatus();
    }
}
