package njurestaurant.njutakeout.entity.count;

import javax.persistence.*;

@Entity
@Table(name="count")
public class Count {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column
    private long viewCourse; //查看课程次数

    @Column
    private long viewDocument; //查看文档次数

    @Column
    private long viewProject; //查看项目次数

    @Column
    private long viewHomePage; //查看首页次数

    @Column
    private long viewNews; //查看资讯次数

    @Column
    private long viewBusiness; //查看业务次数

    @Column
    private long viewFeed; //查看圈子次数

    public Count() {
    }

    public Count(long viewCourse, long viewDocument, long viewProject, long viewHomePage, long viewNews, long viewBusiness, long viewFeed) {
        this.viewCourse = viewCourse;
        this.viewDocument = viewDocument;
        this.viewProject = viewProject;
        this.viewHomePage = viewHomePage;
        this.viewNews = viewNews;
        this.viewBusiness = viewBusiness;
        this.viewFeed = viewFeed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getViewCourse() {
        return viewCourse;
    }

    public void setViewCourse(long viewCourse) {
        this.viewCourse = viewCourse;
    }

    public long getViewDocument() {
        return viewDocument;
    }

    public void setViewDocument(long viewDocument) {
        this.viewDocument = viewDocument;
    }

    public long getViewProject() {
        return viewProject;
    }

    public void setViewProject(long viewProject) {
        this.viewProject = viewProject;
    }

    public long getViewHomePage() {
        return viewHomePage;
    }

    public void setViewHomePage(long viewHomePage) {
        this.viewHomePage = viewHomePage;
    }

    public long getViewNews() {
        return viewNews;
    }

    public void setViewNews(long viewNews) {
        this.viewNews = viewNews;
    }

    public long getViewBusiness() {
        return viewBusiness;
    }

    public void setViewBusiness(long viewBusiness) {
        this.viewBusiness = viewBusiness;
    }

    public long getViewFeed() {
        return viewFeed;
    }

    public void setViewFeed(long viewFeed) {
        this.viewFeed = viewFeed;
    }
}
