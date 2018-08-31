package njurestaurant.njutakeout.response.user;

import njurestaurant.njutakeout.response.Response;

public class PersonResponse extends Response {
	private PersonItem person;

	public PersonResponse() {
	}

	public PersonResponse(PersonItem person) {
		this.person = person;
	}

	public PersonItem getPerson() {
		return person;
	}

	public void setPerson(PersonItem person) {
		this.person = person;
	}
}
