package njurestaurant.njutakeout.response.user;

import njurestaurant.njutakeout.response.Response;

public class QrCodeResponse extends Response {
	private boolean ok; //是否成功
	private String message; //相关信息
	private byte[] image; //二维码图片

	public QrCodeResponse() {
	}

	public QrCodeResponse(boolean ok, String message, byte[] image) {
		this.ok = ok;
		this.message = message;
		this.image = image;
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public String isMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
}
