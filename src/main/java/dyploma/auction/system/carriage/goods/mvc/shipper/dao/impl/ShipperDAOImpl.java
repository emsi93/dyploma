package dyploma.auction.system.carriage.goods.mvc.shipper.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

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
import dyploma.auction.system.carriage.goods.mvc.shipper.model.RegisterModel;

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
		jdbcTemplate
				.update("INSERT INTO companies (company_name,country,postcode,city,street,flat_number,nip_number,phone_number,email,type_of_company) VALUES (?,?,?,?,?,?,?,?,?,?)",
						registerModel.getCompanyName(),
						registerModel.getCountry(),
						registerModel.getPostcode(), registerModel.getCity(),
						registerModel.getStreet(),
						registerModel.getFlatNumber(),
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
		jdbcTemplate.update(
				"INSERT INTO logins(login,password,id_user) VALUES (?,?,?)",
				registerModel.getLogin(), registerModel.getPassword(), userID);
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

}
