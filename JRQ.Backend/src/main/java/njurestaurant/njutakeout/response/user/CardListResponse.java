package njurestaurant.njutakeout.response.user;

import njurestaurant.njutakeout.response.Response;

import java.util.List;

public class CardListResponse extends Response {
	private List<CardItem> cards;

	public CardListResponse() {
	}

	public CardListResponse(List<CardItem> cards) {
		this.cards = cards;
	}

	public List<CardItem> getCards() {
		return cards;
	}

	public void setCards(List<CardItem> cards) {
		this.cards = cards;
	}
}
