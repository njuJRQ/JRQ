package njurestaurant.njutakeout.response.account;

import njurestaurant.njutakeout.response.Response;

import java.util.List;

public class PersonListResponse extends Response {
	private List<PersonItem> persons;

	public PersonListResponse() {
	}

	public PersonListResponse(List<PersonItem> persons) {
		this.persons = persons;
	}

	public List<PersonItem> getPersons() {
		return persons;
	}

	public void setPersons(List<PersonItem> persons) {
		this.persons = persons;
	}
}
