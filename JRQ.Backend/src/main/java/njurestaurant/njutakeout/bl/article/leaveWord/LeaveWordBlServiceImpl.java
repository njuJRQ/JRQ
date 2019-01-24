package njurestaurant.njutakeout.bl.article.leaveWord;

import njurestaurant.njutakeout.blservice.article.leaveWord.LeaveWordBlService;
import njurestaurant.njutakeout.dataservice.article.LeaveWordDataService;
import njurestaurant.njutakeout.entity.article.LeaveWord;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.InfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeaveWordBlServiceImpl implements LeaveWordBlService {
    private final LeaveWordDataService leaveWordDataService;

    @Autowired
    public LeaveWordBlServiceImpl(LeaveWordDataService leaveWordDataService) {
        this.leaveWordDataService = leaveWordDataService;
    }

    @Override
    public InfoResponse publishMyLeaveWord(String content, String courseId, String writerOpenid) {
        leaveWordDataService.addLeaveWord(new LeaveWord(content,writerOpenid,System.currentTimeMillis(),courseId));
        return new InfoResponse();
    }

    @Override
    public InfoResponse deleteMyLeaveWord(String id) throws NotExistException {
        leaveWordDataService.deleteLeaveWordById(id);
        return new InfoResponse();
    }
}
