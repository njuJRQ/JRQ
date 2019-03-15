package njurestaurant.njutakeout.response.job;

import njurestaurant.njutakeout.dataservice.admin.AdminDataService;
import njurestaurant.njutakeout.entity.job.JobCard;
import njurestaurant.njutakeout.response.user.EnterpriseItem;

public class JobCardItem {
    private String id;
    private String position;
    private String wage;
    private String  experienceRequirement;
    private String degreeRequirement;
    private String address;
    private String hr;
    private EnterpriseItem enterprise;

    public JobCardItem() {
    }
    public JobCardItem(JobCard jobCard, AdminDataService adminDataService){
        this.id=jobCard.getId();
        this.position=jobCard.getPosition();
        this.wage=jobCard.getWage();
        this.experienceRequirement=jobCard.getExperienceRequirement();
        this.degreeRequirement=jobCard.getDegreeRequirement();
        this.address=jobCard.getAddress();
        this.hr=jobCard.getHr();
        this.enterprise=new EnterpriseItem(jobCard.getEnterprise(),adminDataService);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getWage() {
        return wage;
    }

    public void setWage(String wage) {
        this.wage = wage;
    }

    public String getExperienceRequirement() {
        return experienceRequirement;
    }

    public void setExperienceRequirement(String experienceRequirement) {
        this.experienceRequirement = experienceRequirement;
    }

    public String getDegreeRequirement() {
        return degreeRequirement;
    }

    public void setDegreeRequirement(String degreeRequirement) {
        this.degreeRequirement = degreeRequirement;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHr() {
        return hr;
    }

    public void setHr(String hr) {
        this.hr = hr;
    }

    public EnterpriseItem getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(EnterpriseItem enterprise) {
        this.enterprise = enterprise;
    }
}
