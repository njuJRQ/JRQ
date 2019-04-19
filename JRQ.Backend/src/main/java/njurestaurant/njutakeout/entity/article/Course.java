package njurestaurant.njutakeout.entity.article;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
	private String detail;

	@Column
	private String image;

	@Column
	private String writerName;

	@Column
	private long timeStamp; //最近一次更新的时间戳

	@Column
	private long likeNum;

	@Column
	private String video;//原有视频路径 但接下来将不在使用，但为了保留原来的数据，暂且保留

	@Column
	private int price;

	@Column
	private boolean isTextualResearchCourse;

	@Column
	private String preview; //视频预览，前1分钟

	@Column
	private long viewNum;//课程浏览量

	@ManyToMany(
			cascade = CascadeType.REFRESH,
			fetch = FetchType.LAZY)
	private List<CourseGroup> courseGroups;

	@Column
	@ElementCollection(targetClass = String.class)
	private List<String> videos;

	@Column
	@ElementCollection(targetClass = String.class)
	private List<String> previews;

	public Course() {
	}

	public Course(String title, String detail,String image, String writerName, long timeStamp, long likeNum, String video, int price, String preview, long viewNum,boolean isTextualResearchCourse,List<CourseGroup> courseGroups,List<String> videos,List<String> previews) {
		this.title = title;
		this.detail=detail;
		this.image = image;
		this.writerName = writerName;
		this.timeStamp = timeStamp;
		this.likeNum = likeNum;
		this.video = video;
		this.price = price;
		this.preview = preview;
		this.viewNum = viewNum;
		this.isTextualResearchCourse=isTextualResearchCourse;
		this.courseGroups=courseGroups;
		this.videos=videos;
		this.previews=previews;
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

	public boolean isTextualResearchCourse() {
		return isTextualResearchCourse;
	}

	public void setTextualResearchCourse(boolean textualResearchCourse) {
		this.isTextualResearchCourse = textualResearchCourse;
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

	public long getViewNum() {
		return viewNum;
	}

	public void setViewNum(long viewNum) {
		this.viewNum = viewNum;
	}

	public List<CourseGroup> getCourseGroups() {
		return courseGroups;
	}

	public void setCourseGroups(List<CourseGroup> courseGroups) {
		this.courseGroups = courseGroups;
	}

	public List<String> getVideos() {
		return videos;
	}

	public void setVideos(List<String> videos) {
		this.videos = videos;
	}

	public List<String> getPreviews() {
		return previews;
	}

	public void setPreviews(List<String> previews) {
		this.previews = previews;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
}
