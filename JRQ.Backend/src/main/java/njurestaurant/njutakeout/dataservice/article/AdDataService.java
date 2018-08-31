package njurestaurant.njutakeout.dataservice.article;

import njurestaurant.njutakeout.entity.article.Ad;
import njurestaurant.njutakeout.exception.NotExistException;

import java.util.List;

public interface AdDataService {

	boolean isAdExistent(String id);

	void saveAd(Ad ad);

	void addAd(Ad ad);

	Ad getAdById(String id) throws NotExistException;

	List<Ad> getCheckedAds();

	List<Ad> getAllAds();

	void deleteAdById(String id) throws NotExistException;
}
