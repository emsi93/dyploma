package dyploma.auction.system.carriage.goods.mvc.shipper.model;

import java.io.Serializable;

public class ShipperUserFormModel implements Serializable{

	private static final long serialVersionUID = -8784538228054813813L;
	
	
	private String name;
	private String surname;
	private String login;
	private String password;
	private String country;
	private String postcode;
	private String city;
	private String street;
	private String houseNumber;
	private String flatNumber;
	private String phoneNumber;
	private String email;
	private String peselNumber;
	public ShipperUserFormModel(String name, String surname, String login,
			String password, String country, String postcode, String city,
			String street, String houseNumber, String flatNumber,
			String phoneNumber, String email, String peselNumber) {
		super();
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.password = password;
		this.country = country;
		this.postcode = postcode;
		this.city = city;
		this.street = street;
		this.houseNumber = houseNumber;
		this.flatNumber = flatNumber;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.peselNumber = peselNumber;
	}
	
	public ShipperUserFormModel()
	{
		this(null, null, null,
				null, null, null, null, null, null, null, null, null, null);
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
	
	
	
	
	
}
