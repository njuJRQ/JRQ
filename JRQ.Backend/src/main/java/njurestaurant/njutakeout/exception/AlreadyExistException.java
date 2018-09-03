package njurestaurant.njutakeout.exception;

import njurestaurant.njutakeout.response.WrongResponse;

public class AlreadyExistException extends Exception {
	private WrongResponse response;

	public AlreadyExistException(String existingItem) {
		super(existingItem+"已经存在");
		response = new WrongResponse(10005, this.getMessage());
	}

	public WrongResponse getResponse() {
		return response;
	}
}
