package njurestaurant.njutakeout.data.dao.purchase;

import njurestaurant.njutakeout.entity.purchase.PurchaseCourse;
import njurestaurant.njutakeout.entity.purchase.PurchaseCourseKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseCourseDao extends JpaRepository<PurchaseCourse, PurchaseCourseKey> {
	List<PurchaseCourse> findPurchaseCourseByOpenid(String openid);
	List<PurchaseCourse> findPurchaseCourseByCourseId(String courseId);
}
