package njurestaurant.njutakeout.bl.article.course;

import njurestaurant.njutakeout.blservice.article.course.CourseGroupBlService;
import njurestaurant.njutakeout.dataservice.admin.AdminDataService;
import njurestaurant.njutakeout.dataservice.article.CourseDataService;
import njurestaurant.njutakeout.dataservice.article.CourseGroupDataService;
import njurestaurant.njutakeout.dataservice.article.LikeDataService;
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
import njurestaurant.njutakeout.response.article.course.CourseListResponse;
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
    private final LikeDataService likeDataService;

    @Autowired
    public CourseGroupBlServiceImpl(CourseDataService courseDataService, CourseGroupDataService courseGroupDataService, PurchaseCourseDataService purchaseCourseDataService, EnterpriseDataService enterpriseDataService, AdminDataService adminDataService, LikeDataService likeDataService) {
        this.courseDataService = courseDataService;
        this.courseGroupDataService = courseGroupDataService;
        this.purchaseCourseDataService = purchaseCourseDataService;
        this.enterpriseDataService = enterpriseDataService;
        this.adminDataService = adminDataService;
        this.likeDataService = likeDataService;
    }

    @Override
    public InfoResponse add(String name, List<String> courses) throws NotExistException {
        CourseGroup courseGroup = new CourseGroup();
        courseGroup.setTitle(name);
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
    public InfoResponse update(String id, String title, List<String> courses) throws NotExistException {
        CourseGroup courseGroup = courseGroupDataService.findById(id);
        courseGroup.setTitle(title);
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

    @Override
    public CourseGroupListResponse getAll() {
        List<CourseGroup> courseGroupList = courseGroupDataService.getAll();
        List<CourseGroupItem> courseGroupItemList = new ArrayList<>();
        if (courseGroupList != null && courseGroupList.size() > 0) {
            for (CourseGroup courseGroup : courseGroupList) {
                courseGroupItemList.add(new CourseGroupItem(courseGroup));
            }
        }
        return new CourseGroupListResponse(courseGroupItemList);
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
