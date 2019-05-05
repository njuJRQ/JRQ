package njurestaurant.njutakeout.data.dao.resume;

import njurestaurant.njutakeout.entity.resume.ResumeInternShip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResumeInternShipDao extends JpaRepository<ResumeInternShip, String> {
    public List<ResumeInternShip> findByResumeId(String resumeId);

}
