package njurestaurant.njutakeout.entity.article;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Project {
	@Id
	@GeneratedValue(generator = "jpa-uuid")
	private long id; //编号

	@Column(name = "title")
	private String title; //简介

	@Column(name = "identity")
	private String identity; //身份

	@Column(name = "phone")
	private String phone; //联系方式

	@Column(name = "city")
	private String city; //所在城市

	@Column(name = "industry")
	private String industry; //所属行业

	@Column(name = "business")
	private String business; //业务标签

	@Column(name = "content")
	private String content; //项目详情

	@Column(name = "money")
	private int money; //项目资金

	@Column(name = "attachment")
	private String attachment; //附件路径

	public Project(){
	}

	public Project(String title, String identity, String phone, String city, String industry, String business, String content, int money, String attachment) {
		this.title = title;
		this.identity = identity;
		this.phone = phone;
		this.city = city;
		this.industry = industry;
		this.business = business;
		this.content = content;
		this.money = money;
		this.attachment = attachment;
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
