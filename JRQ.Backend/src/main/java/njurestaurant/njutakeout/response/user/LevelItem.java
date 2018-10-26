package njurestaurant.njutakeout.response.user;

import njurestaurant.njutakeout.entity.user.Level;

public class LevelItem {
	private String name; //会员级别名
	private int cardLimit; //每天能查看名片数
	private int price; //价格
	private double courseDiscountedRatio; //该会员级别购买课程时的折扣率，价格是原价的courseDiscountedRatio倍
	private int checkCardPrice; //查看名片联系方式次数用完以后再查看联系方式需要花多少钱

	public LevelItem(Level level) {
		this.name = level.getName();
		this.cardLimit = level.getCardLimit();
		this.price = level.getPrice();
		this.courseDiscountedRatio = level.getCourseDiscountedRatio();
		this.checkCardPrice = level.getCheckCardPrice();
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
