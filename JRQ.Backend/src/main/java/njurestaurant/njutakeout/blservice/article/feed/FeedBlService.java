package njurestaurant.njutakeout.blservice.article.feed;

import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.article.AbstractListResponse;
import org.springframework.stereotype.Service;

@Service
public interface FeedBlService {
	/**
	 * 用户获取自己发布过的文章摘要
	 * @param openid 用户的微信openid
	 * @return 文章摘要
	 */
	AbstractListResponse getMyHistoryAbstractList(String openid);

	/** TODO: 图片内容发布问题
	 * 用户发布自己的动态
	 * @param openid 用户的微信openid
	 * @param content 文章内容
	 * @return 是否成功
	 */
	InfoResponse publishMyArticle(String openid, String content);
}
