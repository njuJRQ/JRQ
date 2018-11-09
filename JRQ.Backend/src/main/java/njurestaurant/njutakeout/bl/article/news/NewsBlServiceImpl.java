package njurestaurant.njutakeout.bl.article.news;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import njurestaurant.njutakeout.blservice.article.news.NewsBlService;
import njurestaurant.njutakeout.dataservice.article.NewsDataService;
import njurestaurant.njutakeout.entity.article.CJKXNews;
import njurestaurant.njutakeout.entity.article.News;
import njurestaurant.njutakeout.response.article.news.NewsItem;
import njurestaurant.njutakeout.response.article.news.NewsListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NewsBlServiceImpl implements NewsBlService {
	private final NewsDataService newsDataService;

	@Autowired
	public NewsBlServiceImpl(NewsDataService newsDataService) {
		this.newsDataService = newsDataService;
	}

	@Override
	public NewsListResponse getNewsListBefore(String type, String newsId) {
		//获取要返回的新闻列表
		List<News> newsList = null;
		if (newsId.equals("")) {
			newsList = newsDataService.getNewsListBeforeTimestamp("user", -1);
		} else {
			Optional<News> optionalNews = newsDataService.findNewsById(newsId);
			if (!optionalNews.isPresent()) {
				return new NewsListResponse();
			}
			News news = optionalNews.get();
			newsList = newsDataService.getNewsListBeforeTimestamp("user", news.getTimestamp());
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

		return new NewsListResponse(newsItems);
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
				String keywords = (String) obj.get("Keywords");
				newsDataService.addCJKXNews(new CJKXNews(id, time, content, level, type, keywords));
			}
		} else {
			System.err.println("新闻更新失败");
		}
	}

}
