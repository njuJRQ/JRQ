package njurestaurant.njutakeout.response.article;

import njurestaurant.njutakeout.response.Response;

public class ArticleResponse extends Response {
	private ArticleItem article;

	private ArticleResponse(){
	}

	public ArticleResponse(ArticleItem article) {
		this.article = article;
	}

	public ArticleItem getArticle() {
		return article;
	}

	public void setArticle(ArticleItem article) {
		this.article = article;
	}
}
