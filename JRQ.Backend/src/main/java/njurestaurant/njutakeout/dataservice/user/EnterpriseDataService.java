package njurestaurant.njutakeout.dataservice.user;

import njurestaurant.njutakeout.entity.user.Enterprise;
import njurestaurant.njutakeout.exception.NotExistException;

import java.util.List;

public interface EnterpriseDataService {

	boolean isEnterpriseExistent(String id);

	boolean isUserInEnterprise(String openid);

	boolean isUserEnterprise(String openid);

	boolean isAdminInEnterprise(String adminId);

	boolean isAdminEnterprise(String adminId);

	void saveEnterprise(Enterprise enterprise);

	void addEnterprise(Enterprise enterprise);

	Enterprise getEnterpriseById(String id) throws NotExistException;

	Enterprise getEnterpriseByOpenid(String openid) throws NotExistException;

	Enterprise getEnterpriseByAdminId(String adminId) throws NotExistException;

	List<Enterprise> getAllEnterprises();

	//只删除单个Enterprise表项（因为之前Admin就已经被删掉了）
	void deleteEnterpriseById(String id) throws NotExistException;

	//删除Enterprise表项时将Admin相关数据全部删除
	void deleteEnterpriseByIdCascade(String id) throws NotExistException;
}
