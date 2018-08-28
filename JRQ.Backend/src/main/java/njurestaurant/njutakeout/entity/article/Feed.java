package njurestaurant.njutakeout.entity.article;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "feed")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Feed {
	@Id
	@GeneratedValue(generator = "jpa-uuid")
	private long id; //文章编号

	@Column(name = "content")
	private String content; //文章内容

	@Column(name = "images")
	@ElementCollection(targetClass = String.class)
	private List<String> images; //文章图片路径集合（不超过3张）

	@Column(name = "writer")
	private String writerOpenid; //作者微信openid

	@Column(name = "date")
	private String date; //文章发布日期，如"2018-1-1"

	@Column(name = "likeNum")
	private long likeNum; //文章点赞数

	public Feed() {
	}

	public Feed(String content, List<String> images, String writerOpenid, String date, long likeNum) {
		this.content = content;
		this.images = images;
		this.writerOpenid = writerOpenid;
		this.date = date;
		this.likeNum = likeNum;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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
