package njurestaurant.njutakeout.response.partnership;

import njurestaurant.njutakeout.entity.partnership.Partnership;
import njurestaurant.njutakeout.publicdatas.partnership.PartnerType;

import java.util.ArrayList;
import java.util.List;

public class PartnershipItem {
    private String id;
    private String linkMan;
    private String phone;
    private String agencyName;
    private String identityInfo;
    private PartnerType type;
    private List<String> img;

    public PartnershipItem() {
    }

    public PartnershipItem(Partnership partnership) {
        this.id=partnership.getId();
        this.linkMan=partnership.getLinkMan();
        this.phone=partnership.getPhone();
        this.agencyName=partnership.getAgencyName();
        this.identityInfo=partnership.getIdentityInfo();
        this.type=partnership.getType();
        this.img=partnership.getImages();

    }

    public PartnershipItem(String id, String linkMan, String phone, String agencyName, String identityInfo, PartnerType type, List<String> img) {
        this.id = id;
        this.linkMan = linkMan;
        this.phone = phone;
        this.agencyName = agencyName;
        this.identityInfo = identityInfo;
        this.type = type;
        this.img = img;
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

    public List<String> getImg() {
        return img;
    }

    public void setImg(List<String> img) {
        this.img = img;
    }
}
