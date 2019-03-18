package njurestaurant.njutakeout.data.dao.article;

import njurestaurant.njutakeout.entity.article.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseDao extends JpaRepository<Course, String> {
	List<Course> findCoursesByTimeStamp(long timeStamp);
	List<Course> findTop10ByOrderByTimeStampDesc();
	List<Course> findTop10ByTimeStampBeforeOrderByTimeStampDesc(long timeStamp);
	List<Course> findCoursesByWriterName(String writerName);
	List<Course> findByIsTextualResearchCourse(boolean isOrNot);
	List<Course> findCoursesByLikeNum(long likeNum);
	List<Course> findTop10ByOrderByLikeNumDesc();
	List<Course> findTop10ByLikeNumBeforeOrderByLikeNumDesc(long likeNum);
//	List<Course> findCoursesByOrderByLikeNumDesc();
}
