package njurestaurant.njutakeout.response.account;

import njurestaurant.njutakeout.response.Response;

public class AvatarSaveResponse extends Response {
    private String info;

    public AvatarSaveResponse() {
        this.info = "success";
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
