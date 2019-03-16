package njurestaurant.njutakeout.entity.partnership;

import javax.persistence.Embeddable;

@Embeddable
public class IdentityImage {
    private String url;

    public IdentityImage() {
    }

    public IdentityImage(String url) {
        this.url = url;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
