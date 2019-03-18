package njurestaurant.njutakeout.bl.article.course;

import njurestaurant.njutakeout.blservice.article.course.CourseBlService;
import njurestaurant.njutakeout.dataservice.admin.AdminDataService;
import njurestaurant.njutakeout.dataservice.article.CourseDataService;
import njurestaurant.njutakeout.dataservice.article.LeaveWordDataService;
import njurestaurant.njutakeout.dataservice.article.LikeDataService;
import njurestaurant.njutakeout.dataservice.purchase.PurchaseCourseDataService;
import njurestaurant.njutakeout.dataservice.user.EnterpriseDataService;
import njurestaurant.njutakeout.dataservice.user.UserDataService;
import njurestaurant.njutakeout.entity.article.Course;
import njurestaurant.njutakeout.entity.article.Feed;
import njurestaurant.njutakeout.entity.article.LeaveWord;
import njurestaurant.njutakeout.entity.purchase.PurchaseCourseKey;
import njurestaurant.njutakeout.entity.user.Enterprise;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.article.course.CourseItem;
import njurestaurant.njutakeout.response.article.course.CourseListResponse;
import njurestaurant.njutakeout.response.article.course.CourseResponse;
import njurestaurant.njutakeout.response.article.feed.FeedItem;
import njurestaurant.njutakeout.response.article.leaveWord.LeaveWordItem;
import njurestaurant.njutakeout.response.article.leaveWord.LeaveWordViewItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseBlServiceImpl implements CourseBlService {
	private final CourseDataService courseDataService;
	private final PurchaseCourseDataService purchaseCourseDataService;
	private final EnterpriseDataService enterpriseDataService;
	private final AdminDataService adminDataService;
	private final LikeDataService likeDataService;
	private final LeaveWordDataService leaveWordDataService;
	private final UserDataService userDataService;

	@Autowired
	public CourseBlServiceImpl(CourseDataService courseDataService, PurchaseCourseDataService purchaseCourseDataService, EnterpriseDataService enterpriseDataService, AdminDataService adminDataService, LikeDataService likeDataService, LeaveWordDataService leaveWordDataService, UserDataService userDataService) {
		this.courseDataService = courseDataService;
		this.purchaseCourseDataService = purchaseCourseDataService;
		this.enterpriseDataService = enterpriseDataService;
		this.adminDataService = adminDataService;
		this.likeDataService = likeDataService;
		this.leaveWordDataService = leaveWordDataService;
		this.userDataService = userDataService;
	}

	@Override
	public InfoResponse addCourse(String title, String image, String writerName, long likeNum, String video, int price,boolean isTextualResearchCourse) {
		String preview = generatePreviewVideo(video);
		courseDataService.addCourse(new Course(title, image, writerName, System.currentTimeMillis(), likeNum, video, price, preview,0,isTextualResearchCourse,null));
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
	public CourseListResponse getTextualResearchCourseList() {
		List<Course> courses=courseDataService.getTextualResearchCourse();
		List<CourseItem> courseItems=new ArrayList<>();
		if(courses!=null && courses.size()>0)
		for(Course course:courses){
			courseItems.add(new CourseItem(course));
		}
		return new CourseListResponse(courseItems);
	}

	@Override
	public CourseListResponse getOrdinaryCourseList() {
		List<Course> courses=courseDataService.getOrdinaryCourse();
		List<CourseItem> courseItems=new ArrayList<>();
		if(courses!=null && courses.size()>0)
			for(Course course:courses){
				courseItems.add(new CourseItem(course));
			}
		return new CourseListResponse(courseItems);
	}

	@Override
	public InfoResponse updateCourse(String id, String title, String image, String writerName, long likeNum, String video, int price) throws NotExistException {
		Course course = courseDataService.getCourseById(id);
		course.setTitle(title);
		//若课程图片发生了变化，则删除旧图片
		if (!course.getImage().equals(image)) {
			if (! new File(course.getImage()).delete()) {
				System.err.println("课程图片"+course.getImage()+"删除失败");
			}
			course.setImage(image);
		}
		course.setWriterName(writerName);
		course.setTimeStamp(System.currentTimeMillis());
		course.setLikeNum(likeNum);
		//若课程视频发生了变化，则删除旧视频，并重新生成预览视频
		if (!course.getVideo().equals(video)) {
			if (! new File(course.getVideo()).delete()) {
				System.err.println("课程视频"+course.getVideo()+"删除失败");
			}
			if (! new File(course.getPreview()).delete()) {
				System.err.println("课程预览视频"+course.getPreview()+"删除失败");
			}
			course.setVideo(video);
			course.setPreview(generatePreviewVideo(video));
		}
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
				new PurchaseCourseKey(openid, course.getId(),false)); //用户是否已购买
		if (enterpriseDataService.isUserEnterprise(openid)) { //用户获取自己发布的课程
			Enterprise enterprise = enterpriseDataService.getEnterpriseByOpenid(openid);
			if (course.getWriterName().equals(
					adminDataService.getAdminById(enterprise.getAdminId()).getUsername())) {
				hasBought = true;
			}
		}
		boolean hasLiked = likeDataService.isLikeExistent(openid, "course", course.getId());
		List<LeaveWord> leaveWords = leaveWordDataService.getLeaveWordListBefore(openid,"",courseId);
		List<LeaveWordViewItem> leaveWordViewItems = new ArrayList<>();
		for(LeaveWord leaveWord:leaveWords){
			leaveWordViewItems.add(new LeaveWordViewItem(leaveWord,userDataService));
		}
		return new CourseResponse(new CourseItem(course, hasBought, hasLiked),leaveWordViewItems);
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
		List<Course> courses = courseDataService.getMyCourseListBefore(openid, id);
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
				new PurchaseCourseKey(openid, course.getId(),false)); //用户是否已购买
		if (enterpriseDataService.isUserEnterprise(openid)) { //用户获取自己发布的课程
			Enterprise enterprise = enterpriseDataService.getEnterpriseByOpenid(openid);
			if (course.getWriterName().equals(
					adminDataService.getAdminById(enterprise.getAdminId()).getUsername())) {
				hasBought = true;
			}
		}
		return hasBought;
	}

	private static String generatePreviewVideo(String videoPath) {
		try {
			System.out.println("正在为"+videoPath+"生成预览视频...");
			int dotIndex = videoPath.lastIndexOf('.'); //'.'最后出现的位置
			String suffix = videoPath.substring(dotIndex>0?dotIndex:videoPath.length()); //视频文件后缀名（包括.），若不存在.则为空串
			String previewPath = videoPath + "-preview" + suffix; //预览视频路径
			//使用FFmpeg将原视频切割出前60秒：ffmpeg -ss 00:00:00 -i input.mp4 -c copy -t 60 output.mp4
			String []args = new String[] {"ffmpeg", "-ss", "00:00:00", "-i", videoPath, "-c", "copy", "-t", "60", previewPath};
			Process process = Runtime.getRuntime().exec(args);
			//打印标准输出流
			BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = null;
			while ((line = in.readLine()) != null) {
				System.out.println(line);
			}
			in.close();
			//打印标准错误流
			BufferedReader err = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			while ((line = err.readLine()) != null) {
				System.out.println(line);
			}
			err.close();
			System.out.println(videoPath+"的预览视频已生成，路径为"+previewPath);
			return previewPath;
		} catch (IOException e) {
			System.out.println("为"+videoPath+"生成预览的过程中出现错误");
			e.printStackTrace();
			return "";
		}
	}

	//定时任务：每天10:45自动为没有预览视频的课程生成预览视频（用于为以前在数据库中的课程生成预览视频）
//	@Scheduled(cron = "0 45 10 * * ?")
	private void genPreviewVideos() {
		List<Course> courses = courseDataService.getAllCourses();
		for(Course course:courses) {
			if(course.getVideo()!=null && !course.getVideo().equals("") && course.getPreview()==null) {
				course.setPreview(generatePreviewVideo(course.getVideo()));
				courseDataService.saveCourse(course);
			}
		}
	}
}
