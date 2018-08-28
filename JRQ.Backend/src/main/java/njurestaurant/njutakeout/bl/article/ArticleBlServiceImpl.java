package njurestaurant.njutakeout.bl.article;

import njurestaurant.njutakeout.blservice.article.ArticleBlService;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.article.*;

public class ArticleBlServiceImpl implements ArticleBlService {

	@Override
	public AbstractListResponse getAbstractList(String kind, String openid) {
		return null;
	}

	@Override
	public ArticleResponse getArticle(String kind, long id) {
		return null;
	}

	@Override
	public AdResponse getAd() {
		return null;
	}

	@Override
	public InfoResponse likePlus(String kind, long articleId, String openid) {
		return null;
	}
}
