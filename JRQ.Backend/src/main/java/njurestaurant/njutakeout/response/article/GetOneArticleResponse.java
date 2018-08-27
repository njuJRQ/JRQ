package njurestaurant.njutakeout.response.article;

import njurestaurant.njutakeout.response.Response;

public class GetOneArticleResponse extends Response {
	private ArticleItem article;

	private GetOneArticleResponse(){
	}

	public GetOneArticleResponse(ArticleItem article) {
		this.article = article;
	}

	public ArticleItem getArticle() {
		return article;
	}

	public void setArticle(ArticleItem article) {
		this.article = article;
	}
}
