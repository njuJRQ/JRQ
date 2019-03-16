package njurestaurant.njutakeout.response.partnership;

import njurestaurant.njutakeout.response.Response;

import java.util.List;

public class PartnershipListResponse extends Response {
    private List<PartnershipItem> partnershipItems;

    public PartnershipListResponse() {
    }

    public PartnershipListResponse(List<PartnershipItem> partnershipItems) {
        this.partnershipItems = partnershipItems;
    }

    public List<PartnershipItem> getPartnershipItems() {
        return partnershipItems;
    }

    public void setPartnershipItems(List<PartnershipItem> partnershipItems) {
        this.partnershipItems = partnershipItems;
    }
}
