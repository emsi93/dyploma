package dyploma.auction.system.carriage.goods.mvc.administration.model;

public class TypeOfBody {

	private int id;
	private String typeOfBody;
	public TypeOfBody(int id, String typeOfBody) {
		super();
		this.id = id;
		this.typeOfBody = typeOfBody;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTypeOfBody() {
		return typeOfBody;
	}
	public void setTypeOfBody(String typeOfBody) {
		this.typeOfBody = typeOfBody;
	}
	@Override
	public String toString() {
		return "TypeOfBody [id=" + id + ", typeOfBody=" + typeOfBody + "]";
	}
	
	
	
	
}
