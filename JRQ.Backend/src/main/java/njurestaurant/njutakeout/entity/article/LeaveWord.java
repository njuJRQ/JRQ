package njurestaurant.njutakeout.entity.article;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name="leaveWord")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class LeaveWord {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String id; //留言编号

    @Column(length = 600)
    private String content; //留言内容

    @Column
    private String writerOpenid; //作者微信openid

    @Column
    private long timeStamp; //留言发布日期，如"2018-1-1"

    @Column
    private String courseId; //课程id

    public LeaveWord() {
    }

    public LeaveWord(String content, String writerOpenid, long timeStamp, String courseId) {
        this.content = content;
        this.writerOpenid = writerOpenid;
        this.timeStamp = timeStamp;
        this.courseId = courseId;
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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(new Date(timeStamp));
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
