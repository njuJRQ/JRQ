package njurestaurant.njutakeout.blservice.resume;

import njurestaurant.njutakeout.entity.resume.Resume;
import njurestaurant.njutakeout.entity.resume.ResumeEducation;
import njurestaurant.njutakeout.entity.resume.ResumeInternShip;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.resume.ResumeListResponce;
import njurestaurant.njutakeout.response.resume.ResumeResponce;

import java.util.List;

public interface ResumeBlService {
    InfoResponse add(Resume resume)throws NotExistException;

    InfoResponse update(Resume resume)throws NotExistException;

    InfoResponse deleteById(String resumeId) throws NotExistException;
    ResumeResponce findById(String id) throws NotExistException;
    ResumeListResponce getAll();
    ResumeListResponce findByUserId(String userId);


}
