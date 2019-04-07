package njurestaurant.njutakeout.entity.job;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table
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
    private String degree;

    @Column
    private String introduction;

    @Column
    private boolean isFresh;

    @Column
    private String enterprise;

    @Column
    private String advantage;

    @Column
    private String city;


    public JobCard() {
    }

    public JobCard(String expectPosition, String expectWage, String degree, String introduction, boolean isFresh, String enterprise, String advantage, String city) {
        this.expectPosition = expectPosition;
        this.expectWage = expectWage;
        this.degree = degree;
        this.introduction = introduction;
        this.isFresh = isFresh;
        this.enterprise = enterprise;
        this.advantage = advantage;
        this.city = city;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
