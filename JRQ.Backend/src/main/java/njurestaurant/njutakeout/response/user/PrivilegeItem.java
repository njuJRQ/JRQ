package njurestaurant.njutakeout.response.user;

import njurestaurant.njutakeout.entity.user.Privilege;

public class PrivilegeItem {
	private String name; //特权名，取值："enterprise"
	private int price; //价格

	public PrivilegeItem() {
	}

	public PrivilegeItem(Privilege privilege) {
		this.name = privilege.getName();
		this.price = privilege.getPrice();
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
