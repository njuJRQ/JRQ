package njurestaurant.njutakeout.parameters.resume;

import njurestaurant.njutakeout.entity.resume.Resume;
import njurestaurant.njutakeout.entity.resume.ResumeInternShip;
import njurestaurant.njutakeout.entity.resume.ResumeEducation;

import java.util.List;

public class ResumeParameters {
    private String resumeId;
    private String userId;
    private String trueName;
    private String face;
    private boolean isFresh;
    private String degree;
    private int age;
    private String experience;//实习时间
    private String positionId;
    private String position;
    private String email;
    private String phone;
    private String expectCity;
    private int lowWage;
    private int highWage;

    private List<ResumeInternShip> resumeInternShips;
    private List<ResumeEducation> resumeEducation;


    public  ResumeParameters(Resume resume){

        this.resumeId=resume.getResumeId();

        this.userId=resume.getUserId();
        this.trueName=resume.getTrueName();
        this.face=resume.getFace();
        this.isFresh=resume.isIsfresh();
        this.degree=resume.getDegree();

        this.age=resume.getAge();

        this.experience=resume.getExperience();//实习时间

        this.positionId=resume.getPositionId();

        this.position=resume.getPosition();

        this.email=resume.getEmail();

        this.phone=resume.getPhone();

        this.expectCity=resume.getExpectCity();
        this.lowWage=resume.getLowWage();
        this.highWage=resume.getHighWage();
        this.resumeInternShips=resume.getResumeInternShips();
        this.resumeEducation=resume.getResumeEducation();

    }
    public ResumeParameters(   String resumeId,
                               String userId,
                               String trueName,
                               String face,
                               boolean isfresh,
                               String degree,
                               String experience,
                               int age,
                               String position,
                               String positionId,
                               String email,
                               String phone,
                               String expectCity,
                               int lowWage,
                               int highWage,
                               List<ResumeInternShip> resumeInternShips,
                               List<ResumeEducation> resumeEducation){
        this.isFresh=isfresh;
        this.degree=degree;
        this.expectCity=expectCity;
        this.highWage=highWage;
        this.face=face;
        this.email=email;
        this.experience=experience;
        this.resumeId=resumeId;
        this.position=position;
        this.positionId=positionId;
        this.trueName=trueName;
        this.lowWage=lowWage;
        this.userId=userId;
        this.age=age;
        this.phone=phone;
        this.resumeEducation=resumeEducation;
        this.resumeInternShips=resumeInternShips;
    }

    public ResumeParameters() {
    }




    public String getResumeId() {
        return resumeId;
    }

    public void setResumeId(String resumeId) {
        this.resumeId = resumeId;
    }


    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }


    public long getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }


    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public void setIsfresh(boolean isfresh) {
        this.isFresh= isfresh;
    }

    public String getExperience() {
        return experience;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public boolean getfresh() {
        return isFresh;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getDegree() {
        return degree;
    }

    public String getFace() {
        return face;
    }

    public void setResumeInternShips(List<ResumeInternShip> resumeInternShips) {
        this.resumeInternShips = resumeInternShips;
    }

    public void setResumeEducation(List<ResumeEducation> resumeEducation) {
        this.resumeEducation = resumeEducation;
    }

    public void setLowWage(int lowWage) {
        this.lowWage = lowWage;
    }

    public void setHighWage(int highWage) {
        this.highWage = highWage;
    }

    public void setExpectCity(String expectCity) {
        this.expectCity = expectCity;
    }

    public String getExpectCity() {
        return expectCity;
    }

    public List<ResumeInternShip> getResumeInternShips() {
        return resumeInternShips;
    }

    public int getLowWage() {
        return lowWage;
    }

    public int getHighWage() {
        return highWage;
    }

    public List<ResumeEducation> getResumeEducation() {
        return resumeEducation;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}