package njurestaurant.njutakeout.exception;

import njurestaurant.njutakeout.response.WrongResponse;

public class DuplicateUsernameException extends Exception {
	private WrongResponse response;

	public DuplicateUsernameException(String username) {
		super("Username "+username+" already exists");
		response = new WrongResponse(10004, this.getMessage());
	}

	public WrongResponse getResponse() {
		return response;
	}
}
