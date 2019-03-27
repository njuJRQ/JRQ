package njurestaurant.njutakeout.data.dao.article;

import njurestaurant.njutakeout.entity.article.Course;
import njurestaurant.njutakeout.entity.article.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentDao extends JpaRepository<Document, String> {
	List<Document> findDocumentsByTimeStamp(long timeStamp);
	List<Document> findByIsContract(boolean isContract);
	List<Document> findTop10ByIsContractOrderByTimeStampDesc(boolean isContract);
	List<Document> findTop10ByIsContractAndTimeStampBeforeOrderByTimeStampDesc(boolean isContract,long timeStamp);
	List<Document> findDocumentsByWriterNameAndIsContract(String writerName,boolean isContract);
	List<Document> findDocumentsByLikeNumAndIsContract(long likeNum,boolean isContract);
	List<Document> findTop10ByIsContractOrderByLikeNumDesc(boolean isContract);
	List<Document> findTop10ByIsContractAndLikeNumBeforeOrderByLikeNumDesc(boolean isContract,long likeNum);
//	List<Document> findDocumentsByIsContractOrderByLikeNumDesc(boolean isContract);
}
