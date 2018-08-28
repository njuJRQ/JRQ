package njurestaurant.njutakeout.bl.article;

import njurestaurant.njutakeout.blservice.article.ArticleBlService;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.article.*;
import njurestaurant.njutakeout.response.article.course.CourseListResponse;
import njurestaurant.njutakeout.response.article.course.CourseResponse;
import njurestaurant.njutakeout.response.article.document.DocumentListResponse;
import njurestaurant.njutakeout.response.article.document.DocumentResponse;
import njurestaurant.njutakeout.response.article.project.ProjectListResponse;
import njurestaurant.njutakeout.response.article.project.ProjectResponse;

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

}
