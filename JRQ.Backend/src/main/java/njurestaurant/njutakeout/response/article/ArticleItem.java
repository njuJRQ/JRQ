package njurestaurant.njutakeout.response.article;

import java.util.List;

public class ArticleItem {
	private long id; //文章编号
	private String title; //文章标题
	private String content; //文章内容
	private List<String> images; //文章图片路径集合（不超过3张）
	private String writerFace; //作者头像图片路径（可能为空）
	private String writerName; //作者名字
	private String date; //文章发布日期，如"2018-1-1"
	private long likeNum; //文章点赞数
	private String kind; //文章类型，可能值：course，document，project

	public ArticleItem(){
	}

	public ArticleItem(long id, String title, String content, List<String> images, String writerFace, String writerName, String date, long likeNum, String kind) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.images = images;
		this.writerFace = writerFace;
		this.writerName = writerName;
		this.date = date;
		this.likeNum = likeNum;
		this.kind = kind;
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

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}
}
