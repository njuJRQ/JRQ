package njurestaurant.njutakeout.response.business;

import njurestaurant.njutakeout.response.Response;

public class BusinessImageResponse extends Response {
    private BusinessImageItem businessImageItem;

    public BusinessImageResponse() {
    }

    public BusinessImageResponse(BusinessImageItem businessImageItem) {
        this.businessImageItem = businessImageItem;
    }

    public BusinessImageItem getBusinessImageItem() {
        return businessImageItem;
    }

    public void setBusinessImageItem(BusinessImageItem businessImageItem) {
        this.businessImageItem = businessImageItem;
    }
}
