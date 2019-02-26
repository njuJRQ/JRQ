package njurestaurant.njutakeout.response.article.document;

import njurestaurant.njutakeout.entity.article.Document;

public class DocumentItem {
	private String id;//编号
	private String title;//标题
	private String content; //内容
	private String image;//图片路径
	private String attachment; //附件路径
	private String writerName;//作者名字
	private int price;//价格
	private String date;//日期
	private long likeNum;//点赞数
	private boolean hasLiked; //用户是否已经点赞
	private String preview; //附件预览图（若附件为PDF则为图片路径，否则为空）

	//注意：管理员调用这个构造方法
	public DocumentItem(Document document){
		this.id = document.getId();
		this.title = document.getTitle();
		this.content = document.getContent();
		this.image = document.getImage();
		this.attachment = document.getAttachment();
		this.writerName = document.getWriterName();
		this.price = document.getPrice();
		this.date = document.getDate();
		this.likeNum = document.getLikeNum();
		this.preview = document.getPreview();
	}

	//注意：用户调用这个构造方法
	public DocumentItem(Document document, boolean hasLiked){
		this.id = document.getId();
		this.title = document.getTitle();
		this.content = document.getContent();
		this.image = document.getImage();
		this.attachment = document.getAttachment();
		this.writerName = document.getWriterName();
		this.price = document.getPrice();
		this.date = document.getDate();
		this.likeNum = document.getLikeNum();
		this.hasLiked = hasLiked;
		this.preview = document.getPreview();
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getWriterName() {
		return writerName;
	}

	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
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

	public boolean isHasLiked() {
		return hasLiked;
	}

	public void setHasLiked(boolean hasLiked) {
		this.hasLiked = hasLiked;
	}

	public String getPreview() {
		return preview;
	}

	public void setPreview(String preview) {
		this.preview = preview;
	}
}
