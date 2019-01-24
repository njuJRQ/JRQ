package njurestaurant.njutakeout.blservice.article.leaveWord;

import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.InfoResponse;

import java.util.List;

public interface LeaveWordBlService {
    /**
     * 用户给某个课程发布自己的留言
     * @param content 留言内容
     * @param courseId 课程id
     * @param writerOpenid 作者微信openid
     * @return 是否成功
     */
    InfoResponse publishMyLeaveWord(String content, String courseId, String writerOpenid);

    /**
     * 用户根据留言ID删除自己的留言
     * @param id 留言id
     * @return 是否成功
     */
    InfoResponse deleteMyLeaveWord(String id) throws NotExistException;
}
