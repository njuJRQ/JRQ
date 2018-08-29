package njurestaurant.njutakeout.blservice.admin;

import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.admin.AdminListResponse;
import njurestaurant.njutakeout.response.admin.AdminResponse;
import org.springframework.stereotype.Service;


public interface AdminBlService {
	/**
	 * 添加管理员
	 * @param username 用户名
	 * @param password 密码
	 * @param limits 权限
	 * @param date 创建时间
	 * @return 是否成功
	 */
	InfoResponse addAdmin(String username, String password, String limits, String date);

	/**
	 * 获取管理员信息
	 * @param id 管理员id
	 * @return 管理员信息
	 */
	AdminResponse getAdmin(int id);

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
	InfoResponse updateAdmin(int id, String username, String password, String limits, String date);

	/**
	 * 根据管理员ID删除管理员
	 * @param id 管理员ID
	 * @return 是否成功
	 */
	InfoResponse deleteAdmin(int id);
}
