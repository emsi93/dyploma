package dyploma.auction.system.carriage.goods.mvc.webapp.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import dyploma.auction.system.carriage.goods.mvc.webapp.dao.WebappDAOInterface;
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
import dyploma.auction.system.carriage.goods.mvc.webapp.model.GoodWithDate;
import dyploma.auction.system.carriage.goods.mvc.webapp.model.NoteAndComment;
import dyploma.auction.system.carriage.goods.mvc.webapp.model.PricesFromDB;
import dyploma.auction.system.carriage.goods.mvc.webapp.model.ProfileModel;
import dyploma.auction.system.carriage.goods.mvc.webapp.model.PurchaseOffer;
import dyploma.auction.system.carriage.goods.mvc.webapp.model.RegisterModel;
import dyploma.auction.system.carriage.goods.mvc.webapp.model.UserModel;

@Service("ShipperDAO")
@Scope("singleton")
public class WebappDAOImpl implements WebappDAOInterface {

	@Autowired()
	private ComboPooledDataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	@PostConstruct
	public void init() {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@PreDestroy
	public void cleanUp() {
		try {
			dataSource.close();
		} catch (Exception e) {
		}
	}

	public void registerCompany(RegisterModel registerModel, int typeOfCompany)
			throws DataAccessException {
		String role = null;
		if (typeOfCompany == 1)
			role = "ROLE_ADMIN_SHIPPER";
		else
			role = "ROLE_ADMIN_CARRIER";
		if (registerModel.getFlatNumber().equals(""))
			jdbcTemplate
					.update("INSERT INTO companies (company_name,country,postcode,city,street,nip_number,phone_number,email,type_of_company) VALUES (?,?,?,?,?,?,?,?,?)",
							registerModel.getCompanyName(),
							registerModel.getCountry(),
							registerModel.getPostcode(),
							registerModel.getCity(), registerModel.getStreet(),
							registerModel.getNipNumber(),
							registerModel.getPhoneNumber(),
							registerModel.getEmail(), typeOfCompany);
		else
			jdbcTemplate
					.update("INSERT INTO companies (company_name,country,postcode,city,street,flat_number,nip_number,phone_number,email,type_of_company) VALUES (?,?,?,?,?,?,?,?,?,?)",
							registerModel.getCompanyName(),
							registerModel.getCountry(),
							registerModel.getPostcode(),
							registerModel.getCity(), registerModel.getStreet(),
							Integer.parseInt(registerModel.getFlatNumber()),
							registerModel.getNipNumber(),
							registerModel.getPhoneNumber(),
							registerModel.getEmail(), typeOfCompany);
		int companyID = getCompanyID(registerModel.getEmail());
		jdbcTemplate
				.update("INSERT INTO users (name,surname, phone_number,email, id_company) VALUES (?,?,?,?,?)",
						registerModel.getName(), registerModel.getSurname(),
						registerModel.getPhoneNumberUser(),
						registerModel.getEmailUser(), companyID);
		int userID = getUserID(registerModel.getEmailUser());
		jdbcTemplate
				.update("INSERT INTO logins(login,password,id_user, role) VALUES (?,?,?,?)",
						registerModel.getLogin(), registerModel.getPassword(),
						userID, role);
	}

	public int checkUniqueEmailUser(String emailUser) {
		Object[] parameter = { emailUser };
		return jdbcTemplate.queryForObject(
				"SELECT COUNT(*) FROM users WHERE email=?", parameter,
				new RowMapper<Integer>() {

					public Integer mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						return new Integer(rs.getInt(1));
					}
				});
	}

	public int checkUniqueLogin(String login) throws DataAccessException {
		Object[] parameter = { login };
		return jdbcTemplate.queryForObject(
				"SELECT COUNT(*) FROM logins WHERE login=?", parameter,
				new RowMapper<Integer>() {

					public Integer mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						return new Integer(rs.getInt(1));
					}
				});
	}

	public int checkUniqueEmailCompany(String emailCompany)
			throws DataAccessException {
		Object[] parameter = { emailCompany };
		return jdbcTemplate.queryForObject(
				"SELECT COUNT(*) FROM companies WHERE email=?", parameter,
				new RowMapper<Integer>() {

					public Integer mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						return new Integer(rs.getInt(1));
					}
				});
	}

	public int checkUniqueNip(String nip) throws DataAccessException {
		Object[] parameter = { nip };
		return jdbcTemplate.queryForObject(
				"SELECT COUNT(*) FROM companies WHERE nip_number=?", parameter,
				new RowMapper<Integer>() {

					public Integer mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						return new Integer(rs.getInt(1));
					}
				});
	}

	public int getCompanyID(String email) throws DataAccessException {
		Object[] parameter = { email };
		return jdbcTemplate.queryForObject(
				"SELECT id FROM companies WHERE email=?", parameter,
				new RowMapper<Integer>() {

					public Integer mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						return new Integer(rs.getInt(1));
					}
				});
	}

	public int getUserID(String email) throws DataAccessException {
		Object[] parameter = { email };
		return jdbcTemplate.queryForObject(
				"SELECT id FROM users WHERE email=?", parameter,
				new RowMapper<Integer>() {

					public Integer mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						return new Integer(rs.getInt(1));
					}
				});
	}

	public void registerUser(UserModel userModel, int companyID, int typeCompany)
			throws DataAccessException {
		String role = null;
		if (typeCompany == 1)
			role = "ROLE_USER_SHIPPER";
		else
			role = "ROLE_USER_CARRIER";
		jdbcTemplate
				.update("INSERT INTO users (name,surname,phone_number,email,id_company) VALUES (?,?,?,?,?)",
						userModel.getName(), userModel.getSurname(),
						userModel.getPhoneNumberUser(),
						userModel.getEmailUser(), companyID);
		int userID = getUserID(userModel.getEmailUser());
		jdbcTemplate
				.update("INSERT INTO logins(login,password,id_user,role) VALUES (?,?,?,?)",
						userModel.getLogin(), userModel.getPassword(), userID,
						role);
	}

	public List<EmployeeModel> getEmployeesList(int companyID)
			throws DataAccessException {
		return jdbcTemplate
				.query("SELECT id,name,surname,phone_number,email FROM users WHERE id_company=?",
						new RowMapper<EmployeeModel>() {

							public EmployeeModel mapRow(ResultSet rs,
									int rowNumber) throws SQLException {
								return new EmployeeModel(rs.getInt(1), rs
										.getString(2), rs.getString(3), rs
										.getString(4), rs.getString(5));
							}
						}, new Object[] { companyID });
	}

	public ProfileModel getProfileUser(int userID) throws DataAccessException {
		return jdbcTemplate
				.queryForObject(
						"SELECT u.name,u.surname,u.phone_number,u.email, l.login, l.password  FROM users u INNER JOIN logins l on u.id = l.id_user WHERE u.id = ?",
						new RowMapper<ProfileModel>() {
							public ProfileModel mapRow(ResultSet rs,
									int rowNumber) throws SQLException {
								return new ProfileModel(rs.getString(1), rs
										.getString(2), rs.getString(3), rs
										.getString(4), rs.getString(5), rs
										.getString(6), "S");
							}
						}, new Object[] { userID });
	}

	public void editProfile(ProfileModel profileForm, int userID)
			throws DataAccessException {
		jdbcTemplate
				.update("UPDATE users SET name=?,surname=?,phone_number=?,email=? WHERE id = ?",
						profileForm.getName(), profileForm.getSurname(),
						profileForm.getPhoneNumber(), profileForm.getEmail(),
						userID);
		jdbcTemplate.update(
				"UPDATE logins SET login=?,password=? WHERE id_user = ?",
				profileForm.getLogin(), profileForm.getPassword(), userID);
	}

	public CompanyModel getCompanyModel(int userID) throws DataAccessException {
		return jdbcTemplate
				.queryForObject(
						"SELECT c.id, c.company_name, c.country, c.postcode, c.city, c.street, c.flat_number, c.nip_number, c.phone_number, c.website, c.email, c.description, c.note FROM companies c INNER JOIN users u on c.id = u.id_company WHERE u.id = ?",
						new RowMapper<CompanyModel>() {
							public CompanyModel mapRow(ResultSet rs,
									int rowNumber) throws SQLException {
								return new CompanyModel(rs.getInt(1), rs
										.getString(2), rs.getString(3), rs
										.getString(4), rs.getString(5), rs
										.getString(6), rs.getString(7), rs
										.getString(8), rs.getString(9), rs
										.getString(10), rs.getString(11), rs
										.getString(12), rs.getDouble(13));
							}
						}, new Object[] { userID });
	}

	public void editCompany(CompanyModel companyModel, int companyID)
			throws DataAccessException {
		if (companyModel.getFlatNumber().equals(""))
			jdbcTemplate
					.update("UPDATE  companies SET company_name=?, country=?, postcode=?, city=?, street=?,flat_number=?, nip_number=?, phone_number=?, website=?, email=?, description=? WHERE id = ?",
							companyModel.getCompanyName(),
							companyModel.getCountry(),
							companyModel.getPostcode(), companyModel.getCity(),
							companyModel.getStreet(), null,
							companyModel.getNipNumber(),
							companyModel.getPhoneNumber(),
							companyModel.getWebsite(), companyModel.getEmail(),
							companyModel.getDescription(), companyID);
		else
			jdbcTemplate
					.update("UPDATE  companies SET company_name=?, country=?, postcode=?, city=?, street=?, flat_number=?, nip_number=?, phone_number=?, website=?, email=?, description=? WHERE id = ?",
							companyModel.getCompanyName(),
							companyModel.getCountry(),
							companyModel.getPostcode(), companyModel.getCity(),
							companyModel.getStreet(),
							companyModel.getFlatNumber(),
							companyModel.getNipNumber(),
							companyModel.getPhoneNumber(),
							companyModel.getWebsite(), companyModel.getEmail(),
							companyModel.getDescription(), companyID);

	}

	public DetailsEmployeeModel getDetailEmployee(int id)
			throws DataAccessException {
		return jdbcTemplate
				.queryForObject(
						"SELECT u.id, u.name,u.surname,u.phone_number,u.email, l.login, l.enabled, l.role  FROM users u INNER JOIN logins l on u.id = l.id_user WHERE u.id = ?",
						new RowMapper<DetailsEmployeeModel>() {
							public DetailsEmployeeModel mapRow(ResultSet rs,
									int rowNumber) throws SQLException {
								return new DetailsEmployeeModel(rs.getInt(1),
										rs.getString(2), rs.getString(3), rs
												.getString(4), rs.getString(5),
										rs.getString(6), rs.getInt(7), rs
												.getString(8));
							}
						}, new Object[] { id });
	}

	public int getUserIDByLogin(String login) throws DataAccessException {
		Object[] parameter = { login };
		return jdbcTemplate
				.queryForObject(
						"SELECT u.id FROM users u INNER JOIN logins l ON l.id_user = u.id WHERE l.login = ?",
						parameter, new RowMapper<Integer>() {

							public Integer mapRow(ResultSet rs, int rowNumber)
									throws SQLException {
								return new Integer(rs.getInt(1));
							}
						});
	}

	public int getCompanyID(int userID) throws DataAccessException {
		Object[] parameter = { userID };
		return jdbcTemplate.queryForObject(
				"SELECT id_company FROM users WHERE id=?", parameter,
				new RowMapper<Integer>() {

					public Integer mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						return new Integer(rs.getInt(1));
					}
				});
	}

	public void editEmployee(DetailsEmployeeModel detailsEmployeeModel, int id)
			throws DataAccessException {
		jdbcTemplate
				.update("UPDATE users SET name=?,surname=?,phone_number=?,email=? WHERE id = ?",
						detailsEmployeeModel.getName(),
						detailsEmployeeModel.getSurname(),
						detailsEmployeeModel.getPhoneNumber(),
						detailsEmployeeModel.getEmail(), id);
		int enabled;
		if (detailsEmployeeModel.getActivity().equals("Tak"))
			enabled = 1;
		else
			enabled = 0;
		jdbcTemplate.update(
				"UPDATE logins SET login=?,enabled=?,role=? WHERE id_user = ?",
				detailsEmployeeModel.getLogin(), enabled,
				detailsEmployeeModel.getRole(), id);
	}

	public int getTypeOfCompany(int companyID) throws DataAccessException {
		Object[] parameter = { companyID };
		return jdbcTemplate.queryForObject(
				"SELECT type_of_company FROM companies WHERE id=?", parameter,
				new RowMapper<Integer>() {

					public Integer mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						return new Integer(rs.getInt(1));
					}
				});
	}

	public int getLoginID(String name) throws DataAccessException {
		Object[] parameter = { name };
		return jdbcTemplate.queryForObject(
				"SELECT id FROM logins WHERE login=?", parameter,
				new RowMapper<Integer>() {

					public Integer mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						return new Integer(rs.getInt(1));
					}
				});
	}

	public void insertGood(GoodModel goodModel, int loginID)
			throws DataAccessException {

		if (goodModel.getContent().equals(""))
			jdbcTemplate
					.update("INSERT INTO goods (title,trailer,from_country,from_city,from_street,to_country,to_city,to_street,max_price,date_adding,date_of_delivery,weight,deadline_auction,type_good,id_login) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
							goodModel.getTitle(), goodModel.getTrailer(),
							goodModel.getFromCountry(),
							goodModel.getFromCity(), goodModel.getFromStreet(),
							goodModel.getToCountry(), goodModel.getToCity(),
							goodModel.getToStreet(), goodModel.getMaxPrice(),
							getCurrentDate(), goodModel.getDateOfDelivery(),
							goodModel.getWeight(),
							goodModel.getDeadlineAuction(),
							goodModel.getTypeGood(), loginID);
		else
			jdbcTemplate
					.update("INSERT INTO goods (title,content,trailer,from_country,from_city,from_street,to_country,to_city,to_street,max_price,date_adding,date_of_delivery,weight,deadline_auction,type_good,id_login) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
							goodModel.getTitle(), goodModel.getContent(),
							goodModel.getTrailer(), goodModel.getFromCountry(),
							goodModel.getFromCity(), goodModel.getFromStreet(),
							goodModel.getToCountry(), goodModel.getToCity(),
							goodModel.getToStreet(), goodModel.getMaxPrice(),
							getCurrentDate(), goodModel.getDateOfDelivery(),
							goodModel.getWeight(),
							goodModel.getDeadlineAuction(),
							goodModel.getTypeGood(), loginID);

	}

	public String getCurrentDate() throws DataAccessException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public List<GoodModelForList> getGoodsList(int companyID)
			throws DataAccessException {
		return jdbcTemplate
				.query("SELECT g.id, g.title, g.from_country, g.from_city, g.to_country, g.to_city, g.date_adding, g.date_of_delivery, g.max_price, g.actual_price, g.status FROM goods g INNER JOIN logins l ON g.id_login = l.id INNER JOIN users u ON l.id_user = u.id INNER JOIN companies c ON c.id = u.id_company WHERE u.id_company = ?",
						new RowMapper<GoodModelForList>() {

							public GoodModelForList mapRow(ResultSet rs,
									int rowNumber) throws SQLException {
								return new GoodModelForList(rs.getInt(1), rs
										.getString(2), rs.getString(3), rs
										.getString(4), rs.getString(5), rs
										.getString(6), rs.getString(7), rs
										.getString(8), rs.getDouble(9), rs
										.getDouble(10), rs.getString(11));
							}
						}, new Object[] { companyID });
	}

	public List<GoodModelForList> getGoodsList() throws DataAccessException {
		return jdbcTemplate
				.query("SELECT g.id, g.title, g.from_country, g.from_city, g.to_country, g.to_city, g.date_adding, g.date_of_delivery, g.max_price, g.actual_price, g.status FROM goods g INNER JOIN logins l ON g.id_login = l.id INNER JOIN users u ON l.id_user = u.id INNER JOIN companies c ON c.id = u.id_company WHERE g.status = 1",
						new RowMapper<GoodModelForList>() {

							public GoodModelForList mapRow(ResultSet rs,
									int rowNumber) throws SQLException {
								return new GoodModelForList(rs.getInt(1), rs
										.getString(2), rs.getString(3), rs
										.getString(4), rs.getString(5), rs
										.getString(6), rs.getString(7), rs
										.getString(8), rs.getDouble(9), rs
										.getDouble(10), rs.getString(11));
							}
						}, new Object[] {});
	}

	public DetailsGoodModel getDetailsGood(int id) throws DataAccessException {
		return jdbcTemplate
				.queryForObject(
						"SELECT g.id, g.title, g.content, g.trailer, g.from_country, g.from_city, g.from_street, g.to_country, g.to_city, g.to_street, g.max_price, g.date_adding, g.date_of_delivery, g.actual_price, u.name, u.surname, c.company_name, c.id, g.weight, g.deadline_auction, g.type_good, g.status FROM goods g INNER JOIN logins l ON g.id_login = l.id INNER JOIN users u ON l.id_user = u.id INNER JOIN companies c ON c.id = u.id_company WHERE g.id = ?",
						new RowMapper<DetailsGoodModel>() {
							public DetailsGoodModel mapRow(ResultSet rs,
									int rowNumber) throws SQLException {
								return new DetailsGoodModel(rs.getInt(1), rs
										.getString(2), rs.getString(3), rs
										.getString(4), rs.getString(5), rs
										.getString(6), rs.getString(7), rs
										.getString(8), rs.getString(9), rs
										.getString(10), rs.getDouble(11), rs
										.getString(12), rs.getString(13), rs
										.getString(14), rs.getString(15), rs
										.getString(16), rs.getString(17), rs
										.getInt(18), rs.getDouble(19), rs
										.getString(20), rs.getString(21), rs
										.getString(22));
							}
						}, new Object[] { id });
	}

	public GoodModelForEdit getGoodModelForEdit(int id)
			throws DataAccessException {
		return jdbcTemplate
				.queryForObject(
						"SELECT g.id, g.title, g.content, g.trailer, g.from_country, g.from_city, g.from_street, g.to_country, g.to_city, g.to_street, g.max_price, g.date_of_delivery, g.status, g.weight, g.deadline_auction, g.type_good FROM goods g WHERE g.id = ?",
						new RowMapper<GoodModelForEdit>() {
							public GoodModelForEdit mapRow(ResultSet rs,
									int rowNumber) throws SQLException {
								return new GoodModelForEdit(rs.getInt(1), rs
										.getString(2), rs.getString(3), rs
										.getString(4), rs.getString(5), rs
										.getString(6), rs.getString(7), rs
										.getString(8), rs.getString(9), rs
										.getString(10), rs.getDouble(11), rs
										.getString(12), rs.getString(13), rs
										.getDouble(14), rs.getString(15), rs
										.getString(16));
							}
						}, new Object[] { id });
	}

	public void editCargo(GoodModelForEdit goodModelForEdit)
			throws DataAccessException {
		String status = null;
		if (goodModelForEdit.getStatus().equals("Tak"))
			status = "1";
		else
			status = "2";
		jdbcTemplate
				.update("UPDATE goods SET title=?, content=?, trailer=?, from_country=?, from_city=?, from_street=?, to_country=?, to_city=?, to_street=?, max_price=?, date_of_delivery=?, status=?, weight=?, deadline_auction=?, type_good=? WHERE id = ?",
						goodModelForEdit.getTitle(),
						goodModelForEdit.getContent(),
						goodModelForEdit.getTrailer(),
						goodModelForEdit.getFromCountry(),
						goodModelForEdit.getFromCity(),
						goodModelForEdit.getFromStreet(),
						goodModelForEdit.getToCountry(),
						goodModelForEdit.getToCity(),
						goodModelForEdit.getToStreet(),
						goodModelForEdit.getMaxPrice(),
						goodModelForEdit.getDateOfDelivery(), status,
						goodModelForEdit.getWeight(),
						goodModelForEdit.getDeadlineAuction(),
						goodModelForEdit.getTypeGood(),
						goodModelForEdit.getId());

	}

	public void updatePrice(int id, Double price, int userID)
			throws DataAccessException {
		// TODO Auto-generated method stub

		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String data = sdf.format(date).toString();
		jdbcTemplate.update("UPDATE goods SET actual_price=? WHERE id = ?",
				price, id);
		jdbcTemplate
				.update("INSERT INTO purchase_offers(id_login,id_good,price,data) VALUES (?,?,?,?)",
						userID, id, price, data);
	}

	public CompanyModel getInfoCompany(int idCompany)
			throws DataAccessException {
		return jdbcTemplate
				.queryForObject(
						"SELECT c.id, c.company_name, c.country, c.postcode, c.city, c.street, c.flat_number, c.nip_number, c.phone_number, c.website, c.email, c.description, c.note FROM companies c  WHERE c.id = ?",
						new RowMapper<CompanyModel>() {
							public CompanyModel mapRow(ResultSet rs,
									int rowNumber) throws SQLException {
								return new CompanyModel(rs.getInt(1), rs
										.getString(2), rs.getString(3), rs
										.getString(4), rs.getString(5), rs
										.getString(6), rs.getString(7), rs
										.getString(8), rs.getString(9), rs
										.getString(10), rs.getString(11), rs
										.getString(12), rs.getDouble(13));
							}
						}, new Object[] { idCompany });
	}

	public PricesFromDB getPricesFromDB(int id) throws DataAccessException {
		return jdbcTemplate.queryForObject(
				"SELECT max_price, actual_price FROM goods  WHERE id = ?",
				new RowMapper<PricesFromDB>() {
					public PricesFromDB mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						return new PricesFromDB(rs.getDouble(1), rs
								.getString(2));
					}
				}, new Object[] { id });
	}

	public List<GoodData> getGoodData(int companyID) throws DataAccessException {
		return jdbcTemplate
				.query("SELECT g.title, g.actual_price FROM goods g INNER JOIN logins l ON g.id_login = l.id INNER JOIN users u ON l.id_user = u.id INNER JOIN companies c ON c.id = u.id_company WHERE u.id_company = ? AND g.status = 1",
						new RowMapper<GoodData>() {

							public GoodData mapRow(ResultSet rs, int rowNumber)
									throws SQLException {
								return new GoodData(rs.getString(1), rs
										.getDouble(2));
							}
						}, new Object[] { companyID });
	}

	public List<PurchaseOffer> getPurchaseOffer(int id)
			throws DataAccessException {
		return jdbcTemplate
				.query("SELECT l.login, p.price, p.data FROM purchase_offers p INNER JOIN logins l on l.id = p.id_login WHERE p.id_good = ? ORDER BY p.id desc",
						new RowMapper<PurchaseOffer>() {

							public PurchaseOffer mapRow(ResultSet rs,
									int rowNumber) throws SQLException {
								return new PurchaseOffer(rs.getString(1), rs
										.getDouble(2), rs.getString(3));
							}
						}, new Object[] { id });
	}

	public void checkDateOfGoods() throws DataAccessException, ParseException {
		// TODO Auto-generated method stub
		List<GoodWithDate> listGoods = jdbcTemplate.query(
				"SELECT id, deadline_auction FROM goods WHERE status = 1",
				new RowMapper<GoodWithDate>() {

					public GoodWithDate mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						return new GoodWithDate(rs.getInt(1), rs.getString(2));
					}
				}, new Object[] {});

		for (int i = 0; i < listGoods.size(); i++) {
			String currentDate = jdbcTemplate.queryForObject(
					"SELECT CURDATE()", new RowMapper<String>() {
						public String mapRow(ResultSet rs, int rowNumber)
								throws SQLException {
							return new String(rs.getString(1));
						}
					}, new Object[] {});

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = sdf.parse(listGoods.get(i).getData());
			Date date2 = sdf.parse(currentDate);

			if (date1.compareTo(date2) < 0 || date1.compareTo(date2) == 0) {
				jdbcTemplate.update("UPDATE goods SET status = 0 WHERE id=?",
						listGoods.get(i).getId());

				Integer purchaseOfferId = jdbcTemplate.queryForObject(
						"SELECT max(id) FROM purchase_offers WHERE id_good=?",
						new RowMapper<Integer>() {
							public Integer mapRow(ResultSet rs, int rowNumber)
									throws SQLException {
								return new Integer(rs.getInt(1));
							}
						}, new Object[] { listGoods.get(i).getId() });
				jdbcTemplate
						.update("INSERT INTO finished_transactions(id_purchase_offer) VALUES(?)",
								purchaseOfferId);

			}

		}
	}

	public List<FinishedTransaction> getFinishedTransaction(int companyID,
			int typeCompany) throws DataAccessException {
		String sql = null;
		if (typeCompany == 2)
			sql = "SELECT ft.id, g.id , g.title, l.login, po.price, po.data, g.to_country, g.to_city FROM finished_transactions ft INNER JOIN purchase_offers po ON ft.id_purchase_offer = po.id INNER JOIN logins l ON l.id = po.id_login INNER JOIN users u ON u.id = l.id_user INNER JOIN companies c ON c.id = u.id_company INNER JOIN goods g ON g.id = po.id_good WHERE g.status = 0 AND u.id_company = ?";
		else
			sql = "SELECT ft.id, g.id , g.title, l.login, po.price, po.data, g.to_country, g.to_city FROM finished_transactions ft INNER JOIN purchase_offers po ON ft.id_purchase_offer = po.id INNER JOIN logins l ON l.id = po.id_login INNER JOIN users u ON u.id = l.id_user INNER JOIN goods g ON g.id = po.id_good INNER JOIN logins ll ON ll.id = g.id_login INNER JOIN users uu ON uu.id = g.id_login INNER JOIN companies c ON c.id = uu.id_company WHERE g.status = 0 AND uu.id_company = ?";
		return jdbcTemplate.query(sql, new RowMapper<FinishedTransaction>() {

			public FinishedTransaction mapRow(ResultSet rs, int rowNumber)
					throws SQLException {
				return new FinishedTransaction(rs.getInt(1), rs.getInt(2), rs
						.getString(3), rs.getString(4), rs.getDouble(5), rs
						.getString(6), rs.getString(7), rs.getString(8));
			}
		}, new Object[] { companyID });
	}

	public PurchaseOffer getPurchaseOffer(int id, int type)
			throws DataAccessException {
		return jdbcTemplate
				.queryForObject(
						"SELECT p.id, l.login, p.price, p.data FROM purchase_offers p INNER JOIN logins l on l.id = p.id_login WHERE p.id_good = ? AND p.id = (SELECT max(id) FROM purchase_offers)",
						new RowMapper<PurchaseOffer>() {

							public PurchaseOffer mapRow(ResultSet rs,
									int rowNumber) throws SQLException {
								return new PurchaseOffer(rs.getString(2), rs
										.getDouble(3), rs.getString(4));
							}
						}, new Object[] { id });
	}

	public List<String> getTrailers() throws DataAccessException {
		// TODO Auto-generated method stub
		return jdbcTemplate.query(
				"SELECT type_of_trailer FROM types_of_trailers",
				new RowMapper<String>() {

					public String mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						return new String(rs.getString(1));
					}
				}, new Object[] {});
	}

	public List<String> getCountries() throws DataAccessException {
		return jdbcTemplate.query("SELECT name FROM countries",
				new RowMapper<String>() {

					public String mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						return new String(rs.getString(1));
					}
				}, new Object[] {});
	}

	public List<String> getTypesOfGoods() throws DataAccessException {
		return jdbcTemplate.query("SELECT type_of_good FROM types_of_goods",
				new RowMapper<String>() {

					public String mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						return new String(rs.getString(1));
					}
				}, new Object[] {});
	}

	public int getCompanyID(int goodID, int typeOfCompany)
			throws DataAccessException {
		Object[] parameter = { goodID };
		String sql = null;
		if (typeOfCompany == 2) {
			sql = "SELECT c.id FROM goods g INNER JOIN logins l ON l.id = g.id_login INNER JOIN users u ON u.id = l.id_user INNER JOIN companies c ON c.id = u.id_company WHERE g.id = ?";
		} else {
			sql = "SELECT z.companyid FROM (SELECT MAX(p.id), c.id companyid FROM purchase_offers p INNER JOIN logins l ON l.id = p.id_login INNER JOIN users u ON l.id_user = u.id  INNER JOIN companies c ON c.id = u.id_company WHERE p.id_good = ?) AS z;";
		}
		return jdbcTemplate.queryForObject(sql, parameter,
				new RowMapper<Integer>() {

					public Integer mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						return new Integer(rs.getInt(1));
					}
				});
	}

	public String getCompanyName(int companyID) throws DataAccessException {
		Object[] parameter = { companyID };
		return jdbcTemplate.queryForObject(
				"SELECT  company_name FROM companies where id = ?", parameter,
				new RowMapper<String>() {

					public String mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						return new String(rs.getString(1));
					}
				});
	}

	public void insertNoteComment(int companyID2, int userID, int goodID,
			NoteAndComment noteAndComment) throws DataAccessException {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String data = sdf.format(date).toString();
		jdbcTemplate
				.update("INSERT INTO notes_comments(id_login,id_good,id_company,comment,data,note) VALUES(?,?,?,?,?,?)",
						userID, goodID, companyID2,
						noteAndComment.getComment(), data,
						noteAndComment.getNote());

		Integer x = jdbcTemplate.queryForObject(
				"SELECT COUNT(*) FROM notes_comments WHERE id_company = ?",
				new RowMapper<Integer>() {
					public Integer mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						return new Integer(rs.getInt(1));
					}
				}, new Object[] { companyID2 });

		List<Integer> notes = jdbcTemplate.query(
				"SELECT note FROM notes_comments WHERE id_company = ?",
				new RowMapper<Integer>() {

					public Integer mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						return new Integer(rs.getInt(1));
					}
				}, new Object[] { companyID2 });

		int suma = 0;
		for (int i = 0; i < notes.size(); i++)
			suma = suma + notes.get(i);

		float value = (float) suma / x;
		jdbcTemplate.update("UPDATE companies SET note=? WHERE id = ?", value,
				companyID2);

	}

	public List<CommentWithNote> getCommentsWithNotes(int companyID)
			throws DataAccessException {
		return jdbcTemplate
				.query("SELECT l.login, g.id, g.title, n.comment, n.data, n.note FROM notes_comments n INNER JOIN logins l ON l.id = n.id_login INNER JOIN goods g ON g.id = n.id_good WHERE n.id_company = ?",
						new RowMapper<CommentWithNote>() {

							public CommentWithNote mapRow(ResultSet rs,
									int rowNumber) throws SQLException {
								return new CommentWithNote(rs.getString(1), rs
										.getInt(2), rs.getString(3), rs
										.getString(4), rs.getString(5), rs
										.getInt(6));
							}
						}, new Object[] { companyID });
	}

	public List<GoodModelForSearch> getGoodsListForSearch()
			throws DataAccessException {
		return jdbcTemplate
				.query("SELECT g.id, g.title, g.from_country, g.from_city, g.to_country, g.to_city, g.weight, g.trailer, g.type_good, g.max_price, g.actual_price, g.deadline_auction FROM goods g INNER JOIN logins l ON g.id_login = l.id INNER JOIN users u ON l.id_user = u.id INNER JOIN companies c ON c.id = u.id_company WHERE g.status = 1",
						new RowMapper<GoodModelForSearch>() {

							public GoodModelForSearch mapRow(ResultSet rs,
									int rowNumber) throws SQLException {
								return new GoodModelForSearch(rs.getInt(1), rs
										.getString(2), rs.getString(3), rs
										.getString(4), rs.getString(5), rs
										.getString(6),rs.getString(7), rs.getString(8), rs.getString(9) ,rs.getDouble(10), rs.getDouble(11), rs.getString("deadline_auction") );
							}
						}, new Object[] {});
	}

	public List<GoodModelForList> getGoodsListForUser(int userID)
			throws DataAccessException {
		return jdbcTemplate
				.query("SELECT g.id, g.title, g.from_country, g.from_city, g.to_country, g.to_city, g.date_adding, g.date_of_delivery, g.max_price, g.actual_price, g.status FROM goods g INNER JOIN logins l ON g.id_login = l.id INNER JOIN users u ON l.id_user = u.id INNER JOIN companies c ON c.id = u.id_company WHERE l.id_user = ?",
						new RowMapper<GoodModelForList>() {

							public GoodModelForList mapRow(ResultSet rs,
									int rowNumber) throws SQLException {
								return new GoodModelForList(rs.getInt(1), rs
										.getString(2), rs.getString(3), rs
										.getString(4), rs.getString(5), rs
										.getString(6), rs.getString(7), rs
										.getString(8), rs.getDouble(9), rs
										.getDouble(10), rs.getString(11));
							}
						}, new Object[] { userID });
	}

	public List<FinishedTransaction> getFinishedTransactionForUser(int userID,
			int typeOfCompany) throws DataAccessException {
		String sql = null;
		if (typeOfCompany == 2)
			sql = "SELECT ft.id, g.id , g.title, l.login, po.price, po.data, g.to_country, g.to_city FROM finished_transactions ft INNER JOIN purchase_offers po ON ft.id_purchase_offer = po.id INNER JOIN logins l ON l.id = po.id_login INNER JOIN users u ON u.id = l.id_user INNER JOIN companies c ON c.id = u.id_company INNER JOIN goods g ON g.id = po.id_good WHERE g.status = 0 AND l.id_user = ?";
		else
			sql = "SELECT ft.id, g.id , g.title, l.login, po.price, po.data, g.to_country, g.to_city FROM finished_transactions ft INNER JOIN purchase_offers po ON ft.id_purchase_offer = po.id INNER JOIN logins l ON l.id = po.id_login INNER JOIN users u ON u.id = l.id_user INNER JOIN goods g ON g.id = po.id_good INNER JOIN logins ll ON ll.id = g.id_login INNER JOIN users uu ON uu.id = g.id_login INNER JOIN companies c ON c.id = uu.id_company WHERE g.status = 0 AND l.id_user = ?";
		return jdbcTemplate.query(sql, new RowMapper<FinishedTransaction>() {

			public FinishedTransaction mapRow(ResultSet rs, int rowNumber)
					throws SQLException {
				return new FinishedTransaction(rs.getInt(1), rs.getInt(2), rs
						.getString(3), rs.getString(4), rs.getDouble(5), rs
						.getString(6), rs.getString(7), rs.getString(8));
			}
		}, new Object[] { userID });
	}

}
