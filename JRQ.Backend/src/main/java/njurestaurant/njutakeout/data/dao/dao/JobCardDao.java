package njurestaurant.njutakeout.data.dao.dao;

import njurestaurant.njutakeout.entity.job.JobCard;
import njurestaurant.njutakeout.entity.user.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobCardDao extends JpaRepository<JobCard,String> {
    List<JobCard> findByEnterprise(Enterprise enterprise);
}
