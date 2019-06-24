package njurestaurant.njutakeout.data.dao.cardImage;


import njurestaurant.njutakeout.entity.cardImage.CardImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface cardImageDao extends JpaRepository<CardImage,String> {
//    List<CardImage> findCardImageById(String id);
}
