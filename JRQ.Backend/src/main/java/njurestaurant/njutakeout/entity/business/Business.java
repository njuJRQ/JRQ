package njurestaurant.njutakeout.entity.business;

import njurestaurant.njutakeout.publicdatas.business.ProjectRef;
import njurestaurant.njutakeout.publicdatas.business.MarketType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Business {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String id; //文章编号

//	@Column
//	private String title; //文章标题
//
//	@Column(length = 600)
//	private String content; //文章内容

    @Column
    private String linkMan;//联系人

    @Column
    private String phone;//联系电话

    @Column
    private String agencyName;//机构名

    @Column
    private String projectInfo;//项目信息

    @Column
    private ProjectRef projectRef;

    @Column
    private MarketType marketType;


    @Column
    private String writerOpenid; //作者微信openid


    public Business() {
    }

    public Business(String linkMan, String phone, String agencyName, String projectInfo, ProjectRef projectRef, MarketType marketType, String writerOpenid) {
        this.linkMan = linkMan;
        this.phone = phone;
        this.agencyName = agencyName;
        this.projectInfo = projectInfo;
        this.projectRef = projectRef;
        this.marketType =marketType;
        this.writerOpenid = writerOpenid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//	public String getTitle() {
//		return title;
//	}
//
//	public void setTitle(String title) {
//		this.title = title;
//	}
//
//	public String getContent() {
//		return content;
//	}
//
//	public void setContent(String content) {
//		this.content = content;
//	}


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

    public String getWriterOpenid() {
        return writerOpenid;
    }

    public void setWriterOpenid(String writerOpenid) {
        this.writerOpenid = writerOpenid;
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
}
