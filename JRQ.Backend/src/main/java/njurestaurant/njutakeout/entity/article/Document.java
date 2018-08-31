package njurestaurant.njutakeout.entity.article;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Document {
	@Id
	@GeneratedValue(generator = "jpa-uuid")
	private String id;//编号

	@Column
	private String title;//标题

	@Column
	private String content; //内容

	@Column
	private String writerName;//作者名字

	@Column
	private String date;//日期

	@Column
	private long likeNum;//点赞数

	public Document(){
	}

	public Document(String title, String content, String writerName, String date, long likeNum) {
		this.title = title;
		this.content = content;
		this.writerName = writerName;
		this.date = date;
		this.likeNum = likeNum;
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
}
