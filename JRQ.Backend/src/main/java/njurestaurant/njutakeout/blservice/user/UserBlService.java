package njurestaurant.njutakeout.blservice.user;

import njurestaurant.njutakeout.entity.user.Classification;
import njurestaurant.njutakeout.exception.CardLimitUseUpException;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.user.*;

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
	InfoResponse addUser(String openid, String username, String face, List<String> medals, String phone, String email, String company, String department, String position, String intro, String city, int credit, String label, int cardLimit, boolean valid);

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

	/** TODO:用户修改自己的信息应该使用其他API，另外，仅有openid就可以修改是很不安全的
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
	InfoResponse updateUser(String openid, String username, String face, List<String> medals, String phone, String email, String company, String department, String position, String intro, String city, int credit, String label, int cardLimit, boolean valid) throws NotExistException;

	/**
	 * 根据微信openid删除用户
	 * @param openid 微信openid
	 * @return 是否成功
	 */
	InfoResponse deleteUser(String openid);

	/**
	 * 添加用户标签相应的业务分类项
	 * @param userLabel 用户标签：融资租赁，商业保理，地产交易，金融牌照
	 * @param workClass 业务分类：capital, stock, merge分别代表金融类，股票类，并购类
	 * @return 是否成功
	 */
	InfoResponse addClassification(String userLabel, String workClass);

	/**
	 * 根据用户标签获取其对应的业务分类
	 * @param userLabel 用户标签
	 * @return 分类项
	 */
	ClassificationResponse getClassification(String userLabel) throws NotExistException;

	/**
	 * 获取所有业务分类项
	 * @return 业务分类项列表
	 */
	ClassificationListResponse getClassificationList();

	/**
	 * 更新特定用户标签所属的业务分类
	 * @param userLabel 用户标签
	 * @param workClass 业务分类
	 * @return 是否成功
	 */
	InfoResponse updateClassification(String userLabel, String workClass) throws NotExistException;

	/**
	 * 删除特定用户标签的分类项
	 * @param userLabel 用户标签
	 * @return 是否成功
	 */
	InfoResponse deleteClassification(String userLabel);

	/**
	 * 获取业务名片列表
	 * @param workClass 业务类别：capital, stock, merge分别代表金融类，股票类，并购类
	 * @return 业务名片列表
	 */
	PersonListResponse getPersonList(String workClass) throws NotExistException;

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
	CardListResponse getMyCardList(String openid, String kind) throws NotExistException;

	/**
	 * 将用户收到的名片设置为已读
	 * @param senderOpenid 发送者微信openid
	 * @param receiverOpenid 接收者微信openid
	 * @return 是否成功
	 */
	InfoResponse checkMyReceivedCard(String senderOpenid, String receiverOpenid) throws NotExistException;

	/**
	 * 用户查看别人的名片，每天次数有限制
	 * @param userOpenid 用户自己的微信openid
	 * @param otherOpenid 要查看的用户的openid
	 * @return 别人的名片
	 */
	CardResponse getOtherCard(String userOpenid, String otherOpenid) throws NotExistException, CardLimitUseUpException;

}
