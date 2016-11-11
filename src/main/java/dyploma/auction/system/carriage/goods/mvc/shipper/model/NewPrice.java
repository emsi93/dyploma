package dyploma.auction.system.carriage.goods.mvc.shipper.model;

public class NewPrice {

	private Double maxPrice;
	private String actualPrice;
	private Double price;
	public NewPrice(Double maxPrice, String actualPrice, Double price) {
		super();
		this.maxPrice = maxPrice;
		this.actualPrice = actualPrice;
		this.price = price;
	}

	public NewPrice()
	{
		this(null,null,null);
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	
	
}
