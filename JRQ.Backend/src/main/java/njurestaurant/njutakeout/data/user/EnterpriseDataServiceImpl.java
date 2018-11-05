package njurestaurant.njutakeout.data.user;

import njurestaurant.njutakeout.data.dao.user.EnterpriseDao;
import njurestaurant.njutakeout.dataservice.admin.AdminDataService;
import njurestaurant.njutakeout.dataservice.user.EnterpriseDataService;
import njurestaurant.njutakeout.entity.user.Enterprise;
import njurestaurant.njutakeout.exception.NotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Service
public class EnterpriseDataServiceImpl implements EnterpriseDataService {
	private final EnterpriseDao enterpriseDao;
	private final AdminDataService adminDataService; //DataService中封装了相关数据连锁删除

	@Autowired
	public EnterpriseDataServiceImpl(EnterpriseDao enterpriseDao, AdminDataService adminDataService) {
		this.enterpriseDao = enterpriseDao;
		this.adminDataService = adminDataService;
	}

	@Override
	public boolean isEnterpriseExistent(String id) {
		return enterpriseDao.existsById(id);
	}

	public boolean isUserInEnterprise(String openid) {
		return enterpriseDao.findEnterpriseByOpenid(openid).isPresent();
	}

	@Override
	public boolean isUserEnterprise(String openid) {
		Optional<Enterprise> optionalEnterprise = enterpriseDao.findEnterpriseByOpenid(openid);
		return optionalEnterprise.isPresent() && optionalEnterprise.get().getStatus().equals("verified");
	}

	@Override
	public boolean isAdminInEnterprise(String adminId) {
		return enterpriseDao.findEnterpriseByAdminId(adminId).isPresent();
	}

	@Override
	public boolean isAdminEnterprise(String adminId) {
		Optional<Enterprise> optionalEnterprise = enterpriseDao.findEnterpriseByAdminId(adminId);
		return optionalEnterprise.isPresent() && optionalEnterprise.get().getStatus().equals("verified");
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
	public Enterprise getEnterpriseById(String id) throws NotExistException {
		Optional<Enterprise> optionalEnterprise = enterpriseDao.findById(id);
		if (optionalEnterprise.isPresent()) {
			return optionalEnterprise.get();
		} else {
			throw new NotExistException("Enterprise Id", id);
		}
	}

	@Override
	public Enterprise getEnterpriseByOpenid(String openid) throws NotExistException {
		Optional<Enterprise> optionalEnterprise = enterpriseDao.findEnterpriseByOpenid(openid);
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
			throw new NotExistException("Enterprise AdminId", adminId);
		}
	}

	@Override
	public List<Enterprise> getAllEnterprises() {
		return enterpriseDao.findAll();
	}

	@Override
	public void deleteEnterpriseById(String id) throws NotExistException {
		Optional<Enterprise> optionalEnterprise = enterpriseDao.findById(id);
		if(optionalEnterprise.isPresent()) {
			Enterprise enterprise = optionalEnterprise.get();
			if (! new File(enterprise.getLicenseUrl()).delete()) {
				System.err.println("企业营业执照文件"+enterprise.getLicenseUrl()+"删除失败");
			}
			enterpriseDao.delete(enterprise);
		} else {
			throw new NotExistException("Enterprise Id", id);
		}
	}

	@Override
	public void deleteEnterpriseByIdCascade(String id) throws NotExistException {
		Optional<Enterprise> optionalEnterprise = enterpriseDao.findById(id);
		if (optionalEnterprise.isPresent()) {
			Enterprise enterprise = optionalEnterprise.get();
			if (! new File(enterprise.getLicenseUrl()).delete()) {
				System.err.println("企业营业执照文件"+enterprise.getLicenseUrl()+"删除失败");
			}
			if (!enterprise.getAdminId().equals("")) { // 若此企业用户的管理员账号已分配，则删除对应管理员账号
				adminDataService.deleteAdminById(enterprise.getAdminId());
			}
			enterpriseDao.delete(enterprise);
		} else {
			throw new NotExistException("Enterprise Id", id);
		}
	}
}
