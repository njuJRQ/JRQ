package njurestaurant.njutakeout.response.article;

import njurestaurant.njutakeout.entity.article.Project;

public class ProjectItem {
	private long id; //编号
	private String title; //简介
	private String identity; //身份
	private String phone; //联系方式
	private String city; //所在城市
	private String industry; //所属行业
	private String business; //业务标签
	private String content; //项目详情
	private int money; //项目资金
	private String attachment; //附件路径

	public ProjectItem(Project project){
		this.id = project.getId();
		this.title = project.getTitle();
		this.identity = project.getIdentity();
		this.phone = project.getPhone();
		this.city = project.getCity();
		this.industry = project.getIndustry();
		this.business = project.getBusiness();
		this.content = project.getContent();
		this.money = project.getMoney();
		this.attachment = project.getAttachment();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
}
