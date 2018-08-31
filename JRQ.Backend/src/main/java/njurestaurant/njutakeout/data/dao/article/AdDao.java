package njurestaurant.njutakeout.data.dao.article;

import njurestaurant.njutakeout.entity.article.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdDao extends JpaRepository<Ad, String> {
	List<Ad> findAdsByChecked(boolean checked);
}
