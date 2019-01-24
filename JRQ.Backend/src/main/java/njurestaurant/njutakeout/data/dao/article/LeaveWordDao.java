package njurestaurant.njutakeout.data.dao.article;

import njurestaurant.njutakeout.entity.article.LeaveWord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveWordDao extends JpaRepository<LeaveWord, String> {
    List<LeaveWord> findLeaveWordsByTimeStampAndCourseId(long timeStamp,String courseId);
    List<LeaveWord> findTop5ByCourseIdOrderByTimeStampDesc(String courseId);
    List<LeaveWord> findTop5ByTimeStampBeforeAndCourseIdOrderByTimeStamp(long timeStamp,String courseId);
}
