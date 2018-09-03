package njurestaurant.njutakeout.data.article;

import njurestaurant.njutakeout.data.dao.article.CourseDao;
import njurestaurant.njutakeout.dataservice.article.CourseDataService;
import njurestaurant.njutakeout.entity.article.Course;
import njurestaurant.njutakeout.exception.NotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseDataServiceImpl implements CourseDataService {
	private final CourseDao courseDao;

	@Autowired
	public CourseDataServiceImpl(CourseDao courseDao) {
		this.courseDao = courseDao;
	}

	@Override
	public boolean isCourseExistent(String id) {
		return courseDao.existsById(id);
	}

	@Override
	public void saveCourse(Course course) {
		courseDao.save(course);
	}

	@Override
	public void addCourse(Course course) {
		courseDao.save(course);
	}

	@Override
	public Course getCourseById(String id) throws NotExistException {
		Optional<Course> optionalCourse = courseDao.findById(id);
		if (optionalCourse.isPresent()) {
			return optionalCourse.get();
		} else {
			throw new NotExistException("Course ID", id);
		}
	}

	@Override
	public List<Course> getAllCourses() {
		return courseDao.findAll();
	}

	@Override
	public void deleteCourseById(String id) throws NotExistException {
		if (courseDao.existsById(id)) {
			courseDao.deleteById(id);
		} else {
			throw new NotExistException("Course ID", id);
		}
	}
}
