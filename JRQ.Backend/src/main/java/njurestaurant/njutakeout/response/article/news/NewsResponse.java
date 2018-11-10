package njurestaurant.njutakeout.response.article.news;

import njurestaurant.njutakeout.response.Response;

public class NewsResponse extends Response {
	private NewsItem news;

	public NewsResponse() {
	}

	public NewsResponse(NewsItem news) {
		this.news = news;
	}

	public NewsItem getNews() {
		return news;
	}

	public void setNews(NewsItem news) {
		this.news = news;
	}
}
