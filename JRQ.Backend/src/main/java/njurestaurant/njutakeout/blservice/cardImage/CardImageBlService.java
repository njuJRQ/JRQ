package njurestaurant.njutakeout.blservice.cardImage;

import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.cardImage.cardImageResponse;
import njurestaurant.njutakeout.response.job.JobCardListResponse;
import njurestaurant.njutakeout.response.job.JobCardResponse;

public interface CardImageBlService {
    InfoResponse add(String id,String path);

    InfoResponse update(String id,String path) ;

    InfoResponse deleteById(String id) throws NotExistException;

    cardImageResponse findById(String id) throws NotExistException;

}
