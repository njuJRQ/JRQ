package njurestaurant.njutakeout.data.dao.user;

import njurestaurant.njutakeout.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, String> {
}
