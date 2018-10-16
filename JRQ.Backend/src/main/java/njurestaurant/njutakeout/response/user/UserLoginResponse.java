package njurestaurant.njutakeout.response.user;

import njurestaurant.njutakeout.response.LoginResponse;

public class UserLoginResponse extends LoginResponse {
	private UserItem user;

	public UserLoginResponse() {
	}

	public UserLoginResponse(String token, UserItem user) {
		super(token);
		this.user = user;
	}

	public UserItem getUser() {
		return user;
	}

	public void setUser(UserItem user) {
		this.user = user;
	}
}
