package njurestaurant.njutakeout.data.dao.user;

import njurestaurant.njutakeout.entity.user.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnterpriseDao extends JpaRepository<Enterprise, String> {
	Optional<Enterprise> findEnterpriseByAdminId(String adminId);
	Optional<Enterprise> findEnterpriseByOpenid(String openid);
}
