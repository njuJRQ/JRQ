package njurestaurant.njutakeout.bl.job;

import njurestaurant.njutakeout.blservice.job.JobCardBlService;
import njurestaurant.njutakeout.dataservice.job.JobCardDataService;
import njurestaurant.njutakeout.dataservice.user.EnterpriseDataService;
import njurestaurant.njutakeout.dataservice.user.UserDataService;
import njurestaurant.njutakeout.entity.job.EducationExperience;
import njurestaurant.njutakeout.entity.job.JobCard;
import njurestaurant.njutakeout.entity.job.WorkExperience;
import njurestaurant.njutakeout.entity.user.User;
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
    private final UserDataService userDataService;
    private final EnterpriseDataService enterpriseDataService;

    @Autowired
    public JobCardBlServiceImpl(JobCardDataService jobCardDataService, UserDataService userDataService, EnterpriseDataService enterpriseDataService) {
        this.jobCardDataService = jobCardDataService;
        this.userDataService = userDataService;
        this.enterpriseDataService = enterpriseDataService;
    }

    @Override
    public InfoResponse add(String photo, String expectPosition, String expectWage, String degree, String introduction, boolean isFresh, int age, List<WorkExperience> workExperienceList, List<EducationExperience> educationExperienceList, String openid) throws NotExistException {
        User user = userDataService.getUserByOpenid(openid);
        jobCardDataService.add(new JobCard(photo,expectPosition, expectWage, degree, introduction,isFresh,age,workExperienceList,educationExperienceList,user));
        return new InfoResponse();
    }

    @Override
    public InfoResponse update(String id,String photo, String expectPosition, String expectWage, String degree, String introduction, boolean isFresh, int age, List<WorkExperience> workExperienceList, List<EducationExperience> educationExperienceList) throws NotExistException {
        JobCard jobCard = jobCardDataService.findById(id);
        jobCard.setExpectPosition(expectPosition);
        jobCard.setExpectWage(expectWage);
        jobCard.setDegree(degree);
        jobCard.setIntroduction(introduction);
        jobCard.setAge(age);
        jobCard.setFresh(isFresh);
        jobCard.setWorkExperiences(workExperienceList);
        jobCard.setEducationExperiences(educationExperienceList);
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
        return new JobCardResponse(new JobCardItem(jobCardDataService.findById(id), enterpriseDataService));
    }

    @Override
    public JobCardListResponse getAll() {
        List<JobCard> jobCardList = jobCardDataService.getAll();
        List<JobCardItem> jobCardItems = new ArrayList<>();
        if (jobCardList != null && jobCardList.size() > 0) {
            for (JobCard jobCard : jobCardList) {
                jobCardItems.add(new JobCardItem(jobCard, enterpriseDataService));
            }
        }
        return new JobCardListResponse(jobCardItems);
    }

    @Override
    public JobCardListResponse findByUser(String openid) throws NotExistException {
        User user = userDataService.getUserByOpenid(openid);
        List<JobCard> jobCardList = jobCardDataService.findByUser(user);
        List<JobCardItem> jobCardItems = new ArrayList<>();
        if (jobCardList != null && jobCardList.size() > 0) {
            for (JobCard jobCard : jobCardList) {
                jobCardItems.add(new JobCardItem(jobCard, enterpriseDataService));
            }
        }
        return new JobCardListResponse(jobCardItems);
    }

    @Override
    public JobCardListResponse findByExpectPosition(String expectPosition) {
        List<JobCard> jobCardList = jobCardDataService.findByExpectPosition(expectPosition);
        List<JobCardItem> jobCardItems = new ArrayList<>();
        if (jobCardList != null && jobCardList.size() > 0) {
            for (JobCard jobCard : jobCardList) {
                jobCardItems.add(new JobCardItem(jobCard, enterpriseDataService));
            }
        }
        return new JobCardListResponse(jobCardItems);
    }

    @Override
    public JobCardListResponse findByCity(String city) {
        List<JobCard> jobCardList = jobCardDataService.findByCity(city);
        List<JobCardItem> jobCardItems = new ArrayList<>();
        if (jobCardList != null && jobCardList.size() > 0) {
            for (JobCard jobCard : jobCardList) {
                jobCardItems.add(new JobCardItem(jobCard, enterpriseDataService));
            }
        }
        return new JobCardListResponse(jobCardItems);
    }
}
