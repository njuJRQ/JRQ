package njurestaurant.njutakeout.response.article;

import njurestaurant.njutakeout.entity.article.Document;

public class DocumentItem {
	private long id;//编号
	private String title;//标题
	private String writerName;//作者名字
	private String date;//日期
	private String likeNum;//点赞数

	public DocumentItem(Document document){
		this.id = document.getId();
		this.title = document.getTitle();
		this.writerName = document.getWriterName();
		this.date = document.getDate();
		this.likeNum = document.getLikeNum();
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

	public String getLikeNum() {
		return likeNum;
	}

	public void setLikeNum(String likeNum) {
		this.likeNum = likeNum;
	}
}
