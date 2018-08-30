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
	private int price; //价格

	public Level() {
	}

	public Level(String name, int cardLimit, int price) {
		this.name = name;
		this.cardLimit = cardLimit;
		this.price = price;
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
