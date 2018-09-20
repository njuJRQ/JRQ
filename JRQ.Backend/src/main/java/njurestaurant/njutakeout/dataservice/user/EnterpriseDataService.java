package njurestaurant.njutakeout.dataservice.user;

import njurestaurant.njutakeout.entity.user.Enterprise;
import njurestaurant.njutakeout.exception.NotExistException;

import java.util.List;

public interface EnterpriseDataService {

	boolean isUserEnterprise(String openid);

	boolean isAdminEnterprise(String adminId);

	void saveEnterprise(Enterprise enterprise);

	void addEnterprise(Enterprise enterprise);

	Enterprise getEnterpriseByOpenid(String openid) throws NotExistException;

	Enterprise getEnterpriseByAdminId(String adminId) throws NotExistException;

	List<Enterprise> getAllEnterprises();

	void deleteEnterpriseByOpenid(String openid) throws NotExistException;
}
