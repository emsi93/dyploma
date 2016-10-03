package dyploma.auction.system.carriage.goods.mvc.shipper.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import dyploma.auction.system.carriage.goods.mvc.shipper.model.EmployeeModel;
import dyploma.auction.system.carriage.goods.mvc.shipper.model.ProfileModel;
import dyploma.auction.system.carriage.goods.mvc.shipper.model.RegisterModel;
import dyploma.auction.system.carriage.goods.mvc.shipper.model.UserModel;

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

	public void registerUser(UserModel userModel, int tYPE_OF_COMPANY) throws DataAccessException;

	public List<EmployeeModel> getEmployeesList(int companyID)throws DataAccessException;
	
	public ProfileModel getProfileUser(int userID) throws DataAccessException;

	public void editProfile(ProfileModel profileForm, int userID)throws DataAccessException;
}
