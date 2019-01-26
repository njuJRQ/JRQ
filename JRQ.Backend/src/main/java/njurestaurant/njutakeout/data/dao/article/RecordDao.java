package njurestaurant.njutakeout.data.dao.article;

import njurestaurant.njutakeout.entity.article.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordDao extends JpaRepository<Record, String> {
}
