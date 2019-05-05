package njurestaurant.njutakeout.data.resume;

import njurestaurant.njutakeout.data.dao.resume.ResumeDao;
import njurestaurant.njutakeout.dataservice.resume.ResumeDataService;
import njurestaurant.njutakeout.entity.resume.Resume;
import njurestaurant.njutakeout.exception.NotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ResumeDataServiceImpl implements ResumeDataService {
    private final ResumeDao resumeDao;
    @Autowired
    public ResumeDataServiceImpl(ResumeDao resumeDao) {
        this.resumeDao = resumeDao;
    }

    @Override
    public void add(Resume resume) {
        resumeDao.save(resume);


    }

    @Override
    public void deleteById(String resumeId) throws NotExistException {
        resumeDao.deleteById(resumeId);
    }

    @Override
    public Resume findById(String resumeId) throws NotExistException {
        Optional<Resume> optionalResume= resumeDao.findById(resumeId);
        if(optionalResume.isPresent())
            return optionalResume.get();
        else
            throw new NotExistException("Resume ID", resumeId);
    }

    @Override
    public void update(Resume resume) {
        resumeDao.save(resume);
    }

    @Override
    public List<Resume> getAll() {
        return resumeDao.findAll();
    }

    @Override
    public List<Resume> getByUserId(String UserId) {
        return resumeDao.findByUserId(UserId);
    }
}
