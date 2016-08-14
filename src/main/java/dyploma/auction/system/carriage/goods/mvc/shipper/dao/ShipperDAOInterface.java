package dyploma.auction.system.carriage.goods.mvc.shipper.dao;

import org.springframework.dao.DataAccessException;

import dyploma.auction.system.carriage.goods.mvc.shipper.model.ShipperUserFormModel;


public interface ShipperDAOInterface {

	public void insert(String company, ShipperUserFormModel userFormModel) throws DataAccessException;
	
}
