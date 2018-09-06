package njurestaurant.njutakeout.response.article.feed;

import njurestaurant.njutakeout.dataservice.user.UserDataService;
import njurestaurant.njutakeout.entity.article.Feed;
import njurestaurant.njutakeout.entity.user.User;
import njurestaurant.njutakeout.exception.NotExistException;

import java.util.List;

public class FeedViewItem {
	private String id; //文章编号
	private String content; //文章内容
	private List<String> images; //文章图片路径集合（不超过3张）
	private String writerName; //作者名字
	private String writerFace; //作者头像
	private String date; //文章发布日期，如"2018-1-1"
	private long likeNum; //文章点赞数

	public FeedViewItem(Feed feed, UserDataService userDataService) throws NotExistException {
		this.id = feed.getId();
		this.content = feed.getContent();
		this.images = feed.getImages();
		User user = userDataService.getUserByOpenid(feed.getWriterOpenid());
		this.writerName = user.getUsername();
		this.writerFace = user.getFace();
		this.date = feed.getDate();
		this.likeNum = feed.getLikeNum();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getWriterName() {
		return writerName;
	}

	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}

	public String getWriterFace() {
		return writerFace;
	}

	public void setWriterFace(String writerFace) {
		this.writerFace = writerFace;
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
