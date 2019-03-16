package njurestaurant.njutakeout.bl.job;

import njurestaurant.njutakeout.blservice.job.JobCardBlService;
import njurestaurant.njutakeout.dataservice.admin.AdminDataService;
import njurestaurant.njutakeout.dataservice.job.JobCardDataService;
import njurestaurant.njutakeout.dataservice.user.EnterpriseDataService;
import njurestaurant.njutakeout.entity.job.JobCard;
import njurestaurant.njutakeout.entity.user.Enterprise;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.job.JobCardItem;
import njurestaurant.njutakeout.response.job.JobCardListResponse;
import njurestaurant.njutakeout.response.job.JobCardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobCardBlServiceImpl implements JobCardBlService {
    private final JobCardDataService jobCardDataService;
    private final EnterpriseDataService enterpriseDataService;
    private final AdminDataService adminDataService;
    @Autowired
    public JobCardBlServiceImpl(JobCardDataService jobCardDataService,EnterpriseDataService enterpriseDataService,AdminDataService adminDataService){
        this.jobCardDataService=jobCardDataService;
        this.enterpriseDataService=enterpriseDataService;
        this.adminDataService=adminDataService;
    }
    @Override
    public InfoResponse add(String position, String wage, String experienceRequirement, String degreeRequirement, String address, String hr, String hrTitle,String enterpriseId) throws NotExistException {
        Enterprise enterprise=enterpriseDataService.getEnterpriseById(enterpriseId);
        jobCardDataService.add(new JobCard(position,wage,experienceRequirement,degreeRequirement,address,hr,hrTitle,enterprise));
        return new InfoResponse();
    }

    @Override
    public InfoResponse update(String id, String position, String wage, String experienceRequirement, String degreeRequirement, String address, String hr,String hrTtile) throws NotExistException {
        JobCard jobCard=jobCardDataService.findById(id);
        jobCard.setPosition(position);
        jobCard.setWage(wage);
        jobCard.setExperienceRequirement(experienceRequirement);
        jobCard.setDegreeRequirement(degreeRequirement);
        jobCard.setAddress(address);
        jobCard.setHr(hr);
        jobCard.setHrTitle(hrTtile);
        jobCardDataService.update(jobCard);
        return new InfoResponse();
    }

    @Override
    public InfoResponse deleteById(String id) throws NotExistException {
        jobCardDataService.deleteById(id);
        return new InfoResponse();
    }

    @Override
    public JobCardResponse findById(String id) throws NotExistException {
        return new JobCardResponse(new JobCardItem(jobCardDataService.findById(id),adminDataService));
    }

    @Override
    public JobCardListResponse getAll() {
        List<JobCard> jobCardList=jobCardDataService.getAll();
        List<JobCardItem> jobCardItems=new ArrayList<>();
        if(jobCardList!=null && jobCardList.size()>0){
            for(JobCard jobCard:jobCardList){
            jobCardItems.add(new JobCardItem(jobCard,adminDataService));
            }
        }
        return new JobCardListResponse(jobCardItems);
    }

    @Override
    public JobCardListResponse findByEnterprise(String enterpriseId) throws NotExistException {
        Enterprise enterprise=enterpriseDataService.getEnterpriseById(enterpriseId);
        List<JobCard> jobCardList=jobCardDataService.findByEnterprise(enterprise);
        List<JobCardItem> jobCardItems=new ArrayList<>();
        if(jobCardList!=null && jobCardList.size()>0){
            for(JobCard jobCard:jobCardList){
                jobCardItems.add(new JobCardItem(jobCard,adminDataService));
            }
        }
        return new JobCardListResponse(jobCardItems);
    }
}
