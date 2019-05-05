package njurestaurant.njutakeout.entity.resume;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Resume {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String resumeId;

    @Column
    private String userId;

    @Column
    private String trueName;

    @Column
    private String face;

    @Column
    private boolean isfresh;

    @Column
    private String degree;

    @Column
    private int age;

    @Column
    private String experience;//实习时间

    @Column
    private String positionId;

    @Column
    private String position;

    @Column
    private String email;

    @Column
    private String phone;

    @Column
    private String expectCity;
    @Column
    private int lowWage;
    @Column
    private int highWage;

    @OneToMany
    private List<ResumeEducation> resumeEducation;
    @OneToMany
    private List<ResumeInternShip> resumeInternShips;

    public Resume(   String resumeId,
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
        this.isfresh=isfresh;
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

    public Resume() {
    }

    public String getFace() {
        return face;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getDegree() {
        return degree;
    }

    public boolean isIsfresh() {
        return isfresh;
    }

    public String getResumeId() {
        return resumeId;
    }

    public int getAge() {
        return age;
    }

    public String getTrueName() {
        return trueName;
    }

    public String getUserId() {
        return userId;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public void setResumeId(String resumeId) {
        this.resumeId = resumeId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getExperience() {
        return experience;
    }

    public void setIsfresh(boolean isfresh) {
        this.isfresh = isfresh;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }


    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public int getHighWage() {
        return highWage;
    }

    public int getLowWage() {
        return lowWage;
    }

    public List<ResumeEducation> getResumeEducation() {
        return resumeEducation;
    }

    public List<ResumeInternShip> getResumeInternShips() {
        return resumeInternShips;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getEmail() {
        return email;
    }

    public String getExpectCity() {
        return expectCity;
    }

    public String getPhone() {
        return phone;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setExpectCity(String expectCity) {
        this.expectCity = expectCity;
    }

    public void setHighWage(int highWage) {
        this.highWage = highWage;
    }

    public void setLowWage(int lowWage) {
        this.lowWage = lowWage;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public void setResumeEducation(List<ResumeEducation> resumeEducation) {
        this.resumeEducation = resumeEducation;
    }

    public void setResumeInternShips(List<ResumeInternShip> resumeInternShips) {
        this.resumeInternShips = resumeInternShips;
    }


}
