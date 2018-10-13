package njurestaurant.njutakeout.data.dao.article;

import njurestaurant.njutakeout.entity.article.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentDao extends JpaRepository<Document, String> {
	List<Document> findDocumentsByTimeStamp(long timeStamp);
	List<Document> findTop10ByOrderByTimeStampDesc();
	List<Document> findTop10ByTimeStampBeforeOrderByTimeStampDesc(long timeStamp);
}
