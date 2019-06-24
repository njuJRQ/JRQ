package njurestaurant.njutakeout.blservice.post;

import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.cardImage.cardImageResponse;
import njurestaurant.njutakeout.response.post.PostImageListResponse;
import njurestaurant.njutakeout.response.post.PostImageResponse;

public interface PostImageBlService {
    InfoResponse add(String path);

    InfoResponse delete(String path) throws NotExistException;

    PostImageListResponse findAll() throws NotExistException;

}
