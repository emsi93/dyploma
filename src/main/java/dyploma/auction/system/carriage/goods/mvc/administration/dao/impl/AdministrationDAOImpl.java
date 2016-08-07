package dyploma.auction.system.carriage.goods.mvc.administration.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
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

import dyploma.auction.system.carriage.goods.mvc.administration.dao.AdministrationDAOInterface;
import dyploma.auction.system.carriage.goods.mvc.administration.model.TypeOfBody;

@Service("AdministrationDAO")
@Scope("singleton")
public class AdministrationDAOImpl implements AdministrationDAOInterface {

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

	public List<TypeOfBody> getTypeOfBodies() throws DataAccessException {
		return jdbcTemplate.query(
				"SELECT id,type_of_body FROM types_of_bodies",
				new RowMapper<TypeOfBody>() {

					public TypeOfBody mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						return new TypeOfBody(rs.getInt(1), rs.getString(2));

					}

				});
	}

}
