package njurestaurant.njutakeout.parameters.course;

import java.util.List;

public class CourseGroupParameters {
    private String id;
    private String title;
    private String writerName;
    private List<String> courses;

    public CourseGroupParameters() {
    }

    public CourseGroupParameters(String id,String title,String writerName, List<String> courses) {
        this.id=id;
        this.title = title;
        this.writerName=writerName;
        this.courses = courses;
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

    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }
}
