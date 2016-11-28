package dyploma.auction.system.carriage.goods.mvc.webapp.model;

public class PricesFromDB {

	private Double maxPrice;
	private String actualPrice;

	public PricesFromDB(Double maxPrice, String actualPrice) {
		super();
		this.maxPrice = maxPrice;
		this.actualPrice = actualPrice;
	}

	public Double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public String getActualPrice() {
		return actualPrice;
	}

	public void setActualPrice(String actualPrice) {
		this.actualPrice = actualPrice;
	}

}
