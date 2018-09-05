package njurestaurant.njutakeout.blservice.purchase;

import njurestaurant.njutakeout.exception.AlreadyExistException;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.BoolResponse;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.purchase.PurchaseListResponse;
import njurestaurant.njutakeout.response.purchase.PurchaseResponse;

public interface PurchaseBlService {

	/**
	 * 用户下订单(User)
	 * @param openid 用户微信openid
	 * @param type 订单类型："course", "level", "enterprise"
	 * @param detail 订单详情：课程ID，会员等级名称，企业AdminId
	 * @param price 交易金额
	 * @param date 交易日期
	 * @return 是否成功
	 */
	BoolResponse addPurchase(String openid, String type, String detail, int price, String date);

	/**
	 * 根据订单号获取单个订单信息(User&Admin)
	 * @param id 订单ID
	 * @return 订单信息
	 */
	PurchaseResponse getPurchase(String id) throws NotExistException;

	/**
	 * 用户获取自己的订单列表(User&Admin)
	 * @param openid 用户微信openid
	 * @return 订单列表
	 */
	PurchaseListResponse getMyPurchaseList(String openid);

	/**
	 * 获取所有订单信息(Admin)
	 * @return 订单信息列表
	 */
	PurchaseListResponse getPurchaseList();

	/**
	 * 根据订单ID删除订单信息(Admin)
	 * @param id 订单ID
	 * @return 是否成功
	 */
	InfoResponse deletePurchase(String id) throws NotExistException;
}
