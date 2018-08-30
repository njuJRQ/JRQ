package njurestaurant.njutakeout.response.purchase;

import java.util.List;

public class PurchaseListResponse {
	private List<PurchaseItem> purchases;

	public PurchaseListResponse() {
	}

	public PurchaseListResponse(List<PurchaseItem> purchases) {
		this.purchases = purchases;
	}

	public List<PurchaseItem> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<PurchaseItem> purchases) {
		this.purchases = purchases;
	}
}
