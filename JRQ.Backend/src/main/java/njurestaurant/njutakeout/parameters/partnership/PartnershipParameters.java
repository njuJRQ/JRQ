package njurestaurant.njutakeout.parameters.partnership;

import njurestaurant.njutakeout.publicdatas.partnership.PartnerType;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public class PartnershipParameters {
    private String id;
    private String linkMan;
    private String phone;
    private String agencyName;
    private String identityInfo;
    private String type;
    private List<MultipartFile> img;

    public PartnershipParameters() {
    }

    public PartnershipParameters(String id, String linkMan, String phone, String agencyName, String identityInfo, String type, List<MultipartFile> img) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<MultipartFile> getImg() {
        return img;
    }

    public void setImg(List<MultipartFile> img) {
        this.img = img;
    }
}
