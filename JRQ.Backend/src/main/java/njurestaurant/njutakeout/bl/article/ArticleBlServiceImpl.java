package njurestaurant.njutakeout.bl.article;

import njurestaurant.njutakeout.blservice.article.ArticleBlService;
import njurestaurant.njutakeout.dataservice.admin.AdminDataService;
import njurestaurant.njutakeout.dataservice.article.*;
import njurestaurant.njutakeout.dataservice.count.CountDataService;
import njurestaurant.njutakeout.dataservice.user.UserDataService;
import njurestaurant.njutakeout.entity.article.Course;
import njurestaurant.njutakeout.entity.article.Document;
import njurestaurant.njutakeout.entity.article.Feed;
import njurestaurant.njutakeout.entity.article.Project;
import njurestaurant.njutakeout.entity.count.Count;
import njurestaurant.njutakeout.entity.user.User;
import njurestaurant.njutakeout.exception.AlreadyExistException;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.article.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ArticleBlServiceImpl implements ArticleBlService {
	private final CourseDataService courseDataService;
	private final DocumentDataService documentDataService;
	private final ProjectDataService projectDataService;
	private final FeedDataService feedDataService;
	private final UserDataService userDataService;
	private final LikeDataService likeDataService;
	private final AdminDataService adminDataService;
	private final CountDataService countDataService;

	@Autowired
	public ArticleBlServiceImpl(CourseDataService courseDataService, DocumentDataService documentDataService, ProjectDataService projectDataService, FeedDataService feedDataService, UserDataService userDataService, LikeDataService likeDataService, AdminDataService adminDataService, CountDataService countDataService) {
		this.courseDataService = courseDataService;
		this.documentDataService = documentDataService;
		this.projectDataService = projectDataService;
		this.feedDataService = feedDataService;
		this.userDataService = userDataService;
		this.likeDataService = likeDataService;
		this.adminDataService = adminDataService;
		this.countDataService = countDataService;
	}

	@Override
	public AbstractListResponse getAbstractList(String kind, String openid) throws NotExistException {
		List<AbstractItem> abstractItems = new ArrayList<>();
		switch (kind) {
			case "course":
				List<Course> courses = courseDataService.getAllCourses();
				for (Course course:courses) {
					boolean hasLiked = likeDataService.isLikeExistent(openid, kind, course.getId());
					abstractItems.add(new AbstractItem(course, adminDataService, hasLiked));
				}
				break;
			case "document":
				List<Document> documents = documentDataService.getAllDocuments();
				for (Document document:documents) {
					boolean hasLiked = likeDataService.isLikeExistent(openid, kind, document.getId());
					abstractItems.add(new AbstractItem(document, adminDataService, hasLiked));
				}
				break;
			case "project":
				List<Project> projects = projectDataService.getAllProjects();
				for (Project project:projects) {
					boolean hasLiked = likeDataService.isLikeExistent(openid, kind, project.getId());
					abstractItems.add(new AbstractItem(project, adminDataService, hasLiked));
				}
				break;
			case "feed":
				List<Feed> feeds = feedDataService.getAllFeeds();
				for (Feed feed:feeds) {
					boolean hasLiked = likeDataService.isLikeExistent(openid, kind, feed.getId());
					User user = userDataService.getUserByOpenid(feed.getWriterOpenid());
					abstractItems.add(new AbstractItem(feed, userDataService, hasLiked));
				}
				break;
			default: ;
		}
		return new AbstractListResponse(abstractItems);
	}

	@Override
	public AbstractListResponse getAbstractListByLikeNum(String openid, String kind ,String id) throws NotExistException{
		List<AbstractItem> abstractItems = new ArrayList<>();
		switch (kind) {
			case "course":
				List<Course> courses = courseDataService.getTop10CoursesOrderByLikeNum(openid, id);
				for (Course course:courses) {
					boolean hasLiked = likeDataService.isLikeExistent(openid, kind, course.getId());
					abstractItems.add(new AbstractItem(course, adminDataService, hasLiked));
				}
				break;
			case "document":
				List<Document> documents = documentDataService.getTop10DocumentsOrderByLikeNum(openid, id);
				for (Document document:documents) {
					boolean hasLiked = likeDataService.isLikeExistent(openid, kind, document.getId());
					abstractItems.add(new AbstractItem(document, adminDataService, hasLiked));
				}
				break;
			default: ;
		}
		return new AbstractListResponse(abstractItems);
	}

	@Override
	public AbstractListResponse getAbstractListBefore(String kind, String openid, String articleId, String articleType) throws NotExistException {
		List<AbstractItem> abstractItems = new ArrayList<>();
		Count count;
		if(countDataService.getCountById(1)==null){
			count=new Count(0,0,0,0,0,0,0);
		}
		else{
			count=countDataService.getCountById(1);
		}
		switch (kind) {
			case "course":
				List<Course> courses = courseDataService.getMyCourseListBefore(openid, articleId);
				for (Course course:courses) {
					boolean hasLiked = likeDataService.isLikeExistent(openid, kind, course.getId());
					abstractItems.add(new AbstractItem(course, adminDataService, hasLiked));
				}
				count.setViewCourse(count.getViewCourse()+1);//浏览课程页面次数+1
				break;
			case "document":
				List<Document> documents = documentDataService.getMyDocumentListBefore(openid, articleId);
				for (Document document:documents) {
					boolean hasLiked = likeDataService.isLikeExistent(openid, kind, document.getId());
					abstractItems.add(new AbstractItem(document, adminDataService, hasLiked));
				}
				count.setViewDocument(count.getViewDocument()+1);//浏览文档页面次数+1
				break;
			case "project":
				List<Project> projects = projectDataService.getMyProjectListBefore(openid, articleId);
				for (Project project:projects) {
					boolean hasLiked = likeDataService.isLikeExistent(openid, kind, project.getId());
					abstractItems.add(new AbstractItem(project, adminDataService, hasLiked));
				}
				count.setViewProject(count.getViewProject()+1);//浏览项目页面次数+1
				break;
			case "all":
				long timeStamp = -1;
				switch (articleType) {
					case "course": timeStamp = courseDataService.getCourseById(articleId).getTimeStamp(); break;
					case "document": timeStamp = documentDataService.getDocumentById(articleId).getTimeStamp(); break;
					case "project": timeStamp = projectDataService.getProjectById(articleId).getTimeStamp(); break;
					default: ;
				}

				List<Course> coursesPart = courseDataService.getMyCourseListBeforeTimeStamp(openid, timeStamp);
				List<Object> articles = new ArrayList<>(coursesPart);
				List<Long> articleTimeStamps = new ArrayList<>();
				coursesPart.forEach(c->articleTimeStamps.add(c.getTimeStamp()));

				List<Document> documentsPart = documentDataService.getMyDocumentListBeforeTimeStamp(openid, timeStamp);
				articles.addAll(documentsPart);
				documentsPart.forEach(d->articleTimeStamps.add(d.getTimeStamp()));

				List<Project> projectsPart = projectDataService.getMyProjectListBeforeTimeStamp(openid, timeStamp);
				articles.addAll(projectsPart);
				projectsPart.forEach(p->articleTimeStamps.add(p.getTimeStamp()));

				Map<Object, Integer> articleMap = new HashMap<>(); //文章->索引
				for(int i=0;i<articles.size();i++){
					articleMap.put(articles.get(i), i);
				}
				//按照相应的时间戳大小进行排序，从大到小
				articles.sort(Comparator.comparingLong(a -> articleTimeStamps.get(articleMap.get(a))).reversed());

				//将文章放入AbstractItems中
				for(Object a:articles) {
					boolean hasLiked = false;
					if (a.getClass()==Course.class) {
						hasLiked = likeDataService.isLikeExistent(openid, "course", ((Course)a).getId());
						abstractItems.add(new AbstractItem((Course) a, adminDataService, hasLiked));
					} else if (a.getClass()==Document.class) {
						hasLiked = likeDataService.isLikeExistent(openid, "document", ((Document)a).getId());
						abstractItems.add(new AbstractItem((Document) a, adminDataService, hasLiked));
					} else if (a.getClass()==Project.class) {
						hasLiked = likeDataService.isLikeExistent(openid, "project", ((Project)a).getId());
						abstractItems.add(new AbstractItem((Project) a, adminDataService, hasLiked));
					} else {
						throw new NotExistException("Article class", a.getClass().getName());
					}
				}
				count.setViewHomePage(count.getViewHomePage()+1);//浏览首页次数+1
				break;
			default: ;
		}
		countDataService.saveCount(count);
		return new AbstractListResponse(abstractItems);
	}

	@Override
	public AbstractListResponse getAbstractListByCondition(String openid, String condition) {
		List<AbstractItem> abstractItems = new ArrayList<>();
		for (Course course:courseDataService.getAllCourses()) {
			if (course.getTitle().contains(condition)) {
				boolean hasLiked = likeDataService.isLikeExistent(openid, "course", course.getId());
				abstractItems.add(new AbstractItem(course, adminDataService, hasLiked));
			}
		}
		for (Document document:documentDataService.getAllDocuments()) {
			if (document.getContent().contains(condition)) {
				boolean hasLiked = likeDataService.isLikeExistent(openid, "document", document.getId());
				abstractItems.add(new AbstractItem(document, adminDataService, hasLiked));
			}
		}
		for (Project project:projectDataService.getAllProjects()) {
			if (project.getTitle().contains(condition)) {
				boolean hasLiked = likeDataService.isLikeExistent(openid, "project", project.getId());
				abstractItems.add(new AbstractItem(project, adminDataService, hasLiked));
			}
		}
		return new AbstractListResponse(abstractItems);
	}

	@Override
	public ArticleResponse getArticle(String kind, String id) throws NotExistException {
		ArticleItem articleItem;
		switch (kind) {
			case "course": articleItem = new ArticleItem(courseDataService.getCourseById(id)); break;
			case "document": articleItem = new ArticleItem(documentDataService.getDocumentById(id)); break;
			case "project": articleItem = new ArticleItem(projectDataService.getProjectById(id)); break;
			case "feed": articleItem = new ArticleItem(feedDataService.getFeedById(id), userDataService); break;
			default: articleItem = new ArticleItem();
		}
		return new ArticleResponse(articleItem);
	}

	@Override
	public InfoResponse likePlus(String openid, String kind, String articleId) throws NotExistException, AlreadyExistException {
		if (!likeDataService.isLikeExistent(openid, kind, articleId)) {
			likeDataService.addLike(openid, kind, articleId);
			switch (kind) {
				case "course":
					Course course = courseDataService.getCourseById(articleId);
					course.setLikeNum(course.getLikeNum()+1);
					courseDataService.saveCourse(course);
					break;
				case "document":
					Document document = documentDataService.getDocumentById(articleId);
					document.setLikeNum(document.getLikeNum()+1);
					documentDataService.saveDocument(document);
					break;
				case "project":
					Project project = projectDataService.getProjectById(articleId);
					project.setLikeNum(project.getLikeNum()+1);
					projectDataService.saveProject(project);
					break;
				case "feed":
					Feed feed = feedDataService.getFeedById(articleId);
					feed.setLikeNum(feed.getLikeNum()+1);
					feedDataService.saveFeed(feed);
				default: ;
			}
		} else {
			likeDataService.deleteLike(openid, kind, articleId);
			switch (kind) {
				case "course":
					Course course = courseDataService.getCourseById(articleId);
					course.setLikeNum(course.getLikeNum()-1);
					courseDataService.saveCourse(course);
					break;
				case "document":
					Document document = documentDataService.getDocumentById(articleId);
					document.setLikeNum(document.getLikeNum()-1);
					documentDataService.saveDocument(document);
					break;
				case "project":
					Project project = projectDataService.getProjectById(articleId);
					project.setLikeNum(project.getLikeNum()-1);
					projectDataService.saveProject(project);
					break;
				case "feed":
					Feed feed = feedDataService.getFeedById(articleId);
					feed.setLikeNum(feed.getLikeNum()-1);
					feedDataService.saveFeed(feed);
				default: ;
			}
		}
		return new InfoResponse();
	}

}
