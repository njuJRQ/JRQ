package njurestaurant.njutakeout.dataservice.user;

import njurestaurant.njutakeout.entity.user.Enterprise;
import njurestaurant.njutakeout.exception.NotExistException;

import java.util.List;

public interface EnterpriseDataService {

	boolean isEnterpriseExistent(String id);

	boolean isUserEnterprise(String openid);

	boolean isAdminEnterprise(String adminId);

	void saveEnterprise(Enterprise enterprise);

	void addEnterprise(Enterprise enterprise);

	Enterprise getEnterpriseById(String id) throws NotExistException;

	Enterprise getEnterpriseByOpenid(String openid) throws NotExistException;

	Enterprise getEnterpriseByAdminId(String adminId) throws NotExistException;

	List<Enterprise> getAllEnterprises();

	void deleteEnterpriseById(String id) throws NotExistException;
}
