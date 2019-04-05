package njurestaurant.njutakeout.dataservice.job;

import njurestaurant.njutakeout.entity.job.JobCard;
import njurestaurant.njutakeout.entity.user.Enterprise;
import njurestaurant.njutakeout.entity.user.User;
import njurestaurant.njutakeout.exception.NotExistException;

import java.util.List;

public interface JobCardDataService {
    void add(JobCard jobCard);
    void update(JobCard jobCard);
    void deleteById(String id) throws NotExistException;
    JobCard findById(String id) throws NotExistException;
    List<JobCard> getAll();
    List<JobCard> findByUser(User user);
    List<JobCard> findByExpectPosition(String expectPosition);

    List<JobCard> findByCity(String city);
}
