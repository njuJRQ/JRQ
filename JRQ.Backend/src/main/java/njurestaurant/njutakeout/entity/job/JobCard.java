package njurestaurant.njutakeout.entity.job;

import njurestaurant.njutakeout.entity.user.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class JobCard {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String id;

    @Column
    private String photo;

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
    private int age;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "workExperience")
    @ElementCollection(targetClass = WorkExperience.class)
    private List<WorkExperience> workExperiences;

    @Column(name = "eductionExperience")
    @ElementCollection(targetClass = EducationExperience.class)
    private List<EducationExperience> educationExperiences;


    public JobCard() {
    }

    public JobCard(String photo,String expectPosition, String expectWage,String degree, String introduction, boolean isFresh, int age, List<WorkExperience> workExperiences, List<EducationExperience> educationExperiences, User user) {
        this.photo=photo;
        this.expectPosition = expectPosition;
        this.expectWage = expectWage;
        this.degree = degree;
        this.introduction = introduction;
        this.isFresh=isFresh;
        this.age=age;
        this.workExperiences=workExperiences;
        this.educationExperiences= educationExperiences;
        this.user = user;
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

    public List<WorkExperience> getWorkExperiences() {
        return workExperiences;
    }

    public void setWorkExperiences(List<WorkExperience> workExperiences) {
        this.workExperiences = workExperiences;
    }

    public List<EducationExperience> getEducationExperiences() {
        return educationExperiences;
    }

    public void setEducationExperiences(List<EducationExperience> educationExperiences) {
        this.educationExperiences = educationExperiences;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
