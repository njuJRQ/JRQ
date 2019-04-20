package njurestaurant.njutakeout.entity.article;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@Table
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Document {
	@Id
	@GeneratedValue(generator = "jpa-uuid")
	private String id;//编号

	@Column
	private String title;//标题

	@Column
	private String detail;//标题

	@Column
	private String content; //内容

	@Column
	private String image;//图片路径

	@Column
	private String attachment; //附件路径

	@Column
	private String writerName;//作者名字

	@Column
	private int price;//价格

	@Column
	private long timeStamp; //最近一次更新的时间戳

	@Column
	private long likeNum;//点赞数

	@Column
	private String preview; //附件预览图（若附件为PDF则为图片路径，否则为空）

	@Column
	private boolean isContract;//是否是合同

	@Column
	@ElementCollection(targetClass = String.class)
	private List<String> attachments;

	@Column
	@ElementCollection(targetClass = String.class)
	private List<String> previews;

	public Document(){
	}

	public Document(String title,String detail, String content, String image, String attachment, String writerName, int price, long timeStamp, long likeNum, String preview,boolean isContract,List<String> attachments,List<String> previews) {
		this.title = title;
		this.detail=detail;
		this.content = content;
		this.image = image;
		this.attachment = attachment;
		this.writerName = writerName;
		this.price = price;
		this.timeStamp = timeStamp;
		this.likeNum = likeNum;
		this.preview = preview;
		this.isContract=isContract;
		this.attachments=attachments;
		this.previews=previews;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getWriterName() {
		return writerName;
	}

	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDate() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.format(new Date(timeStamp));
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public long getLikeNum() {
		return likeNum;
	}

	public void setLikeNum(long likeNum) {
		this.likeNum = likeNum;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getPreview() {
		return preview;
	}

	public void setPreview(String preview) {
		this.preview = preview;
	}

	public boolean isContract() {
		return isContract;
	}

	public void setContract(boolean contract) {
		isContract = contract;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public List<String> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<String> attachments) {
		this.attachments = attachments;
	}

	public List<String> getPreviews() {
		return previews;
	}

	public void setPreviews(List<String> previews) {
		this.previews = previews;
	}
}
