package njurestaurant.njutakeout.response.article;

import njurestaurant.njutakeout.response.Response;

public class GetAdResponse extends Response {
	private String ad; //首页广告图片路径
	private String link; //跳转链接

	public GetAdResponse(){
	}

	public GetAdResponse(String ad, String link) {
		this.ad = ad;
		this.link = link;
	}

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
}
