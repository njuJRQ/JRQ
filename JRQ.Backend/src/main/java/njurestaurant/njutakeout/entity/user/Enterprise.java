package njurestaurant.njutakeout.entity.user;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Enterprise {

	@Id
	@Column
	@GeneratedValue(generator = "jpa-uuid")
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
	private String adminId; //对应的管理员ID，管理员审核之前为""

	@Column
	private String adminUsername; //提交时设置的管理员username

	@Column
	private String adminPassword; //提交时设置的管理员password

	/**
	 * 审核状态
	 * "submitted"：已提交申请，等待管理员审核
	 * "verified"：已通过申请，具有企业用户身份
	 * "rejected"：申请被拒绝（此状态下的记录将会在用户收到消息后被删除）
	 * "disqualified"：原先是企业管理员，但被取消了资格（此状态下的记录将会在用户收到消息后被删除）
	 */
	@Column
	private String status;

	@Column
	private long submitTimestamp; //用户提交的时间戳

	@Column
	private long verifyTimestamp; //后台审核的时间戳（审核结果可能是"verified"，"rejected"，"disqualified"）

	public Enterprise() {
	}

	public Enterprise(String name, String description, String licenseUrl, String openid, String adminId, String adminUsername, String adminPassword, String status, long submitTimestamp) {
		this.name = name;
		this.description = description;
		this.licenseUrl = licenseUrl;
		this.openid = openid;
		this.adminId = adminId;
		this.adminUsername = adminUsername;
		this.adminPassword = adminPassword;
		this.status = status;
		this.submitTimestamp = submitTimestamp;
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

	public String getAdminUsername() {
		return adminUsername;
	}

	public void setAdminUsername(String adminUsername) {
		this.adminUsername = adminUsername;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
}
