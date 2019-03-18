package njurestaurant.njutakeout.response.job;

import njurestaurant.njutakeout.response.Response;

import java.util.List;

public class JobCardListResponse extends Response {
    private List<JobCardItem> jobCardItems;

    public JobCardListResponse() {
    }

    public JobCardListResponse(List<JobCardItem> jobCardItems) {
        this.jobCardItems = jobCardItems;
    }

    public List<JobCardItem> getJobCardItems() {
        return jobCardItems;
    }

    public void setJobCardItems(List<JobCardItem> jobCardItems) {
        this.jobCardItems = jobCardItems;
    }
}
