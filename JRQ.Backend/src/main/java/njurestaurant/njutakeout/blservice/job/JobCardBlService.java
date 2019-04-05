package njurestaurant.njutakeout.blservice.job;

import njurestaurant.njutakeout.entity.job.EducationExperience;
import njurestaurant.njutakeout.entity.job.WorkExperience;
import njurestaurant.njutakeout.entity.user.User;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.job.JobCardListResponse;
import njurestaurant.njutakeout.response.job.JobCardResponse;

import java.util.List;

public interface JobCardBlService {
    InfoResponse add(String photo, String expectPosition, String expectWage, String degree, String introduction, boolean isFresh, int age, List<WorkExperience> workExperienceList, List<EducationExperience> educationExperienceList, String openid) throws NotExistException;
    InfoResponse update(String id,String photo,String expectPosition,String expectWage,String degree,String introduction, boolean isFresh, int age, List<WorkExperience> workExperienceList, List<EducationExperience> educationExperienceList) throws NotExistException;
    InfoResponse deleteById(String id) throws NotExistException;
    JobCardResponse findById(String id) throws NotExistException;
    JobCardListResponse getAll();
    JobCardListResponse findByUser(String openid) throws NotExistException;
    JobCardListResponse findByExpectPosition(String expectPosition);

    JobCardListResponse findByCity(String city);
}
