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
import dyploma.auction.system.carriage.goods.mvc.shipper.model.ShipperUserModel;
import dyploma.auction.system.carriage.goods.mvc.shipper.model.ShipperUserFormModel;

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

	public void insert(String company, ShipperUserFormModel userFormModel)
			throws DataAccessException {
		Object[] parts = company.split(",");
		for (int i = 0; i < parts.length; i++) {
			if (parts[i].equals(""))
				parts[i] = null;
		}
		jdbcTemplate
				.update("INSERT INTO companies (company_name,country, postcode, city, street, house_number, suice_number, nip_number, phone_number, website, email, description, type_of_company) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)",
						parts);
		Object[] parameters = { parts[0], parts[7] };
		int idCompany = jdbcTemplate.queryForObject(
				"SELECT id FROM companies WHERE company_name=? AND nip_number=?",
				parameters, new RowMapper<Integer>() {

					public Integer mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						return new Integer(rs.getInt(1));
					}
				});

		String shipperUserModel = new ShipperUserModel(userFormModel, 2,
				idCompany, "true").toString();
		Object[] parameters2 = shipperUserModel.split(",");
		for (int i = 0; i < parameters2.length; i++) {
			if (parameters2[i].equals(""))
				parameters2[i] = null;
		}
		jdbcTemplate
				.update("INSERT INTO users (name,surname,login,password,id_permission,country, postcode, city, street, house_number, flat_number, phone_number, email, pesel_number, id_company, status) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
						parameters2);
	}

}
