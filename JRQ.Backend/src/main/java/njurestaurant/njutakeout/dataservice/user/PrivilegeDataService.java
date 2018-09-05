package njurestaurant.njutakeout.dataservice.user;

import njurestaurant.njutakeout.entity.user.Privilege;
import njurestaurant.njutakeout.exception.NotExistException;

import java.util.List;

public interface PrivilegeDataService {

	void savePrivilege(Privilege privilege);

	void addPrivilege(Privilege privilege);

	Privilege getPrivilegeByName(String name) throws NotExistException;

	List<Privilege> getAllPrivileges();

	void deletePrivilegeByName(String name) throws NotExistException;
}
