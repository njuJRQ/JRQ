package njurestaurant.njutakeout.data.article;

import njurestaurant.njutakeout.data.dao.article.NewsDao;
import njurestaurant.njutakeout.data.dao.article.CJKXNewsDao;
import njurestaurant.njutakeout.dataservice.article.NewsDataService;
import njurestaurant.njutakeout.entity.article.CJKXNews;
import njurestaurant.njutakeout.entity.article.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NewsDataServiceImpl implements NewsDataService {
	private final NewsDao newsDao;
	private final CJKXNewsDao cjkxNewsDao;

	@Autowired
	public NewsDataServiceImpl(NewsDao newsDao, CJKXNewsDao cjkxNewsDao) {
		this.newsDao = newsDao;
		this.cjkxNewsDao = cjkxNewsDao;
	}

	@Override
	public void addCJKXNews(CJKXNews cjkxNews) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long timestamp = 0; //时间戳精确到毫秒
		try {
			timestamp = sdf.parse(cjkxNews.getTime()).getTime();
			newsDao.save(new News("财经快讯", cjkxNews.getId(), timestamp));
			cjkxNewsDao.save(cjkxNews);
		} catch (ParseException e) {
			System.err.println("时间格式错误！");
			e.printStackTrace();
		}
	}

	@Override
	public Optional<News> findNewsById(String newsId) {
		return newsDao.findById(newsId);
	}

	@Override
	public Optional<CJKXNews> findCJKXNewsById(String id) {
		return cjkxNewsDao.findById(id);
	}

	@Override
	public List<News> getNewsListBeforeTimestamp(String type, long timestamp) {
		//获取timestamp之前的新闻列表
		List<News> newsList = null;
		if (timestamp<0) {
			switch (type) {
				case "user":
					newsList = newsDao.findTop10ByOrderByTimestampDesc();
					break;
				case "admin":
					newsList = newsDao.findTop50ByOrderByTimestampDesc();
					break;
				default:
					newsList = new ArrayList<>();
					break;
			}
		} else {
			switch (type) {
				case "user":
					newsList = newsDao.findTop10ByTimestampBeforeOrderByTimestampDesc(timestamp); break;
				case "admin":
					newsList = newsDao.findTop50ByTimestampBeforeOrderByTimestampDesc(timestamp); break;
				default:
					newsList = new ArrayList<>();
					break;
			}
		}

		//将同一时间戳的新闻加入到列表中
		if (!newsList.isEmpty()) {
			List<News> sameTimeNewsList = newsDao.findNewsByTimestamp(newsList.get(newsList.size()-1).getTimestamp());
			for (News stn:sameTimeNewsList) {
				boolean flag = false; //标记stn是否在newsList中
				for (News n:newsList) {
					if(stn.getId().equals(n.getId())) {
						flag = true;
						break;
					}
				}
				if (!flag) { //stn不在newsList中，加入进去
					newsList.add(stn);
				}
			}
		}

		return newsList;
	}

	@Override
	public void saveCJKXNews(CJKXNews cjkxNews) {
		cjkxNewsDao.save(cjkxNews);
	}

	@Override
	public void saveNews(News news) {
		newsDao.save(news);
	}

	@Override
	public void deleteCJKXNews(CJKXNews cjkxNews) {
		cjkxNewsDao.delete(cjkxNews);
	}

	@Override
	public void deleteNews(News news) {
		newsDao.delete(news);
	}

}
