package njurestaurant.njutakeout.dataservice.article;

import njurestaurant.njutakeout.entity.article.Publish;
import njurestaurant.njutakeout.entity.article.PublishKey;

import java.util.List;

public interface PublishDataService {

	void addPublish(Publish publish);

	List<Publish> getPublishesByAdminId(String adminId);

	void deletePublishByKey(PublishKey publishKey);
}
