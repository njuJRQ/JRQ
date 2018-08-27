package njurestaurant.njutakeout.response.article;

import java.util.List;

public class AbstractItem {
	private long id; //文章编号
	private String title; //文章标题
	private List<String> images; //文章图片路径集合（不超过3张）
	private String writerFace; //作者头像图片路径（可能为空）
	private String writerName; //作者名字
	private String date; //文章发布日期，如“2018-1-1”
	private long likeNum; //文章点赞数

	public AbstractItem(){
	}

	public AbstractItem(long id, String title, List<String> images, String writerFace, String writerName, String date, long likeNum) {
		this.id = id;
		this.title = title;
		this.images = images;
		this.writerFace = writerFace;
		this.writerName = writerName;
		this.date = date;
		this.likeNum = likeNum;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public String getWriterFace() {
		return writerFace;
	}

	public void setWriterFace(String writerFace) {
		this.writerFace = writerFace;
	}

	public String getWriterName() {
		return writerName;
	}

	public void setWriterName(String writerName) {
		this.writerName = writerName;
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
