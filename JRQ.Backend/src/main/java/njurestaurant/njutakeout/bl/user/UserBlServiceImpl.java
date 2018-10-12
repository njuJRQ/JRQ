package njurestaurant.njutakeout.bl.user;

import njurestaurant.njutakeout.blservice.user.UserBlService;
import njurestaurant.njutakeout.dataservice.user.ClassificationDataService;
import njurestaurant.njutakeout.dataservice.user.EnterpriseDataService;
import njurestaurant.njutakeout.dataservice.user.LevelDataService;
import njurestaurant.njutakeout.dataservice.user.UserDataService;
import njurestaurant.njutakeout.entity.user.*;
import njurestaurant.njutakeout.exception.CannotGetOpenIdAndSessionKeyException;
import njurestaurant.njutakeout.exception.CardLimitUseUpException;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.account.OpenIdAndSessionKeyResponse;
import njurestaurant.njutakeout.response.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserBlServiceImpl implements UserBlService {
	private final UserDataService userDataService;
	private final ClassificationDataService classificationDataService;
	private final LevelDataService levelDataService;
	private final EnterpriseDataService enterpriseDataService;

	@Autowired
	public UserBlServiceImpl(UserDataService userDataService, ClassificationDataService classificationDataService, LevelDataService levelDataService, EnterpriseDataService enterpriseDataService) {
		this.userDataService = userDataService;
		this.classificationDataService = classificationDataService;
		this.levelDataService = levelDataService;
		this.enterpriseDataService = enterpriseDataService;
	}

	@Override
	public InfoResponse addUser(String openid, String username, String face, List<String> medals, String phone, String email, String company, String department, String position, String intro, String city, int credit, String label, String levelName, boolean valid) throws NotExistException {
		userDataService.addUser(new User(openid, username, face, medals, phone, email, company, department, position, intro, city, credit, label,
				levelDataService.getLevelByName(levelName).getCardLimit(), levelName, valid));
		return new InfoResponse();
	}

	@Override
	public UserResponse getUser(String openid) throws NotExistException {
		return new UserResponse(new UserItem(userDataService.getUserByOpenid(openid), enterpriseDataService));
	}

	@Override
	public UserListResponse getUserList() {
		List<User> userList = userDataService.getAllUsers();
		List<UserItem> userItemList = new ArrayList<>();
		for(User user:userList) {
			userItemList.add(new UserItem(user, enterpriseDataService));
		}
		return new UserListResponse(userItemList);
	}

	@Override
	public InfoResponse updateUser(String openid, String username, String face, List<String> medals, String phone, String email, String company, String department, String position, String intro, String city, int credit, String label, String levelName, boolean valid) throws NotExistException {
		User user = userDataService.getUserByOpenid(openid);
		int used = levelDataService.getLevelByName(user.getLevelName()).getCardLimit() - user.getCardLimit(); //已经用掉的次数
		userDataService.updateUserByOpenid(openid, username, face, medals, phone, email, company, department, position, intro, city, credit, label,
				levelDataService.getLevelByName(levelName).getCardLimit()-used, levelName, valid);
		return new InfoResponse();
	}

	@Override
	public InfoResponse deleteUser(String openid) throws NotExistException {
		userDataService.deleteUserByOpenid(openid);
		return new InfoResponse();
	}

	private static final String []colorPool = {"rgba(255, 161, 177, 0.699)",
			"rgba(138, 138, 252, 0.767)",
			"rgba(109, 156, 90, 0.726)",
			"rgba(255, 58, 58, 0.678)"}; //业务标签的颜色值

	@Override
	public InfoResponse addClassification(String userLabel, String workClass) {
		classificationDataService.addClassification(
				new Classification(userLabel, workClass, colorPool[new Random().nextInt(colorPool.length)]));
		return new InfoResponse();
	}

	@Override
	public ClassificationResponse getClassification(String userLabel) throws NotExistException {
		return new ClassificationResponse(new ClassificationItem(
				classificationDataService.getClassificationByUserLabel(userLabel)));
	}

	@Override
	public ClassificationListResponse getClassificationList() {
		List<Classification> classificationList = classificationDataService.getAllClassifications();
		List<ClassificationItem> classificationItemList = new ArrayList<>();
		for(Classification classification:classificationList) {
			classificationItemList.add(new ClassificationItem(classification));
		}
		return new ClassificationListResponse(classificationItemList);
	}

	@Override
	public InfoResponse updateClassification(String userLabel, String workClass) throws NotExistException {
		classificationDataService.updateClassificationByUserLabel(userLabel, workClass);
		return new InfoResponse();
	}

	@Override
	public InfoResponse deleteClassification(String userLabel) throws NotExistException {
		classificationDataService.deleteClassificationByUserLabel(userLabel);
		return new InfoResponse();
	}

	@Override
	public InfoResponse addLevel(String name, int cardLimit, int price) {
		levelDataService.addLevel(new Level(name, cardLimit, price));
		return new InfoResponse();
	}

	@Override
	public LevelListResponse getLevelList() {
		List<Level> levels = levelDataService.getAllLevels();
		List<LevelItem> levelItems = new ArrayList<>();
		for(Level level:levels) {
			levelItems.add(new LevelItem(level));
		}
		return new LevelListResponse(levelItems);
	}

	@Override
	public InfoResponse updateLevel(String name, int cardLimit, int price) throws NotExistException {
		levelDataService.updateLevelByName(name, cardLimit, price);
		return new InfoResponse();
	}

	@Override
	public InfoResponse deleteLevel(String name) throws NotExistException {
		levelDataService.deleteLevelByName(name);
		return new InfoResponse();
	}

	@Override
	public UserResponse loginMyUser(String openid, String username) throws NotExistException {
		try {
			User user = userDataService.getUserByOpenid(openid);
			return new UserResponse(new UserItem(user, enterpriseDataService));
		} catch (NotExistException exception) {
			int initCardLimit = levelDataService.getLevelByName("common").getCardLimit();
			String defaultLabel = "";
			List<Classification> classifications = classificationDataService.getAllClassifications();
			if (!classifications.isEmpty()) {
				defaultLabel = classifications.get(0).getUserLabel();
			}
			User user = new User(openid, username, "", new ArrayList<>(), "", "", "", "", "", "", "", 0, defaultLabel, initCardLimit, "common", true);
			userDataService.addUser(user);
			return new UserResponse(new UserItem(user, enterpriseDataService));
		}
	}

	@Value(value = "${wechat.url}")
	private String wechatUrl;

	@Value(value = "${wechat.id}")
	private String appId;

	@Value(value = "${wechat.secret}")
	private String appSecret;

	@Override
	public OpenIdAndSessionKeyResponse getOpenIdAndSessionKey(String jsCode) throws CannotGetOpenIdAndSessionKeyException {
		RestTemplate client = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<String> entity = new HttpEntity<String>("", headers);
		ResponseEntity<String> response = client.exchange(wechatUrl + appId + "&secret=" + appSecret + "&js_code=" + jsCode + "&grant_type=authorization_code", HttpMethod.GET, entity, String.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			System.out.println("jsCode = [" + jsCode + "]");
			System.out.println("hhhhhhh" + (String) JSONObject.fromObject(response.getBody()).get("openid"));
			System.out.println(response);
			return new OpenIdAndSessionKeyResponse((String) JSONObject.fromObject(response.getBody()).get("openid"), (String) JSONObject.fromObject(response.getBody()).get("session_key"));
		} else {
			throw new CannotGetOpenIdAndSessionKeyException();
		}
	}

	@Override
	public UserResponse getMyUser(String openid) throws NotExistException {
		return new UserResponse(new UserItem(userDataService.getUserByOpenid(openid), enterpriseDataService));
	}

	@Override
	public InfoResponse updateMyProfile(String openid, String username, String face, String phone, String email, String company, String department, String position, String intro, String city, String label) throws NotExistException {
		User user = userDataService.getUserByOpenid(openid);
		userDataService.updateUserByOpenid(openid, username, face, user.getMedals(), phone, email, company, department, position, intro, city, user.getCredit(), label, user.getCardLimit(), user.getLevelName(), user.isValid());
		return new InfoResponse();
	}

	@Override
	public PersonResponse getPerson(String openid) throws NotExistException {
		return new PersonResponse(new PersonItem(userDataService.getUserByOpenid(openid), classificationDataService));
	}

	@Override
	public PersonListResponse getPersonList(String workClass) throws NotExistException {
		List<User> userList = userDataService.getAllUsers();
		List<PersonItem> personItemList = new ArrayList<>();
		for(User user:userList) {
			if(classificationDataService.getClassificationByUserLabel(user.getLabel()).getWorkClass().equals(workClass)) {
				personItemList.add(new PersonItem(user, classificationDataService));
			}
		}
		return new PersonListResponse(personItemList);
	}

	@Override
	public PersonListResponse getPersonListByCondition(String condition) {
		List<User> userList = userDataService.getAllUsers();
		List<PersonItem> personItemList = new ArrayList<>();
		for (User user:userList) {
			if (user.getUsername().contains(condition) || user.getCompany().contains(condition)) {
				personItemList.add(new PersonItem(user, classificationDataService));
			}
		}
		return new PersonListResponse(personItemList);
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
	public CardResponse getOtherCard(String userOpenid, String otherOpenid) throws NotExistException {
		User other = userDataService.getUserByOpenid(otherOpenid);
		if (userOpenid.equals(otherOpenid)) {
			return new CardResponse(new CardItem(other));
		}
		User user = userDataService.getUserByOpenid(userOpenid);
		if (user.getCardLimit() > 0) {
			user.setCardLimit(user.getCardLimit() - 1);
			userDataService.saveUser(user);
			return new CardResponse(new CardItem(other));
		} else {
			return new CardResponse(); //剩余次数用尽，返回空对象
		}
	}

	/**
	 * 定时任务：每天0点自动重置所有用户查看别人名片的次数
	 */
	@Scheduled(cron = "0 0 0 * * ?")
	private void resetUserCardLimit() throws NotExistException {
		List<User> users = userDataService.getAllUsers();
		for(User user:users) {
			user.setCardLimit(levelDataService.getLevelByName(user.getLevelName()).getCardLimit());
			userDataService.saveUser(user);
		}
	}

}
