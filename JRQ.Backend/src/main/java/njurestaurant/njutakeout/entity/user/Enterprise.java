package njurestaurant.njutakeout.entity.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Enterprise {

	@Id
	@Column
	private String id; //企业ID

	@Column
	private String name; //企业名称

	@Column
	private String description; //企业简介

	@Column
	private String licenseUrl; //营业执照URL

	@Column
	private String openid; //用户微信编号

	@Column
	private String adminId; //对应的管理员ID

	@Column
	private boolean hasVerified; //是否审核通过

	public Enterprise() {
	}

	public Enterprise(String id, String name, String description, String licenseUrl, String openid, String adminId, boolean hasVerified) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.licenseUrl = licenseUrl;
		this.openid = openid;
		this.adminId = adminId;
		this.hasVerified = hasVerified;
	}

	public Enterprise(String openid, String adminId) {
		this.openid = openid;
		this.adminId = adminId;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLicenseUrl() {
		return licenseUrl;
	}

	public void setLicenseUrl(String licenseUrl) {
		this.licenseUrl = licenseUrl;
	}

	public boolean isHasVerified() {
		return hasVerified;
	}

	public void setHasVerified(boolean hasVerified) {
		this.hasVerified = hasVerified;
	}
}
