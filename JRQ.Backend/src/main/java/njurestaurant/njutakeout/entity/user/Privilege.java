package njurestaurant.njutakeout.entity.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Privilege {
	@Id
	@Column
	private String name; //特权名，取值："enterprise"

	@Column
	private int price; //价格

	public Privilege() {
	}

	public Privilege(String name, int price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
