package njurestaurant.njutakeout.data.dao.admin;

import njurestaurant.njutakeout.entity.admin.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminDao extends JpaRepository<Admin, String> {
}
