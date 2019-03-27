package njurestaurant.njutakeout.parameters.job;

import njurestaurant.njutakeout.entity.job.EducationExperience;
import njurestaurant.njutakeout.entity.job.WorkExperience;
import njurestaurant.njutakeout.response.job.EducationExperienceItem;
import njurestaurant.njutakeout.response.job.WorkExperienceItem;

import java.util.List;

public class JobCardParameters {
    private String id;
    private String photo;
    private String expectPosition;
    private String expectWage;
    private String degree;
    private String introduction;
    private boolean isFresh;
    private int age;
    private List<WorkExperience> workExperienceList;
    private List<EducationExperience> educationExperienceList;
    private String openid;

    public JobCardParameters() {
    }

    public JobCardParameters(String id, String photo, String expectPosition, String expectWage, String degree, String introduction, boolean isFresh, int age, List<WorkExperience> workExperienceList, List<EducationExperience> educationExperienceList, String openid) {
        this.id = id;
        this.photo = photo;
        this.expectPosition = expectPosition;
        this.expectWage = expectWage;
        this.degree = degree;
        this.introduction = introduction;
        this.isFresh = isFresh;
        this.age = age;
        this.workExperienceList = workExperienceList;
        this.educationExperienceList = educationExperienceList;
        this.openid = openid;
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

    public List<WorkExperience> getWorkExperienceList() {
        return workExperienceList;
    }

    public void setWorkExperienceList(List<WorkExperience> workExperienceList) {
        this.workExperienceList = workExperienceList;
    }

    public List<EducationExperience> getEducationExperienceList() {
        return educationExperienceList;
    }

    public void setEducationExperienceList(List<EducationExperience> educationExperienceList) {
        this.educationExperienceList = educationExperienceList;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
