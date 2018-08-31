package njurestaurant.njutakeout.data.dao.article;

import njurestaurant.njutakeout.entity.article.Like;
import njurestaurant.njutakeout.entity.article.LikeKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeDao extends JpaRepository<Like, LikeKey> {
}
