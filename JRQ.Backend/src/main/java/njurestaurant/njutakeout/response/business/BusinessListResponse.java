package njurestaurant.njutakeout.response.business;

import njurestaurant.njutakeout.response.Response;

import java.util.List;

public class BusinessListResponse extends Response {
    private List<BusinessItem> businessItemList;

    public BusinessListResponse() {
    }

    public BusinessListResponse(List<BusinessItem> businessItemList) {
        this.businessItemList = businessItemList;
    }

    public List<BusinessItem> getBusinessItemList() {
        return businessItemList;
    }

    public void setBusinessItemList(List<BusinessItem> businessItemList) {
        this.businessItemList = businessItemList;
    }
}
