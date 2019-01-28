package njurestaurant.njutakeout.response.count;


import njurestaurant.njutakeout.entity.count.Count;

public class CountItem {
    private long viewCourse; //查看课程次数
    private long viewDocument; //查看文档次数
    private long viewProject; //查看项目次数
    private long viewHomePage; //查看首页次数
    private long viewNews; //查看资讯次数
    private long viewBusiness; //查看业务次数
    private long viewFeed; //查看圈子次数

    public CountItem(Count count) {
        this.viewCourse = count.getViewCourse();
        this.viewDocument = count.getViewDocument();
        this.viewProject = count.getViewProject();
        this.viewHomePage = count.getViewHomePage();
        this.viewNews = count.getViewNews();
        this.viewBusiness = count.getViewBusiness();
        this.viewFeed = count.getViewFeed();
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
