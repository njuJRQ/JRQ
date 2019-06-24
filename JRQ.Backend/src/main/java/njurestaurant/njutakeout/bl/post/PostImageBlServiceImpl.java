package njurestaurant.njutakeout.bl.post;

import njurestaurant.njutakeout.blservice.post.PostImageBlService;
import njurestaurant.njutakeout.dataservice.post.PostimageDataService;
import njurestaurant.njutakeout.entity.post.Postimage;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.post.PostImageListResponse;
import org.springframework.stereotype.Service;

@Service
public class PostImageBlServiceImpl implements PostImageBlService {
    private final PostimageDataService postimageDataService;

    public PostImageBlServiceImpl(PostimageDataService postimageDataService) {
        this.postimageDataService= postimageDataService;
    }

    @Override
    public InfoResponse add(String path) {

        postimageDataService.add(new Postimage(path));
        return new InfoResponse();
    }

    @Override
    public InfoResponse delete(String path) throws NotExistException {
        postimageDataService.delete(path);
        return new InfoResponse();
    }

    @Override
    public PostImageListResponse findAll() throws NotExistException {
        return new PostImageListResponse(postimageDataService.findAll());
    }
}
