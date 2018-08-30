package njurestaurant.njutakeout.response.purchase;

public class PurchaseResponse {
	private PurchaseItem purchase;

	public PurchaseResponse() {
	}

	public PurchaseResponse(PurchaseItem purchase) {
		this.purchase = purchase;
	}

	public PurchaseItem getPurchase() {
		return purchase;
	}

	public void setPurchase(PurchaseItem purchase) {
		this.purchase = purchase;
	}
}
