package njurestaurant.njutakeout.bl.user;

import njurestaurant.njutakeout.blservice.user.EnterpriseBlService;
import njurestaurant.njutakeout.dataservice.admin.AdminDataService;
import njurestaurant.njutakeout.dataservice.article.CourseDataService;
import njurestaurant.njutakeout.dataservice.article.DocumentDataService;
import njurestaurant.njutakeout.dataservice.article.ProjectDataService;
import njurestaurant.njutakeout.dataservice.user.EnterpriseDataService;
import njurestaurant.njutakeout.dataservice.user.UserDataService;
import njurestaurant.njutakeout.entity.admin.Admin;
import njurestaurant.njutakeout.entity.article.Course;
import njurestaurant.njutakeout.entity.article.Document;
import njurestaurant.njutakeout.entity.article.Project;
import njurestaurant.njutakeout.entity.user.Enterprise;
import njurestaurant.njutakeout.entity.user.User;
import njurestaurant.njutakeout.exception.NotExistException;
import njurestaurant.njutakeout.response.BoolResponse;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.admin.AdminItem;
import njurestaurant.njutakeout.response.admin.AdminResponse;
import njurestaurant.njutakeout.response.article.course.CourseItem;
import njurestaurant.njutakeout.response.article.course.CourseListResponse;
import njurestaurant.njutakeout.response.article.document.DocumentItem;
import njurestaurant.njutakeout.response.article.document.DocumentListResponse;
import njurestaurant.njutakeout.response.article.project.ProjectItem;
import njurestaurant.njutakeout.response.article.project.ProjectListResponse;
import njurestaurant.njutakeout.response.user.EnterpriseItem;
import njurestaurant.njutakeout.response.user.EnterpriseListResponse;
import njurestaurant.njutakeout.response.user.EnterpriseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EnterpriseBlServiceImpl implements EnterpriseBlService {
	private final EnterpriseDataService enterpriseDataService;
	private final UserDataService userDataService;
	private final AdminDataService adminDataService;
	private final CourseDataService courseDataService;
	private final DocumentDataService documentDataService;
	private final ProjectDataService projectDataService;

	@Autowired
	public EnterpriseBlServiceImpl(EnterpriseDataService enterpriseDataService, UserDataService userDataService, AdminDataService adminDataService, CourseDataService courseDataService, DocumentDataService documentDataService, ProjectDataService projectDataService) {
		this.enterpriseDataService = enterpriseDataService;
		this.userDataService = userDataService;
		this.adminDataService = adminDataService;
		this.courseDataService = courseDataService;
		this.documentDataService = documentDataService;
		this.projectDataService = projectDataService;
	}

	@Override
	public EnterpriseResponse getEnterpriseById(String id) throws NotExistException {
		return new EnterpriseResponse(new EnterpriseItem(enterpriseDataService.getEnterpriseById(id), adminDataService));
	}

	@Override
	public EnterpriseListResponse getAllEnterprises() {
		List<Enterprise> enterprises = enterpriseDataService.getAllEnterprises();
		List<EnterpriseItem> enterpriseItems = new ArrayList<>();
		for(Enterprise enterprise:enterprises){
			enterpriseItems.add(new EnterpriseItem(enterprise, adminDataService));
		}
		return new EnterpriseListResponse(enterpriseItems);
	}

	@Override
	public BoolResponse verifyEnterpriseById(String id) throws NotExistException {
		Enterprise enterprise = enterpriseDataService.getEnterpriseById(id);
		enterprise.setStatus("verified");
		enterprise.setVerifyTimestamp(System.currentTimeMillis());
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");//设置日期格式
		String date=df.format(new Date());// new Date()为获取当前系统时间
		Admin admin = new Admin(enterprise.getAdminUsername(), enterprise.getAdminPassword(), "3", date);
		adminDataService.addAdmin(admin); //此处新增Admin之后Admin的ID会自动生成
		enterprise.setAdminId(admin.getId());
		enterpriseDataService.saveEnterprise(enterprise);
		return new BoolResponse(true, "企业用户 "+enterprise.getName()+" 已通过审核");
	}

	@Override
	public InfoResponse deleteEnterpriseById(String id) throws NotExistException {
		Enterprise enterprise = enterpriseDataService.getEnterpriseById(id);
		switch (enterprise.getStatus()) {
			case "submitted":
				enterprise.setStatus("rejected");
				enterpriseDataService.saveEnterprise(enterprise);
				return new InfoResponse();
			case "verified":
				enterprise.setStatus("disqualified");
				enterpriseDataService.saveEnterprise(enterprise);
				adminDataService.deleteAdminById(enterprise.getAdminId());
				return new InfoResponse();
			default:
				return new InfoResponse("企业用户" + id + "状态已为" + enterprise.getStatus());
		}
	}

	@Override
	public BoolResponse setMyUserAsEnterprise(String enterpriseName, String description, String licenseUrl, String openid, String username, String password) {
		if (enterpriseDataService.isUserEnterprise(openid)) {
			return new BoolResponse(false, "已经是企业用户，无需重复认证");
		}
		User user = null;
		try {
			user = userDataService.getUserByOpenid(openid);
		} catch (NotExistException e) {
			return new BoolResponse(false, e.getMessage());
		}
		//企业用户认证时不需要扣款，故也不需要加入到purchase表里面
		if (adminDataService.isAdminExistent(username)) {
			return new BoolResponse(false, "用户名"+username+"已存在");
		} else {
			//刚提交申请时，adminId为""，Admin表中没有相关信息
			enterpriseDataService.addEnterprise(new Enterprise(enterpriseName, description, licenseUrl, openid, "", username, password, "submitted", System.currentTimeMillis()));
			return new BoolResponse(true, "企业用户申请已提交，用户名为'"+username+"'，密码为'"+password+"'");
		}
	}

	@Override
	public BoolResponse isMyUserEnterprise(String openid) {
		return new BoolResponse(enterpriseDataService.isUserEnterprise(openid), "ok");
	}

	@Override
	public EnterpriseResponse getMySubmittedEnterprise(String openid) throws NotExistException {
		Enterprise enterprise = null;
		try {
			 enterprise = enterpriseDataService.getEnterpriseByOpenid(openid);
		} catch (NotExistException e) {
			return new EnterpriseResponse(new EnterpriseItem());
		}

		EnterpriseItem enterpriseItem = new EnterpriseItem(enterprise, adminDataService);
		if(enterprise.getStatus().equals("rejected") || enterprise.getStatus().equals("disqualified")) {
			enterpriseDataService.deleteEnterpriseById(enterprise.getId());
		}
		return new EnterpriseResponse(enterpriseItem);
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
				courseItems.add(new CourseItem(course));
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
