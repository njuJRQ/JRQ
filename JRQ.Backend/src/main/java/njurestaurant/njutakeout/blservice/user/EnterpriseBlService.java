package njurestaurant.njutakeout.blservice.user;

import njurestaurant.njutakeout.response.BoolResponse;

public interface EnterpriseBlService {
	/**
	 * 用户升级自己为企业账户
	 * @param openid 用户的微信openid
	 * @return 成功则返回用户名密码信息；失败则返回错误信息
	 */
	BoolResponse setMyUserAsEnterprise(String openid);
}
