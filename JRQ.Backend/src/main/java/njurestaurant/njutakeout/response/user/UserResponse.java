package njurestaurant.njutakeout.response.user;

import njurestaurant.njutakeout.response.Response;

public class UserResponse extends Response {
	private UserItem user;

	public UserResponse() {
	}

	public UserResponse(UserItem user) {
		this.user = user;
	}

	public UserItem getUser() {
		return user;
	}

	public void setUser(UserItem user) {
		this.user = user;
	}
}
