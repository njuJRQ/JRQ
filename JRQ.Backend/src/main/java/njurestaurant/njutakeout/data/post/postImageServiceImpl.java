package njurestaurant.njutakeout.data.post;

import njurestaurant.njutakeout.data.dao.cardImage.cardImageDao;
import njurestaurant.njutakeout.data.dao.post.PostImageDao;
import njurestaurant.njutakeout.dataservice.card_image.CardimageDataService;
import njurestaurant.njutakeout.dataservice.post.PostimageDataService;
import njurestaurant.njutakeout.entity.cardImage.CardImage;
import njurestaurant.njutakeout.entity.post.Postimage;
import njurestaurant.njutakeout.exception.NotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class postImageServiceImpl implements PostimageDataService {

    private final PostImageDao postImageDao;
    @Autowired
    public postImageServiceImpl(PostImageDao postImageDao) {
        this.postImageDao = postImageDao;
    }

    public void add(Postimage postimage) {
        postImageDao.save(postimage);
    }
    @Override
    public void delete(String path) throws NotExistException {
        postImageDao.delete(new Postimage(path));
    }

    @Override
    public List<Postimage> findAll() throws NotExistException {
        return postImageDao.findAll();
    }
}
