package njurestaurant.njutakeout.response.article;

import njurestaurant.njutakeout.dataservice.admin.AdminDataService;
import njurestaurant.njutakeout.dataservice.user.UserDataService;
import njurestaurant.njutakeout.entity.article.Course;
import njurestaurant.njutakeout.entity.article.Document;
import njurestaurant.njutakeout.entity.article.Feed;
import njurestaurant.njutakeout.entity.article.Project;
import njurestaurant.njutakeout.entity.user.User;
import njurestaurant.njutakeout.exception.NotExistException;

import java.util.Collections;
import java.util.List;

public class AbstractItem {
	private String id; //文章编号
	private String title; //文章标题
	private List<String> images; //文章图片路径集合（不超过3张）
	private String writerFace; //作者头像图片路径（可能为空）
	private String writerName; //作者名字
	private String date; //文章发布日期，如“2018-1-1”
	private long likeNum; //文章点赞数
	private String kind; //文章类型，可能值：course，document，project
	private boolean hasLiked; //用户是否已经点赞

	public AbstractItem(){
	}

	public AbstractItem(String id, String title, List<String> images, String writerFace, String writerName, String date, long likeNum, String kind) {
		this.id = id;
		this.title = title;
		this.images = images;
		this.writerFace = writerFace;
		this.writerName = writerName;
		this.date = date;
		this.likeNum = likeNum;
		this.kind = kind;
	}

	public AbstractItem(Course course, AdminDataService adminDataService, boolean hasLiked) {
		this.id = course.getId();
		this.title = course.getTitle();
		this.images = Collections.singletonList(course.getImage());
		try {
			this.writerFace = adminDataService.getAdminByUsername(course.getWriterName()).getFace();
		} catch (NotExistException e) {
			this.writerFace = "";
		}
		this.writerName = course.getWriterName();
		this.date = course.getDate();
		this.likeNum = course.getLikeNum();
		this.kind = "course";
		this.hasLiked = hasLiked;
	}

	public AbstractItem(Document document, AdminDataService adminDataService, boolean hasLiked) {
		this.id = document.getId();
		this.title = document.getTitle();
		this.images = Collections.emptyList();
		try {
			this.writerFace = adminDataService.getAdminByUsername(document.getWriterName()).getFace();
		} catch (NotExistException e) {
			this.writerFace = "";
		}
		this.writerName = document.getWriterName();
		this.date = document.getDate();
		this.likeNum = document.getLikeNum();
		this.kind = "document";
		this.hasLiked = hasLiked;
	}

	public AbstractItem(Project project, AdminDataService adminDataService, boolean hasLiked) {
		this.id = project.getId();
		this.title = project.getTitle();
		this.images = Collections.emptyList();
		try {
			this.writerFace = adminDataService.getAdminByUsername(project.getWriterName()).getFace();
		} catch (NotExistException e) {
			this.writerFace = "";
		}
		this.writerName = project.getWriterName();
		this.date = project.getDate();
		this.likeNum = project.getLikeNum();
		this.kind = "project";
		this.hasLiked = hasLiked;
	}

	public AbstractItem(Feed feed, UserDataService userDataService, boolean hasLiked) throws NotExistException {
		this.id = feed.getId();
		this.title = feed.getContent();
		this.images = feed.getImages();
		User user = userDataService.getUserByOpenid(feed.getWriterOpenid());
		this.writerFace = user.getFace();
		this.writerName = user.getUsername();
		this.date = feed.getDate();
		this.likeNum = feed.getLikeNum();
		this.kind = "feed";
		this.hasLiked = hasLiked;
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

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public String getWriterFace() {
		return writerFace;
	}

	public void setWriterFace(String writerFace) {
		this.writerFace = writerFace;
	}

	public String getWriterName() {
		return writerName;
	}

	public void setWriterName(String writerName) {
		this.writerName = writerName;
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

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}
}
