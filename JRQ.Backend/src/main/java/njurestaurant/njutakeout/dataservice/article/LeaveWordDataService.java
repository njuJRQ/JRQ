package njurestaurant.njutakeout.dataservice.article;

import njurestaurant.njutakeout.entity.article.Feed;
import njurestaurant.njutakeout.entity.article.LeaveWord;
import njurestaurant.njutakeout.exception.NotExistException;

import java.util.List;

public interface LeaveWordDataService {
    void addLeaveWord(LeaveWord leaveWord);
    void deleteLeaveWordById(String id) throws NotExistException;
    LeaveWord getLeaveWordById(String id) throws NotExistException;
    List<LeaveWord> getLeaveWordListBefore(String openid, String id,String courseId) throws NotExistException;
    List<LeaveWord> getLeaveWordListBeforeMore(String openid,long timeStamp,String courseId) throws NotExistException;

}
