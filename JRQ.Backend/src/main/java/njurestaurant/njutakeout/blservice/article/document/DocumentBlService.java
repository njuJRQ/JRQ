package njurestaurant.njutakeout.blservice.article.document;

import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.article.document.DocumentListResponse;
import njurestaurant.njutakeout.response.article.document.DocumentResponse;

public interface DocumentBlService {
	/**
	 * 添加文档
	 * @param title 文档标题
	 * @param writerName 作者名字
	 * @param date 发布日期
	 * @param likeNum 点赞数
	 * @return 是否成功
	 */
	InfoResponse addDocument(String title, String writerName, String date, long likeNum);

	/**
	 * 根据文档ID获取文档
	 * @param id 文档ID
	 * @return 文档内容
	 */
	DocumentResponse getDocument(long id);

	/**
	 * 获取文档列表
	 * @return 文档列表
	 */
	DocumentListResponse getDocumentList();

	/**
	 * 根据文档ID修改文档
	 * @param id 文档ID
	 * @param title 文档标题
	 * @param writerName 作者名字
	 * @param date 发布日期
	 * @param likeNum 点赞数
	 * @return 是否成功
	 */
	InfoResponse updateDocument(long id, String title, String writerName, String date, long likeNum);

	/**
	 * 根据文档ID删除文档
	 * @param id 文档ID
	 * @return 是否成功
	 */
	InfoResponse deleteDocument(long id);
}
