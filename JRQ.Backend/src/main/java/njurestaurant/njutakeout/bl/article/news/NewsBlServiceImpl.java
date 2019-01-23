package njurestaurant.njutakeout.bl.article.news;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import njurestaurant.njutakeout.blservice.article.news.NewsBlService;
import njurestaurant.njutakeout.dataservice.article.NewsDataService;
import njurestaurant.njutakeout.dataservice.count.CountDataService;
import njurestaurant.njutakeout.entity.article.CJKXNews;
import njurestaurant.njutakeout.entity.article.News;
import njurestaurant.njutakeout.entity.count.Count;
import njurestaurant.njutakeout.response.BoolResponse;
import njurestaurant.njutakeout.response.IntResponse;
import njurestaurant.njutakeout.response.article.news.NewsItem;
import njurestaurant.njutakeout.response.article.news.NewsListResponse;
import njurestaurant.njutakeout.response.article.news.NewsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NewsBlServiceImpl implements NewsBlService {
	private final NewsDataService newsDataService;
	private final CountDataService countDataService;

	@Autowired
	public NewsBlServiceImpl(NewsDataService newsDataService, CountDataService countDataService) {
		this.newsDataService = newsDataService;
		this.countDataService = countDataService;
	}

	@Override
	public NewsResponse getNews(String newsId) {
		Optional<News> optionalNews = newsDataService.findNewsById(newsId);
		if (optionalNews.isPresent()) {
			News news = optionalNews.get();
			switch (news.getSource()) {
				case "财经快讯":
					Optional<CJKXNews> optionalCJKXNews = newsDataService.findCJKXNewsById(news.getSourceId());
					if (optionalCJKXNews.isPresent()) {
						return new NewsResponse(new NewsItem(news.getId(), news.getSource(), optionalCJKXNews.get()));
					}
					break;
				default:
			}
		}
		return new NewsResponse();
	}

	@Override
	public IntResponse getNewsNumber() {
		return new IntResponse(true, newsDataService.getNewsNumber(), "");
	}

	@Override
	public NewsListResponse getNewsListBefore(String type, String newsId) {
		//获取要返回的新闻列表
		List<News> newsList = null;
		if (newsId.equals("")) {
			newsList = newsDataService.getNewsListBeforeTimestamp(type, -1);
		} else {
			Optional<News> optionalNews = newsDataService.findNewsById(newsId);
			if (!optionalNews.isPresent()) {
				return new NewsListResponse();
			}
			News news = optionalNews.get();
			newsList = newsDataService.getNewsListBeforeTimestamp(type, news.getTimestamp());
		}

		//根据newsList获取真正的新闻内容列表
		List<NewsItem> newsItems = new ArrayList<>();
		for (News news:newsList) {
			switch (news.getSource()) {
				case "财经快讯":
					Optional<CJKXNews> optionalCJKXNews = newsDataService.findCJKXNewsById(news.getSourceId());
					optionalCJKXNews.ifPresent(cjkxNews -> newsItems.add(new NewsItem(news.getId(), news.getSource(), cjkxNews)));
					break;
				default:
			}
		}

		//浏览首页次数+1
		Count count = countDataService.getCountById(1);
		count.setViewNews(count.getViewNews()+1);
		countDataService.saveCount(count);

		return new NewsListResponse(newsItems);
	}

	@Override
	public BoolResponse updateNews(String newsId, String time, String content) {
		Optional<News> optionalNews = newsDataService.findNewsById(newsId);
		if (optionalNews.isPresent()) {
			//计算新的时间的时间戳
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			long timestamp = 0; //时间戳精确到毫秒
			try {
				timestamp = sdf.parse(time).getTime();
			} catch (ParseException e) {
				System.err.println("时间格式错误！");
				e.printStackTrace();
				return new BoolResponse(false, e.getMessage());
			}
			//更新数据库，当相应的News与CJKXNews都在数据库中时才会同时更新两者，否则都不更新
			News news = optionalNews.get();
			switch (news.getSource()) {
				case "财经快讯":
					Optional<CJKXNews> optionalCJKXNews = newsDataService.findCJKXNewsById(news.getSourceId());
					if (optionalCJKXNews.isPresent()) {
						CJKXNews cjkxNews = optionalCJKXNews.get();
						cjkxNews.setTime(time);
						cjkxNews.setContent(content);
						news.setTimestamp(timestamp);
						newsDataService.saveNews(news);
						newsDataService.saveCJKXNews(cjkxNews);
					} else {
						return new BoolResponse(false, "要修改的财经快讯ID='"+news.getSourceId()+"'不存在");
					}
					break;
				default:
			}
			return new BoolResponse(true, "修改成功");
		} else {
			return new BoolResponse(false, "要修改的全局新闻ID='"+newsId+"'不存在");
		}
	}

	@Override
	public BoolResponse deleteNews(String newsId) {
		Optional<News> optionalNews = newsDataService.findNewsById(newsId);
		if (optionalNews.isPresent()) {
			News news = optionalNews.get();
			switch (news.getSource()) {
				case "财经快讯":
					Optional<CJKXNews> optionalCJKXNews = newsDataService.findCJKXNewsById(news.getSourceId());
					if (optionalCJKXNews.isPresent()) {
						newsDataService.deleteCJKXNews(optionalCJKXNews.get());
					} else {
						return new BoolResponse(false, "要删除的"+news.getSource()+"ID='"+news.getSourceId()+"'不存在");
					}
					break;
				default:
			}
			newsDataService.deleteNews(news);
			return new BoolResponse(true, "删除成功");
		} else {
			return new BoolResponse(false, "要删除的全局新闻ID='"+newsId+"'不存在");
		}
	}

	/**
	 * 定时任务：每天每半个小时获取一次最新资讯（目前测试是一次5条）
	 */
	@Scheduled(cron = "0 0/30 * * * ?")
	private void updateLocalNews() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<String> entity = new HttpEntity<>("", headers);
		RestTemplate client = new RestTemplate();
		ResponseEntity<String> response = client.exchange(
				"http://test190112.fbecn.com:8088/livenews/livenews_json.json", HttpMethod.GET, entity, String.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			JSONArray arr = JSONObject.fromObject(response.getBody()).getJSONArray("livenews");
			for(int i=0;i<arr.size();i++) {
				JSONObject obj = arr.getJSONObject(i);
				String id = (String) obj.get("ID");
				String time = (String) obj.get("Time");
				String content = (String) obj.get("Content");
				String level = (String) obj.get("Level");
				String type = (String) obj.get("Type");
				switch (type) {
					case "0": type = "环球"; break;
					case "1": type = "A股"; break;
					case "2": type = "行业"; break;
					case "3": type = "数据"; break;
					case "4": type = "央行"; break;
					default:
				}
				String keywords = (String) obj.get("Keywords");
				newsDataService.addCJKXNews(new CJKXNews(id, time, content, level, type, keywords));
			}
		} else {
			System.err.println("新闻更新失败");
		}
	}

}
