package njurestaurant.njutakeout.data.dao.article;

import njurestaurant.njutakeout.entity.article.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectDao extends JpaRepository<Project, String> {
	List<Project> findProjectsByTimeStamp(long timeStamp);
	List<Project> findTop10ByOrderByTimeStampDesc();
	List<Project> findTop10ByTimeStampBeforeOrderByTimeStampDesc(long timeStamp);
	void deleteProjectsByWriterName(String writerName);
}
