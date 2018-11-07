package njurestaurant.njutakeout.data.user;

import njurestaurant.njutakeout.data.dao.user.ClassificationDao;
import njurestaurant.njutakeout.data.dao.user.ClassificationDescriptionDao;
import njurestaurant.njutakeout.dataservice.user.ClassificationDataService;
import njurestaurant.njutakeout.entity.user.Classification;
import njurestaurant.njutakeout.entity.user.ClassificationDescription;
import njurestaurant.njutakeout.exception.NotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassificationDataServiceImpl implements ClassificationDataService {
	private final ClassificationDao classificationDao;
	private final ClassificationDescriptionDao classificationDescriptionDao;

	@Autowired
	public ClassificationDataServiceImpl(ClassificationDao classificationDao, ClassificationDescriptionDao classificationDescriptionDao) {
		this.classificationDao = classificationDao;
		this.classificationDescriptionDao = classificationDescriptionDao;
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
			throw new NotExistException("UserLabel", userLabel);
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
			throw new NotExistException("UserLabel", userLabel);
		}
	}

	@Override
	public void deleteClassificationByUserLabel(String userLabel) throws NotExistException {
		if (classificationDao.existsById(userLabel)) {
			classificationDao.deleteById(userLabel);
		} else {
			throw new NotExistException("Classification", userLabel);
		}
	}

	@Override
	public List<ClassificationDescription> getAllClassificationDescriptions() {
		return classificationDescriptionDao.findAll();
	}

	@Override
	public ClassificationDescription getClassificationDescriptionByWorkClass(String workClass) throws NotExistException {
		Optional<ClassificationDescription> optionalClassificationDescription =  classificationDescriptionDao.findClassificationDescriptionByWorkClass(workClass);
		if (optionalClassificationDescription.isPresent()) {
			return optionalClassificationDescription.get();
		} else {
			throw new NotExistException("ClassificationDescription workClass", workClass);
		}
	}

	@Override
	public void saveClassificationDescription(ClassificationDescription classificationDescription) {
		classificationDescriptionDao.save(classificationDescription);
	}


}
