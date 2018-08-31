package njurestaurant.njutakeout.response.article.feed;

import njurestaurant.njutakeout.response.Response;

public class FeedResponse extends Response {
	private FeedItem feed;

	public FeedResponse() {
	}

	public FeedResponse(FeedItem feed) {
		this.feed = feed;
	}

	public FeedItem getFeed() {
		return feed;
	}

	public void setFeed(FeedItem feed) {
		this.feed = feed;
	}
}
