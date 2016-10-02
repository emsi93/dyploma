package dyploma.auction.system.carriage.goods.mvc.shipper.dao;

import org.springframework.dao.DataAccessException;

import dyploma.auction.system.carriage.goods.mvc.shipper.model.RegisterModel;

public interface ShipperDAOInterface {

	public void registerCompany(RegisterModel registerModel, int typeOfCompany)
			throws DataAccessException;

	public int checkUniqueEmailUser(String emailUser)
			throws DataAccessException;

	public int checkUniqueLogin(String login) throws DataAccessException;

	public int checkUniqueEmailCompany(String emailCompany)
			throws DataAccessException;

	public int checkUniqueNip(String nip) throws DataAccessException;

	public int getCompanyID(String email) throws DataAccessException;

	public int getUserID(String email) throws DataAccessException;
}
