package dyploma.auction.system.carriage.goods.mvc.webapp.model;

public class CompanyModel {

	private int id;
	private String companyName;
	private String country;
	private String postcode;
	private String city;
	private String street;
	private String flatNumber;
	private String nipNumber;
	private String phoneNumber;
	private String website;
	private String email;
	private String description;
	private Double note;

	public CompanyModel(int id, String companyName, String country,
			String postcode, String city, String street, String flatNumber,
			String nipNumber, String phoneNumber, String website, String email,
			String description, Double note) {
		super();
		this.id = id;
		this.companyName = companyName;
		this.country = country;
		this.postcode = postcode;
		this.city = city;
		this.street = street;
		this.flatNumber = flatNumber;
		this.nipNumber = nipNumber;
		this.phoneNumber = phoneNumber;
		this.website = website;
		this.email = email;
		this.description = description;
		this.note = note;
	}

	public CompanyModel() {
		this(0, null, null, null, null, null, null, null, null, null, null,
				null, null);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Double getNote() {
		return note;
	}

	public void setNote(Double note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "CompanyModel [id=" + id + ", companyName=" + companyName
				+ ", country=" + country + ", postcode=" + postcode + ", city="
				+ city + ", street=" + street + ", flatNumber=" + flatNumber
				+ ", nipNumber=" + nipNumber + ", phoneNumber=" + phoneNumber
				+ ", website=" + website + ", email=" + email
				+ ", description=" + description + "]";
	}

}
