package njurestaurant.njutakeout.data.dao.user;

import njurestaurant.njutakeout.entity.user.ClassificationDescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClassificationDescriptionDao extends JpaRepository<ClassificationDescription, String> {
	Optional<ClassificationDescription> findClassificationDescriptionByWorkClass(String workClass);
}
