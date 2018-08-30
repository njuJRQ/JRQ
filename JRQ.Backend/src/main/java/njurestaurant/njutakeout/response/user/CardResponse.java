package njurestaurant.njutakeout.response.user;

import njurestaurant.njutakeout.response.Response;

public class CardResponse extends Response {
	private CardItem card;

	public CardResponse() {
	}

	public CardResponse(CardItem card) {
		this.card = card;
	}

	public CardItem getCard() {
		return card;
	}

	public void setCard(CardItem card) {
		this.card = card;
	}
}
