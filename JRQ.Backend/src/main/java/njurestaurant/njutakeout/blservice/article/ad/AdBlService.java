package njurestaurant.njutakeout.blservice.article.ad;

import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.article.ad.AdListResponse;
import njurestaurant.njutakeout.response.article.ad.AdResponse;

public interface AdBlService {
	/**
	 * 添加广告(Admin)
	 * @param image 广告图片URL
	 * @param link 广告导向的链接
	 * @param showPlace 广告展示位置："index"（首页）, "service"（业务）
	 * @return 是否成功
	 */
	InfoResponse addAd(String image, String link, String showPlace);

	/**
	 * 根据广告ID获取广告(Admin)
	 * @param id 广告ID
	 * @return 广告信息
	 */
	AdResponse getAd(String id) throws NotExistException;

	/**
	 * 获取被选中在首页展示的广告(User&Admin)
	 * @param showPlace 广告展示位置："index"（首页）, "service"（业务）
	 * @return 在首页展示的广告信息
	 */
	AdResponse getCheckedAd(String showPlace);

	/**
	 * 获取所有广告信息(Admin)
	 * @return 所有广告列表
	 */
	AdListResponse getAdList();

	/**
	 * 设置在首页展示的广告(Admin)
	 * @param id 被选中的广告ID
	 * @return 是否成功
	 */
	InfoResponse setCheckedAd(String id) throws NotExistException;

	/**
	 * 根据广告ID修改广告信息(Admin)
	 * @param id 广告ID
	 * @param image 广告图片路径
	 * @param link 广告导向的链接
	 * @param showPlace 广告展示位置："index"（首页）, "service"（业务）
	 * @return 是否成功
	 */
	InfoResponse updateAd(String id, String image, String link, String showPlace) throws NotExistException;

	/**
	 * 根据广告ID删除广告(Admin)
	 * @param id 广告ID
	 * @return 是否成功
	 */
	InfoResponse deleteAd(String id) throws NotExistException;
}
