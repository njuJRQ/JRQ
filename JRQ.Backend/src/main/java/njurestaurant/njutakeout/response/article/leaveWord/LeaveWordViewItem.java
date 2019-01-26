package njurestaurant.njutakeout.response.article.leaveWord;

import njurestaurant.njutakeout.dataservice.user.UserDataService;
import njurestaurant.njutakeout.entity.article.LeaveWord;
import njurestaurant.njutakeout.entity.user.User;
import njurestaurant.njutakeout.exception.NotExistException;

public class LeaveWordViewItem {
    private String id; //留言编号
    private String content; //留言内容
    private String writerOpenid; //作者微信openid
    private String writerName; //作者名字
    private String writerFace; //作者头像
    private String date; //留言发布日期，如"2018-1-1"

    public LeaveWordViewItem(LeaveWord leaveWord, UserDataService userDataService) throws NotExistException {
        this.id = leaveWord.getId();
        this.content = leaveWord.getContent();
        this.writerOpenid = leaveWord.getWriterOpenid();
        User user = userDataService.getUserByOpenid(leaveWord.getWriterOpenid());
        this.writerName = user.getUsername();
        this.writerFace = user.getFace();
        this.date = leaveWord.getDate();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriterOpenid() {
        return writerOpenid;
    }

    public void setWriterOpenid(String writerOpenid) {
        this.writerOpenid = writerOpenid;
    }

    public String getWriterName() {
        return writerName;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }

    public String getWriterFace() {
        return writerFace;
    }

    public void setWriterFace(String writerFace) {
        this.writerFace = writerFace;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
