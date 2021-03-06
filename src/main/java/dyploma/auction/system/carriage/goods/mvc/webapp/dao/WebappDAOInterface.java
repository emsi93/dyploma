package dyploma.auction.system.carriage.goods.mvc.webapp.dao;

import java.text.ParseException;
import java.util.List;

import org.springframework.dao.DataAccessException;

import dyploma.auction.system.carriage.goods.mvc.webapp.model.CommentWithNote;
import dyploma.auction.system.carriage.goods.mvc.webapp.model.CompanyModel;
import dyploma.auction.system.carriage.goods.mvc.webapp.model.DetailsEmployeeModel;
import dyploma.auction.system.carriage.goods.mvc.webapp.model.DetailsGoodModel;
import dyploma.auction.system.carriage.goods.mvc.webapp.model.EmployeeModel;
import dyploma.auction.system.carriage.goods.mvc.webapp.model.FinishedTransaction;
import dyploma.auction.system.carriage.goods.mvc.webapp.model.GoodData;
import dyploma.auction.system.carriage.goods.mvc.webapp.model.GoodModel;
import dyploma.auction.system.carriage.goods.mvc.webapp.model.GoodModelForEdit;
import dyploma.auction.system.carriage.goods.mvc.webapp.model.GoodModelForList;
import dyploma.auction.system.carriage.goods.mvc.webapp.model.GoodModelForSearch;
import dyploma.auction.system.carriage.goods.mvc.webapp.model.NoteAndComment;
import dyploma.auction.system.carriage.goods.mvc.webapp.model.PricesFromDB;
import dyploma.auction.system.carriage.goods.mvc.webapp.model.ProfileModel;
import dyploma.auction.system.carriage.goods.mvc.webapp.model.PurchaseOffer;
import dyploma.auction.system.carriage.goods.mvc.webapp.model.RegisterModel;
import dyploma.auction.system.carriage.goods.mvc.webapp.model.UserModel;

public interface WebappDAOInterface {

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
	
	public int getCompanyID(int goodID, int typeOfCompany) throws DataAccessException;
	
	public int getUserID(String email) throws DataAccessException;
	
	public int getUserIDByLogin(String login) throws DataAccessException;

	public void registerUser(UserModel userModel, int companyID, int typeCompany) throws DataAccessException;

	public List<EmployeeModel> getEmployeesList(int companyID)throws DataAccessException;
	
	public ProfileModel getProfileUser(int userID) throws DataAccessException;

	public void editProfile(ProfileModel profileForm, int userID)throws DataAccessException;
	
	public CompanyModel getCompanyModel(int userID) throws DataAccessException;
	
	public CompanyModel getInfoCompany(int idCompany) throws DataAccessException;

	public void editCompany(CompanyModel companyModel, int companyID)throws DataAccessException;

	public DetailsEmployeeModel getDetailEmployee(int id)throws DataAccessException;
	
	public void editEmployee(DetailsEmployeeModel detailsEmployeeModel, int id) throws DataAccessException;

	public int getTypeOfCompany(int companyID) throws DataAccessException;

	public int getLoginID(String name)throws DataAccessException;

	public void insertGood(GoodModel goodModel, int loginID)throws DataAccessException;
	
	public String getCurrentDate()throws DataAccessException;

	public List<GoodModelForList> getGoodsList(int companyID)throws DataAccessException;
	
	public List<GoodModelForList> getGoodsList()throws DataAccessException;

	public DetailsGoodModel getDetailsGood(int id)throws DataAccessException;

	public GoodModelForEdit getGoodModelForEdit(int id)throws DataAccessException;

	public void editCargo(GoodModelForEdit goodModelForEdit)throws DataAccessException;

	public void updatePrice(int id, Double price, int userID)throws DataAccessException;

	public PricesFromDB getPricesFromDB(int id)throws DataAccessException;

	public List<GoodData> getGoodData(int companyID)throws DataAccessException;

	public List<PurchaseOffer> getPurchaseOffer(int id)throws DataAccessException;
	
	public PurchaseOffer getPurchaseOffer(int id, int type)throws DataAccessException;
	
	public void checkDateOfGoods() throws DataAccessException, ParseException;

	public List<FinishedTransaction> getFinishedTransaction(int companyID, int typeOfCompany) throws DataAccessException;
	
	public List<String> getTrailers() throws DataAccessException;

	public List<String> getCountries() throws DataAccessException;
	
	public List<String> getTypesOfGoods() throws DataAccessException;
	
	public String getCompanyName(int companyID) throws DataAccessException;

	public void insertNoteComment(int companyIDByGoodID, int userID, int goodID, NoteAndComment noteAndComment)throws DataAccessException;

	public List<CommentWithNote> getCommentsWithNotes(int companyID)throws DataAccessException;

	public List<GoodModelForSearch> getGoodsListForSearch()throws DataAccessException;

	public List<GoodModelForList> getGoodsListForUser(int userID)throws DataAccessException;

	public List<FinishedTransaction> getFinishedTransactionForUser(int userID,
			int typeOfCompany)throws DataAccessException;
}
