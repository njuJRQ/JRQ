package njurestaurant.njutakeout.blservice.article.news;

import njurestaurant.njutakeout.response.BoolResponse;
import njurestaurant.njutakeout.response.IntResponse;
import njurestaurant.njutakeout.response.article.news.NewsListResponse;
import njurestaurant.njutakeout.response.article.news.NewsResponse;

public interface NewsBlService {

	/**
	 * 根据全局新闻ID获取新闻，不存在则返回空
	 * @param newsId 全局新闻ID
	 * @return 新闻内容
	 */
	NewsResponse getNews(String newsId);

	/**
	 * 获取当前数据库中总新闻条数
	 * @return 新闻条数
	 */
	IntResponse getNewsNumber();

	/**
	 * 获取某条新闻前的新闻列表。为用户返回最新的10条，为管理员返回最新的50条
	 * @param type 请求方名称，可取值"user","admin"
	 * @param newsId 参照新闻的id（News表中的ID，而非特定新闻表的ID）。ID为""则返回当前最新的新闻
	 * @return 新闻列表
	 */
	NewsListResponse getNewsListBefore(String type, String newsId);

	/**
	 * 修改某条新闻的内容(Admin)
	 * @param newsId 新闻的全局ID（在News表中的ID）
	 * @param time 新的新闻时间
	 * @param content 新的新闻内容
	 * @return 是否操作成功
	 */
	BoolResponse updateNews(String newsId, String time, String content);

	/**
	 * 删除某条新闻(Admin)
	 * @param newsId 新闻的全局ID（在News表中的ID）
	 * @return 是否操作成功
	 */
	BoolResponse deleteNews(String newsId);
}
