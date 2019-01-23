package njurestaurant.njutakeout.data.dao.count;

import njurestaurant.njutakeout.entity.count.Count;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountDao extends JpaRepository<Count,String> {
    Count getCountById(int id);
}
