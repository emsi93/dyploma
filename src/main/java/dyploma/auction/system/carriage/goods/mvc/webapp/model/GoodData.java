package dyploma.auction.system.carriage.goods.mvc.webapp.model;

public class GoodData {

	private String name;
	private Double actualPrice;
	public GoodData(String name, Double actualPrice) {
		super();
		this.name = name;
		this.actualPrice = actualPrice;
	}
	public GoodData()
	{
		this(null,null);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getActualPrice() {
		return actualPrice;
	}
	public void setActualPrice(Double actualPrice) {
		this.actualPrice = actualPrice;
	}
	@Override
	public String toString() {
		return "GoodData [name=" + name + ", actualPrice=" + actualPrice + "]";
	}
	
	
	
}
