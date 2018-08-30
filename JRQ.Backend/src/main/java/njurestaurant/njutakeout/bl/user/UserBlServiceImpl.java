package njurestaurant.njutakeout.bl.user;

import njurestaurant.njutakeout.blservice.user.UserBlService;
import njurestaurant.njutakeout.dataservice.user.UserDataService;
import njurestaurant.njutakeout.entity.user.SendCard;
import njurestaurant.njutakeout.entity.user.SendCardKey;
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
	public InfoResponse sendMyCard(String senderOpenid, String receiverOpenid) {
		userDataService.addSendCard(new SendCard(senderOpenid, receiverOpenid, false));
		return new InfoResponse();
	}

	@Override
	public PersonListResponse getMyPersonList(String openid, String kind) throws NotExistException {
		List<String> personOpenidList = new ArrayList<>();
		if(kind.equals("new") || kind.equals("current")) {
			List<SendCard> sendCards = userDataService.getSendsByOpenid(openid); //用户收到的
			if(kind.equals("new")) { //用户新收到，尚未查看的
				for(SendCard sendCard:sendCards) {
					if(!sendCard.isChecked()){
						personOpenidList.add(sendCard.getSenderOpenid());
					}
				}
			} else if(kind.equals("current")) { //用户总共收到的
				for(SendCard sendCard:sendCards) {
					personOpenidList.add(sendCard.getSenderOpenid());
				}
			}
		} else if(kind.equals("whoHasMyCard")) {
			List<SendCard> sendCards = userDataService.getReceivesByOpenid(openid); //用户发给别人的
			for(SendCard sendCard:sendCards) {
				personOpenidList.add(sendCard.getReceiverOpenid());
			}
		}

		List<PersonItem> personItemList = new ArrayList<>();
		for(String personId:personOpenidList) {
			personItemList.add(new PersonItem(userDataService.getUserByOpenid(personId)));
		}
		return new PersonListResponse(personItemList);
	}

	@Override
	public InfoResponse checkMyReceivedCard(String senderOpenid, String receiverOpenid) throws NotExistException {
		userDataService.checkSendCard(new SendCardKey(senderOpenid, receiverOpenid));
		return new InfoResponse();
	}
}
