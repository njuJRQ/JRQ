package njurestaurant.njutakeout.bl.article;

import njurestaurant.njutakeout.blservice.article.ArticleBlService;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.article.*;

public class ArticleBlServiceImpl implements ArticleBlService {

	@Override
	public GetAbstractsResponse getAbstracts(String kind) {
		return null;
	}

	@Override
	public GetOneArticleResponse getOneArticleById(long id) {
		return null;
	}

	@Override
	public GetAdResponse getAd() {
		return null;
	}

	@Override
	public LikePlusResponse likePlus(long id, String openId) {
		return null;
	}

	@Override
	public InfoResponse addCourse(String title, String image, String writerName, String date, long likeNum, String video, int price) {
		return null;
	}

	@Override
	public CourseResponse getCourse(long id) {
		return null;
	}

	@Override
	public CourseListResponse getCourseList() {
		return null;
	}

	@Override
	public InfoResponse updateCourse(long id, String title, String image, String writerName, String date, long likeNum, String video, int price) {
		return null;
	}

	@Override
	public InfoResponse deleteCourse(long id) {
		return null;
	}

	@Override
	public InfoResponse addDocument(String title, String writerName, String date, long likeNum) {
		return null;
	}

	@Override
	public DocumentResponse getDocument(long id) {
		return null;
	}

	@Override
	public DocumentListResponse getDocumentList() {
		return null;
	}

	@Override
	public InfoResponse updateDocument(long id, String title, String writerName, String date, long likeNum) {
		return null;
	}

	@Override
	public InfoResponse deleteDocument(long id) {
		return null;
	}

	@Override
	public InfoResponse addProject(String title, String identity, String phone, String city, String industry, String business, String content, int money, String attachment) {
		return null;
	}

	@Override
	public ProjectResponse getProject(long id) {
		return null;
	}

	@Override
	public ProjectListResponse getProjectList() {
		return null;
	}

	@Override
	public InfoResponse updateProject(long id, String title, String identity, String phone, String city, String industry, String business, String content, int money, String attachment) {
		return null;
	}

	@Override
	public InfoResponse deleteProject(long id) {
		return null;
	}
}
