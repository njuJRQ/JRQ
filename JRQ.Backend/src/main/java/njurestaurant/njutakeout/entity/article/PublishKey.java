package njurestaurant.njutakeout.entity.article;

import java.io.Serializable;

public class PublishKey implements Serializable {
	private String adminId; //发布者的管理员ID，UUID长度为32
	private String kind; //类别："course", "document", "project"（不包括圈子，圈子发布者ID记录在了Feed字段中）
	private String articleId; //文章ID，UUID长度为32

	public PublishKey(String adminId, String kind, String articleId) {
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
