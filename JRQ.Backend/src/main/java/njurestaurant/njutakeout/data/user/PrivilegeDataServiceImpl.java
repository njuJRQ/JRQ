package njurestaurant.njutakeout.data.user;

import njurestaurant.njutakeout.data.dao.user.PrivilegeDao;
import njurestaurant.njutakeout.dataservice.user.PrivilegeDataService;
import njurestaurant.njutakeout.entity.user.Privilege;
import njurestaurant.njutakeout.exception.NotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrivilegeDataServiceImpl implements PrivilegeDataService {
	private final PrivilegeDao privilegeDao;

	@Autowired
	public PrivilegeDataServiceImpl(PrivilegeDao privilegeDao) {
		this.privilegeDao = privilegeDao;
	}

	@Override
	public void savePrivilege(Privilege privilege) {
		privilegeDao.save(privilege);
	}

	@Override
	public void addPrivilege(Privilege privilege) {
		privilegeDao.save(privilege);
	}

	@Override
	public Privilege getPrivilegeByName(String name) throws NotExistException {
		Optional<Privilege> optionalPrivilege = privilegeDao.findById(name);
		if (optionalPrivilege.isPresent()) {
			return optionalPrivilege.get();
		} else {
			throw new NotExistException("Privilege", name);
		}
	}

	@Override
	public List<Privilege> getAllPrivileges() {
		return privilegeDao.findAll();
	}

	@Override
	public void deletePrivilegeByName(String name) throws NotExistException {
		if (privilegeDao.existsById(name)) {
			deletePrivilegeByName(name);
		} else {
			throw new NotExistException("Privilege", name);
		}
	}
}
