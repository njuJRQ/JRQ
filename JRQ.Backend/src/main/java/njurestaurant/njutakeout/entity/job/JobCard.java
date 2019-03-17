package njurestaurant.njutakeout.entity.job;

import njurestaurant.njutakeout.entity.user.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="jobCard")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class JobCard {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String id;

    @Column
    private String expectPosition;

    @Column
    private String expectWage;

    @Column
    private String  experience;

    @Column
    private String degree;

    @Column
    private String introduction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    public JobCard() {
    }

    public JobCard(String expectPosition, String expectWage, String experience, String degree, String introduction, User user) {
        this.expectPosition = expectPosition;
        this.expectWage = expectWage;
        this.experience = experience;
        this.degree = degree;
        this.introduction = introduction;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
