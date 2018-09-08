package njurestaurant.njutakeout.response.article.feed;

import njurestaurant.njutakeout.response.Response;

import java.util.List;

public class FeedViewListResponse extends Response{
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
