package njurestaurant.njutakeout.bl.article.feed;

import njurestaurant.njutakeout.blservice.article.feed.FeedBlService;
import njurestaurant.njutakeout.dataservice.article.FeedDataService;
import njurestaurant.njutakeout.dataservice.user.UserDataService;
import njurestaurant.njutakeout.entity.article.Feed;
import njurestaurant.njutakeout.entity.user.User;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.article.AbstractItem;
import njurestaurant.njutakeout.response.article.AbstractListResponse;
import njurestaurant.njutakeout.response.article.feed.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeedBlServiceImpl implements FeedBlService {
	private final FeedDataService feedDataService;
	private final UserDataService userDataService;

	@Autowired
	public FeedBlServiceImpl(FeedDataService feedDataService, UserDataService userDataService) {
		this.feedDataService = feedDataService;
		this.userDataService = userDataService;
	}

	@Override
	public InfoResponse publishMyFeed(String content, List<String> images, String writerOpenid, String date) {
		feedDataService.addFeed(new Feed(content, images, writerOpenid, date, 0));
		return new InfoResponse();
	}

	@Override
	public FeedResponse getFeed(String id) throws NotExistException {
		return new FeedResponse(new FeedItem(feedDataService.getFeedById(id)));
	}

	@Override
	public FeedViewResponse getFeedView(String id) throws NotExistException {
		return new FeedViewResponse(new FeedViewItem(feedDataService.getFeedById(id), userDataService));
	}

	@Override
	public FeedListResponse getFeedList() {
		List<Feed> feeds = feedDataService.getAllFeeds();
		List<FeedItem> feedItems = new ArrayList<>();
		for(Feed feed:feeds) {
			feedItems.add(new FeedItem(feed));
		}
		return new FeedListResponse(feedItems);
	}

	@Override
	public FeedViewListResponse getFeedViewList() throws NotExistException {
		List<Feed> feeds = feedDataService.getAllFeeds();
		List<FeedViewItem> feedViewItems = new ArrayList<>();
		for (Feed feed : feeds) {
			feedViewItems.add(new FeedViewItem(feed, userDataService));
		}
		return new FeedViewListResponse(feedViewItems);
	}

	@Override
	public AbstractListResponse getMyHistoryAbstractList(String openid) throws NotExistException {
		List<Feed> feeds = feedDataService.getFeedsByWriterOpenid(openid);
		List<AbstractItem> abstractItems = new ArrayList<>();
		for (Feed feed:feeds) {
			abstractItems.add(new AbstractItem(feed, userDataService));
		}
		return new AbstractListResponse(abstractItems);
	}

	@Override
	public InfoResponse updateMyFeed(String id, String content, List<String> images, String date) throws NotExistException {
		Feed feed = feedDataService.getFeedById(id);
		feed.setContent(content);
		feed.setImages(images);
		feed.setDate(date);
		return new InfoResponse();
	}

	@Override
	public InfoResponse deleteMyFeed(String id) throws NotExistException {
		feedDataService.deleteFeedById(id);
		return new InfoResponse();
	}
}
