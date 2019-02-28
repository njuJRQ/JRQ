package njurestaurant.njutakeout.response.article.project;

import njurestaurant.njutakeout.entity.article.Project;

public class ProjectItem {
    private String id; //编号
    private String title; //简介
    private String writerName; //作者名字
    private String identity; //身份
    private String phone; //联系方式
    private String city; //所在城市
    private String industry; //所属行业
    private String business; //业务标签
    private String content; //项目详情
    private int money; //项目资金
    private String attachment; //附件路径
    private String date; //发布日期
    private long likeNum; //点赞数
    private boolean hasLiked; //用户是否已经点赞
    private long vieNum;//浏览量

    //注意：管理员调用这个构造方法
    public ProjectItem(Project project) {
        this.id = project.getId();
        this.title = project.getTitle();
        this.writerName = project.getWriterName();
        this.identity = project.getIdentity();
        this.phone = project.getPhone();
        this.city = project.getCity();
        this.industry = project.getIndustry();
        this.business = project.getBusiness();
        this.content = project.getContent();
        this.money = project.getMoney();
        this.attachment = project.getAttachment();
        this.date = project.getDate();
        this.likeNum = project.getLikeNum();
        this.vieNum = project.getVieNum();
    }

    //注意：用户调用这个构造方法
    public ProjectItem(Project project, boolean hasLiked) {
        this.id = project.getId();
        this.title = project.getTitle();
        this.writerName = project.getWriterName();
        this.identity = project.getIdentity();
        this.phone = project.getPhone();
        this.city = project.getCity();
        this.industry = project.getIndustry();
        this.business = project.getBusiness();
        this.content = project.getContent();
        this.money = project.getMoney();
        this.attachment = project.getAttachment();
        this.date = project.getDate();
        this.likeNum = project.getLikeNum();
        this.hasLiked = hasLiked;
        this.vieNum = project.getVieNum();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriterName() {
        return writerName;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(long likeNum) {
        this.likeNum = likeNum;
    }

    public boolean isHasLiked() {
        return hasLiked;
    }

    public void setHasLiked(boolean hasLiked) {
        this.hasLiked = hasLiked;
    }

    public long getVieNum() {
        return vieNum;
    }

    public void setVieNum(long vieNum) {
        this.vieNum = vieNum;
    }
}
