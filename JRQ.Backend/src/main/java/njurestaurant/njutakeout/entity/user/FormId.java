package njurestaurant.njutakeout.entity.user;

import javax.persistence.*;

@Entity
@Table
@IdClass(FormIdKey.class)
public class FormId {
	@Id
	@Column(length = 28)
	private String openid; //用户的openid

	@Id
	@Column(length = 40)
	private String formId; //用户拥有的form_id，实测form_id长为32位，这里放长一些为40位

	@Column
	private long timestamp; //该form_id到期时间，由后端服务器产生，以毫秒为单位

	public FormId() {
	}

	public FormId(String openid, String formId, long timestamp) {
		this.openid = openid;
		this.formId = formId;
		this.timestamp = timestamp;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
}
