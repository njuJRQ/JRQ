package njurestaurant.njutakeout.bl.user;

import njurestaurant.njutakeout.blservice.user.UserBlService;
import njurestaurant.njutakeout.dataservice.user.UserDataService;
import njurestaurant.njutakeout.entity.user.User;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserBlServiceImpl implements UserBlService {
	private final UserDataService userDataService;

	@Autowired
	public UserBlServiceImpl(UserDataService userDataService) {
		this.userDataService = userDataService;
	}

	@Override
	public InfoResponse addUser(String openid, String username, String face, List<String> medals, String phone, String email, String company, String department, String position, String intro, String city, int credit, String label, boolean valid) {
		userDataService.addUser(new User(openid, username, face, medals, phone, email, company, department, position, intro, city, credit, label, valid));
		return new InfoResponse();
	}

	@Override
	public UserResponse getUser(String openid) throws NotExistException {
		return new UserResponse(new UserItem(userDataService.getUserByOpenid(openid)));
	}

	@Override
	public UserListResponse getUserList() {
		List<User> userList = userDataService.getAllUsers();
		List<UserItem> userItemList = new ArrayList<>();
		for(User user:userList) {
			userItemList.add(new UserItem(user));
		}
		return new UserListResponse(userItemList);
	}

	@Override
	public InfoResponse updateUser(String openid, String username, String face, List<String> medals, String phone, String email, String company, String department, String position, String intro, String city, int credit, String label, boolean valid) throws NotExistException {
		userDataService.updateUserByOpenid(openid, username, face, medals, phone, email, company, department, position, intro, city, credit, label, valid);
		return new InfoResponse();
	}

	@Override
	public InfoResponse deleteUser(String openid) {
		userDataService.deleteUserByOpenid(openid);
		return new InfoResponse();
	}

	@Override
	public PersonListResponse getPersonList(String kind) {
		List<User> userList = userDataService.getAllUsers();
		List<PersonItem> personItemList = new ArrayList<>();
		for(User user:userList) {
			personItemList.add(new PersonItem(user));
		}
		return new PersonListResponse(personItemList);
	}

	@Override
	public PersonListResponse getPersonListByCondition(String condition) {
		//TODO: 添加查询条件
		return null;
	}

	@Override
	public PersonListResponse getMyPersonList(String openid, String kind) {
		//TODO: 对名片进行分类
		return null;
	}
}
