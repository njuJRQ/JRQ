package njurestaurant.njutakeout.blservice.user;

import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.user.PersonListResponse;
import njurestaurant.njutakeout.response.user.UserListResponse;
import njurestaurant.njutakeout.response.user.UserResponse;

import java.util.List;

public interface UserBlService {
	/**
	 * 添加用户
	 * @param openid 微信openid
	 * @param username 用户名
	 * @param face 用户头像
	 * @param medals 用户类别提示（可多个）
	 * @param phone 电话号码
	 * @param email 电子邮件
	 * @param company 公司名称
	 * @param department 部门
	 * @param position 职位
	 * @param intro 个人简介
	 * @param city 所在城市
	 * @param credit 账户余额
	 * @param label 用户类别信息，可取值：融资租赁，商业保理，地产交易，金融牌照
	 * @param valid 是否冻结/启用，true代表启用
	 * @return 是否成功
	 */
	InfoResponse addUser(String openid, String username, String face, List<String> medals, String phone, String email, String company, String department, String position, String intro, String city, int credit, String label, boolean valid);

	/**
	 * 根据微信openid获取用户信息
	 * @param openid 微信的openid
	 * @return 用户信息
	 */
	UserResponse getUser(String openid) throws NotExistException;

	/**
	 * 获取用户列表
	 * @return 用户列表
	 */
	UserListResponse getUserList();

	/**
	 * 根据微信openid更新用户信息
	 * @param openid 微信openid
	 * @param username 用户名
	 * @param face 用户头像
	 * @param medals 用户类别提示（可多个）
	 * @param phone 电话号码
	 * @param email 电子邮件
	 * @param company 公司名称
	 * @param department 部门
	 * @param position 职位
	 * @param intro 个人简介
	 * @param city 所在城市
	 * @param credit 账户余额
	 * @param label 用户类别信息，可取值：融资租赁，商业保理，地产交易，金融牌照
	 * @param valid 是否冻结/启用，true代表启用
	 * @return 是否成功
	 */
	InfoResponse updateUser(String openid, String username, String face, List<String> medals, String phone, String email, String company, String department, String position, String intro, String city, int credit, String label, boolean valid) throws NotExistException;

	/**
	 * 根据微信openid删除用户
	 * @param openid 微信openid
	 * @return 是否成功
	 */
	InfoResponse deleteUser(String openid);

	/**
	 * 获取业务名片列表
	 * @param kind 业务类别：capital, stock, merge分别代表金融类，股票类，并购类
	 * @return 业务名片列表
	 */
	PersonListResponse getPersonList(String kind);

	/**
	 * 获取符合特定条件的业务名片列表
	 * @param condition 条件
	 * @return 业务名片列表
	 */
	PersonListResponse getPersonListByCondition(String condition);

	/**
	 * 用户向别人发送名片
	 * @param senderOpenid 发送者微信openid
	 * @param receiverOpenid 接收者微信openid
	 * @return 是否成功
	 */
	InfoResponse sendMyCard(String senderOpenid, String receiverOpenid);

	/**
	 * 用户获取自己的名片列表
	 * @param openid 用户的微信openid
	 * @param kind 名片类型 "new","current","whoHasMyCard"
	 * @return 特定类型的名片列表
	 */
	PersonListResponse getMyPersonList(String openid, String kind) throws NotExistException;

	/**
	 * 将用户收到的名片设置为已读
	 * @param senderOpenid 发送者微信openid
	 * @param receiverOpenid 接收者微信openid
	 * @return 是否成功
	 */
	InfoResponse checkMyReceivedCard(String senderOpenid, String receiverOpenid) throws NotExistException;

}
