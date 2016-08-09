package dyploma.auction.system.carriage.goods.mvc.shipper.model;

import java.io.Serializable;

public class ShipperFormModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8784538228054813813L;
	
	private String companyName;

	public ShipperFormModel(String companyName) {
		super();
		this.companyName = companyName;
	}
	
	public ShipperFormModel()
	{
		this(null);
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	
}
