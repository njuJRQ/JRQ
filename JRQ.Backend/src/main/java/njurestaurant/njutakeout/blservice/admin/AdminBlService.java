package njurestaurant.njutakeout.blservice.admin;

import njurestaurant.njutakeout.exception.DuplicateUsernameException;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.exception.WrongPasswordException;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.admin.AdminListResponse;
import njurestaurant.njutakeout.response.admin.AdminResponse;


public interface AdminBlService {

	/**
	 * 管理员登录验证
	 * @param username 用户名
	 * @param password 密码
	 * @return 是否验证成功
	 */
	InfoResponse loginAdmin(String username, String password) throws NotExistException, WrongPasswordException;

	/**
	 * 添加管理员
	 * @param username 用户名
	 * @param password 密码
	 * @param limits 权限
	 * @param date 创建时间
	 * @return 是否成功
	 */
	InfoResponse addAdmin(String username, String password, String limits, String date) throws DuplicateUsernameException;

	/**
	 * 获取管理员信息
	 * @param id 管理员id
	 * @return 管理员信息
	 */
	AdminResponse getAdmin(String id) throws NotExistException;

	/**
	 * 获取管理员列表
	 * @return 管理员列表
	 */
	AdminListResponse getAdminList();

	/**
	 * 根据管理员ID修改管理员信息
	 * @param id 管理员ID
	 * @param username 用户名
	 * @param password 密码
	 * @param limits 权限
	 * @param date 创建日期
	 * @return 是否成功
	 */
	InfoResponse updateAdmin(String id, String username, String password, String limits, String date) throws NotExistException;

	/**
	 * 根据管理员ID删除管理员
	 * @param id 管理员ID
	 * @return 是否成功
	 */
	InfoResponse deleteAdmin(String id) throws NotExistException;
}
