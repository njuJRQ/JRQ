package njurestaurant.njutakeout.response.article.leaveWord;

import njurestaurant.njutakeout.entity.article.LeaveWord;

public class LeaveWordItem {
    private String id; //留言编号
    private String content; //留言内容
    private String writerOpenid; //作者微信openid
    private String date; //留言发布日期，如"2018-1-1"

    public LeaveWordItem(LeaveWord leaveWord) {
        this.id = leaveWord.getId();
        this.content = leaveWord.getContent();
        this.writerOpenid = leaveWord.getWriterOpenid();
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
