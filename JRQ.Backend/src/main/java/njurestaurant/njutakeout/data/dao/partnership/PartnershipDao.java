package njurestaurant.njutakeout.data.dao.partnership;

import njurestaurant.njutakeout.entity.partnership.Partnership;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnershipDao extends JpaRepository<Partnership,String> {
}
