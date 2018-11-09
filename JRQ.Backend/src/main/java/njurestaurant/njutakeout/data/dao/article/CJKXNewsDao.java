package njurestaurant.njutakeout.data.dao.article;

import njurestaurant.njutakeout.entity.article.CJKXNews;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CJKXNewsDao extends JpaRepository<CJKXNews, String> {
}
