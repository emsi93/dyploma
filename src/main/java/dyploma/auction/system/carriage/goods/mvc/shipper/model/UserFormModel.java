package dyploma.auction.system.carriage.goods.mvc.shipper.model;

import java.io.Serializable;

public class UserFormModel implements Serializable{

	private static final long serialVersionUID = -8784538228054813813L;
	
	private String name;
	private String phoneNumber;

	public UserFormModel(String name, String phoneNumber) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
	
	public UserFormModel(){
		this(null, null);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
	
}
