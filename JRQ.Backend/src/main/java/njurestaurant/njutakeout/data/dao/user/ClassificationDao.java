package njurestaurant.njutakeout.data.dao.user;

import njurestaurant.njutakeout.entity.user.Classification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassificationDao extends JpaRepository<Classification, String> {
}
