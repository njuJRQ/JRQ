package njurestaurant.njutakeout.data.cardImage;

import njurestaurant.njutakeout.data.dao.cardImage.cardImageDao;
import njurestaurant.njutakeout.dataservice.card_image.CardimageDataService;
import njurestaurant.njutakeout.entity.cardImage.CardImage;
import njurestaurant.njutakeout.exception.NotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class cardImageServiceImpl implements CardimageDataService {

    private final cardImageDao cardImageDao;
    @Autowired
    public cardImageServiceImpl(cardImageDao cardImageDao) {
        this.cardImageDao = cardImageDao;
    }
    @Override
    public void add(CardImage cardImage) {
        cardImageDao.save(cardImage);
    }

//    @Override
//    public void update(CardImage cardImage) {
//        cardImageDao
//    }

    @Override
    public void deleteById(String id) throws NotExistException {
        if (cardImageDao.findById(id).isPresent()) {
            cardImageDao.deleteById(id);
        } else {
            throw new NotExistException("JobCard ID", id);
        }
    }

    @Override
    public CardImage findById(String id) throws NotExistException {
        Optional<CardImage> optionalCardImage=cardImageDao.findById(id);
        if(optionalCardImage.isPresent()){
            return optionalCardImage.get();
        }else {
            throw new NotExistException("cardImageBlService id:",id);
        }

    }
}
