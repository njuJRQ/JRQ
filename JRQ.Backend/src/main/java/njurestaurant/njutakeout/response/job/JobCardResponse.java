package njurestaurant.njutakeout.response.job;

import njurestaurant.njutakeout.response.Response;

public class JobCardResponse extends Response {
    private JobCardItem jobCardItem;

    public JobCardResponse() {
    }

    public JobCardResponse(JobCardItem jobCardItem) {
        this.jobCardItem = jobCardItem;
    }

    public JobCardItem getJobCardItem() {
        return jobCardItem;
    }

    public void setJobCardItem(JobCardItem jobCardItem) {
        this.jobCardItem = jobCardItem;
    }
}
