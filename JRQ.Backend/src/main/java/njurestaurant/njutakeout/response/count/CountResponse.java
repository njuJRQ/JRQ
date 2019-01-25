package njurestaurant.njutakeout.response.count;

import njurestaurant.njutakeout.response.Response;

public class CountResponse extends Response {
    private CountItem countItem;

    public CountResponse() {
    }

    public CountResponse(CountItem countItem) {
        this.countItem = countItem;
    }

    public CountItem getCountItem() {
        return countItem;
    }

    public void setCountItem(CountItem countItem) {
        this.countItem = countItem;
    }
}
