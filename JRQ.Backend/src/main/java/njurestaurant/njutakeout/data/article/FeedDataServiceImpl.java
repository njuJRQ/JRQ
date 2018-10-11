package njurestaurant.njutakeout.data.article;

import njurestaurant.njutakeout.data.dao.article.FeedDao;
import njurestaurant.njutakeout.dataservice.article.FeedDataService;
import njurestaurant.njutakeout.entity.article.Feed;
import njurestaurant.njutakeout.exception.NotExistException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.stereotype.Service;

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
		return feedDao.findAll();
	}

	@Override
	public List<Feed> getFeedsByTimeStamp(long timeStamp) {
		return feedDao.findFeedsByTimeStamp(timeStamp);
	}

	@Override
	public List<Feed> getTop10ByOrderByTimeStampDesc() {
		return feedDao.findTop10ByOrderByTimeStampDesc();
	}

//	private final static SessionFactory sessionFactory;
//	static {
//		//根据hibernate.cfg.utl得到一个SessionFactory
//		sessionFactory = new Configuration().configure().buildSessionFactory();
//	}

	@Override
	public List<Feed> getFeedsByTimeStampBeforeOrderByTimeStampDescLimit10(long timeStamp) {
//		Session session = sessionFactory.getCurrentSession();
//		Query<Feed> query = session.createQuery("select f from Feed f where f.timeStamp<=1", Feed.class);
		List<Feed> feeds = feedDao.findFeedsByTimeStampBeforeOrderByTimeStampDesc(timeStamp);
		return feeds.subList(0, Math.min(feeds.size(), 10));
	}

	@Override
	public List<Feed> getFeedsByWriterOpenid(String writerOpenid) {
		return feedDao.findFeedsByWriterOpenid(writerOpenid);
	}

	@Override
	public void deleteFeedById(String id) throws NotExistException {
		if (feedDao.existsById(id)) {
			feedDao.deleteById(id);
		} else {
			throw new NotExistException("Feed ID", id);
		}
	}
}
