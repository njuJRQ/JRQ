package njurestaurant.njutakeout.response.article.feed;

import njurestaurant.njutakeout.entity.article.Feed;

import java.util.List;

public class FeedItem {
	private String id; //文章编号
	private String title; //文章标题
	private String content; //文章内容
	private List<String> images; //文章图片路径集合（不超过3张）
	private String writerOpenid; //作者微信openid
	private String date; //文章发布日期，如"2018-1-1"
	private long likeNum; //文章点赞数

	public FeedItem(Feed feed) {
		this.id = feed.getId();
		this.title = feed.getTitle();
		this.content = feed.getContent();
		this.images = feed.getImages();
		this.writerOpenid = feed.getWriterOpenid();
		this.date = feed.getDate();
		this.likeNum = feed.getLikeNum();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public String getWriterOpenid() {
		return writerOpenid;
	}

	public void setWriterOpenid(String writerOpenid) {
		this.writerOpenid = writerOpenid;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public long getLikeNum() {
		return likeNum;
	}

	public void setLikeNum(long likeNum) {
		this.likeNum = likeNum;
	}
}
