package njurestaurant.njutakeout.data.user;

import njurestaurant.njutakeout.data.dao.user.SendCardDao;
import njurestaurant.njutakeout.data.dao.user.UserDao;
import njurestaurant.njutakeout.dataservice.user.UserDataService;
import njurestaurant.njutakeout.entity.user.SendCard;
import njurestaurant.njutakeout.entity.user.SendCardKey;
import njurestaurant.njutakeout.entity.user.User;
import njurestaurant.njutakeout.exception.NotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDataServiceImpl implements UserDataService {
	private final int cardLimit = 20; //每个用户每天最多能看的卡片数
	private final UserDao userDao;
	private final SendCardDao sendCardDao;

	@Autowired
	public UserDataServiceImpl(UserDao userDao, SendCardDao sendCardDao) {
		this.userDao = userDao;
		this.sendCardDao = sendCardDao;
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
			throw new NotExistException("User");
		}
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.findAll();
	}

	@Override
	public void updateUserByOpenid(String openid, String username, String face, List<String> medals, String phone, String email, String company, String department, String position, String intro, String city, int credit, String label, int cardLimit, boolean valid) throws NotExistException {
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
			user.setValid(valid);
			userDao.save(user);
		} else {
			throw new NotExistException("User");
		}
	}

	@Override
	public void deleteUserByOpenid(String openid) throws NotExistException {
		if (userDao.existsById(openid)) {
			userDao.deleteById(openid);
		} else {
			throw new NotExistException("User");
		}
	}

	@Override
	public void addSendCard(SendCard sendCard) {
		sendCardDao.save(sendCard);
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
			throw new NotExistException("SendCard");
		}
	}

	/**
	 * 定时任务：每天0点自动重置所有用户查看别人名片的次数
	 */
	@Scheduled(cron = "0 0 0 * * ?")
	private void resetUserCardLimit(){
		List<User> users = userDao.findAll();
		for(User user:users) {
			user.setCardLimit(cardLimit);
			userDao.save(user);
		}
	}
}
