package njurestaurant.njutakeout.entity.resume;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class ResumeEducation {
    @Column
    private String id;
    @Column
    private String major;
    @Column
    private  String school;
    @Column
    private   String fromTime;
    @Column
    private   String toTime;
    @Column
    private  String degree;
    @Column
    private  String resumeId;


    public ResumeEducation(){

    }
    public ResumeEducation(String id, String major, String school, String fromTime,
                           String toTime, String degree){
        this.id=id;
        this.degree=degree;
        this.fromTime=fromTime;
        this.major=major;
        this.school=school;
        this.toTime=toTime;
    }


    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public void setResumeId(String resumeId) {
        this.resumeId = resumeId;
    }

    public String getResumeId() {
        return resumeId;
    }
    public String getToTime() {
        return toTime;
    }

    public String getFromTime() {
        return fromTime;
    }

    public String getMajor() {
        return major;
    }

    public String getDegree() {
        return degree;
    }

    public String getSchool() {
        return school;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setSchool(String school) {
        this.school = school;
    }


}
