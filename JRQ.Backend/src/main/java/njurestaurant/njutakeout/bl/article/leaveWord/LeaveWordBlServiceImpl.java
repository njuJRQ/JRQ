package njurestaurant.njutakeout.bl.article.leaveWord;

import njurestaurant.njutakeout.blservice.article.leaveWord.LeaveWordBlService;
import njurestaurant.njutakeout.dataservice.article.LeaveWordDataService;
import njurestaurant.njutakeout.dataservice.user.UserDataService;
import njurestaurant.njutakeout.entity.article.LeaveWord;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.article.leaveWord.LeaveWordViewItem;
import njurestaurant.njutakeout.response.article.leaveWord.LeaveWordViewListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeaveWordBlServiceImpl implements LeaveWordBlService {
    private final LeaveWordDataService leaveWordDataService;
    private final UserDataService userDataService;

    @Autowired
    public LeaveWordBlServiceImpl(LeaveWordDataService leaveWordDataService, UserDataService userDataService) {
        this.leaveWordDataService = leaveWordDataService;
        this.userDataService = userDataService;
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

    @Override
    public LeaveWordViewListResponse getLeaveWordViewListBefore(String openid, String courseId, String id) throws NotExistException{
        List<LeaveWord> leaveWords = leaveWordDataService.getLeaveWordListBefore(openid,id,courseId);
        List<LeaveWordViewItem> leaveWordViewItems = new ArrayList<>();
        for(LeaveWord leaveWord:leaveWords){
            leaveWordViewItems.add(new LeaveWordViewItem(leaveWord,userDataService));
        }
        return new LeaveWordViewListResponse(leaveWordViewItems);
    }
}
