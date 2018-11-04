package njurestaurant.njutakeout.dataservice.user;

import njurestaurant.njutakeout.entity.user.SendCard;
import njurestaurant.njutakeout.entity.user.SendCardKey;
import njurestaurant.njutakeout.entity.user.User;
import njurestaurant.njutakeout.exception.NotExistException;

import java.util.List;

public interface UserDataService {

	void saveUser(User user);

	void addUser(User user);

	User getUserByOpenid(String openid) throws NotExistException;

	List<User> getAllUsers();

	void updateUserByOpenid(String openid, String username, String face, List<String> medals, String phone, String email, String company, String department, String position, String intro, String city, int credit, String label, int cardLimit, String levelName, boolean valid) throws NotExistException;

	//注意：删除Enterprise时统一使用deleteEnterpriseById（封装了相关数据连锁删除）
	void deleteUserByOpenid(String openid) throws NotExistException;

	void addSendCard(SendCard sendCard);

	List<SendCard> getSendsByOpenid(String openid);

	List<SendCard> getReceivesByOpenid(String openid);

	void checkSendCard(SendCardKey sendCardKey) throws NotExistException;
}
