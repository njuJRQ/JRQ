package njurestaurant.njutakeout.data.dao.user;

import njurestaurant.njutakeout.entity.user.Level;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LevelDao extends JpaRepository<Level, String> {
}
