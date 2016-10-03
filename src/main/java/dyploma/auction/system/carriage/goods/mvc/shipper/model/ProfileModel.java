package dyploma.auction.system.carriage.goods.mvc.shipper.model;

public class ProfileModel {

	private String name;
	private String surname;
	private String phoneNumber;
	private String email;
	private String login;
	private String password;
	private String password2;
	public ProfileModel(String name, String surname, String phoneNumber,
			String email, String login, String password, String s) {
		super();
		this.name = name;
		this.surname = surname;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.login = login;
		this.password = password;
		this.password2 = null;
	}
	public ProfileModel()
	{
		this(null,null,null,null,null,null,null);
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
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
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
