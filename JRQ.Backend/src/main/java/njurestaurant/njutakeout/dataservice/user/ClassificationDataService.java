package njurestaurant.njutakeout.dataservice.user;

import njurestaurant.njutakeout.entity.user.Classification;
import njurestaurant.njutakeout.exception.NotExistException;

import java.util.List;

public interface ClassificationDataService {

	void addClassification(Classification classification);

	Classification getClassificationByUserLabel(String userLabel) throws NotExistException;

	List<Classification> getAllClassifications();

	void updateClassificationByUserLabel(String userLabel, String workClass) throws NotExistException;

	void deleteClassificationByUserLabel(String userLabel);
}
