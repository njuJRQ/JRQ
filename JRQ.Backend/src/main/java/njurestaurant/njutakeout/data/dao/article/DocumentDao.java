package njurestaurant.njutakeout.data.dao.article;

import njurestaurant.njutakeout.entity.article.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentDao extends JpaRepository<Document, String> {
}
