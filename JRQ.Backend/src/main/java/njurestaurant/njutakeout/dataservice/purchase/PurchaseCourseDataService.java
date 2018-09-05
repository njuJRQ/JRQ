package njurestaurant.njutakeout.dataservice.purchase;

import njurestaurant.njutakeout.entity.purchase.Purchase;
import njurestaurant.njutakeout.entity.purchase.PurchaseCourse;
import njurestaurant.njutakeout.entity.purchase.PurchaseCourseKey;
import njurestaurant.njutakeout.exception.AlreadyExistException;
import njurestaurant.njutakeout.exception.NotExistException;

import java.util.List;

public interface PurchaseCourseDataService {

	void addPurchaseCourse(PurchaseCourse purchaseCourse) throws AlreadyExistException;

	PurchaseCourse getPurchaseCourseByKey(PurchaseCourseKey purchaseCourseKey) throws NotExistException;

	List<PurchaseCourse> getPurchaseCourseByOpenid(String openid);

	List<PurchaseCourse> getPurchaseCourseByCourseId(String courseId);

	List<PurchaseCourse> getAllPurchaseCourses();

	void deletePurchaseCourseByKey(PurchaseCourseKey purchaseCourseKey) throws NotExistException;
}
