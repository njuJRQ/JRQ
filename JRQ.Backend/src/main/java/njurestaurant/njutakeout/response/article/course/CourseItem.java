package njurestaurant.njutakeout.response.article.course;

import njurestaurant.njutakeout.entity.article.Course;

public class CourseItem {
	private String id;
	private String title;
	private String image;
	private String writerName;
	private String date;
	private long likeNum;
	private String video;
	private int price;

	public CourseItem(Course course){
		this.id = course.getId();
		this.title = course.getTitle();
		this.image = course.getImage();
		this.writerName = course.getWriterName();
		this.date = course.getDate();
		this.likeNum = course.getLikeNum();
		this.video = course.getVideo();
		this.price = course.getPrice();
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
