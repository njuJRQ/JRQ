package njurestaurant.njutakeout.response.article.feed;

import njurestaurant.njutakeout.response.Response;

public class FeedViewResponse extends Response{
	private FeedViewItem feedView;

	public FeedViewResponse(FeedViewItem feedView) {
		this.feedView = feedView;
	}

	public FeedViewItem getFeedView() {
		return feedView;
	}

	public void setFeedView(FeedViewItem feedView) {
		this.feedView = feedView;
	}
}
