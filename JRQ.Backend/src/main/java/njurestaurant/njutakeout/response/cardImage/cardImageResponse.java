package njurestaurant.njutakeout.response.cardImage;

import njurestaurant.njutakeout.entity.cardImage.CardImage;
import njurestaurant.njutakeout.response.Response;

public class cardImageResponse extends Response {
    private CardImage cardImage;

    public cardImageResponse(){

    }

    public cardImageResponse(CardImage cardImage){
        this.cardImage=cardImage;
    }
    public CardImage getCardImage(){
        return cardImage;
    }

    public void setCardImage(CardImage cardImage){
        this.cardImage=cardImage;
    }



}
