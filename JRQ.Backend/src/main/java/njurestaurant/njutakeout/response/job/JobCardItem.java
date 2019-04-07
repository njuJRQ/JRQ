package njurestaurant.njutakeout.response.job;

import njurestaurant.njutakeout.entity.job.JobCard;

public class JobCardItem {
    private String id;
    private String expectPosition;
    private String expectWage;
    private String degree;
    private String introduction;
    private boolean isFresh;
    private String enterprise;
    private String advantage;

    public JobCardItem() {
    }

    public JobCardItem(JobCard jobCard) {
        this.id = jobCard.getId();
        this.expectPosition = jobCard.getExpectPosition();
        this.expectWage = jobCard.getExpectWage();
        this.degree = jobCard.getDegree();
        this.introduction = jobCard.getIntroduction();
        this.isFresh = jobCard.isFresh();
        this.enterprise = jobCard.getEnterprise();
        this.advantage = jobCard.getAdvantage();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExpectPosition() {
        return expectPosition;
    }

    public void setExpectPosition(String expectPosition) {
        this.expectPosition = expectPosition;
    }

    public String getExpectWage() {
        return expectWage;
    }

    public void setExpectWage(String expectWage) {
        this.expectWage = expectWage;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public boolean isFresh() {
        return isFresh;
    }

    public void setFresh(boolean fresh) {
        isFresh = fresh;
    }

    public String getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(String enterprise) {
        this.enterprise = enterprise;
    }

    public String getAdvantage() {
        return advantage;
    }

    public void setAdvantage(String advantage) {
        this.advantage = advantage;
    }
}
