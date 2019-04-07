package njurestaurant.njutakeout.response.article.course;

import njurestaurant.njutakeout.entity.article.Course;
import njurestaurant.njutakeout.entity.article.CourseGroup;

import java.util.ArrayList;
import java.util.List;

public class CourseGroupItem {
    private String id;
    private String title;
    private String image;
    private String writerName;
    private int price;
    private List<CourseItem> courseList;

    public CourseGroupItem() {
    }

    public CourseGroupItem(CourseGroup courseGroup) {
        this.id = courseGroup.getId();
        this.title = courseGroup.getTitle();
        this.image = courseGroup.getImage();
        this.price = courseGroup.getPrice();
        this.writerName = courseGroup.getWriterName();
        List<Course> courses = courseGroup.getCourseList();
        List<CourseItem> courseItems = new ArrayList<>();
        if (courses != null && courses.size() > 0) {
            for (Course course : courses) {
                courseItems.add(new CourseItem(course));
            }
        }
        this.courseList = courseItems;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getWriterName() {
        return writerName;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<CourseItem> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<CourseItem> courseList) {
        this.courseList = courseList;
    }
}
