package dyploma.auction.system.carriage.goods.mvc.shipper.model;

public class GoodModelForList {

	private int id;
	private String title;
	private String fromCountry;
	private String fromCity;
	private String toCountry;
	private String toCity;
	private String dateAdding;
	private String dateOfDelivery;
	private Double maxPrice;
	private Double actualPrice;

	public GoodModelForList(int id, String title, String fromCountry,
			String fromCity, String toCountry, String toCity,
			String dateAdding, String dateOfDelivery, Double maxPrice,
			Double actualPrice) {
		super();
		this.id = id;
		this.title = title;
		this.fromCountry = fromCountry;
		this.fromCity = fromCity;
		this.toCountry = toCountry;
		this.toCity = toCity;
		this.dateAdding = dateAdding;
		this.dateOfDelivery = dateOfDelivery;
		this.maxPrice = maxPrice;
		this.actualPrice = actualPrice;
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

	@Override
	public String toString() {
		return "GoodModelForList [id=" + id + ", title=" + title
				+ ", fromCountry=" + fromCountry + ", fromCity=" + fromCity
				+ ", toCountry=" + toCountry + ", toCity=" + toCity
				+ ", dateAdding=" + dateAdding + ", dateOfDelivery="
				+ dateOfDelivery + ", maxPrice=" + maxPrice + ", actualPrice="
				+ actualPrice + "]";
	}
	
	
}