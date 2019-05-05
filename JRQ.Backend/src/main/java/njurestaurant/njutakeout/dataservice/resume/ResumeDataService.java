package njurestaurant.njutakeout.dataservice.resume;

import njurestaurant.njutakeout.entity.cardImage.CardImage;
import njurestaurant.njutakeout.entity.job.JobCard;
import njurestaurant.njutakeout.entity.resume.Resume;
import njurestaurant.njutakeout.exception.NotExistException;

import java.util.List;

public interface ResumeDataService {
    void add(Resume resume);
    void deleteById(String resumeId) throws NotExistException;
    Resume findById(String resumeId) throws NotExistException;
    void update(Resume resume);
    List<Resume> getAll();
    List<Resume> getByUserId(String UserId);



}
