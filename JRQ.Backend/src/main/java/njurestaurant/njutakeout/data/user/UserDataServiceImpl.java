package njurestaurant.njutakeout.data.user;

import njurestaurant.njutakeout.data.dao.admin.AdminDao;
import njurestaurant.njutakeout.data.dao.user.EnterpriseDao;
import njurestaurant.njutakeout.data.dao.user.SendCardDao;
import njurestaurant.njutakeout.data.dao.user.UserDao;
import njurestaurant.njutakeout.dataservice.article.FeedDataService;
import njurestaurant.njutakeout.dataservice.user.EnterpriseDataService;
import njurestaurant.njutakeout.dataservice.user.UserDataService;
import njurestaurant.njutakeout.entity.admin.Admin;
import njurestaurant.njutakeout.entity.article.Feed;
import njurestaurant.njutakeout.entity.user.Enterprise;
import njurestaurant.njutakeout.entity.user.SendCard;
import njurestaurant.njutakeout.entity.user.SendCardKey;
import njurestaurant.njutakeout.entity.user.User;
import njurestaurant.njutakeout.exception.NotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Service
public class UserDataServiceImpl implements UserDataService {
	private final UserDao userDao;
	private final SendCardDao sendCardDao;
	private final FeedDataService feedDataService;
	private final EnterpriseDataService enterpriseDataService; //DataService中封装了相关数据连锁删除

	@Autowired
	public UserDataServiceImpl(UserDao userDao, SendCardDao sendCardDao, FeedDataService feedDataService, EnterpriseDataService enterpriseDataService) {
		this.userDao = userDao;
		this.sendCardDao = sendCardDao;
		this.feedDataService = feedDataService;
		this.enterpriseDataService = enterpriseDataService;
	}

	@Override
	public void saveUser(User user) {
		userDao.save(user);
	}

	@Override
	public void addUser(User user) {
		userDao.save(user);
	}

	@Override
	public User getUserByOpenid(String openid) throws NotExistException {
		Optional<User> optionalUser = userDao.findById(openid);
		if(optionalUser.isPresent()) {
			return optionalUser.get();
		} else {
			throw new NotExistException("用户openid", openid);
		}
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.findAll();
	}

	@Override
	public void updateUserByOpenid(String openid, String username, String face, List<String> medals, String phone, String email, String company, String department, String position, String intro, String city, int credit, String label, int cardLimit, String levelName, boolean valid) throws NotExistException {
		Optional<User> optionalUser = userDao.findById(openid);
		if(optionalUser.isPresent()) {
			User user = optionalUser.get();
			user.setUsername(username);
			user.setFace(face);
			user.setMedals(medals);
			user.setPhone(phone);
			user.setEmail(email);
			user.setCompany(company);
			user.setDepartment(department);
			user.setPosition(position);
			user.setIntro(intro);
			user.setCity(city);
			user.setCredit(credit);
			user.setLabel(label);
			user.setCardLimit(cardLimit);
			user.setLevelName(levelName);
			user.setValid(valid);
			userDao.save(user);
		} else {
			throw new NotExistException("User openid", openid);
		}
	}

	@Override
	public void deleteUserByOpenid(String openid) throws NotExistException {
		Optional<User> optionalUser = userDao.findById(openid);
		if (optionalUser.isPresent()) {
			sendCardDao.deleteSendCardsByReceiverOpenid(openid);  //删除此人接收名片的记录
			sendCardDao.deleteSendCardsBySenderOpenid(openid);  //删除此人送给别人名片的记录
			feedDataService.deleteFeedsByWriterOpenid(openid);  //删除此人发过的圈子内容

			//若此人在Enterprise表中，需要删除该项
			try {
				Enterprise enterprise = enterpriseDataService.getEnterpriseByOpenid(openid);
				enterpriseDataService.deleteEnterpriseByIdCascade(enterprise.getId()); //连环删除Admin的相关数据
			} catch (NotExistException e) {
				//若此人不在Enterprise表中，什么也不需要做
			}

			//删除此人头像
			User user = optionalUser.get();
			if (!new File(user.getFace()).delete()) {
				System.err.println("头像文件"+user.getFace()+"删除失败！");
			}
			userDao.deleteById(openid);
		} else {
			throw new NotExistException("User openid", openid);
		}
	}

	@Override
	public void addSendCard(SendCard sendCard) {
		// 若已经送过名片，则无变化
		if (!sendCardDao.existsById(new SendCardKey(sendCard.getSenderOpenid(), sendCard.getReceiverOpenid()))) {
			sendCardDao.save(sendCard);
		}
	}

	@Override
	public List<SendCard> getSendsByOpenid(String openid) {
		return sendCardDao.findAllByReceiverOpenid(openid);
	}

	@Override
	public List<SendCard> getReceivesByOpenid(String openid) {
		return sendCardDao.findAllBySenderOpenid(openid);
	}

	@Override
	public void checkSendCard(SendCardKey sendCardKey) throws NotExistException {
		Optional<SendCard> optionalSendCard = sendCardDao.findById(sendCardKey);
		if(optionalSendCard.isPresent()) {
			SendCard sendCard = optionalSendCard.get();
			sendCard.setChecked(true);
			sendCardDao.save(sendCard);
		} else {
			throw new NotExistException("SendCard key", "(senderOpenid="+sendCardKey.getSenderOpenid()+",receiverOpenid="+sendCardKey.getReceiverOpenid()+")");
		}
	}

}
