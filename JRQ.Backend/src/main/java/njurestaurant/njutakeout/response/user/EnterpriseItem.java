package njurestaurant.njutakeout.response.user;

import njurestaurant.njutakeout.entity.user.Enterprise;

public class EnterpriseItem {
	private String id; //企业ID

	private String name; //企业名称

	private String description; //企业简介

	private String licenseUrl; //营业执照URL

	private String openid; //用户微信编号

	private String adminId; //对应的管理员ID

	private String status; //审核状态

	private long submitTimestamp; //用户提交的时间戳

	private long verifyTimestamp; //后台审核的时间戳

	public EnterpriseItem() {
	}

	public EnterpriseItem(Enterprise enterprise) {
		this.id = enterprise.getId();
		this.name = enterprise.getName();
		this.description = enterprise.getDescription();
		this.licenseUrl = enterprise.getLicenseUrl();
		this.openid = enterprise.getOpenid();
		this.adminId = enterprise.getAdminId();
		this.status = enterprise.getStatus();
		this.submitTimestamp = enterprise.getSubmitTimestamp();
		this.verifyTimestamp = enterprise.getVerifyTimestamp();
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getSubmitTimestamp() {
		return submitTimestamp;
	}

	public void setSubmitTimestamp(long submitTimestamp) {
		this.submitTimestamp = submitTimestamp;
	}

	public long getVerifyTimestamp() {
		return verifyTimestamp;
	}

	public void setVerifyTimestamp(long verifyTimestamp) {
		this.verifyTimestamp = verifyTimestamp;
	}
}
