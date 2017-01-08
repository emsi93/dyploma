package dyploma.auction.system.carriage.goods.mvc.webapp.model;

public class PurchaseOffer {

	private String login;
	private Double price;
	private String data;
	public PurchaseOffer(String login, Double price, String data) {
		super();
		this.login = login;
		this.price = price;
		this.data = data;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	
}
