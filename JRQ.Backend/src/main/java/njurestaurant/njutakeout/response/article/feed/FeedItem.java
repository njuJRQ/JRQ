package njurestaurant.njutakeout.response.article.feed;

import njurestaurant.njutakeout.entity.article.Feed;

import java.util.List;

public class FeedItem {
	private String id; //文章编号
//	private String title; //文章标题
//	private String content; //文章内容
	private String linkMan;//联系人
	private String phone;//电话
	private String agencyName;//机构名
	private String projectRef;//项目关联
	private String projectInfo;//项目信息
	private List<String> images; //文章图片路径集合（不超过3张）
	private String writerOpenid; //作者微信openid
	private String date; //文章发布日期，如"2018-1-1"
	private long likeNum; //文章点赞数
	private long viewNum; //文章浏览量

	public FeedItem(Feed feed) {
		this.id = feed.getId();
//		this.title = feed.getTitle();
//		this.content = feed.getContent();
		this.linkMan=feed.getLinkMan();
		this.phone=feed.getPhone();
		this.agencyName=feed.getAgencyName();
		this.projectRef=feed.getProjectRef();
		this.projectInfo=feed.getProjectInfo();
		this.images = feed.getImages();
		this.writerOpenid = feed.getWriterOpenid();
		this.date = feed.getDate();
		this.likeNum = feed.getLikeNum();
		this.viewNum = feed.getViewNum();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public long getViewNum() {
		return viewNum;
	}

	public void setViewNum(long viewNum) {
		this.viewNum = viewNum;
	}

}
