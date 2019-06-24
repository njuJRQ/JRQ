package njurestaurant.njutakeout.response.post;

import njurestaurant.njutakeout.entity.cardImage.CardImage;
import njurestaurant.njutakeout.entity.post.Postimage;
import njurestaurant.njutakeout.response.Response;

public class PostImageResponse extends Response {
    private Postimage postimage;

    public PostImageResponse(){

    }
    public PostImageResponse(Postimage postimage){
        this.postimage=postimage;
    }

    public void setPostimage(Postimage postimage) {
        this.postimage = postimage;
    }

    public Postimage getPostimage() {
        return postimage;
    }
}
