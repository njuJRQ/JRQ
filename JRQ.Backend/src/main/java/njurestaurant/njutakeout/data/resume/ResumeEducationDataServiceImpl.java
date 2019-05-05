package njurestaurant.njutakeout.data.resume;

import njurestaurant.njutakeout.data.dao.resume.ResumeEducationDao;
import njurestaurant.njutakeout.dataservice.resume.ResumeEducationDataService;
import njurestaurant.njutakeout.entity.resume.ResumeEducation;
import njurestaurant.njutakeout.exception.NotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeEducationDataServiceImpl implements ResumeEducationDataService{
    private final ResumeEducationDao resumeEducationDao ;
    @Autowired
    public ResumeEducationDataServiceImpl(ResumeEducationDao resumeEducationDao) {
        this.resumeEducationDao = resumeEducationDao;
    }

    @Override
    public void add(ResumeEducation resumeEducation) {
        resumeEducationDao.save(resumeEducation);
    }

    @Override
    public ResumeEducation findById(String resumeEducation) throws NotExistException {
        return resumeEducationDao.findById(resumeEducation).get();
    }

    @Override
    public void update(ResumeEducation resumeEducation) {
        resumeEducationDao.save(resumeEducation);
    }

    @Override
    public List<ResumeEducation> getByResumeId(String ResumeId) {
        return resumeEducationDao.findByResumeId(ResumeId);
    }
}
