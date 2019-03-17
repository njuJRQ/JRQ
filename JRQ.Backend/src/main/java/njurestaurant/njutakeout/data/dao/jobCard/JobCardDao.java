package njurestaurant.njutakeout.data.dao.jobCard;

import njurestaurant.njutakeout.entity.job.JobCard;
import njurestaurant.njutakeout.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobCardDao extends JpaRepository<JobCard,String> {
    List<JobCard> findByUser(User user);
    List<JobCard> findByExpectPosition(String expectPosition);
}
