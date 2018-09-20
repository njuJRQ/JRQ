package njurestaurant.njutakeout.entity.article;

import javax.persistence.*;

@Entity
@Table
@IdClass(PublishKey.class)
public class Publish {
	@Id
	@Column(length = 32)
	private String adminId; //发布者的管理员ID，UUID长度为32

	@Id
	private String kind; //类别："course", "document", "project"（不包括圈子，圈子发布者ID记录在了Feed字段中）

	@Id
	@Column(length = 32)
	private String articleId; //文章ID，UUID长度为32

	public Publish() {
	}

	public Publish(String adminId, String kind, String articleId) {
		this.adminId = adminId;
		this.kind = kind;
		this.articleId = articleId;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
}
