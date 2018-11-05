package njurestaurant.njutakeout.response.user;

import njurestaurant.njutakeout.dataservice.admin.AdminDataService;
import njurestaurant.njutakeout.dataservice.user.EnterpriseDataService;
import njurestaurant.njutakeout.entity.admin.Admin;
import njurestaurant.njutakeout.entity.user.Enterprise;
import njurestaurant.njutakeout.exception.NotExistException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EnterpriseItem {
	private String id; //企业ID

	private String name; //企业名称

	private String description; //企业简介

	private String licenseUrl; //营业执照URL

	private String openid; //用户微信编号

	private String adminId; //对应的管理员ID

	private String adminUsername; //对应的管理员用户名

	private String adminPassword; //对应的管理员密码

	private String status; //审核状态

	private String submitTime; //用户提交的时间

	private String verifyTime; //后台审核的时间

	public EnterpriseItem() {
	}

	public EnterpriseItem(Enterprise enterprise, AdminDataService adminDataService) {
		this.id = enterprise.getId();
		this.name = enterprise.getName();
		this.description = enterprise.getDescription();
		this.licenseUrl = enterprise.getLicenseUrl();
		this.openid = enterprise.getOpenid();
		this.adminId = enterprise.getAdminId();
		if (enterprise.getId()!=null && !enterprise.getId().equals("")) { //首先查看是否在admin列表中
			try {
				Admin admin = adminDataService.getAdminById(enterprise.getAdminId());
				this.adminUsername = admin.getUsername();
				this.adminPassword = admin.getPassword();
			} catch (NotExistException e) {
				this.adminUsername = enterprise.getAdminUsername();
				this.adminPassword = enterprise.getAdminPassword();
			}
		} else {
			this.adminUsername = enterprise.getAdminUsername();
			this.adminPassword = enterprise.getAdminPassword();
		}
		this.status = enterprise.getStatus();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.submitTime = simpleDateFormat.format(new Date(enterprise.getSubmitTimestamp()));
		if (this.status.equals("verified")) {
			this.verifyTime = simpleDateFormat.format(new Date(enterprise.getVerifyTimestamp()));
		} else {
			this.verifyTime = "无相关信息";
		}
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

	public String getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}

	public String getVerifyTime() {
		return verifyTime;
	}

	public void setVerifyTime(String verifyTime) {
		this.verifyTime = verifyTime;
	}
}
