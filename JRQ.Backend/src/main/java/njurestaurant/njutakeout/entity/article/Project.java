package njurestaurant.njutakeout.entity.article;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Project {
	@Id
	@GeneratedValue(generator = "jpa-uuid")
	private String id; //编号

	@Column
	private String title; //简介

	@Column
	private String writerName; //作者名字

	@Column
	private String identity; //身份

	@Column
	private String phone; //联系方式

	@Column
	private String city; //所在城市

	@Column
	private String industry; //所属行业

	@Column
	private String business; //业务标签

	@Column
	private String content; //项目详情

	@Column
	private int money; //项目资金

	@Column
	private String attachment; //附件路径

	@Column
	private String date; //发布日期

	@Column
	private long likeNum; //点赞数

	public Project(){
	}

	public Project(String title, String writerName, String identity, String phone, String city, String industry, String business, String content, int money, String attachment, String date, long likeNum) {
		this.title = title;
		this.writerName = writerName;
		this.identity = identity;
		this.phone = phone;
		this.city = city;
		this.industry = industry;
		this.business = business;
		this.content = content;
		this.money = money;
		this.attachment = attachment;
		this.date = date;
		this.likeNum = likeNum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriterName() {
		return writerName;
	}

	public void setWriterName(String writerName) {
		this.writerName = writerName;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public long getLikeNum() {
		return likeNum;
	}

	public void setLikeNum(long likeNum) {
		this.likeNum = likeNum;
	}
}
