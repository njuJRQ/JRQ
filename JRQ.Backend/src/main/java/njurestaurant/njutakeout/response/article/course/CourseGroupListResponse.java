package njurestaurant.njutakeout.response.article.course;

import njurestaurant.njutakeout.response.Response;

import java.util.List;

public class CourseGroupListResponse extends Response {
   private List<CourseGroupItem> courseGroupItems;

    public CourseGroupListResponse() {
    }

    public CourseGroupListResponse(List<CourseGroupItem> courseGroupItems) {
        this.courseGroupItems = courseGroupItems;
    }

    public List<CourseGroupItem> getCourseGroupItems() {
        return courseGroupItems;
    }

    public void setCourseGroupItems(List<CourseGroupItem> courseGroupItems) {
        this.courseGroupItems = courseGroupItems;
    }
}
