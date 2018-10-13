package njurestaurant.njutakeout.dataservice.article;

import njurestaurant.njutakeout.entity.article.Document;
import njurestaurant.njutakeout.exception.NotExistException;

import java.util.List;

public interface DocumentDataService {

	boolean isDocumentExistent(String id);

	void saveDocument(Document document);

	void addDocument(Document document);

	Document getDocumentById(String id) throws NotExistException;

	List<Document> getAllDocuments();

	void deleteDocumentById(String id) throws NotExistException;

	List<Document> getDocumentsByTimeStamp(long timeStamp);

	List<Document> getTop10ByOrderByTimeStampDesc();

	List<Document> getTop10ByTimeStampBeforeOrderByTimeStampDesc(long timeStamp);
}
