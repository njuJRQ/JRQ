package njurestaurant.njutakeout.data.dao.admin;

import njurestaurant.njutakeout.entity.admin.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminDao extends JpaRepository<Admin, String> {
	List<Admin> findAdminByUsername(String username);
}
