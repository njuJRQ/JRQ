package njurestaurant.njutakeout.data.dao.resume;

import njurestaurant.njutakeout.entity.resume.ResumeEducation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResumeEducationDao extends JpaRepository<ResumeEducation, String> {
    public List<ResumeEducation> findByResumeId(String resumeId);

}
