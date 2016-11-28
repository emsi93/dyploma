package dyploma.auction.system.carriage.goods.mvc.webapp.model;

public class EmployeeModel {

	private int id;
	private String name;
	private String surname;
	private String phoneNumber;
	private String email;
	public EmployeeModel(int id, String name, String surname, String phoneNumber,
			String email) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "EmployeeModel [id=" + id + ", name=" + name + ", surname="
				+ surname + ", phoneNumber=" + phoneNumber + ", email=" + email
				+ "]";
	}
	
	
}
