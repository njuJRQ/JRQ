package njurestaurant.njutakeout.bl.user;

import njurestaurant.njutakeout.blservice.user.EnterpriseBlService;
import njurestaurant.njutakeout.dataservice.admin.AdminDataService;
import njurestaurant.njutakeout.dataservice.article.CourseDataService;
import njurestaurant.njutakeout.dataservice.article.DocumentDataService;
import njurestaurant.njutakeout.dataservice.article.ProjectDataService;
import njurestaurant.njutakeout.dataservice.purchase.PurchaseDataService;
import njurestaurant.njutakeout.dataservice.user.EnterpriseDataService;
import njurestaurant.njutakeout.dataservice.user.PrivilegeDataService;
import njurestaurant.njutakeout.dataservice.user.UserDataService;
import njurestaurant.njutakeout.entity.admin.Admin;
import njurestaurant.njutakeout.entity.article.Course;
import njurestaurant.njutakeout.entity.article.Document;
import njurestaurant.njutakeout.entity.article.Project;
import njurestaurant.njutakeout.entity.purchase.Purchase;
import njurestaurant.njutakeout.entity.user.Enterprise;
import njurestaurant.njutakeout.entity.user.User;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.BoolResponse;
import njurestaurant.njutakeout.response.admin.AdminItem;
import njurestaurant.njutakeout.response.admin.AdminResponse;
import njurestaurant.njutakeout.response.article.course.CourseItem;
import njurestaurant.njutakeout.response.article.course.CourseListResponse;
import njurestaurant.njutakeout.response.article.document.DocumentItem;
import njurestaurant.njutakeout.response.article.document.DocumentListResponse;
import njurestaurant.njutakeout.response.article.project.ProjectItem;
import njurestaurant.njutakeout.response.article.project.ProjectListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class EnterpriseBlServiceImpl implements EnterpriseBlService {
	private final EnterpriseDataService enterpriseDataService;
	private final UserDataService userDataService;
	private final PurchaseDataService purchaseDataService;
	private final AdminDataService adminDataService;
	private final PrivilegeDataService privilegeDataService;
	private final CourseDataService courseDataService;
	private final DocumentDataService documentDataService;
	private final ProjectDataService projectDataService;

	@Autowired
	public EnterpriseBlServiceImpl(EnterpriseDataService enterpriseDataService, UserDataService userDataService, PurchaseDataService purchaseDataService, AdminDataService adminDataService, PrivilegeDataService privilegeDataService, CourseDataService courseDataService, DocumentDataService documentDataService, ProjectDataService projectDataService) {
		this.enterpriseDataService = enterpriseDataService;
		this.userDataService = userDataService;
		this.purchaseDataService = purchaseDataService;
		this.adminDataService = adminDataService;
		this.privilegeDataService = privilegeDataService;
		this.courseDataService = courseDataService;
		this.documentDataService = documentDataService;
		this.projectDataService = projectDataService;
	}



	@Override
	public BoolResponse setMyUserAsEnterprise(String openid, String username, String password) {
		if (enterpriseDataService.isUserEnterprise(openid)) {
			return new BoolResponse(false, "已经是企业用户，无需重复购买");
		}
		User user = null;
		try {
			user = userDataService.getUserByOpenid(openid);
		} catch (NotExistException e) {
			return new BoolResponse(false, e.getMessage());
		}
		int price = 0; //企业认证价格
		try {
			price = privilegeDataService.getPrivilegeByName("enterprise").getPrice();
		} catch (NotExistException e) {
			return new BoolResponse(false, e.getMessage());
		}
		if (user.getCredit()<price) {
			return new BoolResponse(false, "账户余额不足");
		} else if (adminDataService.isAdminExistent(username)) {
			return new BoolResponse(false, "用户名"+username+"已存在");
		} else {
			SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");//设置日期格式
			String date=df.format(new Date());// new Date()为获取当前系统时间
			Admin admin = new Admin(username, password, "3", date);
			adminDataService.addAdmin(admin); //此处新增Admin之后Admin的ID会自动生成
			enterpriseDataService.addEnterprise(new Enterprise(openid, admin.getId()));
			user.setCredit(user.getCredit()-price);
			purchaseDataService.addPurchase(new Purchase(openid, "enterprise", admin.getId(), price, date));
			return new BoolResponse(true, "企业用户升级成功，用户名为'"+username+"'，密码为'"+password+"'");
		}
	}

	@Override
	public BoolResponse isMyUserEnterprise(String openid) {
		return new BoolResponse(enterpriseDataService.isUserEnterprise(openid), "ok");
	}

	@Override
	public AdminResponse getMyEnterpriseAdmin(String openid) throws NotExistException {
		if(enterpriseDataService.isUserEnterprise(openid)) {
			return new AdminResponse(new AdminItem(
					adminDataService.getAdminById(enterpriseDataService.getEnterpriseByOpenid(openid).getAdminId())));
		} else {
			throw new NotExistException("Enterprise openid", openid);
		}
	}

	@Override
	public BoolResponse isAdminEnterprise(String adminId) {
		return new BoolResponse(enterpriseDataService.isAdminEnterprise(adminId), "");
	}

	@Override
	public CourseListResponse getMyPublishedCourseList(String adminId) throws NotExistException {
		Admin admin = adminDataService.getAdminById(adminId);
		List<Course> courses = courseDataService.getAllCourses();
		List<CourseItem> courseItems = new ArrayList<>();
		for(Course course:courses) {
			if(admin.getUsername().equals(course.getWriterName())) {
				courseItems.add(new CourseItem(course, true));
			}
		}
		return new CourseListResponse(courseItems);
	}

	@Override
	public DocumentListResponse getMyPublishedDocumentList(String adminId) throws NotExistException {
		Admin admin = adminDataService.getAdminById(adminId);
		List<Document> documents = documentDataService.getAllDocuments();
		List<DocumentItem> documentItems = new ArrayList<>();
		for(Document document:documents) {
			if(admin.getUsername().equals(document.getWriterName())) {
				documentItems.add(new DocumentItem(document));
			}
		}
		return new DocumentListResponse(documentItems);
	}

	@Override
	public ProjectListResponse getMyPublishedProjectList(String adminId) throws NotExistException {
		Admin admin = adminDataService.getAdminById(adminId);
		List<Project> projects = projectDataService.getAllProjects();
		List<ProjectItem> projectItems = new ArrayList<>();
		for(Project project:projects) {
			if(admin.getUsername().equals(project.getWriterName())) {
				projectItems.add(new ProjectItem(project));
			}
		}
		return new ProjectListResponse(projectItems);
	}
}
