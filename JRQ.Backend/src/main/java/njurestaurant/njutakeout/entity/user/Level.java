package njurestaurant.njutakeout.entity.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Level {
	@Id
	@Column
	private String name; //会员级别名

	@Column
	private int cardLimit; //每天能查看名片数

	@Column
	private int price; //会员级别价格

	@Column
	private double courseDiscountedRatio; //该会员级别购买课程时的折扣率，价格是原价的courseDiscountedRatio倍

	@Column
	private int checkCardPrice; //查看名片联系方式次数用完以后再查看联系方式需要花多少钱

	public Level() {
	}

	public Level(String name, int cardLimit, int price, double courseDiscountedRatio, int checkCardPrice) {
		this.name = name;
		this.cardLimit = cardLimit;
		this.price = price;
		this.courseDiscountedRatio = courseDiscountedRatio;
		this.checkCardPrice = checkCardPrice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCardLimit() {
		return cardLimit;
	}

	public void setCardLimit(int cardLimit) {
		this.cardLimit = cardLimit;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public double getCourseDiscountedRatio() {
		return courseDiscountedRatio;
	}

	public void setCourseDiscountedRatio(double courseDiscountedRatio) {
		this.courseDiscountedRatio = courseDiscountedRatio;
	}

	public int getCheckCardPrice() {
		return checkCardPrice;
	}

	public void setCheckCardPrice(int checkCardPrice) {
		this.checkCardPrice = checkCardPrice;
	}
}
