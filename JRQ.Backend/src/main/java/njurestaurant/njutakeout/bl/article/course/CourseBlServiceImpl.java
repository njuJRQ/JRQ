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
	public InfoResponse addCourse(String title, String image, String writerName, long likeNum, String video, int price) {
		courseDataService.addCourse(new Course(title, image, writerName, System.currentTimeMillis(), likeNum, video, price));
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
	public InfoResponse updateCourse(String id, String title, String image, String writerName, long likeNum, String video, int price) throws NotExistException {
		Course course = courseDataService.getCourseById(id);
		course.setTitle(title);
		course.setImage(image);
		course.setWriterName(writerName);
		course.setTimeStamp(System.currentTimeMillis());
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
			boolean hasBought = hasUserBoughtCourse(openid, course);
			boolean hasLiked = likeDataService.isLikeExistent(openid, "course", course.getId());
			CourseItem courseItem = new CourseItem(course, hasBought, hasLiked);
			courseItems.add(courseItem);
		}
		return new CourseListResponse(courseItems);
	}

	@Override
	public CourseListResponse getMyCourseListBefore(String openid, String id) throws NotExistException {
		List<Course> courses = null;
		if (id.equals("")) {
			courses = courseDataService.getTop10ByOrderByTimeStampDesc();
		} else {
			Course course = courseDataService.getCourseById(id);
			courses = courseDataService.getTop10ByTimeStampBeforeOrderByTimeStampDesc(course.getTimeStamp());
		}

		if (!courses.isEmpty()) {
			List<Course> sameStampCourses = courseDataService.getCoursesByTimeStamp(courses.get(courses.size()-1).getTimeStamp());
			for(Course ssc:sameStampCourses) {
				boolean flag = false; //标记ssc是否在courses中
				for(Course c:courses){
					if(ssc.getId().equals(c.getId())){
						flag = true;
						break;
					}
				}
				if(!flag){ //ssc不在courses里面，加入进去
					courses.add(ssc);
				}
			}
		}

		List<CourseItem> courseItems = new ArrayList<>();
		for(Course course:courses){
			boolean hasBought = hasUserBoughtCourse(openid, course);
			boolean hasLiked = likeDataService.isLikeExistent(openid, "course", course.getId());
			courseItems.add(new CourseItem(course, hasBought, hasLiked));
		}
		return new CourseListResponse(courseItems);
	}

	private boolean hasUserBoughtCourse(String openid, Course course) throws NotExistException {
		boolean hasBought = purchaseCourseDataService.isPurchaseCourseExistent(
				new PurchaseCourseKey(openid, course.getId())); //用户是否已购买
		if (enterpriseDataService.isUserEnterprise(openid)) { //用户获取自己发布的课程
			Enterprise enterprise = enterpriseDataService.getEnterpriseByOpenid(openid);
			if (course.getWriterName().equals(
					adminDataService.getAdminById(enterprise.getAdminId()).getUsername())) {
				hasBought = true;
			}
		}
		return hasBought;
	}
}
