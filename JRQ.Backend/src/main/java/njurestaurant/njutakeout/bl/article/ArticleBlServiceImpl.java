package njurestaurant.njutakeout.bl.article;

import njurestaurant.njutakeout.blservice.article.ArticleBlService;
import njurestaurant.njutakeout.dataservice.article.*;
import njurestaurant.njutakeout.dataservice.user.UserDataService;
import njurestaurant.njutakeout.entity.article.Course;
import njurestaurant.njutakeout.entity.article.Document;
import njurestaurant.njutakeout.entity.article.Feed;
import njurestaurant.njutakeout.entity.article.Project;
import njurestaurant.njutakeout.entity.user.User;
import njurestaurant.njutakeout.exception.AlreadyExistException;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.article.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleBlServiceImpl implements ArticleBlService {
	private final CourseDataService courseDataService;
	private final DocumentDataService documentDataService;
	private final ProjectDataService projectDataService;
	private final FeedDataService feedDataService;
	private final UserDataService userDataService;
	private final LikeDataService likeDataService;

	@Autowired
	public ArticleBlServiceImpl(CourseDataService courseDataService, DocumentDataService documentDataService, ProjectDataService projectDataService, FeedDataService feedDataService, UserDataService userDataService, LikeDataService likeDataService) {
		this.courseDataService = courseDataService;
		this.documentDataService = documentDataService;
		this.projectDataService = projectDataService;
		this.feedDataService = feedDataService;
		this.userDataService = userDataService;
		this.likeDataService = likeDataService;
	}

	@Override
	public AbstractListResponse getAbstractList(String kind, String openid) throws NotExistException {
		List<AbstractItem> abstractItems = new ArrayList<>();
		switch (kind) {
			case "course":
				List<Course> courses = courseDataService.getAllCourses();
				for (Course course:courses) {
					abstractItems.add(new AbstractItem(course));
				}
				break;
			case "document":
				List<Document> documents = documentDataService.getAllDocuments();
				for (Document document:documents) {
					abstractItems.add(new AbstractItem(document));
				}
				break;
			case "project":
				List<Project> projects = projectDataService.getAllProjects();
				for (Project project:projects) {
					abstractItems.add(new AbstractItem(project));
				}
				break;
			case "feed":
				List<Feed> feeds = feedDataService.getAllFeeds();
				for (Feed feed:feeds) {
					User user = userDataService.getUserByOpenid(feed.getWriterOpenid());
					abstractItems.add(new AbstractItem(feed, userDataService));
				}
				break;
			default: ;
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
