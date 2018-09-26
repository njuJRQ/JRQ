package njurestaurant.njutakeout.response.article.ad;

import njurestaurant.njutakeout.entity.article.Ad;

public class AdItem {
	private String id;
	private String image; //图片路径
	private String link; //广告导向的链接
	private String showPlace; //广告显示位置

	public AdItem() {
	}

	public AdItem(Ad ad) {
		this.id = ad.getId();
		this.image = ad.getImage();
		this.link = ad.getLink();
		this.showPlace = ad.getShowPlace();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getShowPlace() {
		return showPlace;
	}

	public void setShowPlace(String showPlace) {
		this.showPlace = showPlace;
	}
}
