package njurestaurant.njutakeout.bl.user;

import njurestaurant.njutakeout.blservice.user.UserBlService;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.user.PersonListResponse;
import njurestaurant.njutakeout.response.user.UserListResponse;
import njurestaurant.njutakeout.response.user.UserResponse;

import java.util.List;

public class UserBlServiceImpl implements UserBlService {
	@Override
	public InfoResponse addUser(String openid, String username, String face, List<String> medals, String phone, String email, String company, String department, String position, String intro, boolean valid) {
		return null;
	}

	@Override
	public UserResponse getUser(String openid) {
		return null;
	}

	@Override
	public UserListResponse getUserList() {
		return null;
	}

	@Override
	public InfoResponse updateUser(String openid, String username, String face, List<String> medals, String phone, String email, String company, String department, String position, String intro, boolean valid) {
		return null;
	}

	@Override
	public InfoResponse deleteUser(String openid) {
		return null;
	}

	@Override
	public PersonListResponse getPersonList(String kind) {
		return null;
	}

	@Override
	public PersonListResponse getPersonListByCondition(String condition) {
		return null;
	}

	@Override
	public PersonListResponse getMyPersonList(String openid, String kind) {
		return null;
	}
}
