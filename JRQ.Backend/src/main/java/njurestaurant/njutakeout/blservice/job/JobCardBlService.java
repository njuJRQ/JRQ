package njurestaurant.njutakeout.blservice.job;

import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.job.JobCardListResponse;
import njurestaurant.njutakeout.response.job.JobCardResponse;

public interface JobCardBlService {
    InfoResponse add(String position,String wage,String experienceRequirement,String degreeRequirement,String address,String hr,String enterpriseId) throws NotExistException;
    InfoResponse update(String id,String position,String wage,String experienceRequirement,String degreeRequirement,String address,String hr) throws NotExistException;
    InfoResponse deleteById(String id) throws NotExistException;
    JobCardResponse findById(String id) throws NotExistException;
    JobCardListResponse getAll();
    JobCardListResponse findByEnterprise(String enterpriseId) throws NotExistException;
}
