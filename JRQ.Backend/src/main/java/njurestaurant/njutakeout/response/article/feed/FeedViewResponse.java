package njurestaurant.njutakeout.response.article.feed;

public class FeedViewResponse {
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
