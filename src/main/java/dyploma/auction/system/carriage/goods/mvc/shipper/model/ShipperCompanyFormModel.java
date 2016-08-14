package dyploma.auction.system.carriage.goods.mvc.shipper.model;

import java.io.Serializable;

public class ShipperCompanyFormModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8784538228054813813L;
	
	private String companyName;
	private String country;
	private String postcode;
	private String city;
	private String street;
	private String houseNumber;
	private String suiceNumber;
	private String nipNumber;
	private String phoneNumber;
	private String website;
	private String email;
	private String description;
	public ShipperCompanyFormModel(String companyName, String country,
			String postcode, String city, String street, String houseNumber,
			String suiceNumber, String nipNumber, String phoneNumber,
			String website, String email, String description) {
		super();
		this.companyName = companyName;
		this.country = country;
		this.postcode = postcode;
		this.city = city;
		this.street = street;
		this.houseNumber = houseNumber;
		this.suiceNumber = suiceNumber;
		this.nipNumber = nipNumber;
		this.phoneNumber = phoneNumber;
		this.website = website;
		this.email = email;
		this.description = description;
	}
	
	public ShipperCompanyFormModel()
	{
		this(null, null, null,
				null, null, null, null, null, null, null, null, null);
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

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getSuiceNumber() {
		return suiceNumber;
	}

	public void setSuiceNumber(String suiceNumber) {
		this.suiceNumber = suiceNumber;
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

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ShipperFormModel [companyName=" + companyName + ", country="
				+ country + ", postcode=" + postcode + ", city=" + city
				+ ", street=" + street + ", houseNumber=" + houseNumber
				+ ", suiceNumber=" + suiceNumber + ", nipNumber=" + nipNumber
				+ ", phoneNumber=" + phoneNumber + ", website=" + website
				+ ", email=" + email + ", description=" + description + "]";
	}

	
	
}
