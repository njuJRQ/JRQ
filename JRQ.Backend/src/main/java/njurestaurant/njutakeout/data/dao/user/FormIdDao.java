package njurestaurant.njutakeout.data.dao.user;

import njurestaurant.njutakeout.entity.user.FormId;
import njurestaurant.njutakeout.entity.user.FormIdKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FormIdDao extends JpaRepository<FormId, FormIdKey> {
	List<FormId> findAllByOpenidOrderByTimestamp(String openid);

	@Modifying
	@Transactional
	void deleteAllByTimestampBefore(long timestamp); //删除过期的formId

	@Modifying
	@Transactional
	void deleteAllByOpenid(String openid); //删除特定用户的所有formId
}
