package njurestaurant.njutakeout.entity.article;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="feed")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Feed {
	@Id
	@GeneratedValue(generator = "jpa-uuid")
	private String id; //文章编号

//	@Column
//	private String title; //文章标题
//
//	@Column(length = 600)
//	private String content; //文章内容

	@Column
	private String linkMan;//联系人

	@Column
	private String phone;//联系电话

	@Column
	private String agencyName;//机构名

	@Column
	private String projectRef;//项目关联

	@Column
	private String projectInfo;//项目信息

	@Column
	@ElementCollection(targetClass = String.class)
	private List<String> images; //文章图片路径集合（不超过3张）

	@Column
	private String writerOpenid; //作者微信openid

	@Column
	private long timeStamp; //文章发布日期，如"2018-1-1"

	@Column
	private long likeNum; //文章点赞数

	@Column
	private long viewNum; //文章浏览量

	@Column
	private Boolean isPreferred; //是否是均融优选

	public Feed() {
	}

	public Feed(String linkMan, String phone, String agencyName, String projectRef, String projectInfo, List<String> images, String writerOpenid, long timeStamp, long likeNum, long viewNum, Boolean isPreferred) {
		this.linkMan = linkMan;
		this.phone = phone;
		this.agencyName = agencyName;
		this.projectRef = projectRef;
		this.projectInfo = projectInfo;
		this.images = images;
		this.writerOpenid = writerOpenid;
		this.timeStamp = timeStamp;
		this.likeNum = likeNum;
		this.viewNum = viewNum;
		this.isPreferred = isPreferred;
	}

	//	public Feed(String title, String content, List<String> images, String writerOpenid, long timeStamp, long likeNum, long viewNum, Boolean isPreferred) {
//		this.title = title;
//		this.content = content;
//		this.images = images;
//		this.writerOpenid = writerOpenid;
//		this.timeStamp = timeStamp;
//		this.likeNum = likeNum;
//		this.viewNum = viewNum;
//		this.isPreferred = isPreferred;
//	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

//	public String getTitle() {
//		return title;
//	}
//
//	public void setTitle(String title) {
//		this.title = title;
//	}
//
//	public String getContent() {
//		return content;
//	}
//
//	public void setContent(String content) {
//		this.content = content;
//	}


	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public String getProjectRef() {
		return projectRef;
	}

	public void setProjectRef(String projectRef) {
		this.projectRef = projectRef;
	}

	public String getProjectInfo() {
		return projectInfo;
	}

	public void setProjectInfo(String projectInfo) {
		this.projectInfo = projectInfo;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public String getWriterOpenid() {
		return writerOpenid;
	}

	public void setWriterOpenid(String writerOpenid) {
		this.writerOpenid = writerOpenid;
	}

	public String getDate() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.format(new Date(timeStamp));
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public long getLikeNum() {
		return likeNum;
	}

	public void setLikeNum(long likeNum) {
		this.likeNum = likeNum;
	}

	public long getViewNum() {
		return viewNum;
	}

	public void setViewNum(long viewNum) {
		this.viewNum = viewNum;
	}

	public Boolean getPreferred() {
		return isPreferred;
	}

	public void setPreferred(Boolean preferred) {
		isPreferred = preferred;
	}
}
