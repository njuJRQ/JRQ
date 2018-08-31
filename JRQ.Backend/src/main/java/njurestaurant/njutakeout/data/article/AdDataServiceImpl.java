package njurestaurant.njutakeout.data.article;

import njurestaurant.njutakeout.data.dao.article.AdDao;
import njurestaurant.njutakeout.dataservice.article.AdDataService;
import njurestaurant.njutakeout.entity.article.Ad;
import njurestaurant.njutakeout.exception.NotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdDataServiceImpl implements AdDataService {
	private final AdDao adDao;

	@Autowired
	public AdDataServiceImpl(AdDao adDao) {
		this.adDao = adDao;
	}

	@Override
	public boolean isAdExistent(String id) {
		return adDao.existsById(id);
	}

	@Override
	public void saveAd(Ad ad) {
		adDao.save(ad);
	}

	@Override
	public void addAd(Ad ad) {
		adDao.save(ad);
	}

	@Override
	public Ad getAdById(String id) throws NotExistException {
		Optional<Ad> optionalAd = adDao.findById(id);
		if (optionalAd.isPresent()) {
			return optionalAd.get();
		} else {
			throw new NotExistException("Ad");
		}
	}

	@Override
	public List<Ad> getCheckedAds() {
		return adDao.findAdsByChecked(true);
	}

	@Override
	public List<Ad> getAllAds() {
		return adDao.findAll();
	}

	@Override
	public void deleteAdById(String id) throws NotExistException {
		if (adDao.existsById(id)) {
			adDao.deleteById(id);
		} else {
			throw new NotExistException("Ad");
		}
	}
}
