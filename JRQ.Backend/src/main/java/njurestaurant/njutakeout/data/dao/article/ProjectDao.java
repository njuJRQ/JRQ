package njurestaurant.njutakeout.data.dao.article;

import njurestaurant.njutakeout.entity.article.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectDao extends JpaRepository<Project, String> {
}
