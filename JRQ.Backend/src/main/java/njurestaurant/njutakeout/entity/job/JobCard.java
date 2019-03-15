package njurestaurant.njutakeout.entity.job;

import njurestaurant.njutakeout.entity.user.Enterprise;
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
    private String position;


    @Column
    private String wage;

    @Column
    private String  experienceRequirement;

    @Column
    private String degreeRequirement;

    @Column
    private String address;

    @Column
    private String hr;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enterprise_id")
    private Enterprise enterprise;

    public JobCard() {
    }

    public JobCard(String position, String wage, String experienceRequirement, String degreeRequirement, String address, String hr,Enterprise enterprise) {
        this.position = position;
        this.enterprise = enterprise;
        this.wage = wage;
        this.experienceRequirement = experienceRequirement;
        this.degreeRequirement = degreeRequirement;
        this.address = address;
        this.hr = hr;
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

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
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
}
