package dyploma.auction.system.carriage.goods.mvc.webapp.model;

public class NewPrice {

	private int id;
	private Double maxPrice;
	private String actualPrice;
	private Double price;
	public NewPrice(int id, Double maxPrice, String actualPrice, Double price) {
		super();
		this.id = id;
		this.maxPrice = maxPrice;
		this.actualPrice = actualPrice;
		this.price = price;
	}

	public NewPrice()
	{
		this(0,null,null,null);
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
