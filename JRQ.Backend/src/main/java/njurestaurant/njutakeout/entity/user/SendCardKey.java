package njurestaurant.njutakeout.entity.user;

import java.io.Serializable;

public class SendCardKey implements Serializable {
	private String senderOpenid; //发送者openid
	private String receiverOpenid; //接收者openid

	public SendCardKey() {
	}

	public SendCardKey(String senderOpenid, String receiverOpenid) {
		this.senderOpenid = senderOpenid;
		this.receiverOpenid = receiverOpenid;
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
}
