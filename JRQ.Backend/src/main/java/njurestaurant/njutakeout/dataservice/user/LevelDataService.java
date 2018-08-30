package njurestaurant.njutakeout.dataservice.user;

import njurestaurant.njutakeout.entity.user.Level;
import njurestaurant.njutakeout.exception.NotExistException;

import java.util.List;

public interface LevelDataService {

	void addLevel(Level level);

	Level getLevelByName(String name) throws NotExistException;

	List<Level> getAllLevels();

	void updateLevelByName(String name, int cardLimit, int price) throws NotExistException;

	void deleteLevelByName(String name) throws NotExistException;
}
