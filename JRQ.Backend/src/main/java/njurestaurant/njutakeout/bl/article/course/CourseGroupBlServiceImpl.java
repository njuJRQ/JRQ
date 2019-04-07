package njurestaurant.njutakeout.bl.article.course;

import njurestaurant.njutakeout.blservice.article.course.CourseGroupBlService;
import njurestaurant.njutakeout.dataservice.admin.AdminDataService;
import njurestaurant.njutakeout.dataservice.article.CourseDataService;
import njurestaurant.njutakeout.dataservice.article.CourseGroupDataService;
import njurestaurant.njutakeout.dataservice.purchase.PurchaseCourseDataService;
import njurestaurant.njutakeout.dataservice.user.EnterpriseDataService;
import njurestaurant.njutakeout.entity.article.Course;
import njurestaurant.njutakeout.entity.article.CourseGroup;
import njurestaurant.njutakeout.entity.purchase.PurchaseCourseKey;
import njurestaurant.njutakeout.entity.user.Enterprise;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.article.course.CourseGroupItem;
import njurestaurant.njutakeout.response.article.course.CourseGroupListResponse;
import njurestaurant.njutakeout.response.article.course.CourseGroupResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseGroupBlServiceImpl implements CourseGroupBlService {
    private final CourseDataService courseDataService;
    private final CourseGroupDataService courseGroupDataService;
    private final PurchaseCourseDataService purchaseCourseDataService;
    private final EnterpriseDataService enterpriseDataService;
    private final AdminDataService adminDataService;

    @Autowired
    public CourseGroupBlServiceImpl(CourseDataService courseDataService, CourseGroupDataService courseGroupDataService, PurchaseCourseDataService purchaseCourseDataService, EnterpriseDataService enterpriseDataService, AdminDataService adminDataService) {
        this.courseDataService = courseDataService;
        this.courseGroupDataService = courseGroupDataService;
        this.purchaseCourseDataService = purchaseCourseDataService;
        this.enterpriseDataService = enterpriseDataService;
        this.adminDataService = adminDataService;
    }

    @Override
    public InfoResponse add(String name, String writerName, String image, List<String> courses, int price) throws NotExistException {
        CourseGroup courseGroup = new CourseGroup();
        courseGroup.setTitle(name);
        courseGroup.setWriterName(writerName);
        courseGroup.setImage(image);
        courseGroup.setTimeStamp(System.currentTimeMillis());
        courseGroup.setPrice(price);
        List<Course> courseList = new ArrayList<>();
        if (courses != null && courses.size() > 0) {
            for (String courseId : courses) {
                courseList.add(courseDataService.getCourseById(courseId));
            }
        }
        courseGroup.setCourseList(courseList);
        courseGroupDataService.add(courseGroup);
        return new InfoResponse();
    }

    @Override
    public InfoResponse update(String id, String title, String writerName, String image, List<String> courses, int price) throws NotExistException {
        CourseGroup courseGroup = courseGroupDataService.findById(id);
        courseGroup.setTitle(title);
        courseGroup.setWriterName(writerName);
        courseGroup.setImage(image);
        courseGroup.setTimeStamp(System.currentTimeMillis());
        courseGroup.setPrice(price);
        List<Course> courseList = new ArrayList<>();
        if (courses != null && courses.size() > 0) {
            for (String courseId : courses) {
                courseList.add(courseDataService.getCourseById(courseId));
            }
        }
        courseGroup.setCourseList(courseList);
        courseGroupDataService.update(courseGroup);
        return new InfoResponse();
    }

    @Override
    public CourseGroupResponse findById(String id) throws NotExistException {
        return new CourseGroupResponse(new CourseGroupItem(courseGroupDataService.findById(id)));
    }

    /**
     * 获得课程组合列表
     *
     * @param id     @return 课程组合列表
     * @param openid
     */
    @Override
    public CourseGroupListResponse getCourseGroupListBefore(String id, String openid) {
        List<CourseGroup> courseGroups = courseGroupDataService.getCourseGroupListBefore(id);
        List<CourseGroupItem> courseGroupItems = new ArrayList<>();
        for (CourseGroup courseGroup : courseGroups) {
            courseGroupItems.add(new CourseGroupItem(courseGroup));
        }
        return new CourseGroupListResponse(courseGroupItems);
    }

    @Override
    public CourseGroupListResponse getAll() {
        List<CourseGroup> courseGroups = courseGroupDataService.getAll();
        List<CourseGroupItem> courseGroupItems = new ArrayList<>();
        for (CourseGroup courseGroup : courseGroups) {
            courseGroupItems.add(new CourseGroupItem(courseGroup));
        }
        return new CourseGroupListResponse(courseGroupItems);
    }

    @Override
    public CourseGroupListResponse getMyCourseGroupList(String openid) throws NotExistException {
        List<CourseGroup> courseGroupList = courseGroupDataService.getAll();
        List<CourseGroupItem> courseGroupItems = new ArrayList<>();
        for (CourseGroup courseGroup : courseGroupList) {
            if (hasUserBoughtCourseGroup(openid, courseGroup)) {
                CourseGroupItem courseItem = new CourseGroupItem(courseGroup);
                courseGroupItems.add(courseItem);
            }
        }
        return new CourseGroupListResponse(courseGroupItems);
    }

    @Override
    public InfoResponse deleteById(String id) throws NotExistException {
        courseGroupDataService.deleteById(id);
        return new InfoResponse();
    }


    private boolean hasUserBoughtCourseGroup(String openid, CourseGroup courseGroup) throws NotExistException {
        boolean hasBought = purchaseCourseDataService.isPurchaseCourseExistent(
                new PurchaseCourseKey(openid, courseGroup.getId(), true)); //用户是否已购买
        if (enterpriseDataService.isUserEnterprise(openid)) { //用户获取自己发布的课程
            Enterprise enterprise = enterpriseDataService.getEnterpriseByOpenid(openid);
            if (courseGroup.getWriterName().equals(
                    adminDataService.getAdminById(enterprise.getAdminId()).getUsername())) {
                hasBought = true;
            }
        }
        return hasBought;
    }

}
