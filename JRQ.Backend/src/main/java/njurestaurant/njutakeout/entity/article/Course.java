package njurestaurant.njutakeout.entity.article;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Course {
	@Id
	@GeneratedValue(generator = "jpa-uuid")
	private String id;

	@Column
	private String title;

	@Column
	private String image;

	@Column
	private String writerName;

	@Column
	private long timeStamp; //最近一次更新的时间戳

	@Column
	private long likeNum;

	@Column
	private String video;

	@Column
	private int price;

	@Column
	private String preview; //视频预览，前1分钟

	@Column
	private long vieNum;//课程浏览量

	public Course(){
	}

	public Course(String title, String image, String writerName, long timeStamp, long likeNum, String video, int price, String preview,long vieNum) {
		this.title = title;
		this.image = image;
		this.writerName = writerName;
		this.timeStamp = timeStamp;
		this.likeNum = likeNum;
		this.video = video;
		this.price = price;
		this.preview = preview;
		this.vieNum = vieNum;
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
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.format(new Date(timeStamp));
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

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getPreview() {
		return preview;
	}

	public void setPreview(String preview) {
		this.preview = preview;
	}

	public long getVieNum() {
		return vieNum;
	}

	public void setVieNum(long vieNum) {
		this.vieNum = vieNum;
	}
}
