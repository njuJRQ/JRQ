package njurestaurant.njutakeout.data.user;

import njurestaurant.njutakeout.data.dao.user.UserDao;
import njurestaurant.njutakeout.dataservice.user.UserDataService;
import njurestaurant.njutakeout.entity.user.User;
import njurestaurant.njutakeout.exception.NotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDataServiceImpl implements UserDataService {
	private final UserDao userDao;

	@Autowired
	public UserDataServiceImpl(UserDao userDao) {
		this.userDao = userDao;
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
	public void updateUserByOpenid(String openid, String username, String face, List<String> medals, String phone, String email, String company, String department, String position, String intro, String city, int credit, String label, boolean valid) throws NotExistException {
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
			user.setValid(valid);
			userDao.save(user);
		} else {
			throw new NotExistException("User");
		}
	}

	@Override
	public void deleteUserByOpenid(String openid) {
		userDao.deleteById(openid);
	}
}
