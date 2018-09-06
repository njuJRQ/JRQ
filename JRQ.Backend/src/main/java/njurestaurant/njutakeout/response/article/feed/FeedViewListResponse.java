package njurestaurant.njutakeout.response.article.feed;

import java.util.List;

public class FeedViewListResponse {
	private List<FeedViewItem> feedViews;

	public FeedViewListResponse(List<FeedViewItem> feedViews) {
		this.feedViews = feedViews;
	}

	public List<FeedViewItem> getFeedViews() {
		return feedViews;
	}

	public void setFeedViews(List<FeedViewItem> feedViews) {
		this.feedViews = feedViews;
	}
}
