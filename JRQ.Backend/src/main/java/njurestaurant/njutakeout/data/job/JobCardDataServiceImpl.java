package njurestaurant.njutakeout.data.job;

import njurestaurant.njutakeout.data.dao.jobCard.JobCardDao;
import njurestaurant.njutakeout.dataservice.job.JobCardDataService;
import njurestaurant.njutakeout.entity.job.JobCard;
import njurestaurant.njutakeout.entity.user.User;
import njurestaurant.njutakeout.exception.NotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class JobCardDataServiceImpl implements JobCardDataService {
    private final JobCardDao jobCardDao;
    @Autowired
    public JobCardDataServiceImpl(JobCardDao jobCardDao){
        this.jobCardDao=jobCardDao;
    }
    @Override
    public void add(JobCard jobCard) {
        jobCardDao.save(jobCard);

    }

    @Override
    public void update(JobCard jobCard) {
        jobCardDao.save(jobCard);
    }

    @Override
    public void deleteById(String id) throws NotExistException {
        if(jobCardDao.findById(id).isPresent()){
            jobCardDao.deleteById(id);
        }else{
            throw new NotExistException("JobCard ID",id);
        }

    }

    @Override
    public JobCard findById(String id) throws NotExistException {
        Optional<JobCard> optionalJobCard=jobCardDao.findById(id);
        if(optionalJobCard.isPresent()){
            return optionalJobCard.get();
        }else{
            throw new NotExistException("JobCard ID",id);
        }
    }

    @Override
    public List<JobCard> getAll() {
        return jobCardDao.findAll();
    }

    @Override
    public List<JobCard> findByUser(User user) {
        return jobCardDao.findByUser(user);
    }

    @Override
    public List<JobCard> findByExpectPosition(String expectPosition) {
        return jobCardDao.findByExpectPosition(expectPosition);
    }
}
