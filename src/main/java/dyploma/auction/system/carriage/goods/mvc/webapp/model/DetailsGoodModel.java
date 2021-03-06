package dyploma.auction.system.carriage.goods.mvc.webapp.model;

public class DetailsGoodModel {

	private int id;
	private String title;
	private String content;
	private String trailer;
	private String fromCountry;
	private String fromCity;
	private String fromStreet;
	private String toCountry;
	private String toCity;
	private String toStreet;
	private Double maxPrice;
	private String dateAdding;
	private String dateOfDelivery;
	private String actualPrice;
	private String name;
	private String surname;
	private String company;
	private int idCompany;
	private Double weight;
	private String deadlineAuction;
	private String typeGood;
	private String status;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTrailer() {
		return trailer;
	}

	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}

	public String getFromCountry() {
		return fromCountry;
	}

	public void setFromCountry(String fromCountry) {
		this.fromCountry = fromCountry;
	}

	public String getFromCity() {
		return fromCity;
	}

	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	public String getFromStreet() {
		return fromStreet;
	}

	public void setFromStreet(String fromStreet) {
		this.fromStreet = fromStreet;
	}

	public String getToCountry() {
		return toCountry;
	}

	public void setToCountry(String toCountry) {
		this.toCountry = toCountry;
	}

	public String getToCity() {
		return toCity;
	}

	public void setToCity(String toCity) {
		this.toCity = toCity;
	}

	public String getToStreet() {
		return toStreet;
	}

	public void setToStreet(String toStreet) {
		this.toStreet = toStreet;
	}

	public Double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public String getDateAdding() {
		return dateAdding;
	}

	public void setDateAdding(String dateAdding) {
		this.dateAdding = dateAdding;
	}

	public String getDateOfDelivery() {
		return dateOfDelivery;
	}

	public void setDateOfDelivery(String dateOfDelivery) {
		this.dateOfDelivery = dateOfDelivery;
	}

	public String getActualPrice() {
		return actualPrice;
	}

	public void setActualPrice(String actualPrice) {
		this.actualPrice = actualPrice;
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

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdCompany() {
		return idCompany;
	}

	public void setIdCompany(int idCompany) {
		this.idCompany = idCompany;
	}

	public DetailsGoodModel(int id, String title, String content,
			String trailer, String fromCountry, String fromCity,
			String fromStreet, String toCountry, String toCity,
			String toStreet, Double maxPrice, String dateAdding,
			String dateOfDelivery, String actualPrice, String name,
			String surname, String company, int idCompany, Double weight, String deadlineAuction, String typeGood, String status) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.trailer = trailer;
		this.fromCountry = fromCountry;
		this.fromCity = fromCity;
		this.fromStreet = fromStreet;
		this.toCountry = toCountry;
		this.toCity = toCity;
		this.toStreet = toStreet;
		this.maxPrice = maxPrice;
		this.dateAdding = dateAdding;
		this.dateOfDelivery = dateOfDelivery;
		this.actualPrice = actualPrice;
		this.name = name;
		this.surname = surname;
		this.company = company;
		this.idCompany = idCompany;
		this.weight = weight;
		this.deadlineAuction = deadlineAuction;
		this.typeGood = typeGood;
		if(status.equals("1"))
			this.status = "Tak";
		else
			this.status = "Nie";
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getDeadlineAuction() {
		return deadlineAuction;
	}

	public void setDealineAuction(String deadlineAuction) {
		this.deadlineAuction = deadlineAuction;
	}

	public String getTypeGood() {
		return typeGood;
	}

	public void setTypeGood(String typeGood) {
		this.typeGood = typeGood;
	}

	public void setDeadlineAuction(String deadlineAuction) {
		this.deadlineAuction = deadlineAuction;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
