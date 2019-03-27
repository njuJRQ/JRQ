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
	 * @param image 图片路径
	 * @param attachment 文档附件路径
	 * @param writerName 作者名字
	 * @param price 价格
	 * @param likeNum 点赞数
	 * @return 是否成功
	 */
	InfoResponse addDocument(String title, String content, String image, String attachment, String writerName, int price, long likeNum,boolean isContract);

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
	 * 获取合同列表(Admin)
	 * @return 合同列表
	 */
	DocumentListResponse getContractList();

	/**
	 * 根据文档ID修改文档(Admin)
	 * @param id 文档ID
	 * @param title 文档标题
	 * @param content 文档内容
	 * @param image 图片路径
	 * @param attachment 文档附件路径
	 * @param writerName 作者名字
	 * @param price 价格
	 * @param likeNum 点赞数
	 * @return 是否成功
	 */
	InfoResponse updateDocument(String id, String title, String content, String image, String attachment, String writerName, int price, long likeNum) throws NotExistException;

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
	DocumentListResponse getMyDocumentList(String openid,boolean isContract);

	/**
	 * 获取某一篇文档文章时间戳前的10篇文章
	 * 文章列表按照新旧排序，最新的在最前面，最旧的在最后面，如果有时间戳完全相同的，则不管10篇的限制，全部加入列表中
	 * @param openid 用户的微信openid
	 * @param id 文档ID
	 * @return 文档详细信息列表（包括是否点赞）
	 */
	DocumentListResponse getMyDocumentListBefore(String openid, String id,boolean isContract) throws NotExistException;
}
