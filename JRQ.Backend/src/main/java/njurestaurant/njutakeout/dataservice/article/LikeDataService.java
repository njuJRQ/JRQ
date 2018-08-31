package njurestaurant.njutakeout.dataservice.article;

import njurestaurant.njutakeout.exception.AlreadyExistException;
import njurestaurant.njutakeout.exception.NotExistException;

public interface LikeDataService {

	boolean isLikeExistent(String openid, String kind, String articleId);

	void addLike(String openid, String kind, String articleId) throws AlreadyExistException;

	void deleteLike(String openid, String kind, String articleId) throws NotExistException;

}
