package dyploma.auction.system.carriage.goods.mvc.webapp.model;

public class GoodModelForSearch {

	private int id;
	private String title;
	private String fromCountry;
	private String fromCity;
	private String toCountry;
	private String toCity;
	private String weight;
	private String typeTrailer;
	private String typeGood;
	private Double maxPrice;
	private Double actualPrice;
	private String deadlineAuction;
	
	public GoodModelForSearch(int id, String title, String fromCountry,
			String fromCity, String toCountry, String toCity, String weight,
			String typeTrailer, String typeGood, Double maxPrice, Double actualPrice, String deadlineAuction) {
		super();
		this.id = id;
		this.title = title;
		this.fromCountry = fromCountry;
		this.fromCity = fromCity;
		this.toCountry = toCountry;
		this.toCity = toCity;
		this.weight = weight;
		this.typeTrailer = typeTrailer;
		this.typeGood = typeGood;
		this.maxPrice = maxPrice;
		this.actualPrice = actualPrice;
		this.deadlineAuction = deadlineAuction;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getTypeTrailer() {
		return typeTrailer;
	}
	public void setTypeTrailer(String typeTrailer) {
		this.typeTrailer = typeTrailer;
	}
	public String getTypeGood() {
		return typeGood;
	}
	public void setTypeGood(String typeGood) {
		this.typeGood = typeGood;
	}
	public Double getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}
	public Double getActualPrice() {
		return actualPrice;
	}
	public void setActualPrice(Double actualPrice) {
		this.actualPrice = actualPrice;
	}
	public String getDeadlineAuction() {
		return deadlineAuction;
	}
	public void setDeadlineAuction(String deadlineAuction) {
		this.deadlineAuction = deadlineAuction;
	}
	
	
	
	
}
