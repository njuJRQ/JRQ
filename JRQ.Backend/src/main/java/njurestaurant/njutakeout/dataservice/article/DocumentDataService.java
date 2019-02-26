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

	List<Document> getAllDocumentsOrderByLikeNum();

	void deleteDocumentById(String id) throws NotExistException;

	void deleteDocumentsByWriterName(String writerName);

	/**
	 * 用户获取特定文档前的10篇文档，从新到旧排序（不包括这篇文档）
	 * @param openid 用户微信openid
	 * @param id 特定文档ID
	 * @return 文档列表
	 */
	public List<Document> getMyDocumentListBefore(String openid, String id) throws NotExistException;

	/**
	 * 用户获取特定时间戳前的10篇文档，从新到旧排序（不包括这篇文档）
	 * @param openid 用户微信openid
	 * @param timeStamp 特定时间戳
	 * @return 文档列表
	 */
	public List<Document> getMyDocumentListBeforeTimeStamp(String openid, long timeStamp) throws NotExistException;
}
