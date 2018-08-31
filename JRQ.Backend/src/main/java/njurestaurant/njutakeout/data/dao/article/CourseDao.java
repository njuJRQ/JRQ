package njurestaurant.njutakeout.data.dao.article;

import njurestaurant.njutakeout.entity.article.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseDao extends JpaRepository<Course, String> {
}
