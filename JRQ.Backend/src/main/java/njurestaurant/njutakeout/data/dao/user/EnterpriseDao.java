package njurestaurant.njutakeout.data.dao.user;

import njurestaurant.njutakeout.entity.user.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnterpriseDao extends JpaRepository<Enterprise, String> {
}
