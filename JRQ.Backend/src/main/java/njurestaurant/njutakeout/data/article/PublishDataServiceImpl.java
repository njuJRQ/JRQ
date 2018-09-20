package njurestaurant.njutakeout.data.article;

import njurestaurant.njutakeout.data.dao.article.PublishDao;
import njurestaurant.njutakeout.dataservice.article.PublishDataService;
import njurestaurant.njutakeout.entity.article.Publish;
import njurestaurant.njutakeout.entity.article.PublishKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublishDataServiceImpl implements PublishDataService {
	private final PublishDao publishDao;

	@Autowired
	public PublishDataServiceImpl(PublishDao publishDao) {
		this.publishDao = publishDao;
	}

	@Override
	public void addPublish(Publish publish) {
		publishDao.save(publish);
	}

	@Override
	public List<Publish> getPublishesByAdminId(String adminId) {
		return publishDao.findPublishesByAdminId(adminId);
	}

	@Override
	public void deletePublishByKey(PublishKey publishKey) {
		publishDao.deleteById(publishKey);
	}
}
