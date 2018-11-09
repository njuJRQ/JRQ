package njurestaurant.njutakeout.blservice.article.news;

import njurestaurant.njutakeout.response.article.news.NewsListResponse;

public interface NewsBlService {
	/**
	 * 获取某条新闻前的新闻列表。为用户返回最新的10条，为管理员返回最新的50条
	 * @param type 请求方名称，可取值"user","admin"
	 * @param newsId 参照新闻的id（News表中的ID，而非特定新闻表的ID）。ID为""则返回当前最新的新闻
	 * @return 新闻列表
	 */
	NewsListResponse getNewsListBefore(String type, String newsId);
}
