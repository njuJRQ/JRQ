package njurestaurant.njutakeout.dataservice.card_image;

import njurestaurant.njutakeout.entity.cardImage.CardImage;
import njurestaurant.njutakeout.exception.NotExistException;

public interface CardimageDataService {
    void add(CardImage cardImage);
//    void update(CardImage cardImage);
    void deleteById(String id) throws NotExistException;
    CardImage findById(String id) throws NotExistException;

}
