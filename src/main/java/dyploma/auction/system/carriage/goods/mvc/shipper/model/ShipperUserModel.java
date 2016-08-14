package dyploma.auction.system.carriage.goods.mvc.shipper.model;

public class ShipperUserModel {

	private String name;
	private String surname;
	private String login;
	private String password;
	private int idPermission;
	private String country;
	private String postcode;
	private String city;
	private String street;
	private String houseNumber;
	private String flatNumber;
	private String phoneNumber;
	private String email;
	private String peselNumber;
	private int idCompany;
	private String status;

	public ShipperUserModel(ShipperUserFormModel userFormModel, int idPermission,
			int idCompany, String status) {
		this.name = userFormModel.getName();
		this.surname = userFormModel.getSurname();
		this.login = userFormModel.getLogin();
		this.password = userFormModel.getPassword();
		this.country = userFormModel.getCountry();
		this.postcode = userFormModel.getPostcode();
		this.city = userFormModel.getCity();
		this.street = userFormModel.getStreet();
		this.houseNumber = userFormModel.getHouseNumber();
		this.flatNumber = userFormModel.getFlatNumber();
		this.phoneNumber = userFormModel.getPhoneNumber();
		this.email = userFormModel.getEmail();
		this.peselNumber = userFormModel.getPeselNumber();
		this.idPermission = idPermission;
		this.idCompany = idCompany;
		this.status = status;
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

	public int getIdPermission() {
		return idPermission;
	}

	public void setIdPermission(int idPermission) {
		this.idPermission = idPermission;
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

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getFlatNumber() {
		return flatNumber;
	}

	public void setFlatNumber(String flatNumber) {
		this.flatNumber = flatNumber;
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

	public String getPeselNumber() {
		return peselNumber;
	}

	public void setPeselNumber(String peselNumber) {
		this.peselNumber = peselNumber;
	}

	public int getIdCompany() {
		return idCompany;
	}

	public void setIdCompany(int idCompany) {
		this.idCompany = idCompany;
	}

	public String isStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return name + "," + surname + "," + login + "," + password + ","
				+ idPermission + "," + country + "," + postcode + "," + city
				+ "," + street + "," + houseNumber + "," + flatNumber + ","
				+ phoneNumber + "," + email + "," + peselNumber + ","
				+ idCompany + "," + status;
	}

}
