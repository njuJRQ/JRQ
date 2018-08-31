package njurestaurant.njutakeout.entity.article;

import javax.persistence.*;

@Entity
@Table(name = "userLike")
@IdClass(LikeKey.class)
public class Like {
	@Id
	@Column(name = "openid", length = 28)
	private String openid; //微信用户ID

	@Id
	@Column(name = "kind")
	private String kind; //文章类别："course", "document", "project", "feed"

	@Id
	@Column(name = "articleId")
	private String articleId; //文章ID

	public Like() {
	}

	public Like(String openid, String kind, String articleId) {
		this.openid = openid;
		this.kind = kind;
		this.articleId = articleId;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
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
