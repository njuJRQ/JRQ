package njurestaurant.njutakeout.dataservice.user;

import njurestaurant.njutakeout.entity.user.SendCard;
import njurestaurant.njutakeout.entity.user.SendCardKey;
import njurestaurant.njutakeout.entity.user.User;
import njurestaurant.njutakeout.exception.NotExistException;

import java.util.List;

public interface UserDataService {

	void addUser(User user);

	User getUserByOpenid(String openid) throws NotExistException;

	List<User> getAllUsers();

	void updateUserByOpenid(String openid, String username, String face, List<String> medals, String phone, String email, String company, String department, String position, String intro, String city, int credit, String label, boolean valid) throws NotExistException;

	void deleteUserByOpenid(String openid);

	void addSendCard(SendCard sendCard);

	List<SendCard> getSendsByOpenid(String openid);

	List<SendCard> getReceivesByOpenid(String openid);

	void checkSendCard(SendCardKey sendCardKey) throws NotExistException;
}
