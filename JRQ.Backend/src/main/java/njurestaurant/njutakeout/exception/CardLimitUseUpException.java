package njurestaurant.njutakeout.exception;

import njurestaurant.njutakeout.response.WrongResponse;

public class CardLimitUseUpException extends Exception {
	private WrongResponse response = new WrongResponse(10002, "Card Limit has been used up");

	public WrongResponse getResponse() {
		return response;
	}
}
