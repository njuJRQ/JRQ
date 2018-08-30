package njurestaurant.njutakeout.exception;

import njurestaurant.njutakeout.response.WrongResponse;

public class WrongPasswordException extends Exception {
	private WrongResponse response;

	public WrongPasswordException(String username) {
		super("Password of "+username+" is wrong");
		response = new WrongResponse(10003, this.getMessage());
	}

	public WrongResponse getResponse() {
		return response;
	}
}
