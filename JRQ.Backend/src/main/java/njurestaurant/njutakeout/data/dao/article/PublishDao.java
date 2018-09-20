package njurestaurant.njutakeout.data.dao.article;

import njurestaurant.njutakeout.entity.article.Publish;
import njurestaurant.njutakeout.entity.article.PublishKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublishDao extends JpaRepository<Publish, PublishKey> {
	List<Publish> findPublishesByAdminId(String adminId);
}
