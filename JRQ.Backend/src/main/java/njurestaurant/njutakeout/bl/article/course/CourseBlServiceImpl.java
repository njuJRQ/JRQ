package njurestaurant.njutakeout.bl.article.course;

import njurestaurant.njutakeout.blservice.article.course.CourseBlService;
import njurestaurant.njutakeout.dataservice.admin.AdminDataService;
import njurestaurant.njutakeout.dataservice.article.CourseDataService;
import njurestaurant.njutakeout.dataservice.article.LikeDataService;
import njurestaurant.njutakeout.dataservice.purchase.PurchaseCourseDataService;
import njurestaurant.njutakeout.dataservice.user.EnterpriseDataService;
import njurestaurant.njutakeout.entity.article.Course;
import njurestaurant.njutakeout.entity.purchase.PurchaseCourseKey;
import njurestaurant.njutakeout.entity.user.Enterprise;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.article.course.CourseItem;
import njurestaurant.njutakeout.response.article.course.CourseListResponse;
import njurestaurant.njutakeout.response.article.course.CourseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseBlServiceImpl implements CourseBlService {
	private final CourseDataService courseDataService;
	private final PurchaseCourseDataService purchaseCourseDataService;
	private final EnterpriseDataService enterpriseDataService;
	private final AdminDataService adminDataService;
	private final LikeDataService likeDataService;

	@Autowired
	public CourseBlServiceImpl(CourseDataService courseDataService, PurchaseCourseDataService purchaseCourseDataService, EnterpriseDataService enterpriseDataService, AdminDataService adminDataService, LikeDataService likeDataService) {
		this.courseDataService = courseDataService;
		this.purchaseCourseDataService = purchaseCourseDataService;
		this.enterpriseDataService = enterpriseDataService;
		this.adminDataService = adminDataService;
		this.likeDataService = likeDataService;
	}

	@Override
	public InfoResponse addCourse(String title, String image, String writerName, String date, long likeNum, String video, int price) {
		courseDataService.addCourse(new Course(title, image, writerName, date, likeNum, video, price));
		return new InfoResponse();
	}

	@Override
	public CourseResponse getCourse(String id) throws NotExistException {
		return new CourseResponse(new CourseItem(courseDataService.getCourseById(id)));
	}

	@Override
	public CourseListResponse getCourseList() {
		List<Course> courses = courseDataService.getAllCourses();
		List<CourseItem> courseItems = new ArrayList<>();
		for (Course course:courses) {
			courseItems.add(new CourseItem(course));
		}
		return new CourseListResponse(courseItems);
	}

	@Override
	public InfoResponse updateCourse(String id, String title, String image, String writerName, String date, long likeNum, String video, int price) throws NotExistException {
		Course course = courseDataService.getCourseById(id);
		course.setTitle(title);
		course.setImage(image);
		course.setWriterName(writerName);
		course.setDate(date);
		course.setLikeNum(likeNum);
		course.setVideo(video);
		course.setPrice(price);
		courseDataService.saveCourse(course);
		return new InfoResponse();
	}

	@Override
	public InfoResponse deleteCourse(String id) throws NotExistException {
		courseDataService.deleteCourseById(id);
		return new InfoResponse();
	}

	@Override
	public CourseResponse getMyCourse(String openid, String courseId) throws NotExistException {
		Course course = courseDataService.getCourseById(courseId);
		boolean hasBought = purchaseCourseDataService.isPurchaseCourseExistent(
				new PurchaseCourseKey(openid, course.getId())); //用户是否已购买
		if (enterpriseDataService.isUserEnterprise(openid)) { //用户获取自己发布的课程
			Enterprise enterprise = enterpriseDataService.getEnterpriseByOpenid(openid);
			if (course.getWriterName().equals(
					adminDataService.getAdminById(enterprise.getAdminId()).getUsername())) {
				hasBought = true;
			}
		}
		boolean hasLiked = likeDataService.isLikeExistent(openid, "course", course.getId());
		return new CourseResponse(new CourseItem(course, hasBought, hasLiked));
	}

	@Override
	public CourseListResponse getMyCourseList(String openid) throws NotExistException {
		List<Course> courses = courseDataService.getAllCourses();
		List<CourseItem> courseItems = new ArrayList<>();
		for (Course course:courses) {
			boolean hasBought = purchaseCourseDataService.isPurchaseCourseExistent(
					new PurchaseCourseKey(openid, course.getId())); //用户是否已购买
			if (enterpriseDataService.isUserEnterprise(openid)) { //用户获取自己发布的课程
				Enterprise enterprise = enterpriseDataService.getEnterpriseByOpenid(openid);
				if (course.getWriterName().equals(
						adminDataService.getAdminById(enterprise.getAdminId()).getUsername())) {
					hasBought = true;
				}
			}
			boolean hasLiked = likeDataService.isLikeExistent(openid, "course", course.getId());
			CourseItem courseItem = new CourseItem(course, hasBought, hasLiked);
			courseItems.add(courseItem);
		}
		return new CourseListResponse(courseItems);
	}
}
