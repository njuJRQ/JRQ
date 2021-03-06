package njurestaurant.njutakeout.entity.article;

import java.io.Serializable;

public class LikeKey implements Serializable {
	private String openid; //微信用户ID
	private String kind; //文章类别："course", "document", "project", "feed"
	private String articleId; //文章ID

	public LikeKey() {
	}

	public LikeKey(String openid, String kind, String articleId) {
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
