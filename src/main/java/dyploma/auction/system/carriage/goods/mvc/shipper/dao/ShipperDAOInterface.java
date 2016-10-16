package dyploma.auction.system.carriage.goods.mvc.shipper.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import dyploma.auction.system.carriage.goods.mvc.shipper.model.CompanyModel;
import dyploma.auction.system.carriage.goods.mvc.shipper.model.DetailsEmployeeModel;
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

	public int getCompanyID(int userID) throws DataAccessException;
	
	public int getUserID(String email) throws DataAccessException;
	
	public int getUserIDByLogin(String login) throws DataAccessException;

	public void registerUser(UserModel userModel, int companyID) throws DataAccessException;

	public List<EmployeeModel> getEmployeesList(int companyID)throws DataAccessException;
	
	public ProfileModel getProfileUser(int userID) throws DataAccessException;

	public void editProfile(ProfileModel profileForm, int userID)throws DataAccessException;
	
	public CompanyModel getCompanyModel(int userID) throws DataAccessException;

	public void editCompany(CompanyModel companyModel, int companyID)throws DataAccessException;

	public DetailsEmployeeModel getDetailEmployee(int id)throws DataAccessException;
	
	public void editEmployee(DetailsEmployeeModel detailsEmployeeModel, int id) throws DataAccessException;

}
