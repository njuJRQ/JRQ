package njurestaurant.njutakeout.response.article.course;

import njurestaurant.njutakeout.response.Response;

public class CourseGroupResponse extends Response {
    private CourseGroupItem courseGroupItem;

    public CourseGroupResponse() {
    }

    public CourseGroupResponse(CourseGroupItem courseGroupItem) {
        this.courseGroupItem = courseGroupItem;
    }

    public CourseGroupItem getCourseGroupItem() {
        return courseGroupItem;
    }

    public void setCourseGroupItem(CourseGroupItem courseGroupItem) {
        this.courseGroupItem = courseGroupItem;
    }
}
