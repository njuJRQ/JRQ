package njurestaurant.njutakeout.data.article;

import njurestaurant.njutakeout.data.dao.article.CourseGroupDao;
import njurestaurant.njutakeout.dataservice.article.CourseGroupDataService;
import njurestaurant.njutakeout.entity.article.CourseGroup;
import njurestaurant.njutakeout.exception.NotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseGroupDataServiceImpl implements CourseGroupDataService {
    private final CourseGroupDao courseGroupDao;

    @Autowired
    public CourseGroupDataServiceImpl(CourseGroupDao courseGroupDao) {
        this.courseGroupDao = courseGroupDao;
    }

    @Override
    public void add(CourseGroup courseGroup) {
        courseGroupDao.save(courseGroup);
    }

    @Override
    public void update(CourseGroup courseGroup) {
        courseGroupDao.save(courseGroup);
    }

    @Override
    public void deleteById(String id) throws NotExistException {
        if (courseGroupDao.findById(id).isPresent()) {
            courseGroupDao.deleteById(id);
        } else {
            throw new NotExistException("CourceGroup ID", id);
        }
    }

    @Override
    public CourseGroup findById(String id) throws NotExistException {
        Optional<CourseGroup> optionalCourseGroup = courseGroupDao.findById(id);
        if (optionalCourseGroup.isPresent()) {
            return optionalCourseGroup.get();
        } else {
            throw new NotExistException("CourceGroup ID", id);
        }
    }

    @Override
    public List<CourseGroup> getAll() {
        return courseGroupDao.findAll();
    }

    @Override
    public List<CourseGroup> getCourseGroupListBefore(String id) {
        Optional<CourseGroup> optionalCourseGroup = courseGroupDao.findById(id);
        if (optionalCourseGroup.isPresent()) {
            CourseGroup courseGroup = optionalCourseGroup.get();
            List<CourseGroup> courseGroups;
            if (courseGroup.getTimeStamp() < 0) {
                courseGroups = courseGroupDao.findTop10ByOrderByTimeStampDesc();
            } else {
                courseGroups = courseGroupDao.findTop10ByTimeStampBeforeOrderByTimeStampDesc(courseGroup.getTimeStamp());
            }
            return courseGroups;
        } else {
            return new ArrayList<>();
        }
    }

}
