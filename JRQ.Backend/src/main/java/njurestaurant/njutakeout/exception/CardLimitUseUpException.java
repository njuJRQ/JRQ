package njurestaurant.njutakeout.exception;

import njurestaurant.njutakeout.response.WrongResponse;

public class CardLimitUseUpException extends Exception {
	private WrongResponse response = new WrongResponse(10002, "今日查看次数已经用完");

	public WrongResponse getResponse() {
		return response;
	}
}
