package dyploma.auction.system.carriage.goods.mvc.shipper.dao;

import org.springframework.dao.DataAccessException;

import dyploma.auction.system.carriage.goods.mvc.shipper.model.ShipperUserFormModel;


public interface ShipperDAOInterface {

	public void insert(String company, ShipperUserFormModel userFormModel) throws DataAccessException;
	
	public int checkCompanyNipNumberUniqueValues(String nipNumber)throws DataAccessException;
	
	public int checkCompanyEmailUniqueValues(String email)throws DataAccessException;
	
	public int checkCompanyPhoneNumberUniqueValues(String phoneNumber) throws DataAccessException;
	
	public int checkUserLoginUniqueValues(String login) throws DataAccessException;
	
	public int checkUserPeselUniqueValues(String pesel) throws DataAccessException;
	
	public int checkUserEmailUniqueValues(String email) throws DataAccessException;
	
	public int checkUserPhoneNumberUniqueValues(String phoneNumber) throws DataAccessException;
	
}
