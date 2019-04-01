package njurestaurant.njutakeout.entity.business;

import njurestaurant.njutakeout.publicdatas.business.MarketType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class BusinessImage {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String id;
    @Column
    private MarketType marketType;
    @Column
    private String position;
    @Column
    private String image;

    public BusinessImage() {
    }

    public BusinessImage(MarketType marketType, String position, String image) {
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
