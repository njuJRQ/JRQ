package njurestaurant.njutakeout.bl.job;

import njurestaurant.njutakeout.blservice.job.JobCardBlService;
import njurestaurant.njutakeout.dataservice.job.JobCardDataService;
import njurestaurant.njutakeout.entity.job.JobCard;
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

    @Autowired
    public JobCardBlServiceImpl(JobCardDataService jobCardDataService) {
        this.jobCardDataService = jobCardDataService;
    }

    @Override
    public InfoResponse add(String expectPosition,
                            String expectWage,
                            String degree,
                            String introduction,
                            boolean isFresh,
                            String enterprise,
                            String advantage,
                            String city) {
        jobCardDataService.add(new JobCard(expectPosition, expectWage, degree, introduction, isFresh, enterprise, advantage, city));
        return new InfoResponse();
    }

    @Override
    public InfoResponse update(String id,
                               String expectPosition,
                               String expectWage,
                               String degree,
                               String introduction,
                               boolean isFresh,
                               String enterprise,
                               String advantage,
                               String city) throws NotExistException {
        JobCard jobCard = jobCardDataService.findById(id);
        jobCard.setExpectPosition(expectPosition);
        jobCard.setExpectWage(expectWage);
        jobCard.setDegree(degree);
        jobCard.setIntroduction(introduction);
        jobCard.setFresh(isFresh);
        jobCard.setEnterprise(enterprise);
        jobCard.setAdvantage(advantage);
        jobCard.setCity(city);
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
        return new JobCardResponse(new JobCardItem(jobCardDataService.findById(id)));
    }

    @Override
    public JobCardListResponse getAll() {
        List<JobCard> jobCardList = jobCardDataService.getAll();
        List<JobCardItem> jobCardItems = new ArrayList<>();
        if (jobCardList != null && jobCardList.size() > 0) {
            for (JobCard jobCard : jobCardList) {
                jobCardItems.add(new JobCardItem(jobCard));
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
                jobCardItems.add(new JobCardItem(jobCard));
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
                jobCardItems.add(new JobCardItem(jobCard));
            }
        }
        return new JobCardListResponse(jobCardItems);
    }
}
