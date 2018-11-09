package njurestaurant.njutakeout.response.article.news;

import njurestaurant.njutakeout.entity.article.CJKXNews;

public class NewsItem {
	private String newsId; //News表中的ID

	private String source; //新闻来源

	private String sourceId; //新闻来源中此新闻的ID

	private String time; //财经快讯发布时间（精确到秒）

	private String content; //财经快讯内容

	private String level; //重要性（0 重要；1 一般；2 不重要）

	private String type; //资讯类型（0 环球；1 A股；2 行业；3 数据；4 央行）

	private String keywords; //信息关键字（以"|"分隔）

	public NewsItem() {
	}

	public NewsItem(String newsId, String source, CJKXNews cjkxNews) {
		this.newsId = newsId;
		this.source = source;
		this.sourceId = cjkxNews.getId();
		this.time = cjkxNews.getTime();
		this.content = cjkxNews.getContent();
		this.level = cjkxNews.getLevel();
		this.type = cjkxNews.getType();
		this.keywords = cjkxNews.getKeywords();
	}

	public String getNewsId() {
		return newsId;
	}

	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
}
