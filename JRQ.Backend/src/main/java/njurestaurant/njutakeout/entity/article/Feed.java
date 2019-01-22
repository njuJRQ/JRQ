package njurestaurant.njutakeout.entity.article;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="feed")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Feed {
	@Id
	@GeneratedValue(generator = "jpa-uuid")
	private String id; //文章编号

	@Column(length = 600)
	private String content; //文章内容

	@Column
	@ElementCollection(targetClass = String.class)
	private List<String> images; //文章图片路径集合（不超过3张）

	@Column
	private String writerOpenid; //作者微信openid

	@Column
	private long timeStamp; //文章发布日期，如"2018-1-1"

	@Column
	private long likeNum; //文章点赞数

	public Feed() {
	}

	public Feed(String content, List<String> images, String writerOpenid, long timeStamp, long likeNum) {
		this.content = content;
		this.images = images;
		this.writerOpenid = writerOpenid;
		this.timeStamp = timeStamp;
		this.likeNum = likeNum;
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

	public String getWriterOpenid() {
		return writerOpenid;
	}

	public void setWriterOpenid(String writerOpenid) {
		this.writerOpenid = writerOpenid;
	}

	public String getDate() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.format(new Date(timeStamp));
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public long getLikeNum() {
		return likeNum;
	}

	public void setLikeNum(long likeNum) {
		this.likeNum = likeNum;
	}
}
