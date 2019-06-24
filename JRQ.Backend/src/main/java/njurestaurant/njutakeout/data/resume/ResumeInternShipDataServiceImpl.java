package njurestaurant.njutakeout.data.resume;

import njurestaurant.njutakeout.data.dao.resume.ResumeInternShipDao;
import njurestaurant.njutakeout.dataservice.resume.ResumeInternShipDataService;
import njurestaurant.njutakeout.entity.resume.ResumeInternShip;
import njurestaurant.njutakeout.exception.NotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeInternShipDataServiceImpl implements ResumeInternShipDataService {
    private final ResumeInternShipDao resumeInternShipDao ;
    @Autowired
    public ResumeInternShipDataServiceImpl(ResumeInternShipDao resumeInternShipDao) {
        this.resumeInternShipDao = resumeInternShipDao;
    }

    @Override
    public void add(ResumeInternShip resumeInternShip) {
        resumeInternShipDao.save(resumeInternShip);
    }


    @Override
    public ResumeInternShip findById(String resumeInternShip) throws NotExistException {
        return resumeInternShipDao.findById(resumeInternShip).get();
    }


    @Override
    public void update(ResumeInternShip resumeInternShip) {
        resumeInternShipDao.save(resumeInternShip);
    }

    @Override
    public List<ResumeInternShip> getByResumeId(String ResumeId) {
        return resumeInternShipDao.findByResumeId(ResumeId);
    }
}
