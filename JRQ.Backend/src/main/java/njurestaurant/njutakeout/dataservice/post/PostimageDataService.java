package njurestaurant.njutakeout.dataservice.post;

import njurestaurant.njutakeout.entity.cardImage.CardImage;
import njurestaurant.njutakeout.entity.post.Postimage;
import njurestaurant.njutakeout.exception.NotExistException;

import java.util.List;

public interface PostimageDataService {
    void add(Postimage postimage);
    void delete(String path) throws NotExistException;
    List<Postimage> findAll() throws NotExistException;

}
