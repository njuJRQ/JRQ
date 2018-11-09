package njurestaurant.njutakeout.entity.article;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class News {
	@Id
	@GeneratedValue(generator = "jpa-uuid")
	private String id; //新闻的ID

	@Column
	private String source; //新闻来源，可取值"财经快讯"

	@Column
	private String sourceId; //新闻来源自己的ID

	@Column
	private long timestamp; //新闻发布的时间戳（这个数据来自新闻来源）

	public News() {
	}

	public News(String source, String sourceId, long timestamp) {
		this.source = source;
		this.sourceId = sourceId;
		this.timestamp = timestamp;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
}
