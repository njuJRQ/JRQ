package njurestaurant.njutakeout.bl.user;

import njurestaurant.njutakeout.blservice.user.UserBlService;
import njurestaurant.njutakeout.dataservice.user.ClassificationDataService;
import njurestaurant.njutakeout.dataservice.user.UserDataService;
import njurestaurant.njutakeout.entity.user.Classification;
import njurestaurant.njutakeout.entity.user.SendCard;
import njurestaurant.njutakeout.entity.user.SendCardKey;
import njurestaurant.njutakeout.entity.user.User;
import njurestaurant.njutakeout.exception.CardLimitUseUpException;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserBlServiceImpl implements UserBlService {
	private final UserDataService userDataService;
	private final ClassificationDataService classificationDataService;

	@Autowired
	public UserBlServiceImpl(UserDataService userDataService, ClassificationDataService classificationDataService) {
		this.userDataService = userDataService;
		this.classificationDataService = classificationDataService;
	}

	@Override
	public InfoResponse addUser(String openid, String username, String face, List<String> medals, String phone, String email, String company, String department, String position, String intro, String city, int credit, String label, int cardLimit, boolean valid) {
		userDataService.addUser(new User(openid, username, face, medals, phone, email, company, department, position, intro, city, credit, label, cardLimit, valid));
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
	public InfoResponse updateUser(String openid, String username, String face, List<String> medals, String phone, String email, String company, String department, String position, String intro, String city, int credit, String label, int cardLimit, boolean valid) throws NotExistException {
		userDataService.updateUserByOpenid(openid, username, face, medals, phone, email, company, department, position, intro, city, credit, label, cardLimit, valid);
		return new InfoResponse();
	}

	@Override
	public InfoResponse deleteUser(String openid) {
		userDataService.deleteUserByOpenid(openid);
		return new InfoResponse();
	}

	@Override
	public PersonListResponse getPersonList(String workClass) throws NotExistException {
		List<User> userList = userDataService.getAllUsers();
		List<PersonItem> personItemList = new ArrayList<>();
		for(User user:userList) {
			if(classificationDataService.getClassificationByUserLabel(user.getLabel()).getWorkClass().equals(workClass)) {
				personItemList.add(new PersonItem(user));
			}
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
	public CardListResponse getMyCardList(String openid, String kind) throws NotExistException {
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

		List<CardItem> cardItemList = new ArrayList<>();
		for(String personId:personOpenidList) {
			cardItemList.add(new CardItem(userDataService.getUserByOpenid(personId)));
		}
		return new CardListResponse(cardItemList);
	}

	@Override
	public InfoResponse checkMyReceivedCard(String senderOpenid, String receiverOpenid) throws NotExistException {
		userDataService.checkSendCard(new SendCardKey(senderOpenid, receiverOpenid));
		return new InfoResponse();
	}

	@Override
	public CardResponse getOtherCard(String userOpenid, String otherOpenid) throws NotExistException, CardLimitUseUpException {
		User other = userDataService.getUserByOpenid(otherOpenid);
		User user = userDataService.getUserByOpenid(userOpenid);
		if (user.getCardLimit() > 0) {
			user.setCardLimit(user.getCardLimit() - 1);
			return new CardResponse(new CardItem(other));
		} else {
			throw new CardLimitUseUpException();
		}
	}

}
