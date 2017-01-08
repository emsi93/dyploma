package dyploma.auction.system.carriage.goods.mvc.webapp.model;

public class GoodWithDate {

	private int id;
	private String data;
	public GoodWithDate(int id, String data) {
		super();
		this.id = id;
		this.data = data;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	
}
