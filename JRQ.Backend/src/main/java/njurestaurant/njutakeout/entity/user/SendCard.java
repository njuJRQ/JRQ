package njurestaurant.njutakeout.entity.user;

import javax.persistence.*;

@Entity
@Table
@IdClass(SendCardKey.class)
public class SendCard {
	@Id
	@Column(length = 28)
	private String senderOpenid; //发送者openid

	@Id
	@Column(length = 28)
	private String receiverOpenid; //接收者openid

	@Column
	private boolean checked; //发送者是否已读

	public SendCard() {
	}

	public SendCard(String senderOpenid, String receiverOpenid, boolean checked) {
		this.senderOpenid = senderOpenid;
		this.receiverOpenid = receiverOpenid;
		this.checked = checked;
	}

	public String getSenderOpenid() {
		return senderOpenid;
	}

	public void setSenderOpenid(String senderOpenid) {
		this.senderOpenid = senderOpenid;
	}

	public String getReceiverOpenid() {
		return receiverOpenid;
	}

	public void setReceiverOpenid(String receiverOpenid) {
		this.receiverOpenid = receiverOpenid;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}
