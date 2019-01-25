package njurestaurant.njutakeout.blservice.article.leaveWord;

import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.article.leaveWord.LeaveWordViewListResponse;

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

    /**
     * 获取某一留言时间戳前的5条留言
     * @param openid 用户微信openid
     * @param courseId courseId 课程id
     * @param id 留言id
     * @return
     * @throws NotExistException
     */
    LeaveWordViewListResponse getLeaveWordViewListBefore(String openid, String courseId, String id) throws NotExistException;
}
