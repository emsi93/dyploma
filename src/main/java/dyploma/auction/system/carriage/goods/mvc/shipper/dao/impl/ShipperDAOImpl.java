package dyploma.auction.system.carriage.goods.mvc.shipper.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
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

import dyploma.auction.system.carriage.goods.mvc.shipper.dao.ShipperDAOInterface;
import dyploma.auction.system.carriage.goods.mvc.shipper.model.CompanyModel;
import dyploma.auction.system.carriage.goods.mvc.shipper.model.DetailsEmployeeModel;
import dyploma.auction.system.carriage.goods.mvc.shipper.model.DetailsGoodModel;
import dyploma.auction.system.carriage.goods.mvc.shipper.model.EmployeeModel;
import dyploma.auction.system.carriage.goods.mvc.shipper.model.GoodModel;
import dyploma.auction.system.carriage.goods.mvc.shipper.model.GoodModelForEdit;
import dyploma.auction.system.carriage.goods.mvc.shipper.model.GoodModelForList;
import dyploma.auction.system.carriage.goods.mvc.shipper.model.ProfileModel;
import dyploma.auction.system.carriage.goods.mvc.shipper.model.RegisterModel;
import dyploma.auction.system.carriage.goods.mvc.shipper.model.UserModel;

@Service("ShipperDAO")
@Scope("singleton")
public class ShipperDAOImpl implements ShipperDAOInterface {

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
						userID, "ROLE_ADMIN");
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

	public void registerUser(UserModel userModel, int companyID)
			throws DataAccessException {
		jdbcTemplate
				.update("INSERT INTO users (name,surname,phone_number,email,id_company) VALUES (?,?,?,?,?)",
						userModel.getName(), userModel.getSurname(),
						userModel.getPhoneNumberUser(),
						userModel.getEmailUser(), companyID);
		int userID = getUserID(userModel.getEmailUser());
		jdbcTemplate
				.update("INSERT INTO logins(login,password,id_user,role) VALUES (?,?,?,?)",
						userModel.getLogin(), userModel.getPassword(), userID,
						"ROLE_USER");
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
						"SELECT c.id, c.company_name, c.country, c.postcode, c.city, c.street, c.flat_number, c.nip_number, c.phone_number, c.website, c.email, c.description FROM companies c INNER JOIN users u on c.id = u.id_company WHERE u.id = ?",
						new RowMapper<CompanyModel>() {
							public CompanyModel mapRow(ResultSet rs,
									int rowNumber) throws SQLException {
								return new CompanyModel(rs.getInt(1), rs
										.getString(2), rs.getString(3), rs
										.getString(4), rs.getString(5), rs
										.getString(6), rs.getString(7), rs
										.getString(8), rs.getString(9), rs
										.getString(10), rs.getString(11), rs
										.getString(12));
							}
						}, new Object[] { userID });
	}

	public void editCompany(CompanyModel companyModel, int companyID)
			throws DataAccessException {
		jdbcTemplate
				.update("UPDATE  companies SET company_name=?, country=?, postcode=?, city=?, street=?, flat_number=?, nip_number=?, phone_number=?, website=?, email=?, description=? WHERE id = ?",
						companyModel.getCompanyName(),
						companyModel.getCountry(), companyModel.getPostcode(),
						companyModel.getCity(), companyModel.getStreet(),
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
					.update("INSERT INTO goods (title,trailer,from_country,from_city,from_street,to_country,to_city,to_street,max_price,date_adding,date_of_delivery,id_login) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)",
							goodModel.getTitle(), goodModel.getTrailer(),
							goodModel.getFromCountry(),
							goodModel.getFromCity(), goodModel.getFromStreet(),
							goodModel.getToCountry(), goodModel.getToCity(),
							goodModel.getToStreet(), goodModel.getMaxPrice(),
							getCurrentDate(), goodModel.getDateOfDelivery(),
							loginID);
		else
			jdbcTemplate
					.update("INSERT INTO goods (title,content,trailer,from_country,from_city,from_street,to_country,to_city,to_street,max_price,date_adding,date_of_delivery,id_login) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)",
							goodModel.getTitle(), goodModel.getContent(),
							goodModel.getTrailer(), goodModel.getFromCountry(),
							goodModel.getFromCity(), goodModel.getFromStreet(),
							goodModel.getToCountry(), goodModel.getToCity(),
							goodModel.getToStreet(), goodModel.getMaxPrice(),
							getCurrentDate(), goodModel.getDateOfDelivery(),
							loginID);

	}

	public String getCurrentDate() throws DataAccessException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public List<GoodModelForList> getGoodsList(int companyID)
			throws DataAccessException {
		return jdbcTemplate
				.query("SELECT g.id, g.title, g.from_country, g.from_city, g.to_country, g.to_city, g.date_adding, g.date_of_delivery, g.max_price, g.actual_price FROM goods g INNER JOIN logins l ON g.id_login = l.id INNER JOIN users u ON l.id_user = u.id INNER JOIN companies c ON c.id = u.id_company WHERE u.id_company = ? AND g.status = 1",
						new RowMapper<GoodModelForList>() {

							public GoodModelForList mapRow(ResultSet rs,
									int rowNumber) throws SQLException {
								return new GoodModelForList(rs.getInt(1), rs
										.getString(2), rs.getString(3), rs
										.getString(4), rs.getString(5), rs
										.getString(6), rs.getString(7), rs
										.getString(8),rs.getDouble(9), rs.getDouble(10));
							}
						}, new Object[] { companyID });
	}

	public List<GoodModelForList> getGoodsList() throws DataAccessException {
		return jdbcTemplate
				.query("SELECT g.id, g.title, g.from_country, g.from_city, g.to_country, g.to_city, g.date_adding, g.date_of_delivery, g.max_price, g.actual_price FROM goods g INNER JOIN logins l ON g.id_login = l.id INNER JOIN users u ON l.id_user = u.id INNER JOIN companies c ON c.id = u.id_company WHERE g.status = 1",
						new RowMapper<GoodModelForList>() {

							public GoodModelForList mapRow(ResultSet rs,
									int rowNumber) throws SQLException {
								return new GoodModelForList(rs.getInt(1), rs
										.getString(2), rs.getString(3), rs
										.getString(4), rs.getString(5), rs
										.getString(6), rs.getString(7), rs
										.getString(8), rs.getDouble(9), rs.getDouble(10));
							}
						}, new Object[] {});
	}

	public DetailsGoodModel getDetailsGood(int id) throws DataAccessException {
		return jdbcTemplate
				.queryForObject(
						"SELECT g.id, g.title, g.content, g.trailer, g.from_country, g.from_city, g.from_street, g.to_country, g.to_city, g.to_street, g.max_price, g.date_adding, g.date_of_delivery, g.actual_price, u.name, u.surname, c.company_name, c.id FROM goods g INNER JOIN logins l ON g.id_login = l.id INNER JOIN users u ON l.id_user = u.id INNER JOIN companies c ON c.id = u.id_company WHERE g.id = ?",
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
										.getInt(18));
							}
						}, new Object[] { id });
	}

	public GoodModelForEdit getGoodModelForEdit(int id)
			throws DataAccessException {
		return jdbcTemplate
				.queryForObject(
						"SELECT g.id, g.title, g.content, g.trailer, g.from_country, g.from_city, g.from_street, g.to_country, g.to_city, g.to_street, g.max_price, g.date_of_delivery, g.status FROM goods g WHERE g.id = ?",
						new RowMapper<GoodModelForEdit>() {
							public GoodModelForEdit mapRow(ResultSet rs,
									int rowNumber) throws SQLException {
								return new GoodModelForEdit(rs.getInt(1), rs
										.getString(2), rs.getString(3), rs
										.getString(4), rs.getString(5), rs
										.getString(6), rs.getString(7), rs
										.getString(8), rs.getString(9), rs
										.getString(10), rs.getDouble(11), rs
										.getString(12), rs.getString(13));
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
				.update("UPDATE goods SET title=?, content=?, trailer=?, from_country=?, from_city=?, from_street=?, to_country=?, to_city=?, to_street=?, max_price=?, date_of_delivery=?, status=? WHERE id = ?",
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
						goodModelForEdit.getId());

	}

	public void updatePrice(int id, Double price) throws DataAccessException {
		// TODO Auto-generated method stub
		jdbcTemplate.update("UPDATE goods SET actual_price=? WHERE id = ?",
				price, id);
	}

	public CompanyModel getInfoCompany(int idCompany)
			throws DataAccessException {
		return jdbcTemplate
				.queryForObject(
						"SELECT c.id, c.company_name, c.country, c.postcode, c.city, c.street, c.flat_number, c.nip_number, c.phone_number, c.website, c.email, c.description FROM companies c  WHERE c.id = ?",
						new RowMapper<CompanyModel>() {
							public CompanyModel mapRow(ResultSet rs,
									int rowNumber) throws SQLException {
								return new CompanyModel(rs.getInt(1), rs
										.getString(2), rs.getString(3), rs
										.getString(4), rs.getString(5), rs
										.getString(6), rs.getString(7), rs
										.getString(8), rs.getString(9), rs
										.getString(10), rs.getString(11), rs
										.getString(12));
							}
						}, new Object[] { idCompany });
	}

}
