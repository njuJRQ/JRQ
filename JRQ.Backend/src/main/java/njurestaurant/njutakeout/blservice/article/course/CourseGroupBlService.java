package njurestaurant.njutakeout.blservice.article.course;

import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.article.course.CourseGroupListResponse;
import njurestaurant.njutakeout.response.article.course.CourseGroupResponse;

import java.util.List;

public interface CourseGroupBlService {
    /**
     * 新建课程组合
     * @param name
     * @param courses
     * @return 是否添加成功
     */
    InfoResponse add(String name,List<String> courses) throws NotExistException;

    /**
     * 根据id修改课程组合
     * @param id
     * @param title
     * @param courses
     * @return 是否成功
     */
    InfoResponse update(String id,String title,List<String> courses) throws NotExistException;

    /**
     * 根据id查找课程组合
     * @param id
     * @return 课程组合内容
     */
    CourseGroupResponse findById(String id) throws NotExistException;

    /**
     * 获得课程组合列表
     * @param
     * @return 课程组合列表
     */
    CourseGroupListResponse getAll();

    /**
     * 获得用于已购买的课程组合列表
     * @param openid
     * @return 课程组合列表
     */
    CourseGroupListResponse getMyCourseGroupList(String openid) throws NotExistException;

}
