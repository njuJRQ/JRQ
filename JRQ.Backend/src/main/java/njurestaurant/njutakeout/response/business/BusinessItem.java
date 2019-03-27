package njurestaurant.njutakeout.response.business;

import njurestaurant.njutakeout.entity.business.Business;
import njurestaurant.njutakeout.publicdatas.business.ProjectRef;
import njurestaurant.njutakeout.publicdatas.business.MarketType;

public class BusinessItem {
    private String id;
    private String linkMan;
    private String phone;
    private String agencyName;//机构名
    private String projectInfo;//项目信息
    private ProjectRef projectRef;
    private MarketType marketType;
    private String writerOpenid; //作者微信openid

    public BusinessItem() {
    }
    public BusinessItem(Business business){
        this.id=business.getId();
        this.linkMan=business.getLinkMan();
        this.phone=business.getPhone();
        this.agencyName=business.getAgencyName();
        this.projectInfo=business.getProjectInfo();
        this.marketType =business.getMarketType();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getProjectInfo() {
        return projectInfo;
    }

    public void setProjectInfo(String projectInfo) {
        this.projectInfo = projectInfo;
    }

    public ProjectRef getProjectRef() {
        return projectRef;
    }

    public void setProjectRef(ProjectRef projectRef) {
        this.projectRef = projectRef;
    }

    public MarketType getMarketType() {
        return marketType;
    }

    public void setMarketType(MarketType marketType) {
        this.marketType = marketType;
    }

    public String getWriterOpenid() {
        return writerOpenid;
    }

    public void setWriterOpenid(String writerOpenid) {
        this.writerOpenid = writerOpenid;
    }
}
