package njurestaurant.njutakeout.bl.article.ad;

import njurestaurant.njutakeout.blservice.article.ad.AdBlService;
import njurestaurant.njutakeout.dataservice.article.AdDataService;
import njurestaurant.njutakeout.entity.article.Ad;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.article.ad.AdItem;
import njurestaurant.njutakeout.response.article.ad.AdListResponse;
import njurestaurant.njutakeout.response.article.ad.AdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdBlServiceImpl implements AdBlService {
	private final AdDataService adDataService;

	@Autowired
	public AdBlServiceImpl(AdDataService adDataService) {
		this.adDataService = adDataService;
	}

	@Override
	public InfoResponse addAd(String image, String link, String showPlace) {
		if (adDataService.getCheckedAds(showPlace).isEmpty()) { //showPlace位置没有广告
			adDataService.addAd(new Ad(image, link, true, showPlace));
		} else { //showPlace位置已有广告
			adDataService.addAd(new Ad(image, link, false, showPlace));
		}
		return new InfoResponse();
	}

	@Override
	public AdResponse getAd(String id) throws NotExistException {
		return new AdResponse(new AdItem(adDataService.getAdById(id)));
	}

	@Override
	public AdResponse getCheckedAd(String showPlace) {
		return new AdResponse(new AdItem(adDataService.getCheckedAds(showPlace).get(0)));
	}

	@Override
	public AdListResponse getAdList() {
		List<Ad> ads = adDataService.getAllAds();
		List<AdItem> adItems = new ArrayList<>();
		for(Ad ad:ads) {
			adItems.add(new AdItem(ad));
		}
		return new AdListResponse(adItems);
	}

	@Override
	public InfoResponse setCheckedAd(String id) throws NotExistException {
		//先检查该id是否存在，避免id不存在导致没有项checked为true
		if (!adDataService.isAdExistent(id)) {
			throw new NotExistException("Ad ID", id);
		} else {
			Ad ad = adDataService.getAdById(id);
			List<Ad> ads = adDataService.getShowPlaceAds(ad.getShowPlace());
			for(Ad a:ads) {
				a.setChecked(false);
				adDataService.saveAd(a);
			}
			ad.setChecked(true);
			adDataService.saveAd(ad);
			return new InfoResponse();
		}
	}

	@Override
	public InfoResponse updateAd(String id, String image, String link, String showPlace) throws NotExistException {
		Ad ad = adDataService.getAdById(id);
		ad.setImage(image);
		ad.setLink(link);
		ad.setShowPlace(showPlace);
		adDataService.saveAd(ad);
		return new InfoResponse();
	}

	@Override
	public InfoResponse deleteAd(String id) throws NotExistException {
		adDataService.deleteAdById(id);
		return new InfoResponse();
	}
}
