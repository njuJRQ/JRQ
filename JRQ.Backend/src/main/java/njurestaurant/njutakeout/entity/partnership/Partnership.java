package njurestaurant.njutakeout.entity.partnership;

import njurestaurant.njutakeout.publicdatas.partnership.PartnerType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Partnership {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String id;

    @Column
    private String linkMan;

    @Column
    private String phone;

    @Column
    private String agencyName;

    @Column
    private String identityInfo;

    @Column
    private PartnerType type;

    @Column
    @ElementCollection(targetClass = IdentityImage.class)
    private List<IdentityImage> identityImages;

    public Partnership() {
    }

    public Partnership(String linkMan, String phone, String agencyName, String identityInfo,  PartnerType type, List<IdentityImage> identityImages) {
        this.linkMan = linkMan;
        this.phone = phone;
        this.agencyName = agencyName;
        this.identityInfo = identityInfo;
        this.type = type;
        this.identityImages = identityImages;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getIdentityInfo() {
        return identityInfo;
    }

    public void setIdentityInfo(String identityInfo) {
        this.identityInfo = identityInfo;
    }


    public PartnerType getType() {
        return type;
    }

    public void setType(PartnerType type) {
        this.type = type;
    }

    public List<IdentityImage> getIdentityImages() {
        return identityImages;
    }

    public void setIdentityImages(List<IdentityImage> identityImages) {
        this.identityImages = identityImages;
    }
}
