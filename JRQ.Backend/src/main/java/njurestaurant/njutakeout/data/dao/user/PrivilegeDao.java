package njurestaurant.njutakeout.data.dao.user;

import njurestaurant.njutakeout.entity.user.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeDao extends JpaRepository<Privilege, String> {
}
