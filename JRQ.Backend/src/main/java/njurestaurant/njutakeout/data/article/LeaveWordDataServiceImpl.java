package njurestaurant.njutakeout.data.article;

import njurestaurant.njutakeout.data.dao.article.LeaveWordDao;
import njurestaurant.njutakeout.dataservice.article.LeaveWordDataService;
import njurestaurant.njutakeout.entity.article.Feed;
import njurestaurant.njutakeout.entity.article.LeaveWord;
import njurestaurant.njutakeout.exception.NotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LeaveWordDataServiceImpl implements LeaveWordDataService {
    private final LeaveWordDao leaveWordDao;

    @Autowired
    public LeaveWordDataServiceImpl(LeaveWordDao leaveWordDao) {
        this.leaveWordDao = leaveWordDao;
    }

    @Override
    public void addLeaveWord(LeaveWord leaveWord) {
        leaveWordDao.save(leaveWord);
    }

    @Override
    public void deleteLeaveWordById(String id) throws NotExistException {
        Optional<LeaveWord> optionalLeaveWord = leaveWordDao.findById(id);
        if (optionalLeaveWord.isPresent()) {
            LeaveWord leaveWord = optionalLeaveWord.get();
            leaveWordDao.delete(leaveWord);
        } else {
            throw new NotExistException("LeaveWord ID", id);
        }
    }

    @Override
    public LeaveWord getLeaveWordById(String id) throws NotExistException {
        Optional<LeaveWord> optionalLeaveWord = leaveWordDao.findById(id);
        if (optionalLeaveWord.isPresent()) {
            return optionalLeaveWord.get();
        } else {
            throw new NotExistException("LeaveWOrd ID", id);
        }
    }

    @Override
    public List<LeaveWord> getLeaveWordListBefore(String openid, String id, String courseId) throws NotExistException {
        return getLeaveWordListBeforeMore(openid,
                id.equals("")?-1:getLeaveWordById(id).getTimeStamp(),courseId);
    }

    @Override
    public List<LeaveWord> getLeaveWordListBeforeMore(String openid, long timeStamp, String courseId) throws NotExistException {
        List<LeaveWord> leaveWords;
        if (timeStamp < 0) {
            leaveWords = leaveWordDao.findTop5ByCourseIdOrderByTimeStampDesc(courseId);
        } else {
            leaveWords = leaveWordDao.findTop5ByTimeStampBeforeAndCourseIdOrderByTimeStamp(timeStamp,courseId);
        }
        if (!leaveWords.isEmpty()) {
            List<LeaveWord> sameTimeStampLeaveWords = leaveWordDao.findLeaveWordsByTimeStampAndCourseId(leaveWords.get(leaveWords.size() - 1).getTimeStamp(),courseId);
            for (LeaveWord stl : sameTimeStampLeaveWords) {
                boolean flag = false; //标记ssc是否在courses中
                for (LeaveWord l : leaveWords) {
                    if (stl.getId().equals(l.getId())) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) { //ssc不在courses里面，加入进去
                    leaveWords.add(stl);
                }
            }
        }

        return leaveWords;
    }
}
