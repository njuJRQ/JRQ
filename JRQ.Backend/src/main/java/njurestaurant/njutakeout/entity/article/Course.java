package njurestaurant.njutakeout.entity.article;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Course {
	@Id
	@GeneratedValue(generator = "jpa-uuid")
	private long id;

	@Column(name = "title")
	private String title;

	@Column(name = "image")
	private String image;

	@Column(name = "writerName")
	private String writerName;

	@Column(name = "date")
	private String date;

	@Column(name = "likeNum")
	private long likeNum;

	@Column(name = "video")
	private String video;

	@Column(name = "price")
	private int price;

	public Course(){
	}

	public Course(String title, String image, String writerName, String date, long likeNum, String video, int price) {
		this.title = title;
		this.image = image;
		this.writerName = writerName;
		this.date = date;
		this.likeNum = likeNum;
		this.video = video;
		this.price = price;
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
