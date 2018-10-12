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
	private boolean hasBought; //用户是否已经购买（管理员获取页面不应显示这个属性）

	//注意：管理员获取课程时应当把hasBought设置成true
	public CourseItem(Course course, boolean hasBought){
		this.id = course.getId();
		this.title = course.getTitle();
		this.image = course.getImage();
		this.writerName = course.getWriterName();
		this.date = course.getDate();
		this.likeNum = course.getLikeNum();
		if(hasBought) {
			this.video = course.getVideo();
		} else {
			this.video = "";
		}
		this.price = course.getPrice();
		this.hasBought = hasBought;
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

	public boolean isHasBought() {
		return hasBought;
	}

	public void setHasBought(boolean hasBought) {
		this.hasBought = hasBought;
	}
}
