package njurestaurant.njutakeout.response.partnership;

import njurestaurant.njutakeout.response.Response;

public class PartnershipResponse extends Response {
    private PartnershipItem partnershipItem;

    public PartnershipResponse() {
    }

    public PartnershipResponse(PartnershipItem partnershipItem) {
        this.partnershipItem = partnershipItem;
    }

    public PartnershipItem getPartnershipItem() {
        return partnershipItem;
    }

    public void setPartnershipItem(PartnershipItem partnershipItem) {
        this.partnershipItem = partnershipItem;
    }
}
