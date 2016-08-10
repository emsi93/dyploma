package dyploma.auction.system.carriage.goods.mvc.shipper.model;

import java.io.Serializable;

public class ShipperFormModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8784538228054813813L;
	
	private String companyName;
	private String country;
	private String postcode;
	private String city;
	private String street;
	private String nipNumber;
	private String regonNumber;
	private String phoneNumber;
	private String email;

	
	
	public ShipperFormModel(String companyName, String country,
			String postcode, String city, String street, String nipNumber,
			String regonNumber, String phoneNumber, String email) {
		super();
		this.companyName = companyName;
		this.country = country;
		this.postcode = postcode;
		this.city = city;
		this.street = street;
		this.nipNumber = nipNumber;
		this.regonNumber = regonNumber;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public ShipperFormModel()
	{
		this(null, null, null, null, null, null, null, null, null);
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

	public String getNipNumber() {
		return nipNumber;
	}

	public void setNipNumber(String nipNumber) {
		this.nipNumber = nipNumber;
	}

	public String getRegonNumber() {
		return regonNumber;
	}

	public void setRegonNumber(String regonNumber) {
		this.regonNumber = regonNumber;
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
	
	
}
