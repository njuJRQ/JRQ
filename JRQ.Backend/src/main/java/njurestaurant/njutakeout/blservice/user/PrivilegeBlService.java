package njurestaurant.njutakeout.blservice.user;

import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.BoolResponse;
import njurestaurant.njutakeout.response.user.PrivilegeListResponse;
import njurestaurant.njutakeout.response.user.PrivilegeResponse;

public interface PrivilegeBlService {
	/**
	 * 添加特权(Admin)
	 * @param name 特权名称，取值："enterprise"
	 * @param price 特权价格
	 * @return 是否成功
	 */
	BoolResponse addPrivilege(String name, int price);

	/**
	 * 根据特权名称获取特权信息(Admin)
	 * @param name 特权名称
	 * @return 特权信息
	 */
	PrivilegeResponse getPrivilege(String name) throws NotExistException;

	/**
	 * 获取所有特权列表(Admin)
	 * @return 特权信息列表
	 */
	PrivilegeListResponse getPrivilegeList();

	/**
	 * 根据特权名称更新特权价格(Admin)
	 * @param name 特权名称
	 * @param price 特权价格
	 * @return 是否成功
	 */
	BoolResponse updatePrivilege(String name, int price);

	/**
	 * 根据名称删除特权
	 * @param name 要删除的特权名称
	 * @return 是否成功
	 */
	BoolResponse deleteUser(String name);
}
