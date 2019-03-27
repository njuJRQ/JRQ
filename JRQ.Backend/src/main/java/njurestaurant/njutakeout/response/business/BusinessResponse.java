package njurestaurant.njutakeout.response.business;

import njurestaurant.njutakeout.response.Response;

public class BusinessResponse extends Response {
    private BusinessItem businessItem;

    public BusinessResponse(BusinessItem businessItem) {
        this.businessItem = businessItem;
    }

    public BusinessResponse() {
    }

    public BusinessItem getBusinessItem() {
        return businessItem;
    }

    public void setBusinessItem(BusinessItem businessItem) {
        this.businessItem = businessItem;
    }
}
