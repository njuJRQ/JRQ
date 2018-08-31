package njurestaurant.njutakeout.response.article.ad;

import java.util.List;

public class AdListResponse {
	private List<AdItem> ads;

	public AdListResponse() {
	}

	public AdListResponse(List<AdItem> ads) {
		this.ads = ads;
	}

	public List<AdItem> getAds() {
		return ads;
	}

	public void setAds(List<AdItem> ads) {
		this.ads = ads;
	}
}
