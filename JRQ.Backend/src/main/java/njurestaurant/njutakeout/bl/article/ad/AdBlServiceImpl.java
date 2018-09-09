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
	public InfoResponse addAd(String image, String link) {
		if (adDataService.getCheckedAds().isEmpty()) { //首页没有广告
			adDataService.addAd(new Ad(image, link, true));
		} else { //首页已有广告
			adDataService.addAd(new Ad(image, link, false));
		}
		return new InfoResponse();
	}

	@Override
	public AdResponse getAd(String id) throws NotExistException {
		return new AdResponse(new AdItem(adDataService.getAdById(id)));
	}

	@Override
	public AdResponse getCheckedAd() {
		return new AdResponse(new AdItem(adDataService.getCheckedAds().get(0)));
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
			List<Ad> ads = adDataService.getAllAds();
			for(Ad ad:ads) {
				ad.setChecked(false);
				adDataService.saveAd(ad);
			}
			adDataService.getAdById(id).setChecked(true);
			return new InfoResponse();
		}
	}

	@Override
	public InfoResponse updateAd(String id, String image, String link) throws NotExistException {
		Ad ad = adDataService.getAdById(id);
		ad.setImage(image);
		ad.setLink(link);
		adDataService.saveAd(ad);
		return new InfoResponse();
	}

	@Override
	public InfoResponse deleteAd(String id) throws NotExistException {
		adDataService.deleteAdById(id);
		return new InfoResponse();
	}
}
