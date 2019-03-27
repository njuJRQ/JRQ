package njurestaurant.njutakeout.response.job;

import njurestaurant.njutakeout.dataservice.user.EnterpriseDataService;
import njurestaurant.njutakeout.entity.job.EducationExperience;
import njurestaurant.njutakeout.entity.job.JobCard;
import njurestaurant.njutakeout.entity.job.WorkExperience;
import njurestaurant.njutakeout.response.user.UserItem;

import java.util.ArrayList;
import java.util.List;

public class JobCardItem {
    private String id;
    private String photo;
    private String expectPosition;
    private String expectWage;
    private String degree;
    private String introduction;
    private boolean isFresh;
    private int age;
    private List<WorkExperienceItem> workExperienceItemList;
    private List<EducationExperienceItem> educationExperienceItemList;
    private UserItem user;

    public JobCardItem() {
    }

    public JobCardItem(JobCard jobCard, EnterpriseDataService enterpriseDataService) {
        this.id = jobCard.getId();
        this.photo=jobCard.getPhoto();
        this.expectPosition = jobCard.getExpectPosition();
        this.expectWage = jobCard.getExpectWage();
        this.degree = jobCard.getDegree();
        this.introduction = jobCard.getIntroduction();
        this.isFresh = jobCard.isFresh();
        this.age = jobCard.getAge();
        this.workExperienceItemList = new ArrayList<>();
        List<WorkExperience> workExperiences = jobCard.getWorkExperiences();
        if (workExperiences != null && workExperiences.size() > 0) {
            for (WorkExperience temp : workExperiences) {
                this.workExperienceItemList.add(new WorkExperienceItem(temp));
            }
        }
        this.educationExperienceItemList=new ArrayList<>();
        List<EducationExperience> educationExperiences=jobCard.getEducationExperiences();
        if(educationExperiences!=null && educationExperiences.size()>0){
            for(EducationExperience temp:educationExperiences){
                this.educationExperienceItemList.add(new EducationExperienceItem(temp));
            }
        }
        this.user = new UserItem(jobCard.getUser(), enterpriseDataService);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<WorkExperienceItem> getWorkExperienceItemList() {
        return workExperienceItemList;
    }

    public void setWorkExperienceItemList(List<WorkExperienceItem> workExperienceItemList) {
        this.workExperienceItemList = workExperienceItemList;
    }

    public List<EducationExperienceItem> getEducationExperienceItemList() {
        return educationExperienceItemList;
    }

    public void setEducationExperienceItemList(List<EducationExperienceItem> educationExperienceItemList) {
        this.educationExperienceItemList = educationExperienceItemList;
    }

    public UserItem getUser() {
        return user;
    }

    public void setUser(UserItem user) {
        this.user = user;
    }
}
