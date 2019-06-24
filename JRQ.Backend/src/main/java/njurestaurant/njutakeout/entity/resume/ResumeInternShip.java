package njurestaurant.njutakeout.entity.resume;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
@Table
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class ResumeInternShip {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String id;

    @Column
    private String company;
    @Column
    private String fromTime;
    @Column
    private String toTime;
    @Column
    private String position;
    @Column
    private String grade;
    @Column
    private String content;
    @Column
    private  String resumeId;

    public ResumeInternShip() {
    }

    public String getResumeId() {
        return resumeId;
    }

    public void setResumeId(String resumeId) {
        this.resumeId = resumeId;
    }

    public ResumeInternShip(String id, String company , String fromTime
    , String toTime, String position , String grade, String content) {
        this.company=company;
        this.content=content;
        this.fromTime=fromTime;
        this.id=id;
        this.toTime=toTime;
        this.position=position;
        this.grade=grade;
    }

    public String getFromTime() {
        return fromTime;
    }

    public String getGrade() {
        return grade;
    }

    public String getContent() {
        return content;
    }

    public String getId() {
        return id;
    }

    public String getToTime() {
        return toTime;
    }

    public String getPosition() {
        return position;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public void setId(String id) {
        this.id = id;
    }


    public void setPosition(String position) {
        this.position = position;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }


    public String getCompany() {
        return company;
    }
}
