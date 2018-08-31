package njurestaurant.njutakeout.response.article.feed;

import njurestaurant.njutakeout.response.Response;

import java.util.List;

public class FeedListResponse extends Response {
	private List<FeedItem> feeds;

	public FeedListResponse() {
	}

	public FeedListResponse(List<FeedItem> feeds) {
		this.feeds = feeds;
	}

	public List<FeedItem> getFeeds() {
		return feeds;
	}

	public void setFeeds(List<FeedItem> feeds) {
		this.feeds = feeds;
	}
}
