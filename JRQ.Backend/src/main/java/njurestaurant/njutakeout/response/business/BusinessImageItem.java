package njurestaurant.njutakeout.response.business;

import njurestaurant.njutakeout.entity.business.BusinessImage;
import njurestaurant.njutakeout.publicdatas.business.MarketType;

public class BusinessImageItem {
    private String id;
    private MarketType marketType;
    private String position;
    private String image;

    public BusinessImageItem() {
    }

    public BusinessImageItem(BusinessImage businessImage) {
        this.id=businessImage.getId();
        this.marketType=businessImage.getMarketType();
        this.position=businessImage.getPosition();
        this.image=businessImage.getImage();
    }

    public BusinessImageItem(String id, MarketType marketType, String position, String image) {
        this.id = id;
        this.marketType = marketType;
        this.position = position;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MarketType getMarketType() {
        return marketType;
    }

    public void setMarketType(MarketType marketType) {
        this.marketType = marketType;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
