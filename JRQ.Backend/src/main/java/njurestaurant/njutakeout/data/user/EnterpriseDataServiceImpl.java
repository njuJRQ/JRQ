package njurestaurant.njutakeout.data.user;

import njurestaurant.njutakeout.data.dao.user.EnterpriseDao;
import njurestaurant.njutakeout.dataservice.user.EnterpriseDataService;
import njurestaurant.njutakeout.entity.user.Enterprise;
import njurestaurant.njutakeout.exception.NotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnterpriseDataServiceImpl implements EnterpriseDataService {
	private final EnterpriseDao enterpriseDao;

	@Autowired
	public EnterpriseDataServiceImpl(EnterpriseDao enterpriseDao) {
		this.enterpriseDao = enterpriseDao;
	}

	@Override
	public boolean isUserEnterprise(String openid) {
		return enterpriseDao.existsById(openid);
	}

	@Override
	public boolean isAdminEnterprise(String adminId) {
		return enterpriseDao.findEnterpriseByAdminId(adminId).isPresent();
	}

	@Override
	public void saveEnterprise(Enterprise enterprise) {
		enterpriseDao.save(enterprise);
	}

	@Override
	public void addEnterprise(Enterprise enterprise) {
		enterpriseDao.save(enterprise);
	}

	@Override
	public Enterprise getEnterpriseByOpenid(String openid) throws NotExistException {
		Optional<Enterprise> optionalEnterprise = enterpriseDao.findById(openid);
		if (optionalEnterprise.isPresent()) {
			return optionalEnterprise.get();
		} else {
			throw new NotExistException("Enterprise User openid", openid);
		}
	}

	@Override
	public Enterprise getEnterpriseByAdminId(String adminId) throws NotExistException {
		Optional<Enterprise> optionalEnterprise = enterpriseDao.findEnterpriseByAdminId(adminId);
		if (optionalEnterprise.isPresent()) {
			return optionalEnterprise.get();
		} else {
			throw new NotExistException("Enterprise", adminId);
		}
	}

	@Override
	public List<Enterprise> getAllEnterprises() {
		return enterpriseDao.findAll();
	}

	@Override
	public void deleteEnterpriseByOpenid(String openid) throws NotExistException {
		if (enterpriseDao.existsById(openid)) {
			enterpriseDao.deleteById(openid);
		} else {
			throw new NotExistException("Enterprise User openid", openid);
		}
	}
}
