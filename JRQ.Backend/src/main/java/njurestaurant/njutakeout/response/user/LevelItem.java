package njurestaurant.njutakeout.response.user;

import njurestaurant.njutakeout.entity.user.Level;

public class LevelItem {
	private String name; //会员级别名
	private int cardLimit; //每天能查看名片数
	private int price; //价格

	public LevelItem(Level level) {
		this.name = level.getName();
		this.cardLimit = level.getCardLimit();
		this.price = level.getPrice();
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
}
