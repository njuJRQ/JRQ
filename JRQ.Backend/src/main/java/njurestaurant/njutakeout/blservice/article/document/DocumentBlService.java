package njurestaurant.njutakeout.blservice.article.document;

import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.article.document.DocumentListResponse;
import njurestaurant.njutakeout.response.article.document.DocumentResponse;
import org.springframework.stereotype.Service;

public interface DocumentBlService {
	/**
	 * 添加文档(Admin)
	 * @param title 文档标题
	 * @param content 文档内容
	 * @param writerName 作者名字
	 * @param likeNum 点赞数
	 * @return 是否成功
	 */
	InfoResponse addDocument(String title, String content, String writerName, long likeNum);

	/**
	 * 根据文档ID获取文档(Admin)
	 * @param id 文档ID
	 * @return 文档内容
	 */
	DocumentResponse getDocument(String id) throws NotExistException;

	/**
	 * 获取文档列表(Admin)
	 * @return 文档列表
	 */
	DocumentListResponse getDocumentList();

	/**
	 * 根据文档ID修改文档(Admin)
	 * @param id 文档ID
	 * @param title 文档标题
	 * @param content 文档内容
	 * @param writerName 作者名字
	 * @param likeNum 点赞数
	 * @return 是否成功
	 */
	InfoResponse updateDocument(String id, String title, String content, String writerName, long likeNum) throws NotExistException;

	/**
	 * 根据文档ID删除文档(Admin)
	 * @param id 文档ID
	 * @return 是否成功
	 */
	InfoResponse deleteDocument(String id) throws NotExistException;

	/**
	 * 用户查看特定文档(User)
	 * @param openid 用户的openid
	 * @param documentId 文档ID
	 * @return 文档详细信息（包括是否点赞）
	 */
	DocumentResponse getMyDocument(String openid, String documentId) throws NotExistException;

	/**
	 * 用户查看文档列表(User)
	 * @param openid 用户的openid
	 * @return 文档详细信息列表（包括是否点赞）
	 */
	DocumentListResponse getMyDocumentList(String openid);
}
