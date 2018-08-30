package njurestaurant.njutakeout.data.user;

import njurestaurant.njutakeout.data.dao.user.ClassificationDao;
import njurestaurant.njutakeout.dataservice.user.ClassificationDataService;
import njurestaurant.njutakeout.entity.user.Classification;
import njurestaurant.njutakeout.exception.NotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassificationDataServiceImpl implements ClassificationDataService {
	private final ClassificationDao classificationDao;

	@Autowired
	public ClassificationDataServiceImpl(ClassificationDao classificationDao) {
		this.classificationDao = classificationDao;
	}

	@Override
	public void addClassification(Classification classification) {
		classificationDao.save(classification);
	}

	@Override
	public Classification getClassificationByUserLabel(String userLabel) throws NotExistException {
		Optional<Classification> optionalClassification = classificationDao.findById(userLabel);
		if (optionalClassification.isPresent()) {
			return optionalClassification.get();
		} else {
			throw new NotExistException("UserLabel");
		}
	}

	@Override
	public List<Classification> getAllClassifications() {
		return classificationDao.findAll();
	}

	@Override
	public void updateClassificationByUserLabel(String userLabel, String workClass) throws NotExistException {
		Optional<Classification> optionalClassification = classificationDao.findById(userLabel);
		if (optionalClassification.isPresent()) {
			Classification classification = optionalClassification.get();
			classification.setWorkClass(workClass);
			classificationDao.save(classification);
		} else {
			throw new NotExistException("UserLabel");
		}
	}

	@Override
	public void deleteClassificationByUserLabel(String userLabel) {
		classificationDao.deleteById(userLabel);
	}
}
