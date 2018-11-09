package njurestaurant.njutakeout.entity.article;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class CJKXNews {
	@Id
	private String id; //财经快讯ID编号（唯一标识）

	@Column
	private String time; //财经快讯发布时间（精确到秒）

	@Column
	private String content; //财经快讯内容

	@Column
	private String level; //重要性（0 重要；1 一般；2 不重要）

	@Column
	private String type; //资讯类型（0 环球；1 A股；2 行业；3 数据；4 央行）

	@Column
	private String keywords; //信息关键字（以"|"分隔）

	public CJKXNews() {
	}

	public CJKXNews(String id, String time, String content, String level, String type, String keywords) {
		this.id = id;
		this.time = time;
		this.content = content;
		this.level = level;
		this.type = type;
		this.keywords = keywords;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
}
