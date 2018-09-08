package njurestaurant.njutakeout.entity.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Enterprise {
	@Id
	@Column
	private String openid; //用户微信编号

	@Column
	private String adminId; //对应的管理员ID

	public Enterprise() {
	}

	public Enterprise(String openid, String adminId) {
		this.openid = openid;
		this.adminId = adminId;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
}
