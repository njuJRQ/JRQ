package njurestaurant.njutakeout.dataservice.article;

import njurestaurant.njutakeout.entity.article.CourseGroup;
import njurestaurant.njutakeout.exception.NotExistException;

import java.util.List;

public interface CourseGroupDataService {
    void add(CourseGroup courseGroup);

    void update(CourseGroup courseGroup);

    void deleteById(String id) throws NotExistException;

    CourseGroup findById(String id) throws NotExistException;

    List<CourseGroup> getAll();

    List<CourseGroup> getCourseGroupListBefore(String id);
}
