package njurestaurant.njutakeout.entity.article;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="courseGroup")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class CourseGroup {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String id;
    @Column
    private String title;
    @Column
    private String writerName;

    @ManyToMany
    @JoinTable(
            name="course_group_course",
            joinColumns = @JoinColumn(name = "GROUP_ID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "COURSE_ID", referencedColumnName = "id"))
    private List<Course> courseList;

    public CourseGroup() {
    }

    public CourseGroup(String title, String writerName, List<Course> courseList) {
        this.title = title;
        this.writerName = writerName;
        this.courseList = courseList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriterName() {
        return writerName;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }
}
