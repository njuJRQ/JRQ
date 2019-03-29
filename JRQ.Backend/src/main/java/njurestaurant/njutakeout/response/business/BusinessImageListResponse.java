package njurestaurant.njutakeout.response.business;

import njurestaurant.njutakeout.response.Response;

import java.util.List;

public class BusinessImageListResponse extends Response {
    private List<BusinessImageItem> businessImageItemList;

    public BusinessImageListResponse() {
    }

    public BusinessImageListResponse(List<BusinessImageItem> businessImageItemList) {
        this.businessImageItemList = businessImageItemList;
    }

    public List<BusinessImageItem> getBusinessImageItemList() {
        return businessImageItemList;
    }

    public void setBusinessImageItemList(List<BusinessImageItem> businessImageItemList) {
        this.businessImageItemList = businessImageItemList;
    }
}
