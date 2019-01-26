package njurestaurant.njutakeout.entity.user;

import java.io.Serializable;

public class FormIdKey implements Serializable {
	private String openid; //用户的openid
	private String formId; //用户拥有的form_id

	public FormIdKey() {
	}

	public FormIdKey(String openid, String formId) {
		this.openid = openid;
		this.formId = formId;
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
}
