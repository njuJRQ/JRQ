package njurestaurant.njutakeout.dataservice.resume;

import njurestaurant.njutakeout.entity.resume.Resume;
import njurestaurant.njutakeout.entity.resume.ResumeEducation;
import njurestaurant.njutakeout.entity.resume.ResumeInternShip;
import njurestaurant.njutakeout.exception.NotExistException;

import java.util.List;

public interface ResumeInternShipDataService {
    void add(ResumeInternShip resumeInternShip);
     ResumeInternShip findById(String resumeInternShip) throws NotExistException;
    void update(ResumeInternShip resumeInternShip);
    List<ResumeInternShip> getByResumeId(String ResumeId);



}
