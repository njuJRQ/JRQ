package njurestaurant.njutakeout.response.article.ad;

import njurestaurant.njutakeout.response.Response;

public class AdResponse extends Response {
	private AdItem ad;

	public AdResponse(){
	}

	public AdResponse(AdItem ad) {
		this.ad = ad;
	}

	public AdItem getAd() {
		return ad;
	}

	public void setAd(AdItem ad) {
		this.ad = ad;
	}
}
