package njurestaurant.njutakeout.response.post;

import njurestaurant.njutakeout.entity.post.Postimage;
import njurestaurant.njutakeout.response.Response;

import java.util.List;

public class PostImageListResponse extends Response {
    private List<Postimage> postimageList;

    public PostImageListResponse(){

    }
    public PostImageListResponse(List<Postimage> postimageList){

        this.postimageList=postimageList;
    }

    public List<Postimage> getPostimageList() {
        return postimageList;
    }

    public void setPostimageList(List<Postimage> postimageList) {
        this.postimageList = postimageList;
    }
}
