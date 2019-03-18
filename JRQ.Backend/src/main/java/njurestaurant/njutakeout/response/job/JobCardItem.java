package njurestaurant.njutakeout.response.job;

import njurestaurant.njutakeout.dataservice.admin.AdminDataService;
import njurestaurant.njutakeout.dataservice.user.EnterpriseDataService;
import njurestaurant.njutakeout.entity.job.JobCard;
import njurestaurant.njutakeout.response.user.EnterpriseItem;
import njurestaurant.njutakeout.response.user.UserItem;

public class JobCardItem {
    private String id;
    private String expectPosition;
    private String expectWage;
    private String  experience;
    private String degree;
    private String introduction;
    private UserItem user;

    public JobCardItem() {
    }
    public JobCardItem(JobCard jobCard, EnterpriseDataService enterpriseDataService){
        this.id=jobCard.getId();
       this.expectPosition=jobCard.getExpectPosition();
       this.expectWage=jobCard.getExpectWage();
       this.experience=jobCard.getExperience();
       this.degree=jobCard.getDegree();
       this.introduction=jobCard.getIntroduction();
       this.user=new UserItem(jobCard.getUser(),enterpriseDataService);
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

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
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

    public UserItem getUser() {
        return user;
    }

    public void setUser(UserItem user) {
        this.user = user;
    }
}
