package dyploma.auction.system.carriage.goods.mvc.shipper.model;

public class RegisterModel {

	private String companyName;
	private String country;
	private String postcode;
	private String city;
	private String street;
	private String flatNumber;
	private String nipNumber;
	private String phoneNumber;
	private String email;

	private String name;
	private String surname;
	private String login;
	private String password;
	private String password2;
	private String phoneNumberUser;
	private String emailUser;

	public RegisterModel(String companyName, String country, String postcode,
			String city, String street, String flatNumber, String nipNumber,
			String phoneNumber, String email, String name, String surname,
			String login, String password, String password2,
			String phoneNumberUser, String emailUser) {
		super();
		this.companyName = companyName;
		this.country = country;
		this.postcode = postcode;
		this.city = city;
		this.street = street;
		this.flatNumber = flatNumber;
		this.nipNumber = nipNumber;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.password = password;
		this.password2 = password2;
		this.phoneNumberUser = phoneNumberUser;
		this.emailUser = emailUser;
	}

	public RegisterModel() {
		this(null, null, null, null, null, null, null, null, null, null, null,
				null, null, null, null, null);
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getFlatNumber() {
		return flatNumber;
	}

	public void setFlatNumber(String flatNumber) {
		this.flatNumber = flatNumber;
	}

	public String getNipNumber() {
		return nipNumber;
	}

	public void setNipNumber(String nipNumber) {
		this.nipNumber = nipNumber;
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

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getPhoneNumberUser() {
		return phoneNumberUser;
	}

	public void setPhoneNumberUser(String phoneNumberUser) {
		this.phoneNumberUser = phoneNumberUser;
	}

	public String getEmailUser() {
		return emailUser;
	}

	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}

}
