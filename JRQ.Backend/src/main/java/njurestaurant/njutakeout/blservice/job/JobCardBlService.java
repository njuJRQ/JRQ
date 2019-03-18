package njurestaurant.njutakeout.blservice.job;

import njurestaurant.njutakeout.entity.user.User;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.job.JobCardListResponse;
import njurestaurant.njutakeout.response.job.JobCardResponse;

public interface JobCardBlService {
    InfoResponse add(String expectPosition,String expectWage,String experience,String degree,String introduction,String openid) throws NotExistException;
    InfoResponse update(String id,String expectPosition,String expectWage,String experience,String degree,String introduction) throws NotExistException;
    InfoResponse deleteById(String id) throws NotExistException;
    JobCardResponse findById(String id) throws NotExistException;
    JobCardListResponse getAll();
    JobCardListResponse findByUser(String openid) throws NotExistException;
    JobCardListResponse findByExpectPosition(String expectPosition);
}
