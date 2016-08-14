package dyploma.auction.system.carriage.goods.mvc.shipper.model;

public class ShipperCompanyModel {

	

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
	private final int typeOfCompany;

	public ShipperCompanyModel(ShipperCompanyFormModel shipperFormModel, int typeOfCompany) {
		this.companyName = shipperFormModel.getCompanyName();
		this.country = shipperFormModel.getCountry();
		this.postcode = shipperFormModel.getPostcode();
		this.city = shipperFormModel.getCity();
		this.street = shipperFormModel.getStreet();
		this.houseNumber = shipperFormModel.getHouseNumber();
		this.suiceNumber = shipperFormModel.getSuiceNumber();
		this.nipNumber = shipperFormModel.getNipNumber();
		this.phoneNumber = shipperFormModel.getPhoneNumber();
		this.website = shipperFormModel.getWebsite();
		this.email = shipperFormModel.getEmail();
		this.description = shipperFormModel.getDescription();
		this.typeOfCompany = typeOfCompany;
		
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

	public int getTypeOfCompany() {
		return typeOfCompany;
	}

	@Override
	public String toString() {
		return companyName + "," + country + "," + postcode + "," + city + ","
				+ street + "," + houseNumber + "," + suiceNumber + ","
				+ nipNumber + "," + phoneNumber + "," + website + "," + email
				+ "," + description + "," + typeOfCompany;
	}

}
