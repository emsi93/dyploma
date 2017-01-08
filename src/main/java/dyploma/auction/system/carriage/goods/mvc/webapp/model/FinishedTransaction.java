package dyploma.auction.system.carriage.goods.mvc.webapp.model;

public class FinishedTransaction {

	private int id;
	private int idGood;
	private String goodName;
	private String login;
	private Double price;
	private String dateTransaction;
	private String toCountry;
	private String toCity;
	public FinishedTransaction(int id, int idGood, String goodName,
			String login, Double price, String dateTransaction,
			String toCountry, String toCity) {
		super();
		this.id = id;
		this.idGood = idGood;
		this.goodName = goodName;
		this.login = login;
		this.price = price;
		this.dateTransaction = dateTransaction;
		this.toCountry = toCountry;
		this.toCity = toCity;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdGood() {
		return idGood;
	}
	public void setIdGood(int idGood) {
		this.idGood = idGood;
	}
	public String getGoodName() {
		return goodName;
	}
	public void setGoodName(String goodName) {
		this.goodName = goodName;
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
	public String getDateTransaction() {
		return dateTransaction;
	}
	public void setDateTransaction(String dateTransaction) {
		this.dateTransaction = dateTransaction;
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
	
	
}
