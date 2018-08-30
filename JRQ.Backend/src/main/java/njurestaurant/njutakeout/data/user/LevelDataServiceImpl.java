package njurestaurant.njutakeout.data.user;

import njurestaurant.njutakeout.data.dao.user.LevelDao;
import njurestaurant.njutakeout.dataservice.user.LevelDataService;
import njurestaurant.njutakeout.entity.user.Level;
import njurestaurant.njutakeout.exception.NotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LevelDataServiceImpl implements LevelDataService {
	private final LevelDao levelDao;

	@Autowired
	public LevelDataServiceImpl(LevelDao levelDao) {
		this.levelDao = levelDao;
	}

	@Override
	public void addLevel(Level level) {
		levelDao.save(level);
	}

	@Override
	public Level getLevelByName(String name) throws NotExistException {
		Optional<Level> optionalLevel = levelDao.findById(name);
		if (optionalLevel.isPresent()) {
			return optionalLevel.get();
		} else {
			throw new NotExistException("Level");
		}
	}

	@Override
	public List<Level> getAllLevels() {
		return levelDao.findAll();
	}

	@Override
	public void updateLevelByName(String name, int cardLimit, int price) throws NotExistException {
		Optional<Level> optionalLevel = levelDao.findById(name);
		if (optionalLevel.isPresent()) {
			Level level = optionalLevel.get();
			level.setPrice(price);
			level.setCardLimit(cardLimit);
			levelDao.save(level);
		} else {
			throw new NotExistException("Level");
		}
	}

	@Override
	public void deleteLevelByName(String name) throws NotExistException {
		if (levelDao.existsById(name)) {
			levelDao.deleteById(name);
		} else {
			throw new NotExistException("Level");
		}
	}
}
