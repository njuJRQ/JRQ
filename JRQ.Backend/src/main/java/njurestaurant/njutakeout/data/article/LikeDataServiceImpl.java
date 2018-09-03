package njurestaurant.njutakeout.data.article;

import njurestaurant.njutakeout.data.dao.article.LikeDao;
import njurestaurant.njutakeout.dataservice.article.LikeDataService;
import njurestaurant.njutakeout.entity.article.Like;
import njurestaurant.njutakeout.entity.article.LikeKey;
import njurestaurant.njutakeout.exception.AlreadyExistException;
import njurestaurant.njutakeout.exception.NotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeDataServiceImpl implements LikeDataService {
	private final LikeDao likeDao;

	@Autowired
	public LikeDataServiceImpl(LikeDao likeDao) {
		this.likeDao = likeDao;
	}

	@Override
	public boolean isLikeExistent(String openid, String kind, String articleId) {
		return likeDao.existsById(new LikeKey(openid, kind, articleId));
	}

	@Override
	public void addLike(String openid, String kind, String articleId) throws AlreadyExistException {
		if (!likeDao.existsById(new LikeKey(openid, kind, articleId))) {
			likeDao.save(new Like(openid, kind, articleId));
		} else {
			throw new AlreadyExistException("Like");
		}
	}

	@Override
	public void deleteLike(String openid, String kind, String articleId) throws NotExistException {
		if (likeDao.existsById(new LikeKey(openid, kind, articleId))) {
			likeDao.deleteById(new LikeKey(openid, kind, articleId));
		} else {
			throw new NotExistException("Like key", "(openid="+openid+",kind="+kind+",articleId="+articleId+")");
		}
	}
}
