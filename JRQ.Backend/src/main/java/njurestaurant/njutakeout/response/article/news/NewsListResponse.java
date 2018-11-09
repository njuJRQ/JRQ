package njurestaurant.njutakeout.response.article.news;

import java.util.List;

public class NewsListResponse {
	private List<NewsItem> news;

	public NewsListResponse(){
	}

	public NewsListResponse(List<NewsItem> news) {
		this.news = news;
	}

	public List<NewsItem> getNews() {
		return news;
	}

	public void setNews(List<NewsItem> news) {
		this.news = news;
	}
}
