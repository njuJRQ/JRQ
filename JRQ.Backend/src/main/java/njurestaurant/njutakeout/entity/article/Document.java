package njurestaurant.njutakeout.entity.article;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Document {
	@Id
	@GeneratedValue(generator = "jpa-uuid")
	private long id;//编号

	@Column(name = "title")
	private String title;//标题

	@Column(name = "writerName")
	private String writerName;//作者名字

	@Column(name = "date")
	private String date;//日期

	@Column(name = "likeNum")
	private String likeNum;//点赞数

	public Document(){
	}

	public Document(String title, String writerName, String date, String likeNum) {
		this.title = title;
		this.writerName = writerName;
		this.date = date;
		this.likeNum = likeNum;
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
