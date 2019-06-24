package njurestaurant.njutakeout.bl.cardImage;

import njurestaurant.njutakeout.blservice.cardImage.CardImageBlService;
import njurestaurant.njutakeout.dataservice.card_image.CardimageDataService;
import njurestaurant.njutakeout.entity.cardImage.CardImage;
import njurestaurant.njutakeout.entity.job.JobCard;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.cardImage.cardImageResponse;
import njurestaurant.njutakeout.response.job.JobCardItem;
import njurestaurant.njutakeout.response.job.JobCardListResponse;
import njurestaurant.njutakeout.response.job.JobCardResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardImageBlServiceImpl implements CardImageBlService {
    private final CardimageDataService cardImageDataService;

    public CardImageBlServiceImpl(CardimageDataService cardImageDataService) {
        this.cardImageDataService = cardImageDataService;
    }

    @Override
    public InfoResponse add(String id,String path) {

        cardImageDataService.add(new CardImage(id,path));
        return new InfoResponse();
    }

    @Override
    public InfoResponse update(String id,String path) {
        return null;
    }

    @Override
    public InfoResponse deleteById(String id) throws NotExistException {
        cardImageDataService.deleteById(id);
        return new InfoResponse();
    }

    @Override
    public cardImageResponse findById(String id) throws NotExistException {
        return new cardImageResponse(cardImageDataService.findById(id));
    }

}
