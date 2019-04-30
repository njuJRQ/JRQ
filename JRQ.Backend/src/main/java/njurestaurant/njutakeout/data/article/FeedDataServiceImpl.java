package njurestaurant.njutakeout.data.article;

import njurestaurant.njutakeout.data.dao.article.FeedDao;
import njurestaurant.njutakeout.dataservice.article.FeedDataService;
import njurestaurant.njutakeout.entity.article.Feed;
import njurestaurant.njutakeout.exception.NotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Service
public class FeedDataServiceImpl implements FeedDataService {
	private final FeedDao feedDao;

	@Autowired
	public FeedDataServiceImpl(FeedDao feedDao) {
		this.feedDao = feedDao;
	}

	@Override
	public boolean isFeedExistent(String id) {
		return feedDao.existsById(id);
	}

	@Override
	public void saveFeed(Feed feed) {
		feedDao.save(feed);
	}

	@Override
	public void addFeed(Feed feed) {
		feedDao.save(feed);
	}

	@Override
	public Feed getFeedById(String id) throws NotExistException {
		Optional<Feed> optionalFeed = feedDao.findById(id);
		if (optionalFeed.isPresent()) {
			return optionalFeed.get();
		} else {
			throw new NotExistException("Feed ID", id);
		}
	}

	@Override
	public List<Feed> getAllFeeds() {
		return feedDao.findFeedsByOrderByTimeStampDesc();
	}

	@Override
	public List<Feed> getFeedsByWriterOpenidInAndTimeStamp(List<String> friendOpenids, long timeStamp) {
		return feedDao.findFeedsByWriterOpenidInAndTimeStamp(friendOpenids, timeStamp);
	}

	@Override
	public List<Feed> getTop10ByWriterOpenidInOrderByTimeStampDesc(List<String> friendOpenids) {
		return feedDao.findTop10ByWriterOpenidInOrderByTimeStampDesc(friendOpenids);
	}

	@Override
	public List<Feed> getTop10ByWriterOpenidInAndTimeStampBeforeOrderByTimeStampDesc(List<String> friendOpenids, long timeStamp) {
		return feedDao.findTop10ByWriterOpenidInAndTimeStampBeforeOrderByTimeStampDesc(friendOpenids, timeStamp);
	}

	@Override
	public List<Feed> getFeedsByWriterOpenid(String writerOpenid) {
		return feedDao.findFeedsByWriterOpenidOrderByTimeStampDesc(writerOpenid);
	}


	@Override
	public void deleteFeedById(String id) throws NotExistException {
		Optional<Feed> optionalFeed = feedDao.findById(id);
		if (optionalFeed.isPresent()) {
			Feed feed = optionalFeed.get();
			for (String image:feed.getImages()) {
				if(! new File(image).delete()) {
					System.err.println("圈子文章图片"+image+"删除失败");
				}
			}
			feedDao.delete(feed);
		} else {
			throw new NotExistException("Feed ID", id);
		}
	}

	@Override
	public void deleteFeedsByWriterOpenid(String openid) {
		List<Feed> feeds = feedDao.findFeedsByWriterOpenidOrderByTimeStampDesc(openid);
		for (Feed feed:feeds) {
			for (String image:feed.getImages()) {
				if(! new File(image).delete()) {
					System.err.println("圈子文章图片"+image+"删除失败");
				}
			}
			feedDao.delete(feed);
		}
	}

	@Override
	public List<Feed> getFeedListByIsPreferred(String openid, String id) throws NotExistException {
		return getFeedListByIsPreferredDesc(openid,
				id.equals("")?-1:getFeedById(id).getTimeStamp());
	}

	@Override
	public List<Feed> getFeedListByIsPreferredDesc(String openid, long timeStamp) throws NotExistException {
		List<Feed> feeds = null;
		if (timeStamp<0) {
			feeds = feedDao.findTop10ByIsPreferredOrderByTimeStampDesc(true);
		} else {
			feeds = feedDao.findTop10ByIsPreferredAndTimeStampBeforeOrderByTimeStampDesc(true,timeStamp);
		}
		if (!feeds.isEmpty()) {
			List<Feed> sameFeeds = feedDao.findFeedsByTimeStamp(feeds.get(feeds.size()-1).getTimeStamp());
			addSame(feeds,sameFeeds);
		}
		return feeds;
	}

	@Override
	public List<Feed> getFeedListByLikeNum(String openid,String id) throws NotExistException {
		return getFeedListByLikeNumDesc(openid,
				id.equals("")?-1:getFeedById(id).getLikeNum());
	}

	@Override
	public List<Feed> getFeedListByLikeNumDesc(String openid,long likeNum) throws NotExistException {
		List<Feed> feeds = null;
		if (likeNum<0) {
			feeds = feedDao.findTop10ByOrderByLikeNumDesc();
		} else {
			feeds = feedDao.findTop10ByLikeNumBeforeOrderByLikeNumDesc(likeNum);
		}
		if (!feeds.isEmpty()) {
			List<Feed> sameFeeds = feedDao.findFeedsByLikeNum(feeds.get(feeds.size()-1).getLikeNum());
			addSame(feeds,sameFeeds);
		}
		return feeds;
	}

	@Override
	public List<Feed> getFeedListByTimeStamp(String openid, String id) throws NotExistException {
		return getFeedListByTimeStampDesc(openid,
				id.equals("")?-1:getFeedById(id).getTimeStamp());
	}

	@Override
	public List<Feed> getFeedListByTimeStampDesc(String openid, long timeStamp) throws NotExistException {
		List<Feed> feeds = null;
		if (timeStamp<0) {
			feeds = feedDao.findTop10ByOrderByTimeStampDesc();
		} else {
			feeds = feedDao.findTop10ByTimeStampBeforeOrderByTimeStampDesc(timeStamp);
		}
		if (!feeds.isEmpty()) {
			List<Feed> sameFeeds = feedDao.findFeedsByTimeStamp(feeds.get(feeds.size()-1).getTimeStamp());
			addSame(feeds,sameFeeds);
		}
		return feeds;
	}

	/**
	 * 把与10条数据中最后一条数据时间戳或者热度相同的数据添加到这10条数据中去
	 * @param feeds 10条数据
	 * @param sameFeeds 与最后一条数据时间戳或者热度相同的数据
	 */
	private void addSame(List<Feed> feeds,List<Feed> sameFeeds){
		for(Feed sameFeed : sameFeeds) {
			boolean flag = false;
			for(Feed feed : feeds){
				if(sameFeed.getId().equals(feed.getId())){
					flag = true;
					break;
				}
			}
			if(!flag){
				feeds.add(sameFeed);
			}
		}
	}


}
