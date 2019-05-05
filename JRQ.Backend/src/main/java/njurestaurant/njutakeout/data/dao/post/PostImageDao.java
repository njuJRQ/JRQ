package njurestaurant.njutakeout.data.dao.post;


import njurestaurant.njutakeout.entity.post.Postimage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostImageDao extends JpaRepository<Postimage,String> {
    
}
