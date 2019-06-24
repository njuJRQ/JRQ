package njurestaurant.njutakeout.data.dao.resume;
import njurestaurant.njutakeout.entity.resume.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResumeDao extends JpaRepository<Resume, String> {
    List<Resume> findByUserId(String userId);
}
