package njurestaurant.njutakeout.data.dao.article;

import njurestaurant.njutakeout.entity.article.CourseGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseGroupDao extends JpaRepository<CourseGroup, String> {
    List<CourseGroup> findTop10ByOrderByTimeStampDesc();

    List<CourseGroup> findTop10ByTimeStampBeforeOrderByTimeStampDesc(long timeStamp);
}
