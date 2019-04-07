package njurestaurant.njutakeout.parameters.course;

import java.util.List;

public class CourseGroupParameters {
    private String id;
    private String title;
    private String writerName;
    private String image;
    private int price;
    private List<String> courses;

    public CourseGroupParameters() {
    }

    public CourseGroupParameters(String id, String title, String writerName, String image, int price, List<String> courses) {
        this.id = id;
        this.title = title;
        this.writerName = writerName;
        this.image = image;
        this.price = price;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getWriterName() {
        return writerName;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }
}
