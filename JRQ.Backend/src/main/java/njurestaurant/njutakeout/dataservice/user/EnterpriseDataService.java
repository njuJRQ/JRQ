package njurestaurant.njutakeout.dataservice.user;

import njurestaurant.njutakeout.entity.user.Enterprise;
import njurestaurant.njutakeout.exception.NotExistException;

import java.util.List;

public interface EnterpriseDataService {

	void saveEnterprise(Enterprise enterprise);

	void addEnterprise(Enterprise enterprise);

	Enterprise getEnterpriseByOpenid(String openid) throws NotExistException;

	List<Enterprise> getAllEnterprises();

	void deleteEnterpriseByOpenid(String openid) throws NotExistException;
}
