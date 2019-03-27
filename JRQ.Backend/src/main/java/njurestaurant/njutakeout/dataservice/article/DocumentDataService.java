package njurestaurant.njutakeout.dataservice.article;

import njurestaurant.njutakeout.entity.article.Course;
import njurestaurant.njutakeout.entity.article.Document;
import njurestaurant.njutakeout.exception.NotExistException;

import java.util.List;

public interface DocumentDataService {

	boolean isDocumentExistent(String id);

	void saveDocument(Document document);

	void addDocument(Document document);

	Document getDocumentById(String id) throws NotExistException;

	List<Document> getAllDocuments();

	List<Document> getAllContracts();

	void deleteDocumentById(String id) throws NotExistException;

	void deleteDocumentsByWriterName(String writerName,boolean isContract);

	/**
	 * 用户获取特定文档前的10篇文档，从新到旧排序（不包括这篇文档）
	 * @param openid 用户微信openid
	 * @param id 特定文档ID
	 * @return 文档列表
	 */
	List<Document> getMyDocumentListBefore(String openid, String id,boolean isContract) throws NotExistException;

	/**
	 * 用户获取特定时间戳前的10篇文档，从新到旧排序（不包括这篇文档）
	 * @param openid 用户微信openid
	 * @param timeStamp 特定时间戳
	 * @return 文档列表
	 */
	List<Document> getMyDocumentListBeforeTimeStamp(String openid, long timeStamp,boolean isContract) throws NotExistException;

	/**
	 * 用户获取10条按热度排序的文档，第一次传id为空字符串
	 * @param openid 用户微信openid
	 * @param id 特定文档id
	 * @return 文档列表
	 */
	List<Document> getTop10DocumentsOrderByLikeNum(String openid, String id,boolean isContract) throws NotExistException;

	/**
	 * 用户用户获取小于某热度值的10条按热度排序的文档
	 * @param openid 用户微信openid
	 * @param likeNum 热度值
	 * @return 文档列表
	 */
	List<Document> getDocumentsOrderByLikeNumBefore(String openid ,long likeNum,boolean isContract) throws NotExistException;
}
