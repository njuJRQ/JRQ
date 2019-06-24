package njurestaurant.njutakeout.dataservice.resume;
import njurestaurant.njutakeout.entity.resume.Resume;
import njurestaurant.njutakeout.entity.resume.ResumeEducation;
import njurestaurant.njutakeout.exception.NotExistException;
import java.util.List;
public interface ResumeEducationDataService {
    void add(ResumeEducation resumeEducation);
    ResumeEducation findById(String resumeEducation) throws NotExistException;
    void update(ResumeEducation resumeEducation);
    List<ResumeEducation> getByResumeId(String ResumeId);



}
