package njurestaurant.njutakeout.data.dao.job;

import njurestaurant.njutakeout.entity.job.JobCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobCardDao extends JpaRepository<JobCard, String> {
    List<JobCard> findByExpectPosition(String expectPosition);

    List<JobCard> findByCity(String city);
}
